package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Banner;
import com.example.myapplication.R;

import java.util.List;

public class BannerAdapter  extends PagerAdapter {
    Context context ;
    private List<Banner> items;
    private OnItemClickListener onItemClickListener;

    public BannerAdapter(Context context, List<Banner> items) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return this.items.size();
    }
    public Banner getItem(int pos) {
        return items.get(pos);
    }

    public void setItems(List<Banner> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    private interface OnItemClickListener {
        void onItemClick(View view, Banner obj);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Banner o = items.get(position);
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_slider, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.image);
        CardView lyt_parent = (CardView) v.findViewById(R.id.lyt_parent);
        //Utils.displayImageOriginal(act, image, o.image);
//        image.setImageResource(o.image);
        Glide.with(container.getContext()).load(o.getCoverImage()).into(image);
        lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, o);
                }
            }
        });
        ((ViewPager) container).addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
