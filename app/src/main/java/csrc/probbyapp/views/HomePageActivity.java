package csrc.probbyapp.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import csrc.probbyapp.R;

public class HomePageActivity extends AppCompatActivity {

    private Fragment homeF, mapF, propertiesF, settingsF, profileF;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // Initialising fragments
            homeF = new HomeFragment();
            mapF = new MapFragment();
            propertiesF = new PropertiesFragment();
            settingsF = new SettingsFragment();
            profileF = new ProfileFragment();

            activeFragment = homeF;

            fm.beginTransaction()
                    .add(R.id.fragmentContainer, propertiesF, "properties").hide(propertiesF)
                    .add(R.id.fragmentContainer, mapF, "map").hide(mapF)
                    .add(R.id.fragmentContainer, settingsF, "settings").hide(settingsF)
                    .add(R.id.fragmentContainer, profileF, "profile").hide(profileF)
                    .add(R.id.fragmentContainer, homeF, "home")
                    .commit();
        } else {
            // RESTORE fragments from FragmentManager to prevent "Ghosts"
            homeF = fm.findFragmentByTag("home");
            mapF = fm.findFragmentByTag("map");
            propertiesF = fm.findFragmentByTag("properties");
            settingsF = fm.findFragmentByTag("settings");
            profileF = fm.findFragmentByTag("profile");

            // Find which one was active before the restart
            activeFragment = homeF; // Default fallback
            if (homeF != null && !homeF.isHidden()) activeFragment = homeF;
            else if (mapF != null && !mapF.isHidden()) activeFragment = mapF;
            else if (propertiesF != null && !propertiesF.isHidden()) activeFragment = propertiesF;
            else if (settingsF != null && !settingsF.isHidden()) activeFragment = settingsF;
            else if (profileF != null && !profileF.isHidden()) activeFragment = profileF;
        }

        bottomNav.setSelectedItemId(R.id.menu_home);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_home) {
                loadFragment(homeF);
                return true;
            } else if (itemId == R.id.menu_map) {
                loadFragment(mapF);
                return true;
            } else if (itemId == R.id.menu_properties) {
                loadFragment(propertiesF);
                return true;
            }else if (itemId == R.id.menu_settings) {
                loadFragment(settingsF);
                return true;
            }
            else if(itemId == R.id.menu_profile){
                loadFragment(profileF);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment nextFragment) {
        if (nextFragment == null || nextFragment == activeFragment) return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

       transaction.hide(activeFragment);

        // Show only the selected one
        transaction.show(nextFragment).commit();
        activeFragment = nextFragment;
    }
}
