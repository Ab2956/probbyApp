package csrc.probbyapp.models;

import com.google.gson.annotations.SerializedName;

public class PropertyModel {

    @SerializedName("_id")
    private String id;
    private String address;
    private String city;
    private String postcode;
    private String rooms;
    private String status;
    private String propertyType;
    private int rent;
    private int mortgage;

    public  PropertyModel(){}
    public  PropertyModel(String id, String propertyType, String address, String city,
                          String postcode, String rooms, double rent,
                          double mortgage, String status){
        this.id = id;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.rooms = rooms;
        this.status = status;
        this.propertyType = propertyType;
        this.rent = (int) rent;
        this.mortgage = (int) mortgage;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getRooms() {
        return rooms;
    }
    public void setRooms(String rooms) {
        this.rooms = rooms;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public int getRent() {
        return rent;
    }
    public void setRent(int rent) {
        this.rent = rent;
    }
    public int getMortgage() {
        return mortgage;
    }
    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }



}
