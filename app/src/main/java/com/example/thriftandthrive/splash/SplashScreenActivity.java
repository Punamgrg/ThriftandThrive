package com.example.thriftandthrive.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.home.MainActivity;
import com.example.thriftandthrive.userAccount.UserAccountActivity;
import com.example.thriftandthrive.utils.SharedPrefUtils;

public class SplashScreenActivity extends AppCompatActivity {
    boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getIsLoggedInOrNot();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn)
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, UserAccountActivity.class));

                finish();
            }
        }, 1000);

//identifier return type void ko chai empty
//        public void getIsLoggedInOrNot () {
//            isLoggedIn = SharedPrefUtils.getBool(this, getString(R.string.isLoggedKey), false);
//        }
//
//    }
    }

    private void getIsLoggedInOrNot() {
        isLoggedIn = SharedPrefUtils.getBool(this, getString(R.string.isLoggedKey), false);

    }
}




