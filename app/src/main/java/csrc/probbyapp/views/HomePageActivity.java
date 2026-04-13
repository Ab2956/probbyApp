package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import csrc.probbyapp.R;
import csrc.probbyapp.controllers.UserController;

public class HomePageActivity extends AppCompatActivity {

    private UserController userController = new UserController();
    private Fragment homeF, mapF, propertiesF, settingsF;
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

            activeFragment = homeF;

            fm.beginTransaction()
                    .add(R.id.fragmentContainer, propertiesF, "properties").hide(propertiesF)
                    .add(R.id.fragmentContainer, mapF, "map").hide(mapF)
                    .add(R.id.fragmentContainer, settingsF, "settings").hide(settingsF)
                    .add(R.id.fragmentContainer, homeF, "home")
                    .commit();
        }

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
            return false;
        });

        // logout button implementing userController
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            userController.signOutUser();
            startActivity(new Intent(this, LoginPageActivity.class));
            finish();
        });

    }

    private void loadFragment(Fragment nextFragment) {
        if (nextFragment == null || nextFragment == activeFragment) return;
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (activeFragment != null) {
            transaction.hide(activeFragment);
        }
        transaction.show(nextFragment).commit();
        activeFragment = nextFragment;
    }
}
