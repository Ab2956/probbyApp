package csrc.probbyapp.controllers;

import csrc.probbyapp.database.OnGetPropertiesListener;
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
    public void getProperties(String userId, OnGetPropertiesListener listener){
        try {
            dataHandler.getProperties(userId, listener);
        } catch (Exception e){
            System.out.println("Error getting properties: " + e.getMessage());
        }
    }

}
