package csrc.probbyapp.controllers;

import java.util.List;

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
    public void getProperties(String userId, OnGetListener<List<PropertyModel>> listener){
        try {
            dataHandler.getProperties(userId, listener);
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
    public void getTotalRent(String userId, OnGetListener<Double> listener){
        dataHandler.getTotalRent(userId,  new OnGetListener<Double>() {
            @Override
            public void onSuccess(Double data) {
                listener.onSuccess(data);;
            }
            @Override
            public void onFailure(Exception e) {
                listener.onFailure(e);
            }
        });

    }
    public void getTotalPropertyCount(String userId, OnGetListener<Integer> listener){
        dataHandler.getTotalPropertyCount(userId, new OnGetListener<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                listener.onSuccess(data);
            }
            @Override
            public void onFailure(Exception e) {
                listener.onFailure(e);
            }
            });
    }

    public void getTotalAvailable(String userId, OnGetListener<Integer> listener){
        dataHandler.getTotalAvailable(userId, new OnGetListener<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                listener.onSuccess(data);
            }
            @Override
            public void onFailure(Exception e) {
                listener.onFailure(e);
            }
        });
    }
    public void getTotalMortgage(String userId, OnGetListener<Double> listener){
        dataHandler.getTotalMortgage(userId, new OnGetListener<Double>() {
            @Override
            public void onSuccess(Double data) {
                listener.onSuccess(data);
            }
            @Override
            public void onFailure(Exception e) {
                listener.onFailure(e);
            }
        });
    }
    public void getIncome(String userId, OnGetListener<Double> listener){
        dataHandler.getIncome(userId, new OnGetListener<Double>() {
            @Override
            public void onSuccess(Double data) {
                listener.onSuccess(data);
            }
            @Override
            public void onFailure(Exception e) {
                listener.onFailure(e);
            }
        });
    }
}
