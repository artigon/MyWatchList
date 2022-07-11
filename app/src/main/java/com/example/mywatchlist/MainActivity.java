package com.example.mywatchlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public String mainUserName;
    public User tmpUser;
    public String searchUser;
    public String searchContent;
    public Boolean profilePick;

    userCallBack userCallBack = new userCallBack() {
        @Override
        public void setUser(User user) {
            TextView name = ((TextView) findViewById(R.id.profileName));
            TextView email = ((TextView) findViewById(R.id.profileEmail));
            TextView number = ((TextView) findViewById(R.id.profilePhoneNum));
            name.setText("Name: " + user.getName());
            email.setText("Mail: " + user.getMail());
            number.setText("Phone number: " + user.getNumber());
        }
    };
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase db;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fotter:
        Button homeBtn = findViewById(R.id.mainFooterHomeBtn);
        Button profileBtn = findViewById(R.id.mainFooterProfileBtn);
        Button socialBtn = findViewById(R.id.mainFooterSocialBtn);
        Button contentBtn = findViewById(R.id.mainFooterContentBtn);

        FragmentManager fragmentManager = getSupportFragmentManager();



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, homeFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("") // name can be null
                        .commit();

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePick = true;
                getMyUserData();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, userProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("") // name can be null
                        .commit();

            }
        });

        socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, socialSearchFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("") // name can be null
                        .commit();
            }
        });

        contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentListUpdater();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, contentSearchFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("") // name can be null
                        .commit();
            }
        });





        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        mData = db.getReference();


    }

    public void loginFunc(View view) {

        String userName = ((EditText) findViewById(R.id.logInUserInput)).getText().toString().trim();
        mainUserName = userName;

        String email = ((EditText) findViewById(R.id.emailInput)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentContainerView, homeFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("") // name can be null
                                    .commit();


                        } else {
//                            Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    public void registerFunc(View view) {
        DatabaseReference users = mData.child("Users");

        String email = ((EditText) findViewById(R.id.registerEmailInput)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.registerPaswordInput)).getText().toString().trim();
        String phone = ((EditText) findViewById(R.id.registePhoneNumInput)).getText().toString().trim();
        String username = ((EditText) findViewById(R.id.registerUserInput)).getText().toString().trim();
        users.push().get();
        mainUserName = username;

        User tmpUser = new User(username, email, password, phone);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Registered Successful", Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentContainerView, homeFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("") // name can be null
                                    .commit();
                            mAuth.signInWithEmailAndPassword(email, password);
                            currentUser = mAuth.getCurrentUser();
                            users.child(tmpUser.getName()).setValue(tmpUser);
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                            currentUser.updateProfile(profileUpdates);
                        } else {
//                            Toast.makeText(MainActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    public void readUsersFromData() {
        DatabaseReference Users_ref_Data = db.getReference("Users");
        String searchName = ((EditText) findViewById(R.id.socialSearchBar)).getText().toString().trim();
//        String searchName = searchUser;
        Users_ref_Data.child(searchName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                User user = new User(dataSnapshot.getValue(User.class));

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        tmpUser = new User(user);



//                        userProfileFragment userProfile = new userProfileFragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("User",user.getName());
//                        userProfile.setArguments(bundle);

                        TextView name = ((TextView) findViewById(R.id.profileName));
                        TextView email = ((TextView) findViewById(R.id.profileEmail));
                        TextView number = ((TextView) findViewById(R.id.profilePhoneNum));
                        name.setText("Name: " + user.getName());
                        email.setText("Mail: " + user.getMail());
                        number.setText("Phone number: " + user.getNumber());


                    } else {

//                        Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_LONG).show();

                    }


                } else {

//                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void getMyUserData() {
        DatabaseReference Users_ref_Data = db.getReference("Users");
        Users_ref_Data.child(mainUserName.toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                User user = new User(dataSnapshot.getValue(User.class));

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {
//                        Toast.makeText(MainActivity.this, "Successfully Read", Toast.LENGTH_LONG).show();
                        TextView name = ((TextView) findViewById(R.id.profileName));
                        TextView email = ((TextView) findViewById(R.id.profileEmail));
                        TextView number = ((TextView) findViewById(R.id.profilePhoneNum));
                        name.setText("Name: " + user.getName());
                        email.setText("Mail: " + user.getMail());
                        number.setText("Phone number: " + user.getNumber());


                    } else {

//                        Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_LONG).show();

                    }


                } else {

//                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void readContentFromData() {
        DatabaseReference Content_ref_Data = db.getReference("Content");

        String searchContentName = ((EditText) findViewById(R.id.contentSearchBar)).getText().toString().trim();

        Content_ref_Data.child(searchContentName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                Content content = new Content(dataSnapshot.getValue(Content.class));

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

//                        Toast.makeText(MainActivity.this, "Successfully Read", Toast.LENGTH_LONG).show();
                        TextView conetntName = ((TextView) findViewById(R.id.contentName));
                        TextView contentDate = ((TextView) findViewById(R.id.contentDate));
                        TextView contentType = ((TextView) findViewById(R.id.type));
                        TextView contentNumLikes = ((TextView) findViewById(R.id.numberOfLikes));
                        TextView contentDescription = ((TextView) findViewById(R.id.conetntDiscription));
//                        TextView contentPosts = ((TextView) findViewById(R.id.contentPosts));
                        conetntName.setText("Name: " + content.getName());
                        contentDate.setText("Date of relese: " + content.getDateOfRelese());
                        contentType.setText("Type: " + content.getType());
                        contentNumLikes.setText("Number of likes: " + content.getNumOfLikes());
                        contentDescription.setText(content.getDisciption());
                        //contentPosts.setText((CharSequence) content.getPosts());


                    } else {

//                        Toast.makeText(MainActivity.this, "Content Doesn't Exist", Toast.LENGTH_LONG).show();

                    }


                } else {

//                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void socialListUpdater() {
        StringBuffer userList = new StringBuffer();

        DatabaseReference Users_ref_Data = db.getReference("Users");
        Users_ref_Data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    userList.append(snap.getValue(User.class).getName() + "\n");
                }

                TextView list = (TextView) findViewById(R.id.socialUserList);
                list.setText(userList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addContent() {
        DatabaseReference content = mData.child("Content");

        String contentName = ((EditText) findViewById(R.id.addContentNameInput)).getText().toString().trim();
        String contentDate = ((EditText) findViewById(R.id.addContentDateInput)).getText().toString().trim();
        String contentType = ((EditText) findViewById(R.id.addContentDiscriptionInput)).getText().toString().trim();
        String contentDiscription = ((EditText) findViewById(R.id.addContentDiscriptionInput)).getText().toString().trim();

        Content newContent = new Content(contentName, contentDate, contentType, contentDiscription);

        content.push().get();

        content.child(newContent.getName()).setValue(newContent);
    }

    public void contentListUpdater() {
        StringBuffer userList = new StringBuffer();

        DatabaseReference Users_ref_Data = db.getReference("Content");
        Users_ref_Data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    userList.append(snap.getValue(Content.class).getName() + "\n");
                }
                TextView list = ((TextView) findViewById(R.id.contentList));
                list.setText(userList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}//end