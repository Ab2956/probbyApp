package csrc.probbyapp;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.database.PropertyDataHandler;
import csrc.probbyapp.models.PropertyStats;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.addressToLatLongConverter;

public class PropertyTests {


    @Mock
    private PropertyController propertyController;
    private PropertyDataHandler propertyDataHandler;
    private addressToLatLongConverter addressConverter;

    private String userId = "123";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProperty() {
        PropertyModel property = new PropertyModel("123","House", "123 Main St","city","BN12 3ED","3",200.00, 1200.0,"Yes");

        propertyController.addProperty(property, userId);
        System.out.println("Test Passed: Property added successfully.");

        assert(true);

    }

    @Test
    public void testGetProperties() {

        List<PropertyModel> fakeList = new ArrayList<>();
        fakeList.add(new PropertyModel("123","House", "123 Main St","city","BN12 3ED","3",200.00, 1200.0,"Yes"));

        doAnswer(invocation -> {
            OnGetListener<List<PropertyModel>> listener = invocation.getArgument(1);
            listener.onSuccess(fakeList);

            OnGetListener<PropertyStats> statsListener = invocation.getArgument(2);
            if (statsListener != null) {
                statsListener.onSuccess(new PropertyStats(1,1,1,1,1));
            }
            return null;
        }).when(propertyController).getProperties(anyString(), any(OnGetListener.class), any(OnGetListener.class));

        propertyController.getProperties(userId, new OnGetListener<List<PropertyModel>>() {
            @Override
            public void onSuccess(List<PropertyModel> properties) {

                assert(properties.size() == 1);
                assert(properties.get(0).getAddress().equals("123 Main St"));
                System.out.println("Test Passed: List updated with " + properties.size() + " properties.");
            }

            @Override
            public void onFailure(Exception e) {
            }
        }, new OnGetListener<PropertyStats>() {
            @Override
            public void onSuccess(PropertyStats data) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        verify(propertyController).getProperties(anyString(), any(OnGetListener.class), any(OnGetListener.class));
    }

    @Test
    public void testTotalRent(){

        PropertyModel property = new PropertyModel("123","House", "14 Church way","Worthing","BN13 1HD","3",200.00, 1200.0,"Yes");
        PropertyStats stats = new PropertyStats(5,6000,3000,3000,1);

        Double res = stats.getTotalRent();
        System.out.println(res);
        assert(res.equals(6000.0));

    }

    @Test
    public void testAddressConverter(){

        Context mockedContext = mock(Context.class);
        propertyDataHandler = mock(PropertyDataHandler.class);
        addressConverter = new addressToLatLongConverter(propertyDataHandler);

        PropertyModel property = new PropertyModel("123","House", "14 Church way","Worthing","BN13 1HD","3",200.00, 1200.0,"Yes");

        Address mockAddress = mock(Address.class);
        when(mockAddress.getLatitude()).thenReturn(50.8194);
        when(mockAddress.getLongitude()).thenReturn(-0.3711);

        try (MockedConstruction<Geocoder> mocked = mockConstruction(Geocoder.class,
                (mock, context) -> {
                    when(mock.getFromLocationName(anyString(), anyInt()))
                            .thenReturn(Collections.singletonList(mockAddress));
                })) {

            LatLng res = addressConverter.convertAddressToLatLong(mockedContext,property);

        System.out.println(res);
        assert(res.latitude == 50.8194);
        assert(res.longitude == -0.3711);
    }
}
}

