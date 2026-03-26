package csrc.probbyapp.controllers;

import com.google.firebase.auth.FirebaseAuth;

public class UserController {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public void signOutUser(){
        firebaseAuth.signOut();
    }

    public void getUser(){

    }
}
