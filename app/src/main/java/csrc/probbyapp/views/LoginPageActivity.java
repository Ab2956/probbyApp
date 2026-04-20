package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;


import csrc.probbyapp.controllers.LoginController;
import csrc.probbyapp.R;
import csrc.probbyapp.utils.UiHelper;

public class LoginPageActivity extends AppCompatActivity implements AuthView {
    private boolean isLoggedIn = false;
    private final LoginController loginController = new LoginController(this);
    UiHelper uiHelper = new UiHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        Button loginBtn = findViewById(R.id.btnLogin);
        EditText emailInput = findViewById(R.id.etEmail);
        EditText passInput = findViewById(R.id.etPassword);

        TextView createAccBtn = findViewById(R.id.tvRegister);

        loginBtn.setOnClickListener(v ->  {
            String email = emailInput.getText().toString();
            String pass = passInput.getText().toString();
            loginController.signInUser(email, pass, v);

        });

        createAccBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        });

        uiHelper.applyTouchEffect(loginBtn);
        uiHelper.applyTouchEffect(createAccBtn);

    }
    @Override
    public void navigateTo(){
        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }
    @Override
    public void showMessage(String message, View view){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

}