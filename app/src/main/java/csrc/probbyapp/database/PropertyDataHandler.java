package csrc.probbyapp.database;

import android.util.Log;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.OnGetListener;

public class PropertyDataHandler{

  FirebaseFirestore db ;
  private ListenerRegistration listener;

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

  public void getProperties(String userId, OnGetListener<List<PropertyModel>> listenerCallback) {
      if (listener != null) {
          listener.remove();
      }

      listener = db.collection("users")
              .document(userId)
              .collection("properties")
              .addSnapshotListener((value, error) -> {
                  if (error != null) {
                      listenerCallback.onFailure(error);
                      return;
                  }

                  if (value != null) {
                      List<PropertyModel> properties = new ArrayList<>();

                      for (QueryDocumentSnapshot doc : value) {
                          PropertyModel property = doc.toObject(PropertyModel.class);
                          property.setId(doc.getId());
                          properties.add(property);
                      }

                      listenerCallback.onSuccess(properties);
                  }
              });
  }
    public void getTotalRent(String userId, OnGetListener<Double> listener){
      getPropertiesRef(userId)
              .addSnapshotListener((value, error) -> {
                  if (error != null) {
                      listener.onFailure(error);
                      return;
                  }
                  if (value != null) {
                      double total = 0;
                      for (DocumentSnapshot doc: value ){
                          double rent = doc.getDouble("rent");
                          if (rent != 0) {
                              total += rent;
                          }
                      }
                      listener.onSuccess(total);
                  }
              });
    }
    public void getPropertyById(String userId,String propertyId, OnGetListener<PropertyModel> listener){
      getPropertiesRef(userId).document(propertyId).get()
              .addOnSuccessListener(documentSnapshot -> {
                  if (documentSnapshot.exists()) {
                      PropertyModel property = documentSnapshot.toObject(PropertyModel.class);
                      if (property != null) {
                          property.setId(documentSnapshot.getId());
                          listener.onSuccess(property);
                      }
                  }
              })
              .addOnFailureListener(listener::onFailure);
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
    public void getTotalPropertyCount(String userId, OnGetListener<Integer> listener){
        getPropertiesRef(userId)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        listener.onFailure(error);
                        return;
                    }
                    if (value != null) {
                        listener.onSuccess(value.size());
                    }
                });
    }
    public void getTotalAvailable(String userId, OnGetListener<Integer> listener){
        getPropertiesRef(userId)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        listener.onFailure(error);
                        return;
                    }
                    if (value != null) {
                        int total = 0;
                        for (DocumentSnapshot doc : value) {
                            String status = doc.getString("status");
                            if (status != null && status.equals("Available")) {
                                total++;
                            }
                        }
                        listener.onSuccess(total);

                    }
                });
    }
    public void getTotalMortgage(String userId, OnGetListener<Double> listener){
        getPropertiesRef(userId)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        listener.onFailure(error);
                        return;
                    }
                    if (value != null) {
                        double total = 0;
                        for (DocumentSnapshot doc : value) {
                            double mortgage = doc.getDouble("mortgage");
                            if (mortgage != 0) {
                                total += mortgage;
                            }
                        }
                        listener.onSuccess(total);
                    }
                });
    }
    public void getIncome(String userId, OnGetListener<Double> listener){
       getPropertiesRef(userId)
               .addSnapshotListener((value, error) -> {
                   if (error != null) {
                       listener.onFailure(error);
                       return;
                   }
                   if (value != null) {
                       double total = 0;
                       for (DocumentSnapshot doc : value) {
                           double rent = doc.getDouble("rent");
                           double mortgage = doc.getDouble("mortgage");
                           if (rent != 0 || mortgage != 0) {
                               total += rent - mortgage;
                           }
                       }
                       listener.onSuccess(total);
                   }
               });
    }
  public void getPropertyId(){}




}

