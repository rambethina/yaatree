package com.sample.yaatree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayItemRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.dayTxt.setText(dayList.get(position).getDayStr());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("in on click");
                System.out.println(dayList.get(position).getDayStr());
                Toast.makeText(context, dayList.get(position).getDayStr(), Toast.LENGTH_LONG);
//                model = new ViewModelProvider().get(SharedViewModel.class);
                model.select(dayList.get(position));
//                Fragment dayDetails = new DayDetails(dayList.get(position).getId());
//                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
//                    fragmentManager.popBackStack();
//                }
//                FragmentTransaction ft = fragmentManager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, dayDetails);
//                ft.replace()
//                ft.replace(R.id.nav_host_fragment, dayDetails);
//                ft.replace(R.id.home_fragment, dayDetails);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_home_to_fragment_day_details);

//                ft.addToBackStack(null);
//                ft.commit();

            }
        });
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
        private CardView parent;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("Initialize view holder");
            dayTxt = itemView.findViewById(R.id.day);
            imageView = itemView.findViewById(R.id.dayImage);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
