package com.sample.allthingstravel;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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
    List<DayDetailsModel> dayDetailsList = new ArrayList<>();
    DayDetailsRecyclerViewAdapter dayDetailsRecyclerViewAdapter;
    ArrayList<DayDetailsModel> mapPoints = new ArrayList<>();
//    LatLongList latLongList = new LatLongList();
    DayDetailsViewModelList dayDetailsViewModelList;

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

        FloatingActionButton floatingActionButton = view.findViewById(R.id.goToMapsButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("In click listener :"+mapPoints.size());
                dayDetailsViewModelList = new ViewModelProvider(getActivity()).get(DayDetailsViewModelList.class);
                dayDetailsViewModelList.select(mapPoints);
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_day_details_to_map);
            }
        });
        List<DayDetailsModel> dayDetailsList = new ArrayList<>();
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            initializeDaysFromFireStore(view.getContext(), item.getDocumentId());
           recyclerView = view.findViewById(R.id.dayDetailsRecyclerView);
           recyclerView.setHasFixedSize(true);
//           recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            dayDetailsRecyclerViewAdapter = new DayDetailsRecyclerViewAdapter(view.getContext());
           dayDetailsRecyclerViewAdapter.setDayDetailsList(dayDetailsList);
            recyclerView.setAdapter(dayDetailsRecyclerViewAdapter);

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_details, container, false);
    }

    public void initializeDaysFromFireStore(Context context, String documentId){
        FirebaseApp.initializeApp(context);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("trip_itinerary/"+documentId+"/trip_details")
                .orderBy("day_sort")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            Long id = (long)documentSnapshot.getData().get("day_group");
                            String title = (String)documentSnapshot.getData().get("title");
                            String time = (String)documentSnapshot.getData().get("time");
                            String description = (String)documentSnapshot.getData().get("description");
                            String imageUrl = (String)documentSnapshot.getData().get("image_url");
                            String category = (String)documentSnapshot.getData().get("category");
                            GeoPoint latLng = documentSnapshot.getGeoPoint("lat_long");
//                            System.out.println("Lat long is "+latLng.getLongitude());

//                            String category = (String)documentSnapshot.getGeoPoint("lat_long").get("category");
                            DayDetailsModel dayDetailsModel = new DayDetailsModel(id, title, description, time, imageUrl, category, latLng);
                            if(latLng !=null) {
                                System.out.println("Adding point");
                                mapPoints.add(dayDetailsModel);
                            }
                            dayDetailsList.add(dayDetailsModel);
                        }
                        if(dayDetailsRecyclerViewAdapter != null){
                            dayDetailsRecyclerViewAdapter.setDayDetailsList(dayDetailsList);

                        }
//                        latLongList.select(mapPoints);
                    }
                });

    }
}