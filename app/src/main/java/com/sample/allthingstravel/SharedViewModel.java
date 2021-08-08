package com.sample.allthingstravel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Day> selected = new MutableLiveData<Day>();

    public void select(Day day){
        System.out.println("In SharedView Model"+day.getSummary());
        selected.setValue(day);
    }

    public LiveData<Day> getSelected(){
        if(selected != null && selected.getValue() !=null){
            System.out.println("In SharedView Model get"+selected.getValue().getSummary());
        }else{
            System.out.println("NULL value ????");
        }
        return selected;
    }
}
