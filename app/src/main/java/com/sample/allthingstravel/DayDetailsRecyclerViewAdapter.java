package com.sample.allthingstravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DayDetailsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DayDetailsModel> dayDetailsList = new ArrayList<DayDetailsModel>();
    private Context context;

    public DayDetailsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDayDetailsList(List dayDetailsList){
        this.dayDetailsList = dayDetailsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_simple_list_item, parent, false);
                return new ViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_poi_list_item, parent, false);
                return new POIViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_simple_list_item, parent, false);
                return new ViewHolder(view);
        }
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_simple_list_item, parent, false);
//        return new ViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }

    @Override
    public int getItemViewType(int position) {
        System.out.println(dayDetailsList.get(position).toString());
        String category = dayDetailsList.get(position).getCategory();
        System.out.println(category);
        switch (category) {
            case "simple" :
                return 1;
            case "poi" :
                return 2;
            default:
                return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder0;

        switch (holder.getItemViewType()) {
            case 1:
                viewHolder0 = (ViewHolder)holder;
                viewHolder0.title.setText(dayDetailsList.get(position).getTitle());
                viewHolder0.time.setText(dayDetailsList.get(position).getTime());
                viewHolder0.description.setText(dayDetailsList.get(position).getDescription());
                break;
            case 2:
                POIViewHolder poiViewHolder = (POIViewHolder)holder;
                poiViewHolder.title.setText(dayDetailsList.get(position).getTitle());
                poiViewHolder.time.setText(dayDetailsList.get(position).getTime());
                poiViewHolder.description.setText(dayDetailsList.get(position).getDescription());
                //poiImageUrl
                System.out.println(dayDetailsList.get(position).getImageUrl());
                Glide.with(context)
                .asBitmap()
                .load(dayDetailsList.get(position).getImageUrl())
                .into(poiViewHolder.imageView);
                break;
            default:
                viewHolder0 = (ViewHolder)holder;
                viewHolder0.title.setText(dayDetailsList.get(position).getTitle());
                viewHolder0.time.setText(dayDetailsList.get(position).getTime());
                viewHolder0.description.setText(dayDetailsList.get(position).getDescription());
                break;
        }
//        holder.title.setText(dayDetailsList.get(position).getTitle());
//        holder.time.setText(dayDetailsList.get(position).getTime());
//        holder.description.setText(dayDetailsList.get(position).getDescription());
//        Glide.with(context)
//                .asBitmap()
//                .load(dayDetailsList.get(position).getImageUrl())
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dayDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView time;
        private TextView description;
//        private CardView parent;
//        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            time = itemView.findViewById(R.id.timeTxt);
            description = itemView.findViewById(R.id.dayDescriptionTxt);
//            parent = itemView.findViewById(R.id.cardViewDayDetails);
//            imageView = itemView.findViewById(R.id.dayDetailsImage);
        }
    }

    public class POIViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView time;
        private TextView description;
        private ImageView imageView;

        public POIViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            time = itemView.findViewById(R.id.timeTxt);
            description = itemView.findViewById(R.id.dayDescriptionTxt);
            imageView = itemView.findViewById(R.id.poiImageUrl);
        }
    }
}
