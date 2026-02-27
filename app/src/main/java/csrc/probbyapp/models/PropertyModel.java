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
    private int rentAmount;
    private int mortgageAmount;

}
