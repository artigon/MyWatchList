package com.example.mywatchlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userProfileFragment extends Fragment {


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userProfileFragment newInstance(String param1, String param2) {
        userProfileFragment fragment = new userProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        // Inflate the layout for this fragment

//        mAuth = FirebaseAuth.getInstance();
//        currentUser = mAuth.getCurrentUser();
//    Bundle bundle = getArguments();
//        Log.d("Bundle exists? ", "" + (bundle != null));
//        String userName = getArguments().getString("UserName");
//        String userEmail = getArguments().getString("UserEmail");
//        String userPhone = getArguments().getString("UserPhone");
//        if(usr != null){
//            User userProfile = (User) bundle.getParcelable("User");
//
//            TextView name = ((TextView) view.findViewById(R.id.profileName));
//            TextView email = ((TextView) view.findViewById(R.id.profileEmail));
//            TextView number = ((TextView) view.findViewById(R.id.profilePhoneNum));
//            name.setText("Name: " + userProfile.getName());
//            name.setText("Name: " + userName);
//            email.setText("Mail: " + userEmail);
//            number.setText("Phone number: " + userPhone);
//        }


        MainActivity main = (MainActivity)getActivity();


        return view;
    }


}