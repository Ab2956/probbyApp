package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import csrc.probbyapp.R;
import csrc.probbyapp.controllers.LoginController;
import csrc.probbyapp.controllers.UserController;

public class HomePageActivity extends AppCompatActivity {


    UserController userController = new UserController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.menu_home);

        bottomNav.setOnItemSelectedListener(item -> {
// TODO implement navigation
            if (item.getItemId() == bottomNav.getSelectedItemId()) {
                return true;
            }else if (item.getItemId() == R.id.menu_profile) {

                return true;
            } else if (item.getItemId() == R.id.menu_settings) {

                return true;
            } else if (item.getItemId() == R.id.menu_properties) {

                return true;
            } else if (item.getItemId() == R.id.menu_home) {

                return true;
            } else if (item.getItemId() == R.id.menu_map) {

                return true;
            }
            return false;
        });

        Button logoutBtn = findViewById(R.id.btnLogout);
        logoutBtn.setOnClickListener(v -> {

            userController.signOutUser();
           // Snackbar.make( HomePageActivity,"Successfully logged out", Snackbar.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginPageActivity.class));
            finish();
        });

    }
}