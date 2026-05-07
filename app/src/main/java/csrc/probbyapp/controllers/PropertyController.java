package csrc.probbyapp.controllers;

import java.util.List;

import csrc.probbyapp.models.PropertyStats;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.PropertyModel;

public class PropertyController {

    PropertyDataHandler dataHandler = new PropertyDataHandler();

    public PropertyController() {}

    public void addProperty(PropertyModel property, String userId) {
        try {
            dataHandler.addProperty(property, userId);

        } catch (Exception e){
            System.out.println("Error adding property: " + e.getMessage());
        }
    }
    public void getProperties(String userId, OnGetListener<List<PropertyModel>> listener, OnGetListener<PropertyStats> statsListener){
        try {
            dataHandler.getProperties(userId, listener,statsListener);

        } catch (Exception e){
            System.out.println("Error getting properties: " + e.getMessage());
        }
    }
    public void removeProperty(String userId, String propertyId) {
        try {
            dataHandler.removeProperty(userId, propertyId);
        } catch (Exception e){
            System.out.println("Error removing property: " + e.getMessage());
        }
    }
    public void getPropertyById(String userId, String propertyId, OnGetListener<PropertyModel> listener){
        dataHandler.getPropertyById(userId, propertyId, new OnGetListener<PropertyModel>() {

            @Override
            public void onSuccess(PropertyModel property) {
                listener.onSuccess(property);
            }

            @Override
            public void onFailure(java.lang.Exception e) {
                listener.onFailure(e);
            }
        });
    }

}
