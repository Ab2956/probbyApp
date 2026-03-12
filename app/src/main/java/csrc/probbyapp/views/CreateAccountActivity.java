package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import csrc.probbyapp.R;
import csrc.probbyapp.controllers.LoginController;

public class CreateAccountActivity extends AppCompatActivity implements AuthView {

    LoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loginController = new LoginController((this));
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);



    }

    @Override
    public void navigateTo(){
        startActivity(new Intent(this, LoginPageActivity.class));
        finish();
    }
}