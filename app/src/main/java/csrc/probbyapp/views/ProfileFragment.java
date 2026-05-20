package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.UserController;
import csrc.probbyapp.models.UserModel;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.utils.UIHelper;

public class ProfileFragment extends Fragment {

    // Fragment for the profile page

    private UserController userController = new UserController() ;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private Button logoutBtn;
    private UIHelper uiHelper = new UIHelper();
    TextView tvUserName;
    TextView tvEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUserName = view.findViewById(R.id.username);
        tvEmail = view.findViewById(R.id.email);

        userController.getUser(userId, new OnGetListener<UserModel>() {
            @Override
            public void onSuccess(UserModel data) {
                tvEmail.setText(data.getEmail());
                tvUserName.setText(data.getUserName());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        Button logoutBtn = view.findViewById(R.id.logoutBtn);
        if (logoutBtn != null) {
            logoutBtn.setOnClickListener(v -> {
                userController.signOutUser();
                Intent intent = new Intent(requireActivity(), LoginPageActivity.class);
                startActivity(intent);
                requireActivity().finish();
                Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show();
            });
        }
       uiHelper.applyTouchEffect(logoutBtn);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refreshUserData();
        }
    }

    private void refreshUserData() {
        userController.getUser(userId, new OnGetListener<UserModel>() {
            @Override
            public void onSuccess(UserModel data) {
                if (isAdded() && tvEmail != null && tvUserName != null) {
                    tvEmail.setText(data.getEmail());
                    tvUserName.setText(data.getUserName());
                }
            }

            @Override
            public void onFailure(Exception e) {
                // Handle failure if necessary
            }
        });
    }
}