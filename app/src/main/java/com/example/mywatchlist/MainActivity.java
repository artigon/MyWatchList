package com.example.mywatchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public boolean test = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button homeBtn = findViewById(R.id.homeBtn);
        Button profileBtn = findViewById(R.id.profileBtn);
        Button socialBtn = findViewById(R.id.socialBtn);
        Button contentBtn = findViewById(R.id.contentSearchBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment home = new homeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,home).commit();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfileFragment profile = new userProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,profile).commit();
            }
        });

        socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socialSearchFragment social = new socialSearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,social).commit();
            }
        });

        contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentSearchFragment contentSearch = new contentSearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,contentSearch).commit();
            }
        });


    }

    public void moveToContentFragment()
    {
        contentFragment content = new contentFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,content).commit();
    }

//    public void test()
//    {
//        ;
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
//                ).commit();
//    }
}