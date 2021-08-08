package com.sample.allthingstravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.Iterator;

public class MapsFragment extends Fragment {

    ArrayList<DayDetailsModel> points = new ArrayList<DayDetailsModel>();

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng currentLatLng;
            System.out.println("onMapReady"+points.size());
            Iterator iterator = points.iterator();
            while (iterator.hasNext()){
                DayDetailsModel dayDetailsModel = (DayDetailsModel) iterator.next();
//                System.out.println("ON map ready"+ dayDetailsModel.getLongitude());
                currentLatLng = new LatLng(dayDetailsModel.getLatLng().getLatitude(), dayDetailsModel.getLatLng().getLongitude());
                googleMap.addMarker(new MarkerOptions().position(currentLatLng).title(dayDetailsModel.getTitle()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 10));
//                CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
//                googleMap.animateCamera(zoom);
            }
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DayDetailsViewModelList model = new ViewModelProvider(getActivity()).get(DayDetailsViewModelList.class);
        model.getSelected().observe(getViewLifecycleOwner(), item -> {
//            Iterator iterator = item.iterator();
//            while(iterator.hasNext()){
//
//            }
            System.out.println("Setting map ....");
            points = item;
        });
        System.out.println("In maps fragment on create view");
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DayDetailsViewModelList model = new ViewModelProvider(getActivity()).get(DayDetailsViewModelList.class);
        model.getSelected().observe(getViewLifecycleOwner(), item -> {
//            Iterator iterator = item.iterator();
//            while(iterator.hasNext()){
//
//            }
            System.out.println("Setting map ....");
            points = item;
        });
        System.out.println("In maps fragment on view created");
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}