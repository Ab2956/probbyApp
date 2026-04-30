package csrc.probbyapp.views;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.databinding.tool.Context;
import androidx.fragment.app.Fragment;

import csrc.probbyapp.R;

public class SettingsFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RadioGroup rgTheme = view.findViewById(R.id.rgTheme);
        RadioButton rbLight = view.findViewById(R.id.rbLight);
        RadioButton rbDark = view.findViewById(R.id.rbDark);
        RadioButton rbSystem = view.findViewById(R.id.rbSystem);

        // Use SharedPreferences to remember the user's choice
        sharedPreferences = requireActivity().getSharedPreferences("SettingsPrefs", MODE_PRIVATE);
        int savedTheme = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Check the correct button based on saved state
        if (savedTheme == AppCompatDelegate.MODE_NIGHT_NO) rbLight.setChecked(true);
        else if (savedTheme == AppCompatDelegate.MODE_NIGHT_YES) rbDark.setChecked(true);
        else rbSystem.setChecked(true);

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

        return view;
    }
}