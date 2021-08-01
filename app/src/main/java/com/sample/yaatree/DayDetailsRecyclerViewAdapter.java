package com.sample.yaatree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DayDetailsRecyclerViewAdapter extends RecyclerView.Adapter<DayDetailsRecyclerViewAdapter.ViewHolder> {

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
    public DayDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_details_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayDetailsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(dayDetailsList.get(position).getTitle());
        Glide.with(context)
                .asBitmap()
                .load(dayDetailsList.get(position).getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dayDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private CardView parent;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            parent = itemView.findViewById(R.id.cardViewDayDetails);
            imageView = itemView.findViewById(R.id.dayDetailsImage);
        }
    }
}
