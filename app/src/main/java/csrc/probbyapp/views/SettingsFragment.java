package csrc.probbyapp.views;

import static android.content.Context.MODE_PRIVATE;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.UserController;
import csrc.probbyapp.utils.OnGetListener;
import csrc.probbyapp.utils.UIHelper;

public class SettingsFragment extends Fragment {

    // Fragment for the settings page

    private SharedPreferences sharedPreferences;
    private UserController userController = new UserController();
    private UIHelper uiHelper = new UIHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RadioGroup rgTheme = view.findViewById(R.id.rgTheme);
        RadioButton rbLight = view.findViewById(R.id.rbLight);
        RadioButton rbDark = view.findViewById(R.id.rbDark);

        // Use SharedPreferences to remember the user's choice
        sharedPreferences = requireActivity().getSharedPreferences("SettingsPrefs", MODE_PRIVATE);
        int savedTheme = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Check the correct button based on saved state
        if (savedTheme == AppCompatDelegate.MODE_NIGHT_NO) rbLight.setChecked(true);
        else if (savedTheme == AppCompatDelegate.MODE_NIGHT_YES) rbDark.setChecked(true);

        rgTheme.setOnCheckedChangeListener((group, checkedId) -> {
            int mode;
            if (checkedId == R.id.rbLight) {
                mode = AppCompatDelegate.MODE_NIGHT_NO;
            } else if (checkedId == R.id.rbDark) {
                mode = AppCompatDelegate.MODE_NIGHT_YES;
            } else {
                mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
            }

            // Save the choice
            sharedPreferences.edit().putInt("theme_mode", mode).apply();

            // Apply the theme immediately
            AppCompatDelegate.setDefaultNightMode(mode);
        });

        EditText CurrentPass = view.findViewById(R.id.CurrentPass);
        EditText etPassword = view.findViewById(R.id.etPassword);
        Button btnSubNewPass = view.findViewById(R.id.btnSubNewPass);
        
        btnSubNewPass.setOnClickListener(v -> {
            String current = CurrentPass.getText().toString();
            String news = etPassword.getText().toString();
            
            if (current.isEmpty() || news.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all password fields", Toast.LENGTH_SHORT).show();
                return;
            }
            
            userController.changePassword(news, current, new OnGetListener<String>() {
                @Override
                public void onSuccess(String data) {
                    Toast.makeText(getContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                    CurrentPass.setText("");
                    etPassword.setText("");
                }

                @Override
                public void onFailure(Exception e) {
                    CurrentPass.setError("Check current password");
                    Toast.makeText(getContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            });
        });

        EditText NewUserName = view.findViewById(R.id.NewUserName);
        Button btnSubNewName = view.findViewById(R.id.btnSubNewName);
        
        btnSubNewName.setOnClickListener(v -> {
            String newName = NewUserName.getText().toString();
            if (newName.isEmpty()) {
                Toast.makeText(getContext(), "Please enter a new username", Toast.LENGTH_SHORT).show();
                return;
            }
            
            userController.changeUserName(newName, new OnGetListener<String>() {
                @Override
                public void onSuccess(String data) {
                    Toast.makeText(getContext(), "Username updated successfully", Toast.LENGTH_SHORT).show();
                    NewUserName.setText("");
                }
                @Override
                public void onFailure(Exception e) {
                    NewUserName.setError("Error changing username");
                    Toast.makeText(getContext(), "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        uiHelper.applyTouchEffect(btnSubNewName);
        uiHelper.applyTouchEffect(btnSubNewPass);

        return view;
    }
}
