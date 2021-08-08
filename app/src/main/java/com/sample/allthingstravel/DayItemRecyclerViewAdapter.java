package com.sample.allthingstravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DayItemRecyclerViewAdapter extends RecyclerView.Adapter<DayItemRecyclerViewAdapter.ViewHolder> {
    List<Day> dayList = new ArrayList<Day>();
    private Context context;
    private SharedViewModel model;
    private int option = 2;

    public DayItemRecyclerViewAdapter(Context context, SharedViewModel model){
        this.context = context;
        this.model = model;
    }

    public void setDayList(List dayList){
        this.dayList = dayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DayItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(option == 1) {
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_list_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itenary_list_item, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayItemRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.dayTxt.setText("DAY 0"+dayList.get(position).getId());
        holder.dateStrTxt.setText(dayList.get(position).getTripDate());
        holder.dayDescriptionTxt.setText(dayList.get(position).getSummary());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("in on click");
                System.out.println(dayList.get(position).getSummary());
                Toast.makeText(context, dayList.get(position).getSummary(), Toast.LENGTH_LONG);
                model.select(dayList.get(position));
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_home_to_fragment_day_details);
            }
        });
//        if(dayList.get(position).getImageURL() != null && dayList.get(position).getImageURL().length() > 0){
//            Glide.with(context)
//                    .asBitmap()
//                    .load(dayList.get(position).getImageURL())
//                    .into(holder.imageView);
//        }
                    Glide.with(context)
                    .asBitmap()
                    .load(dayList.get(position).getImageURL())
                    .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView dayTxt;
        private TextView dateStrTxt;
        private CardView parent;
        private ImageView imageView;
        private TextView dayDescriptionTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("Initialize view holder");
            dayTxt = itemView.findViewById(R.id.dayTxt);
            dateStrTxt = itemView.findViewById(R.id.dateStrTxt);
            dayDescriptionTxt = itemView.findViewById(R.id.dayDescriptionTxt);
            imageView = itemView.findViewById(R.id.dayImageUrl);
            if(option == 1){
                parent = itemView.findViewById(R.id.parent);
            } else{
                parent = itemView.findViewById(R.id.itenaryCardListView);
            }
        }
    }
}
