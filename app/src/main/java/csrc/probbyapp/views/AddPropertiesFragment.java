package csrc.probbyapp.views;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;
import java.util.UUID;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.models.PropertyModel;

public class AddPropertiesFragment extends DialogFragment {

    PropertyModel property;
    FirebaseAuth fA = FirebaseAuth.getInstance();
    PropertyController propertyController = new PropertyController();
    EditText pType, address, city, postcode, rooms, rent, mortgage, status;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_properties, container, false);

        pType = view.findViewById(R.id.pType);
        address = view.findViewById(R.id.address);
        city = view.findViewById(R.id.city);
        postcode = view.findViewById(R.id.postcode);
        rooms = view.findViewById(R.id.rooms);
        rent = view.findViewById(R.id.rent);
        mortgage = view.findViewById(R.id.mortgage);
        status = view.findViewById(R.id.status);

        Button subBtn = view.findViewById(R.id.btnSubmit);
        subBtn.setOnClickListener(v -> {

            String uid = UUID.randomUUID().toString();

            property = new PropertyModel(uid, pType.getText().toString(),
                    address.getText().toString(), city.getText().toString(),
                    postcode.getText().toString(), rooms.getText().toString(),
                    Double.parseDouble(mortgage.getText().toString()),
                    Double.parseDouble(rent.getText().toString()), status.getText().toString());

            assert fA.getCurrentUser() != null;
            String userId = fA.getCurrentUser().getUid();

            propertyController.addProperty(property, userId);

            dismiss();
        });

        return view;
    }
}