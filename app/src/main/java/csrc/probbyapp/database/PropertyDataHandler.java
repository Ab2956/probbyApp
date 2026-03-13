package csrc.probbyapp.database;

import com.google.firebase.firestore.FirebaseFirestore;

import csrc.probbyapp.models.PropertyModel;


public class PropertyDataHandler {

  FirebaseFirestore db = FirebaseFirestore.getInstance();

  public void addProperty(PropertyModel property) {}

  public void getProperties() {}
  public void getRent(){}
  public void getRooms(){}
  public void getMortgage(){}
  public void getPropertyType(){}
  public String getAddress(String propertyId){
    return null;
  }
  public String getCity(String propertyId){
    return null;
  }
  public String getPostcode(String propertyId){
    return null;
  }
  public void getStatus(){}
  public void getPropertyId(){}


}
