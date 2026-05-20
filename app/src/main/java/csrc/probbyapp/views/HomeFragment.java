package csrc.probbyapp.views;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;
import csrc.probbyapp.controllers.PropertyController;
import csrc.probbyapp.R;
import csrc.probbyapp.models.PropertyModel;
import csrc.probbyapp.models.PropertyStats;
import csrc.probbyapp.utils.OnGetListener;

public class HomeFragment extends Fragment {

    // Fragment for the home page

    private PropertyController propertyController = new PropertyController();

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
        TextView tvProperties = view.findViewById(R.id.numOfProperties);
        TextView tvAvailable = view.findViewById(R.id.status);
        TextView tvMortgage = view.findViewById(R.id.totalMortgage);
        TextView tvIncome = view.findViewById(R.id.income);


        propertyController.getProperties(userId, new OnGetListener<List<PropertyModel>>() {
            @Override
            public void onSuccess(List<PropertyModel> properties) {
                // This handles the list if you need to populate a RecyclerView
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("HomeFragment", "Error loading properties: " + e.getMessage());
            }
        }, new OnGetListener<PropertyStats>() {
            @Override
            public void onSuccess(PropertyStats stats) {
                // Update ALL UI elements at once from the local calculation
                if (isAdded()) {
                    tvRent.setText("£" + stats.getTotalRent());
                    tvProperties.setText(String.valueOf(stats.getTotalCount()));
                    tvAvailable.setText(String.valueOf(stats.getAvailableCount()));
                    tvMortgage.setText("£" + stats.getTotalMortgage());
                    tvIncome.setText("£" + stats.getNetIncome());
                }
                Log.d("HomeFragment", "Stats updated locally from single DB loop");
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("HomeFragment", "Error loading stats: " + e.getMessage());
            }
        });

    }
}
