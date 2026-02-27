package csrc.probbyapp.services;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.PropertyModel;


public class PropertyServices {
    private PropertyDataHandler dataHandler;

    public PropertyServices(PropertyDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public void addProperty(PropertyModel property) {
        dataHandler.addProperty(property);
    }



}
