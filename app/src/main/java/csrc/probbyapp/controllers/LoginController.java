package csrc.probbyapp.controllers;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.concurrent.Executor;
import csrc.probbyapp.views.AuthView;

public class LoginController {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final AuthView view;

    public LoginController(AuthView view){
        this.view = view;
    }

    public void createAccount(String email,String pass){
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(task ->{
                    if (task.isSuccessful()) {

                    }
                });
    }
    public void signInUser(String email, String pass){
        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        view.navigateTo();
                    }else {

                    }
                });

    }

}
