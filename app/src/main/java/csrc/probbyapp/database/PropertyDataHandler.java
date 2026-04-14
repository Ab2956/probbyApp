package csrc.probbyapp.database;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import csrc.probbyapp.models.PropertyModel;

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
              System.out.println("Property data added successfully");
            })
            .addOnFailureListener(e -> {
              System.out.println("Error adding property data: " + e.getMessage());
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
                    List<PropertyModel> properties = value.toObjects(PropertyModel.class);
                    listener.onSuccess(properties);
                }
            } );
  }

  public void getRent(){}
  public void getRooms(){}
  public void getMortgage(){}
  public void getPropertyType(){}
  public String getAddress(String userId, String propertyId){
      getPropertiesRef(userId)
              .document(propertyId)
              .get()
              .addOnSuccessListener(documentSnapshot -> {

                  if (documentSnapshot.exists()) {
                      DocumentSnapshot propertySnapshot = documentSnapshot;
                      String address = propertySnapshot.getString("address");
                      System.out.println("Address: " + address);
                  } else {
                      System.out.println("Document does not exist");
                  }
              })
              .addOnFailureListener(e -> {
                  System.out.println("Error getting address: " + e.getMessage());
              });

      return null;
  }

  public String getCity(String userId, String propertyId){
      getPropertiesRef(userId)
              .document(propertyId)
              .get()
              .addOnSuccessListener(documentSnapshot -> {

                  if (documentSnapshot.exists()) {
                      DocumentSnapshot propertySnapshot = documentSnapshot;
                      String city = propertySnapshot.getString("city");
                      System.out.println("City: " + city);
                  } else {
                      System.out.println("Document does not exist");
                  }
              })
              .addOnFailureListener(e -> {
                  System.out.println("Error getting city: " + e.getMessage());
              });
    return null;
  }

  public String getPostcode(String userId, String propertyId){
      getPropertiesRef(userId)
              .document(propertyId)
              .get()
              .addOnSuccessListener(documentSnapshot -> {

                  if (documentSnapshot.exists()) {
                      DocumentSnapshot propertySnapshot = documentSnapshot;
                      String postcode = propertySnapshot.getString("postcode");
                      System.out.println("Postcode: " + postcode);
                      } else {
                      System.out.println("Document does not exist");
                  }
              })
              .addOnFailureListener(e -> {
                  System.out.println("Error getting postcode: " + e.getMessage());
              });
    return null;
  }

  public void getStatus(String userId, String propertyId){
      getPropertiesRef(userId)
              .document(propertyId)
              .get()
              .addOnSuccessListener(documentSnapshot -> {
          if (documentSnapshot.exists()) {
              DocumentSnapshot propertySnapshot = documentSnapshot;
              String status = propertySnapshot.getString("status");
              System.out.println("Status: " + status);
          } else {
              System.out.println("Document does not exist");
          }
      }).addOnFailureListener(e -> {
          System.out.println("Error getting status: " + e.getMessage());
      });
    }

    public CollectionReference getPropertiesRef(String userId){
        db.collection("users")
                .document(userId)
                .collection("properties");
        return null;
    }

  public void getPropertyId(){}



}

