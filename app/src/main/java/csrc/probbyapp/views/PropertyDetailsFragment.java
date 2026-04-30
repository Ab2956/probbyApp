package csrc.probbyapp.views;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.utils.UIHelper;

public class PropertyDetailsFragment extends DialogFragment {

    private String propertyId;
    private PropertyController propertyController;
    UIHelper uiHelper = new UIHelper();


    @Override
    public void onStart(){
        super.onStart();
        assert getDialog() != null;
        Objects.requireNonNull(getDialog().getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            propertyId = getArguments().getString("propertyId");
        }
        propertyController = new PropertyController();

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_details, container, false);

        TextView propertyType = view.findViewById(R.id.propertyType);
        TextView propertyRent = view.findViewById(R.id.propertyRent);
        TextView propertyMortgage = view.findViewById(R.id.propertyMortgage);
        TextView propertyAddress = view.findViewById(R.id.propertyAddress);
        TextView propertyRooms = view.findViewById(R.id.propertyRooms);
        TextView propertyStatus = view.findViewById(R.id.propertyStatus);
        Button btnClose = view.findViewById(R.id.close);

        btnClose.setOnClickListener(v -> dismiss());
        uiHelper.applyTouchEffect(btnClose);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        propertyController.getPropertyById(userId, propertyId, new OnGetListener<PropertyModel>() {
            @Override
            public void onSuccess(PropertyModel property) {
                propertyType.setText(property.getPropertyType());

                propertyRent.setText("£" + property.getRent());
                propertyMortgage.setText("£" + property.getMortgage());

                propertyAddress.setText(property.getAddress());
                propertyRooms.setText(property.getRooms() + " Rooms");
                propertyStatus.setText(property.getStatus());
            }

            @Override
            public void onFailure(Exception e) {
                propertyAddress.setText("Error: Could not load details.");
                propertyStatus.setText("Error");
            }
        });

        return view;
    }
}