package com.example.mywatchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public String mainName;

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

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        TextView name = (TextView) view.findViewById(R.id.userName);
        TextView mail = (TextView) view.findViewById(R.id.userMail);
        TextView number = (TextView) view.findViewById(R.id.userPhoneNum);

        name.setText(currentUser.getDisplayName());
        mail.setText(currentUser.getEmail());

//        DatabaseReference ref_phone = mData.child(currentUser.getUid()).child("phone");
//        ref_phone.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String phone_user = dataSnapshot.getValue(String.class);
//                phone.setText(phone_user);
//            }



        Button homeBtn = view.findViewById(R.id.profleFooterHomeBtn);
        Button profileBtn = view.findViewById(R.id.profileFooterProfileBtn);
        Button socialbtn = view.findViewById(R.id.profileFooterSocialBtn);
        Button contentBtn = view.findViewById(R.id.profileFooterContentBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_homeFragment);
            }
        });

        socialbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_socialSearchFragment);
            }
        });

        contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_contentSearchFragment);
            }
        });

        return view;
    }


}