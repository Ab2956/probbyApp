package csrc.probbyapp.controllers;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import csrc.probbyapp.database.UserDataHandler;
import csrc.probbyapp.models.UserModel;
import csrc.probbyapp.utils.OnGetListener;

public class UserController {

    // User functionality - using Firebase Auth to sign out the user
    UserDataHandler userDataHandler = new UserDataHandler();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public void signOutUser(){
        firebaseAuth.signOut();
    }

    public void getUser(String userId, OnGetListener<UserModel> listener){
        UserDataHandler userDataHandler = new UserDataHandler();
        userDataHandler.getUser(userId, listener);

    }

    public void changePassword(String newPassword, String currentPassword, OnGetListener<String> listener) {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null && user.getEmail() != null) {
            // Step 1: Create credentials using the user's email and the CURRENT password entered in UI
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);

            // Step 2: Re-authenticate the user
            user.reauthenticate(credential).addOnCompleteListener(reAuthTask -> {
                if (reAuthTask.isSuccessful()) {
                    // Step 3: Now it is safe to update the password
                    user.updatePassword(newPassword).addOnCompleteListener(updateTask -> {
                        if (updateTask.isSuccessful()) {
                            listener.onSuccess("Password updated successfully");
                        } else {
                            listener.onFailure(updateTask.getException());
                        }
                    });
                } else {
                    listener.onFailure(reAuthTask.getException());
                }
            });
        }
    }

    public void changeUserName(String newUserName, OnGetListener<String> listener){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            userDataHandler.updateUserName(uid, newUserName, listener);
        } else if (listener != null) {
            listener.onFailure(new Exception("User not logged in"));
        }
    }
}
