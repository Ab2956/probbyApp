package csrc.probbyapp.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.PropertyModel;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class addressToLatLongConverter {
    private final PropertyDataHandler dataHandler;

    public addressToLatLongConverter(PropertyDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public LatLng convertAddressToLatLong(Context context, PropertyModel property) {
        Geocoder geocoder = new Geocoder(context);

        String fullAddress = property.getAddress() + ", " +
                property.getCity() + ", " +
                property.getPostcode();
        try {

            List<Address> addressList = geocoder.getFromLocationName(fullAddress, 1);

            if (addressList != null && !addressList.isEmpty()) {
                Address location = addressList.get(0);
                return new LatLng(location.getLatitude(), location.getLongitude());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
}
