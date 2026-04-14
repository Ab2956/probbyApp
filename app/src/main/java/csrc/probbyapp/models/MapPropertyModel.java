package csrc.probbyapp.models;

import com.google.android.gms.maps.model.LatLng;

public class MapPropertyModel {

    private String propertyId;
    private LatLng latLng;

    public MapPropertyModel(String address, LatLng latLng) {
        this.propertyId = propertyId;
        this.latLng = latLng;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public LatLng getLatLng(){
        return latLng;
    }
}
