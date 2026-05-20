package csrc.probbyapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.FilterController;
import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.models.PropertyStats;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.PropertyAdapter;
import csrc.probbyapp.utils.UIHelper;

public class PropertiesFragment extends Fragment {

    // Fragment for the properties page

    List<PropertyModel> propertyList = new ArrayList<>();

    FirebaseAuth fA = FirebaseAuth.getInstance();
    String userId = fA.getCurrentUser().getUid();
    PropertyAdapter propertyAdapter;
    UIHelper uiHelper = new UIHelper();
    FilterController filterController = new FilterController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_properties, container, false);
        PropertyController propertyController = new PropertyController();

        Button btnAddProperty = view.findViewById(R.id.btnAddProperty);
        btnAddProperty.setOnClickListener(v -> {
           AddPropertiesFragment addPropertiesFragment = new AddPropertiesFragment();
           addPropertiesFragment.show(getParentFragmentManager(), "addPropertiesFragment");
        });
        uiHelper.applyTouchEffect(btnAddProperty);

        Button filterBtn = view.findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(getContext(), filterBtn);

            popupMenu.getMenu().add("All");
            popupMenu.getMenu().add("House");
            popupMenu.getMenu().add("Flat");
            popupMenu.getMenu().add("Bungalow");

            popupMenu.setOnMenuItemClickListener(item -> {
                String type = item.getTitle().toString();
                applyFilter(type);
                return true;
            });

           popupMenu.show();

        });
        uiHelper.applyTouchEffect(filterBtn);

        Button orderByBtn = view.findViewById(R.id.orderByBtn);
        orderByBtn.setOnClickListener(v -> {
                    PopupMenu popupMenu = new PopupMenu(getContext(), orderByBtn);
                    popupMenu.getMenu().add("Standard");
                    popupMenu.getMenu().add("Rent");
                    popupMenu.getMenu().add("Mortgage");

                    popupMenu.setOnMenuItemClickListener(item -> {
                        String type = item.getTitle().toString();
                        applyOrder(type);
                        return true;
                    });

                    popupMenu.show();

        });
        uiHelper.applyTouchEffect(orderByBtn);

        RecyclerView rv = view.findViewById(R.id.rvProperties);
        rv.setLayoutManager(new LinearLayoutManager(getContext())); // Check if this exists

        propertyAdapter = new PropertyAdapter(propertyList, property -> {
            PropertyDetailsFragment propertyDetailsFragment = new PropertyDetailsFragment();

            Bundle args = new Bundle();
            args.putString("propertyId", property.getId());
            propertyDetailsFragment.setArguments(args);

            propertyDetailsFragment.show(getParentFragmentManager(), "propertyDetailsFragment");
        },
                (property, position) -> {

                propertyController.removeProperty(userId, property.getId());
                Log.d("Property removed: ", property.getId());
        });




        rv.setAdapter(propertyAdapter);

        propertyController.getProperties(userId, new OnGetListener<List<PropertyModel>>() {
            @Override
            public void onSuccess(List<PropertyModel> properties) {
                if (properties != null) {
                    propertyList = properties;
                    propertyAdapter.updateList(properties);
                }
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Error getting properties: " + e.getMessage());
            }
        }, new OnGetListener<PropertyStats>() {
            @Override
            public void onSuccess(PropertyStats stats) {
                if (stats != null) {

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        return view;
    }

    private void applyFilter(String type){
        List<PropertyModel> filteredProperties = filterController.filterProperties(propertyList, type);
        if (propertyAdapter != null) {
            propertyAdapter.updateList(filteredProperties);
        }

    }

    private void applyOrder(String type){
            List<PropertyModel> sortedProperties = filterController.sortProperties(propertyList, type);
            if (propertyAdapter != null) {
                propertyAdapter.updateList(sortedProperties);
            }
        }
}