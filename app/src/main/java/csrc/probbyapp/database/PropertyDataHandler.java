package csrc.probbyapp.database;

import android.util.Log;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.OnGetPropertiesListener;

public class PropertyDataHandler{

  FirebaseFirestore db ;

  public PropertyDataHandler() {this.db = FirebaseFirestore.getInstance();}
  public PropertyDataHandler(FirebaseFirestore db) {this.db = db;}

  public void addProperty(PropertyModel property, String userId) {
    db.collection("users")
            .document(userId)
            .collection("properties")
            .add(property)
            .addOnSuccessListener(documentReference -> {
              Log.d("Property added: ", documentReference.getId());
            })
            .addOnFailureListener(e -> {
              Log.e("Error adding property data: " , e.getMessage());
            });
  }

  public void getProperties(String userId, OnGetPropertiesListener listener) {
    db.collection("users")
            .document(userId)
            .collection("properties")
            .addSnapshotListener( (value, error) ->{
                if (error != null) {
                    listener.onFailure(error);
                    return;
                }
                if (value != null) {
                    List<PropertyModel> properties = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : value) {
                        PropertyModel property = doc.toObject(PropertyModel.class);
                        property.setId(doc.getId());
                        properties.add(property);
                    }
                    listener.onSuccess(properties);
                }
            } );
  }

    public CollectionReference getPropertiesRef(String userId){
        return db.collection("users")
                .document(userId)
                .collection("properties");
    }

    public void removeProperty(String userId, String propertyId) {
        if (propertyId == null || propertyId.isEmpty()) {
            Log.e("PropertyDataHandler", "Cannot delete property: propertyId is null");
            return;
        }
        getPropertiesRef(userId)
                .document(propertyId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Log.d("Property removed: ", propertyId);
                    })
                .addOnFailureListener(e -> {
                    Log.d("Error removing property: " , e.getMessage());
                });
    }
  public void getPropertyId(){}




}

