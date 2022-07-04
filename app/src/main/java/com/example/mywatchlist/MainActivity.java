package com.example.mywatchlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static User user = new User();
    public static User mainUser = new User("itay","itay@gmail.com","123456","0541234567");
    public static User tmpUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void setMainUser()
    {
        userProfileFragment profile = new userProfileFragment();
        profile.setProfile(mainUser);
    }
}