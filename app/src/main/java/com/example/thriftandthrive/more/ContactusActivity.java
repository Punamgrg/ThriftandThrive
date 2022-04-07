package com.example.thriftandthrive.more;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;

public class ContactusActivity extends AppCompatActivity {
    ImageView contactusbackIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        contactusbackIV = findViewById(R.id.contactusbackIV);
        setOnclickListeners();

    }

    private void setOnclickListeners() {
        contactusbackIV.setOnClickListener( v-> finish());
    }
}

//    ImageView aboutusbackIV;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about_us);
//        aboutusbackIV = findViewById(R.id.aboutusbackIV);
//        setOnclickListeners();
//
//
//
//    }
//
//    private void setOnclickListeners() {
//    }