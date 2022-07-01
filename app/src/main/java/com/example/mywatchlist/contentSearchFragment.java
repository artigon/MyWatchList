package com.example.mywatchlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contentSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contentSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public contentSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contentSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static contentSearchFragment newInstance(String param1, String param2) {
        contentSearchFragment fragment = new contentSearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_content_search, container, false);
        // Inflate the layout for this fragment

        //footer buttons:
        Button homeBtn = view.findViewById(R.id.contentSearchFooterHomeBtn);
        Button profileBtn = view.findViewById(R.id.contentSearchFooterProfileBtn);
        Button socialBtn = view.findViewById(R.id.contentSearchFooterSocialBtn);
        Button contentBtn = view.findViewById(R.id.contentSearchFooterContentBtn);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_contentSearchFragment_to_homeFragment);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_contentSearchFragment_to_userProfileFragment);
            }
        });

        socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_contentSearchFragment_to_socialSearchFragment);
            }
        });

        Button searchBtn = view.findViewById(R.id.theContentContentSearchBtn);
        Button addContentBtn = view.findViewById(R.id.contentSearchAddNewContent);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_contentSearchFragment_to_contentFragment);

            }
        });

        addContentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("test from content search");
                Navigation.findNavController(view).navigate(R.id.action_contentSearchFragment_to_addContentFragment);
                System.out.println("test from content search 2");
            }
        });


        return view;
    }
}