package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import csrc.probbyapp.controllers.LoginController;
import csrc.probbyapp.R;

public class LoginPageActivity extends AppCompatActivity implements AuthView {
    private boolean isLoggedIn = false;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        loginController = new LoginController(this);
        Button loginBtn = findViewById(R.id.btnLogin);
        EditText emailInput = findViewById(R.id.etEmail);
        EditText passInput = findViewById(R.id.etPassword);

        loginBtn.setOnClickListener(v ->  {
            String email = emailInput.getText().toString();
            String pass = passInput.getText().toString();
            loginController.signInUser(email, pass);

        });
    }
    @Override
    public void navigateTo(){
        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }

}