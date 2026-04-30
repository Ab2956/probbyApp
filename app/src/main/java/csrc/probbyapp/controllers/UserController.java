package csrc.probbyapp.controllers;

import com.google.firebase.auth.FirebaseAuth;

import csrc.probbyapp.database.UserDataHandler;
import csrc.probbyapp.models.UserModel;
import csrc.probbyapp.utils.OnGetListener;

public class UserController {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public void signOutUser(){
        firebaseAuth.signOut();
    }

    public void getUser(String userId, OnGetListener<UserModel> listener){
        UserDataHandler userDataHandler = new UserDataHandler();
        userDataHandler.getUser(userId, listener);

    }
}
