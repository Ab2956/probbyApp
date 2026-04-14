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
import csrc.probbyapp.controllers.UserController;
import csrc.probbyapp.R;

public class HomeFragment extends Fragment {

    private UserController userController = new UserController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button logoutBtn = view.findViewById(R.id.btnLogout);
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
