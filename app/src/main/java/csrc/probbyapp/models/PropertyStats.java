package csrc.probbyapp.models;

import java.util.List;

public class PropertyStats {

    // PropertyStats model

    public int totalProperties;
    public double totalRent;
    public double totalMortgage;
    public double income;
    public int available;



    public PropertyStats(int totalProperties, double totalRent, double totalMortgage,
                         double income, int available){
        this.totalProperties = totalProperties;
        this.totalRent = totalRent;
        this.totalMortgage = totalMortgage;
        this.income = income;
        this.available = available;

    }
    public double getNetIncome(){
        return income;
    }
    public double getTotalRent() {
        return totalRent;
    }
    public double getTotalMortgage() {
        return totalMortgage;
    }
    public int getAvailableCount() {
        return available;
    }
    public int getTotalCount() {
        return totalProperties;
    }

}
