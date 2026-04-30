package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import csrc.probbyapp.R;
import csrc.probbyapp.controllers.LoginController;
import csrc.probbyapp.utils.UIHelper;

public class CreateAccountActivity extends AppCompatActivity implements AuthView {

    private LoginController loginController;
    UIHelper uiHelper = new UIHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        Button createAccBtn = findViewById(R.id.btnCreateAcc);
        EditText userNameInput = findViewById(R.id.etName);
        EditText emailInput = findViewById(R.id.etEmail);
        EditText passInput = findViewById(R.id.etPassword);
        EditText nameInput = findViewById(R.id.etName);
        EditText confirmPassInput = findViewById(R.id.etConfirmPassword);
        Button backBtn = findViewById(R.id.btnBack);

        backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginPageActivity.class));
            finish();
        });

        loginController = new LoginController((this));

        createAccBtn.setOnClickListener(v -> {
            String username = userNameInput.getText().toString();
            String email = emailInput.getText().toString();
            String pass = passInput.getText().toString();
            String name = nameInput.getText().toString();
            loginController.createAccount(name, email, pass, v);
        });
        uiHelper.applyTouchEffect(createAccBtn);
    }

    @Override
    public void navigateTo(){
        startActivity(new Intent(this, LoginPageActivity.class));
        finish();
    }
    @Override
    public void showMessage(String message, View view){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }


}