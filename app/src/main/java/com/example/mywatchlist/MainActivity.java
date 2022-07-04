package com.example.mywatchlist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    public static User user = new User();
    public static User mainUser;
    public static User tmpUser = new User();
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase db;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        mData = db.getReference();

        mainUser = new User("itay", "itay@gmail.com", "123456", "0541234567");


    }

    public void loginFunc(View view) {

        String email = ((EditText) findViewById(R.id.emailInput)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_LONG).show();

                            Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_homeFragment);

                        } else {
                            Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_LONG).show();
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

        User tmp = new User(username, email, password, phone);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registered Successful", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_homeFragment);
                            mAuth.signInWithEmailAndPassword(email, password);
                            currentUser = mAuth.getCurrentUser();
                            users.child(tmp.getName()).setValue(tmp);
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                            currentUser.updateProfile(profileUpdates);
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    public void readFromData(int choice, String path) {
        DatabaseReference ref_Data = mData.child(path);
        ref_Data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (choice == 0) {
                    User user = dataSnapshot.getValue(User.class);
                    View view = getLayoutInflater().inflate(R.layout.fragment_user_profile, null);
                    TextView name = (TextView) view.findViewById(R.id.userName);
                    TextView mail = (TextView) view.findViewById(R.id.userMail);
                    TextView number = (TextView) view.findViewById(R.id.userPhoneNum);
                    name.setText("Name: " + user.getName());
                    mail.setText("Mail: " + user.getMail());
                    number.setText("Phone number: " + user.getNumber());
                } else if (choice == 1) {
                    //object content here
                    View view = getLayoutInflater().inflate(R.layout.fragment_content, null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    public void setMainUser() {
//        //Fragment frag = getFragmentManager().findFragmentById(R.id.userProfileFragment);
//        userProfileFragment fragment_obj = (userProfileFragment) getSupportFragmentManager().
//                findFragmentById(R.id.userProfileFragment);
//        ((TextView) fragment_obj.getView().findViewById(R.id.userName)).setText(currentUser.getName());
//        ((TextView) fragment_obj.getView().findViewById(R.id.userMail)).setText(mainUser.getMail());
//        ((TextView) fragment_obj.getView().findViewById(R.id.userPhoneNum)).setText(mainUser.getNumber());
//    }
}