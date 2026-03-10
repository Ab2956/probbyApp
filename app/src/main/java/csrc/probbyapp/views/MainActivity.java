package csrc.probbyapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import csrc.probbyapp.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override                                                                                               // Splash activity for the welcome page when loading into app
            public void run() {
                Intent intentSplash = new Intent(MainActivity.this, LoginPageActivity.class);
                startActivity(intentSplash);
                finish();
            }
        },1000);                                                                                          // 2000 millisecond delay for the welcome page

        Log.d(TAG, "onCreate: ");
    }

}