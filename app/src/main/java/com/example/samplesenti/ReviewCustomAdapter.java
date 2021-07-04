package com.example.samplesenti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ReviewCustomAdapter extends RecyclerView.Adapter<ReviewCustomAdapter.CustomViewHolder> {
    private ArrayList<Review> arrayList;
    private Context context;
    //private String myUid;


    public ReviewCustomAdapter(ArrayList<Review> arrayList, ReviewRecyclerView mainActivity) {
        this.arrayList = arrayList;
        this.context = context;
       // myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //final String uid = arrayList.get(position).getMyUid();
        holder.tv_total.setText("총점 : " +String.valueOf(arrayList.get(position).getTotal()));
        holder.tv_time.setText("응답시간 : " +String.valueOf(arrayList.get(position).getTime()));
        holder.tv_trust.setText("신뢰도 : " +String.valueOf(arrayList.get(position).getTrust()));
        holder.tv_sentence.setText("한줄평 : " +String.valueOf(arrayList.get(position).getReview()));

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView tv_total;
        TextView tv_time;
        TextView tv_trust;
        TextView tv_sentence;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_total = itemView.findViewById(R.id.tv_total);
            this.tv_time = itemView.findViewById(R.id.tv_time);
            this.tv_trust = itemView.findViewById(R.id.tv_trust);
            this.tv_sentence = itemView.findViewById(R.id.tv_sentence);

        }
    }
}
