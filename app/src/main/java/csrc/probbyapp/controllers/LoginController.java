package csrc.probbyapp.controllers;

import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;
import csrc.probbyapp.views.AuthView;
import csrc.probbyapp.database.UserDataHandler;

public class LoginController {

    // Login functionality - using Firebase Auth

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final AuthView view;
    private final UserDataHandler userDataHandler = new UserDataHandler();

    // Constructor for LoginController
    public LoginController(AuthView view){
        this.view = view;
    }

    // create account method - using Firebase Auth to create and get the current user
    public void createAccount(String userName, String email,String pass, View v){
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(task ->{
                    if (task.isSuccessful()) {
                        assert firebaseAuth.getCurrentUser() != null;
                        String uid = firebaseAuth.getCurrentUser().getUid();

                        userDataHandler.addUser(uid, userName, email);
                        view.showMessage("Successfully created account", v);
                        view.navigateTo();
                    }else {
                        view.showMessage(Objects.requireNonNull(task.getException()).getMessage(), v);
                    }
                });
    }

    // sign in method - using Firebase Auth to sign in the user
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
