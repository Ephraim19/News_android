package com.eph.news.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eph.news.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    public String[] news;
    Context context;
    public NewsAdapter(Context context,String[] news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_news,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        holder.MyText1.setText(news[position]);
        Log.v("qqqq", news[position]);
    }

    @Override
    public int getItemCount() {
        return news.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView MyText1;
        CheckBox MyText2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            MyText1 = itemView.findViewById(R.id.Text);
//            myText2 = itemView.findViewById(R.id.images);
        }
    }
}












