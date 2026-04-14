package csrc.probbyapp.database;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class UserDataHandler {

    public FirebaseFirestore db;

    public UserDataHandler(){
        this.db = FirebaseFirestore.getInstance();
    }
    public UserDataHandler(FirebaseFirestore db){
        this.db = db;
    }

    public void addUser(String uid, String name, String email){
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);

        db.collection("users")
                .document(uid)
                .set(user);
        System.out.println("User data added successfully");
    }
    public void getUser(String uid) {
        db.collection("users")
                .document(uid)
                .get();
        System.out.println("User data retrieved successfully");
    }
}
