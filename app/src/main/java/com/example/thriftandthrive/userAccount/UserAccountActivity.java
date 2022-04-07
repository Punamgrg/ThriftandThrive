package com.example.thriftandthrive.userAccount;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.userAccount.fragments.LoginFragment;
import com.example.thriftandthrive.userAccount.fragments.RegisterFragment;


public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {
    TextView registerTV, newtoTV;
    boolean isRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        registerTV = findViewById(R.id.registerTV);
        newtoTV = findViewById(R.id.newtoTV);
        registerTV.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.formContainerFL, new LoginFragment()).commit();
    }


    @Override
    public void onClick(View v) {

            if (v == registerTV) {
                if (!isRegister) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new RegisterFragment()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new LoginFragment()).commit();
                }
                changeTexts();
                isRegister = !isRegister;
            }
        }

        void changeTexts() {
            if (!isRegister) {
                registerTV.setText("Login");
                newtoTV.setText("Already Have an Account? ");
            } else {
                registerTV.setText("Register");
                newtoTV.setText("New to Thrift and Thrive? ");
            }
        }
    }


