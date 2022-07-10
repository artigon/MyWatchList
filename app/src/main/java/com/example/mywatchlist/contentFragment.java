package com.example.mywatchlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public contentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static contentFragment newInstance(String param1, String param2) {
        contentFragment fragment = new contentFragment();
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
        View view = inflater.inflate(R.layout.fragment_content,container, false);
        // Inflate the layout for this fragment

        MainActivity main = (MainActivity)getActivity();



        //footer buttons:
        Button homeBtn = view.findViewById(R.id.contentFooterHomeBtn);
        Button profileBtn = view.findViewById(R.id.contentFooterProfileBtn);
        Button socialBtn = view.findViewById(R.id.contentFooterSocialBtn);
        Button contentSearchBtn = view.findViewById(R.id.contentFooterContentBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_contentFragment_to_homeFragment);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.getMyUserData();
                Navigation.findNavController(view).navigate(R.id.action_contentFragment_to_userProfileFragment);
            }
        });

        socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.socialListUpdater();
                Navigation.findNavController(view).navigate(R.id.action_contentFragment_to_socialSearchFragment);
            }
        });

        contentSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.contentListUpdater();
                Navigation.findNavController(view).navigate(R.id.action_contentFragment_to_contentSearchFragment);
            }
        });


        return view;
    }
}