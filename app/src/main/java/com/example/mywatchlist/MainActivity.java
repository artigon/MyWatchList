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
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase db;
    private DatabaseReference mData;
    public String mainUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_LONG).show();

                            Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_homeFragment);

//                            getMyUserData();

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
        mainUserName = username;

        User tmpUser = new User(username, email, password, phone);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registered Successful", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_homeFragment);
                            mAuth.signInWithEmailAndPassword(email, password);
                            currentUser = mAuth.getCurrentUser();
                            users.child(tmpUser.getName()).setValue(tmpUser);
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                            currentUser.updateProfile(profileUpdates);
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    public void voidSocialListUpdater(){
//        StringBuffer userList = new StringBuffer();
//        DatabaseReference Users_ref_Data = db.getReference("Users");
//        Users_ref_Data.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snap : dataSnapshot.getChildren())
//                {
//                    userList.append(snap.getValue(User.class).getName() + "\n");
//                }
//                TextView list = (TextView) findViewById(R.id.socialUserList);
//                list.setText(userList.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    public void readFromData() {
        DatabaseReference Users_ref_Data = db.getReference("Users");
        DatabaseReference Content_ref_Data = db.getReference("Content");

        String searchName = ((EditText) findViewById(R.id.socialSearchBar)).getText().toString().trim();


        Users_ref_Data.child(searchName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                User user = new User(dataSnapshot.getValue(User.class));

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {
                        Toast.makeText(MainActivity.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        TextView name = (TextView) findViewById(R.id.userName);
                        TextView mail = (TextView) findViewById(R.id.userMail);
                        TextView number = (TextView) findViewById(R.id.userPhoneNum);
                        name.setText("Name: " + user.getName());
                        mail.setText("Mail: " + user.getMail());
                        number.setText("Phone number: " + user.getNumber());


                    } else {

                        Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();

                    }


                } else {

                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        TextView name = ((TextView) findViewById(R.id.userName));
                        TextView mail = ((TextView) findViewById(R.id.userMail));
                        TextView number = ((TextView) findViewById(R.id.userPhoneNum));
                        name.setText("Name: " + user.getName());
                        mail.setText("Mail: " + user.getMail());
                        number.setText("Phone number: " + user.getNumber());


                    } else {

                        Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();

                    }


                } else {

                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}