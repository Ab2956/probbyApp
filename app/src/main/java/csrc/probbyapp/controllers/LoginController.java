package csrc.probbyapp.controllers;

import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;
import csrc.probbyapp.views.AuthView;
import csrc.probbyapp.database.UserDataHandler;

public class LoginController {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final AuthView view;
    private final UserDataHandler userDataHandler = new UserDataHandler();

    public LoginController(AuthView view){
        this.view = view;
    }

    public void createAccount(String name, String email,String pass, View v){
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(task ->{
                    if (task.isSuccessful()) {
                        assert firebaseAuth.getCurrentUser() != null;
                        String uid = firebaseAuth.getCurrentUser().getUid();

                        userDataHandler.addUser(uid, name, email);
                        view.showMessage("Successfully created account", v);
                        view.navigateTo();
                    }else {
                        view.showMessage(Objects.requireNonNull(task.getException()).getMessage(), v);
                    }
                });
    }

    public void signInUser(String email, String pass, View v){
        if(email.isEmpty() || pass.isEmpty()){
            view.showMessage("Please enter both email and password", v);
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //FirebaseUser user = firebaseAuth.getCurrentUser();
                        view.showMessage("Successfully logged in", v);
                        view.navigateTo();
                    }else {
                        view.showMessage(Objects.requireNonNull(task.getException()).getMessage(), v);
                    }
                });

    }

}
