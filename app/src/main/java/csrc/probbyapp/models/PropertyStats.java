package csrc.probbyapp.models;

import java.util.List;

public class PropertyStats {

    public int totalProperties;
    public double totalRent;
    public double totalMortgage;
    public double income;
    public int available;
    public List<PropertyModel> properties;

    public PropertyStats(int totalProperties, double totalRent, double totalMortgage,
                         double income, int available, List<PropertyModel> properties) {
        this.totalProperties = totalProperties;
        this.totalRent = totalRent;
        this.totalMortgage = totalMortgage;
        this.income = income;
        this.available = available;
        this.properties = properties;
    }
}
