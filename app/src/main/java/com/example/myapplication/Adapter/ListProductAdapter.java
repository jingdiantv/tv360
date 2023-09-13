package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Banner;
import com.example.myapplication.Model.HomeModel;
import com.example.myapplication.R;

import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {
    private List<Banner> categoryList;
    private  DetailFilmListener detailFilmListener;
    public void setData(Context context,List<Banner> list){
        this.categoryList = list;
        try {
            this.detailFilmListener = ((DetailFilmListener)context) ;
        }catch (ClassCastException ex)
        {
            throw new ClassCastException(ex.getMessage());
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_product, parent,false);
        return new ListProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {
        Banner category = categoryList.get(position);
        if (category == null){
            return;
        }
        Glide.with(holder.itemView.getContext()).load(category.getCoverImage()).into(holder.imgCategory);
//        holder.imgCategory.setImageResource(category.getImgFilm());
        holder.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id",category.getId());
                intent.putExtra("type",category.getType());
                detailFilmListener.detailFilmListener(intent);
            }

        });
    }



    @Override
    public int getItemCount() {
        if(categoryList != null) {
            return categoryList.size();
        }
        return 0;
    }

    public static class ListProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCategory;

        public ListProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.listviewfilm);
        }

    }
    public  interface  DetailFilmListener
    {
        public  void  detailFilmListener(Intent intent);
    }

}
