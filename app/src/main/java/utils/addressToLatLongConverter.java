package utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import csrc.probbyapp.database.PropertyDataHandler;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class addressToLatLongConverter {
    private final PropertyDataHandler dataHandler;

    public addressToLatLongConverter(PropertyDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public LatLng convertAddressToLatLong(Context context, String propertyId) {
        Geocoder geocoder = new Geocoder(context);
        LatLng latLng = null;

        try {
            String fullAddress = dataHandler.getAddress(propertyId) +
                                 dataHandler.getCity(propertyId) +
                                 dataHandler.getPostcode(propertyId);
            List<Address> addressList = geocoder.getFromLocationName(fullAddress, 1);
            Address location = addressList.get(0);
            latLng = new LatLng(location.getLatitude(), location.getLongitude());

        }catch (IOException e){
            e.printStackTrace();
        }
        return latLng;

    }
}
