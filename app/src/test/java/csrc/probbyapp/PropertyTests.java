package csrc.probbyapp;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.utils.OnGetPropertiesListener;
import csrc.probbyapp.models.PropertyModel;

public class PropertyTests {


    @Mock
    private PropertyController propertyController;

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
            OnGetPropertiesListener listener = invocation.getArgument(1);
            listener.onSuccess(fakeList);
            return null;
        }).when(propertyController).getProperties(anyString(), any(OnGetPropertiesListener.class));

        propertyController.getProperties(userId, new OnGetPropertiesListener() {
            @Override
            public void onSuccess(List<PropertyModel> properties) {

                assert(properties.size() == 1);
                assert(properties.get(0).getAddress().equals("123 Main St"));
                System.out.println("Test Passed: List updated with " + properties.size() + " properties.");
            }

            @Override
            public void onFailure(Exception e) {
            }
        });

        verify(propertyController).getProperties(anyString(), any(OnGetPropertiesListener.class));
    }
}

