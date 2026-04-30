package csrc.probbyapp.views;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;
import java.util.UUID;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.utils.UIHelper;

public class AddPropertiesFragment extends DialogFragment {

    PropertyModel property;
    FirebaseAuth fA = FirebaseAuth.getInstance();
    PropertyController propertyController = new PropertyController();
    EditText pType, address, city, postcode, rooms, rent, mortgage, status;
    UIHelper uiHelper = new UIHelper();

    String[] propertyTypes = {"House", "Studio", "Flat", "Bungalow", "Land"};
    String[] propertyStatus = {"Available", "Rented", "Maintenance"};

    public void addPropertiesFragment () {}

    @Override
    public void onStart(){
        super.onStart();
        assert getDialog() != null;
        Objects.requireNonNull(getDialog().getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view =  inflater.inflate(R.layout.fragment_add_properties, container, false);


        address = view.findViewById(R.id.address);
        city = view.findViewById(R.id.city);
        postcode = view.findViewById(R.id.postcode);
        rooms = view.findViewById(R.id.rooms);
        rent = view.findViewById(R.id.rent);
        mortgage = view.findViewById(R.id.mortgage);


        AutoCompleteTextView typeDropdown = view.findViewById(R.id.pType);
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, propertyTypes);
        typeDropdown.setAdapter(adapterType);

        AutoCompleteTextView statusDropdown = view.findViewById(R.id.status);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, propertyStatus);
        statusDropdown.setAdapter(adapterStatus);

        Button subBtn = view.findViewById(R.id.btnSubmit);
        subBtn.setOnClickListener(v -> {

            String uid = UUID.randomUUID().toString();
            String rentInput = rent.getText().toString().trim();
            String mortgageInput = mortgage.getText().toString().trim();

            double rentVal = 0.0;
            double mortgageVal = 0.0;

            try {
                if (!rentInput.isEmpty()) {
                    rentVal = Double.parseDouble(rentInput);
                }
            } catch (NumberFormatException e) {
                rent.setError("Please enter a valid number (Check for letters like 'O')");
                return;
            }

            try {
                if (!mortgageInput.isEmpty()) {
                    mortgageVal = Double.parseDouble(mortgageInput);
                }
            } catch (NumberFormatException e) {
                mortgage.setError("Please enter a valid number");
                return;
            }

            property = new PropertyModel(uid,

                    typeDropdown.getText().toString(),
                    address.getText().toString(),
                    city.getText().toString(),
                    postcode.getText().toString(),
                    rooms.getText().toString(),
                    mortgageVal,
                    rentVal,
                    statusDropdown.getText().toString());

            assert fA.getCurrentUser() != null;
            String userId = fA.getCurrentUser().getUid();

            propertyController.addProperty(property, userId);

            dismiss();
        });
        uiHelper.applyTouchEffect(subBtn);

        return view;
    }
}