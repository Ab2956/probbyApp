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

import csrc.probbyapp.R;
import csrc.probbyapp.controllers.UserController;

public class ProfileFragment extends Fragment {

    UserController userController = new UserController() ;
    Button logoutBtn;

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

        Button logoutBtn = view.findViewById(R.id.logoutBtn);
        if (logoutBtn != null) {
            logoutBtn.setOnClickListener(v -> {
                userController.signOutUser();
                Intent intent = new Intent(requireActivity(), LoginPageActivity.class);
                startActivity(intent);
                requireActivity().finish();
            });
        }
    }
}