package com.example.thriftandthrive.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.home.fragments.CartFragment;
import com.example.thriftandthrive.home.fragments.CategoryFragment;
import com.example.thriftandthrive.home.fragments.FavouritesFragment;
import com.example.thriftandthrive.home.fragments.MoreFragment;
import com.example.thriftandthrive.home.fragments.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    CartFragment cartFragment;
    FavouritesFragment favouritesFragment;
    CategoryFragment categoryFragment;
    MoreFragment moreFragment;
    Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainBottomNav);
        homeFragment = new HomeFragment();
        homeFragment.setBottomNavigationView(bottomNavigationView);
        currentFragment = homeFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getTitle().equals(getString(R.string.home))) {
                    if (homeFragment == null)
                        homeFragment = new HomeFragment();
                    changeFragment(homeFragment);
                    return true;

                }
                if (item.getTitle().equals(getString(R.string.cart))) {
                    if (cartFragment == null)
                        cartFragment = new CartFragment();
                    changeFragment(cartFragment);
                    return true;
                }
                if (item.getTitle().equals(getString(R.string.favourites))) {
                    if (favouritesFragment == null)
                        favouritesFragment = new FavouritesFragment();
                    changeFragment(favouritesFragment);
                    return true;
                }
                if (item.getTitle().equals(getString(R.string.categories))){
                    if (categoryFragment== null)
                        categoryFragment = new CategoryFragment();
                    changeFragment(categoryFragment);
                    return true;
                }
                if (item.getTitle().equals(getString(R.string.more))){
                    if (moreFragment== null)
                        moreFragment = new MoreFragment();
                    changeFragment(moreFragment);
                    return true;
                }
//                if (item.getTitle().equals(getString(R.string.ca)) {
//                    if (favouritesFragment == null)
//                        favouritesFragment = new FavouritesFragment();
//                    changeFragment(favouritesFragment);
//                    return true;
//                }


                return false;
            }
        });
    }

    void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();

        if (fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, fragment).commit();
        }
        currentFragment = fragment;
    }
}