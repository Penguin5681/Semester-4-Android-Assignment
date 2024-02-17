package com.example.assignedproject2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllProfilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllProfilesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ProfileDataModel> profileList;
    private RecyclerView recyclerView;
    private String[] names;
    private String[] emails;
    private String[] specializations;

    public AllProfilesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllProfilesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllProfilesFragment newInstance(String param1, String param2) {
        AllProfilesFragment fragment = new AllProfilesFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_profiles, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ProfileAdapter profileAdapter = new ProfileAdapter(getContext(), profileList);
        recyclerView.setAdapter(profileAdapter);
        profileAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        profileList = new ArrayList<>();
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        int profileCount = sharedPreferences.getInt("profileCount", 0);

        for (int i = 0; i < profileCount; i++) {
            String name = sharedPreferences.getString("profile_" + i + "_name", "");
            String email = sharedPreferences.getString("profile_" + i + "_email", "");
            String specialization = sharedPreferences.getString("profile_" + i + "_specialization", "");

            if (name != null)
                profileList.add(new ProfileDataModel(name, email, specialization));
        }
    }
}