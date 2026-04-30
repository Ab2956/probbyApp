package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.controllers.UserController;
import csrc.probbyapp.R;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.utils.UIHelper;

public class HomeFragment extends Fragment {

    private UserController userController = new UserController();
    private PropertyController propertyController = new PropertyController();
    UIHelper uiHelper = new UIHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        TextView tvRent = view.findViewById(R.id.totalRent);
        propertyController.getTotalRent(userId, new OnGetListener<Double>() {
            @Override
            public void onSuccess(Double data) {
                if (isAdded()) {
                    tvRent.setText("£" + data);
                }
                Log.d("Total rent: ", data.toString());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("Error getting total rent: ", e.getMessage());
            }
        });
        propertyController.getTotalPropertyCount(userId, new OnGetListener<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if (isAdded()) {
                    TextView tvProperties = view.findViewById(R.id.numOfProperties);
                    tvProperties.setText(data.toString());
                }
                Log.d("Total properties: ", data.toString());
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("Error getting total properties: ", e.getMessage());
            }
        });
        propertyController.getTotalAvailable(userId, new OnGetListener<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if (isAdded()) {
                    TextView tvAvailable = view.findViewById(R.id.status);
                    tvAvailable.setText(data.toString());
                }
                Log.d("Total available: ", data.toString());
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("Error getting total available: ", e.getMessage());
            }
        });
        propertyController.getTotalMortgage(userId, new OnGetListener<Double>() {
            @Override
            public void onSuccess(Double data) {
                if (isAdded()) {
                    TextView tvMortgage = view.findViewById(R.id.totalMortgage);
                    tvMortgage.setText("£" + data);
                }
                Log.d("Total mortgage: ", data.toString());
                }
                @Override
            public void onFailure(Exception e) {
                Log.e("Error getting total mortgage: ", e.getMessage());
            }
        });
        propertyController.getIncome(userId, new OnGetListener<Double>() {
            @Override
            public void onSuccess(Double data) {
                if (isAdded()) {
                    TextView tvIncome = view.findViewById(R.id.income);
                    tvIncome.setText("£" + data);
                }
                Log.d("Total income: ", data.toString());
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("Error getting total income: ", e.getMessage());
            }
        });
    }
}
