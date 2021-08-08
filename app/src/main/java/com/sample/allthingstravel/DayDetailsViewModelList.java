package com.sample.allthingstravel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.GeoPoint;
import com.google.type.LatLng;

import java.util.ArrayList;
import java.util.List;

public class DayDetailsViewModelList extends ViewModel {

    private final MutableLiveData<ArrayList<DayDetailsModel>> pointsOfInterest = new MutableLiveData<ArrayList<DayDetailsModel>>();

    public void select(ArrayList day){
        pointsOfInterest.setValue(day);
    }

    public LiveData<ArrayList<DayDetailsModel>> getSelected(){
        return pointsOfInterest;
    }
}
