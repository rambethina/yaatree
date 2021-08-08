package com.sample.allthingstravel;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    DayItemRecyclerViewAdapter dayItemRecyclerViewAdapter;
    List<Day> dayList = new ArrayList<Day>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        System.out.println("initialize home fragment");
//        List<Day> dayList = new ArrayList<Day>();
//        dayList.add(new Day("Day1 - Jackson downtown", "https://tile.loc.gov/image-services/iiif/service:pnp:highsm:38200:38295/full/pct:6.25/0/default.jpg", 1));
//        dayList.add(new Day("Day2 - Jenny lake", "https://images.world-of-waterfalls.com/Hidden_Falls_Jenny_Lake_078_08132017.jpg", 2));
//        dayList.add(new Day("Day3 - Taggart Lake", "https://www.planetware.com/photos-large/USWY/wyoming-grand-teton-national-park-taggart-lake-trail.jpg", 3));
//        dayList.add(new Day("Day4", "https://assets3.thrillist.com/v1/image/2873537/414x310/crop;jpeg_quality=65.jpg", 4));
//        dayList.add(new Day("Day5", "data:image/webp;base64,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", 5));
//        dayList.add(new Day("Day6", "https://assets3.thrillist.com/v1/image/2873537/414x310/crop;jpeg_quality=65.jpg", 6));
//        dayList.add(new Day("Day7", "https://assets3.thrillist.com/v1/image/2873537/414x310/crop;jpeg_quality=65.jpg", 7));
//        dayList.add(new Day("Day8", "https://assets3.thrillist.com/v1/image/2873537/414x310/crop;jpeg_quality=65.jpg", 8));
//        dayList.add(new Day("Day9", "https://assets3.thrillist.com/v1/image/2873537/414x310/crop;jpeg_quality=65.jpg", 9));
        initializeDaysFromFireStore(getContext());

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        dayItemRecyclerViewAdapter = new DayItemRecyclerViewAdapter(view.getContext(), model);
        dayItemRecyclerViewAdapter.setDayList(dayList);
        recyclerView.setAdapter(dayItemRecyclerViewAdapter);
        return view;
    }

    public void initializeDaysFromFireStore(Context context){
        FirebaseApp.initializeApp(context);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        System.out.println("reading from database");
        firebaseFirestore.collection("trip_itinerary")
                .orderBy("day")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            String documentId = documentSnapshot.getId();
                            System.out.println("document id :"+documentId);
                            Long id = (long)documentSnapshot.getData().get("trip_id");
                            Long day = (long)documentSnapshot.getData().get("day");
                            String tripDate = (String)documentSnapshot.getData().get("trip_date");
                            String summary = (String)documentSnapshot.getData().get("summary");
                            String imageUrl = (String)documentSnapshot.getData().get("image_url");
                            System.out.println(summary+" "+ imageUrl+" "+id);
                            dayList.add(new Day(documentId, summary, imageUrl, tripDate, day));
                        }
                        dayItemRecyclerViewAdapter.setDayList(dayList);
                    }
                });
//        if(dayItemRecyclerViewAdapter!=null){
//            dayItemRecyclerViewAdapter.setDayList(dayList);
//        }
    }

}