package csrc.probbyapp.database;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

import csrc.probbyapp.models.UserModel;
import csrc.probbyapp.utils.OnGetListener;

public class UserDataHandler {

    // Data handler for the users from the database and a part of the data layer

    public FirebaseFirestore db;

    public UserDataHandler(){
        this.db = FirebaseFirestore.getInstance();
    }
    public UserDataHandler(FirebaseFirestore db){
        this.db = db;
    }

    // add user to the database
    public void addUser(String uid, String userName, String email){
        Map<String, Object> user = new HashMap<>();
        user.put("userName", userName);
        user.put("email", email);

        db.collection("users")
                .document(uid)
                .set(user);
        Log.e("User data added successfully", "User data added successfully");
    }

    // gets user from the database
    public void getUser(String uid, OnGetListener<UserModel> listener) {
        db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        UserModel user = documentSnapshot.toObject(UserModel.class);
                        if (user != null) {
                            user.setId(documentSnapshot.getId());
                            listener.onSuccess(user);
                        }
                    }
                    Log.e("User data retrieved successfully", "User data retrieved successfully");
                });
    }

    public void updateUserName(String uid, String userName, OnGetListener<String> listener){
        Map<String, Object> user = new HashMap<>();
        user.put("userName", userName);
        db.collection("users")
                .document(uid)
                .update(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("UserDataHandler", "User name updated successfully");
                    if (listener != null) listener.onSuccess("User name updated successfully");
                })
                .addOnFailureListener(e -> {
                    Log.e("UserDataHandler", "Error updating user name", e);
                    if (listener != null) listener.onFailure(e);
                });
    }
}
