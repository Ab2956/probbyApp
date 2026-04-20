package csrc.probbyapp.controllers;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.MapPropertyModel;
import csrc.probbyapp.utils.OnMapReadyDataListener;
import csrc.probbyapp.utils.addressToLatLongConverter;
import csrc.probbyapp.utils.OnGetPropertiesListener;
import csrc.probbyapp.models.PropertyModel;

public class MapController {

    PropertyController propertyController = new PropertyController();
    PropertyDataHandler propertyDataHandler = new PropertyDataHandler();
    addressToLatLongConverter addressConverter = new addressToLatLongConverter(propertyDataHandler);
    MapPropertyModel mapProperty;

    public void getAllPropertiesLatLong(Context context, String userId, OnMapReadyDataListener listener) {

        propertyController.getProperties(userId, new OnGetPropertiesListener() {
            @Override
            public void onSuccess(List<PropertyModel> properties) {
                List<MapPropertyModel> mapProperties = new ArrayList<>();

                // O(n) because returning all the properties data to be changed into lat long for map
                for (PropertyModel property : properties) {

                    LatLng latLng = addressConverter.convertAddressToLatLong(context, property);

                    if (latLng != null) {
                        mapProperties.add(mapProperty = new MapPropertyModel(property.getId(), latLng, property.getAddress()));
                    }
                }
               listener.onDataReady(mapProperties);
            }
            @Override
            public void onFailure(Exception e) {
                System.out.println("Error getting properties: " + e.getMessage());
            }
        });
    };
}