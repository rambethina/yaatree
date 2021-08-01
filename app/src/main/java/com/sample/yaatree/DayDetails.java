package com.sample.yaatree;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DayDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int id;

    public DayDetails(int id) {
        // Required empty public constructor
        this.id = id;
    }

    public DayDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DayDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static DayDetails newInstance(String param1, String param2) {
        DayDetails fragment = new DayDetails(1);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<DayDetailsModel> dayDetailsList = new ArrayList<>();
        dayDetailsList.add(new DayDetailsModel(1, "Test Title", "Test description", "morning", "https://www.planetware.com/photos-large/USWY/wyoming-grand-teton-national-park-taggart-lake-trail.jpg"));
        dayDetailsList.add(new DayDetailsModel(2, "Test Title2", "Test description", "morning", "https://www.planetware.com/photos-large/USWY/wyoming-grand-teton-national-park-taggart-lake-trail.jpg"));
        System.out.println("In onViewCreated");
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            System.out.println("In observe method ***");
            System.out.println("IN Day details Item passed is :"+item.getDayStr());
           recyclerView = view.findViewById(R.id.dayDetailsRecyclerView);
           recyclerView.setHasFixedSize(true);
           recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
           DayDetailsRecyclerViewAdapter dayDetailsRecyclerViewAdapter = new DayDetailsRecyclerViewAdapter(view.getContext());
           dayDetailsRecyclerViewAdapter.setDayDetailsList(dayDetailsList);
            recyclerView.setAdapter(dayDetailsRecyclerViewAdapter);

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("In Day details passed id is :"+id);
//        SharedViewModel model = new ViewModelProvider(this).get(SharedViewModel.class);
//        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        model.getSelected().observe(getViewLifecycleOwner(), item -> {
//            System.out.println("In observe method ***");
//            System.out.println("IN Day details Item passed is :"+item.getDayStr());
//        });
        return inflater.inflate(R.layout.fragment_day_details, container, false);
    }
}