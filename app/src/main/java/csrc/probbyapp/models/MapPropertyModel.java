package csrc.probbyapp.models;

import com.google.android.gms.maps.model.LatLng;

public class MapPropertyModel {

    // Map model for the properties to be displayed on the map
    private String propertyId;
    private LatLng latLng;
    private String address;


    public MapPropertyModel(String propertyId, LatLng latLng, String address) {
        this.propertyId = propertyId;
        this.latLng = latLng;
        this.address = address;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public LatLng getLatLng(){
        return latLng;
    }
    public String getAddress(){
        return address;
    }
}
