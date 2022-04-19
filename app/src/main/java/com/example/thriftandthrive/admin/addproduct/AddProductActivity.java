package com.example.thriftandthrive.admin.addproduct;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.response.Category;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    List<Category> cats = new ArrayList<>();
    private static final int TAKE_PICTURE = 2;
    private static final int PICK_PICTURE = 1;
    String currentPhotoPath;
    List<String> photoPath = new ArrayList<>();
    List<Uri> photoUris = new ArrayList<>();
    RecyclerView imageRv;
    RecyclerView catRv;
    RAdapter iAdapter;
    RAdapter cAdapter;
    Button uploadBtn;
    EditText productNameET, descriptionET, priceET, quantityET, discountPriceET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Product");
        imageRv = findViewById(R.id.imageRv);
        catRv = findViewById(R.id.catRv);
        uploadBtn = findViewById(R.id.uploadBtn);
        productNameET = findViewById(R.id.productNameET);
        descriptionET = findViewById(R.id.descriptionET);
        priceET = findViewById(R.id.priceET);
        quantityET = findViewById(R.id.quantityET);
        discountPriceET = findViewById(R.id.discountPriceET);
        setImgRV();
        setCatRv();
    }

    private void setImgRV() {
        atRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cAdapter = new RAdapter(false, null, cats, this, new RAdapter.OnItemCLick() {
            @Override
            public void onCLick(int position) {
                cats.remove(position);
                cAdapter.notifyItemRemoved(position);
            }
        });
        catRv.setAdapter(cAdapter);
    }
}