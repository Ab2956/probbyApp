package csrc.probbyapp.controllers;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.MapPropertyModel;
import csrc.probbyapp.utils.addressToLatLongConverter;
import csrc.probbyapp.database.OnGetPropertiesListener;
import csrc.probbyapp.models.PropertyModel;

public class MapController {

    PropertyController propertyController = new PropertyController();
    PropertyDataHandler propertyDataHandler = new PropertyDataHandler();
    addressToLatLongConverter addressConverter = new addressToLatLongConverter(propertyDataHandler);
    MapPropertyModel mapProperty;

    public List<MapPropertyModel> getAllPropertiesLatLong(Context context, String userId, List<PropertyModel> properties) {
        List<MapPropertyModel> mapProperties = new ArrayList<>();

        propertyController.getProperties(userId, new OnGetPropertiesListener() {
            @Override
            public List<PropertyModel> onSuccess(List<PropertyModel> properties) {
                return properties;
            }
            @Override
            public void onFailure(Exception e) {
                System.out.println("Error getting properties: " + e.getMessage());
            }
        });

        // O(n) because returning all the properties data to be changed into lat long for map
       for (PropertyModel property : properties) {
           LatLng latLng = addressConverter.convertAddressToLatLong(context, userId, property.getId());
           mapProperties.add(mapProperty = new MapPropertyModel(property.getId(), latLng));
       }

       return mapProperties;
    };
}