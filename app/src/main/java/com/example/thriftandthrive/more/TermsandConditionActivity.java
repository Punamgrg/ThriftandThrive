package com.example.thriftandthrive.more;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;

public class TermsandConditionActivity extends AppCompatActivity {
    ImageView termsandConbackIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsand_condition);
        termsandConbackIV= findViewById(R.id.termsandConbackIV);
        setOnclickListeners();


    }

    private void setOnclickListeners() {
        termsandConbackIV.setOnClickListener( v-> finish());
    }

}


//    private void setOnclickListeners() {
//        profileBackIV.setOnClickListener( v-> finish());
//    }
//}