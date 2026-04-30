package csrc.probbyapp.controllers;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.MapPropertyModel;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.utils.addressToLatLongConverter;
import csrc.probbyapp.models.PropertyModel;

public class MapController {

    PropertyController propertyController = new PropertyController();
    PropertyDataHandler propertyDataHandler = new PropertyDataHandler();
    addressToLatLongConverter addressConverter = new addressToLatLongConverter(propertyDataHandler);
    MapPropertyModel mapProperty;

    public void getAllPropertiesLatLong(Context context, String userId, OnGetListener<List<MapPropertyModel>> listener) {

        propertyController.getProperties(userId, new OnGetListener<List<PropertyModel>>() {
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
               listener.onSuccess(mapProperties);
            }
            @Override
            public void onFailure(Exception e) {
                System.out.println("Error getting properties: " + e.getMessage());
            }
        });
    };
}