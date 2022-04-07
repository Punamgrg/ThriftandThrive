package com.example.thriftandthrive.more;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;

public class ProfileActivity extends AppCompatActivity {
    ImageView profileBackIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileBackIV = findViewById(R.id.profilebackIV);
        setOnclickListeners();






    }

    private void setOnclickListeners() {
        profileBackIV.setOnClickListener( v-> finish());
    }
}
