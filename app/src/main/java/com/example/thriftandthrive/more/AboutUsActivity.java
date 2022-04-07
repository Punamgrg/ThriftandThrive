package com.example.thriftandthrive.more;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;

public class AboutUsActivity extends AppCompatActivity {
    ImageView aboutusbackIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        aboutusbackIV = findViewById(R.id.aboutusbackIV);
        setOnclickListeners();


    }

    private void setOnclickListeners() {
        aboutusbackIV.setOnClickListener( v-> finish());
    }
}