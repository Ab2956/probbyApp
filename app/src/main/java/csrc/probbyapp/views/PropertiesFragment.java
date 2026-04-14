package csrc.probbyapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.database.OnGetPropertiesListener;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.PropertyAdapter;

public class PropertiesFragment extends Fragment {
    List<PropertyModel> propertyList = new ArrayList<>();

    PropertyController propertyController = new PropertyController();
    FirebaseAuth fA = FirebaseAuth.getInstance();
    String userId = fA.getCurrentUser().getUid();
    PropertyAdapter propertyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_properties, container, false);

        Button btnAddProperty = view.findViewById(R.id.btnAddProperty);
        btnAddProperty.setOnClickListener(v -> {
           AddPropertiesFragment addPropertiesFragment = new AddPropertiesFragment();
           addPropertiesFragment.show(getParentFragmentManager(), "addPropertiesFragment");
        });

        RecyclerView rv = view.findViewById(R.id.rvProperties);
        rv.setLayoutManager(new LinearLayoutManager(getContext())); // Check if this exists!

        propertyAdapter = new PropertyAdapter(propertyList, property -> {
            PropertyDetailsFragment propertyDetailsFragment = new PropertyDetailsFragment();

            Bundle args = new Bundle();
            args.putString("propertyId", property.getId());
            propertyDetailsFragment.setArguments(args);

            propertyDetailsFragment.show(getParentFragmentManager(), "propertyDetailsFragment");
        });

        rv.setAdapter(propertyAdapter);

        propertyController.getProperties(userId, new OnGetPropertiesListener() {
            @Override
            public List<PropertyModel> onSuccess(List<PropertyModel> properties) {
                if (properties != null) {
                    propertyAdapter.updateList(properties);
                }
                return properties;
            }
            @Override
            public void onFailure(Exception e) {
                System.out.println("Error getting properties: " + e.getMessage());
            }
        });


        return view;
    }
}