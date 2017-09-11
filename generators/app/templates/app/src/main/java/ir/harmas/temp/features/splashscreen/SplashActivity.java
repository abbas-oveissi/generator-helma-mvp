package <%= appPackage %>.features.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import <%= appPackage %>.R;
import <%= appPackage %>.features.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this,CategoryActivity.class);
                startActivity(i);
                finish();
            }
        },1500);
    }
}
