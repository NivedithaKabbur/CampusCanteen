package com.trianz.campuscanteen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Niveditha.Kabbur on 4/27/2017.
 */

public class CampusCafeAdapter extends RecyclerView.Adapter<CampusCafeAdapter.MyViewHolder> {

    private List<CampusCafe> campusCafeList;
    Context context;

    public CampusCafeAdapter(Context context, List<CampusCafe> campusCafeList) {

        this.context = context;
        this.campusCafeList = campusCafeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cafe_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.bindContent(campusCafeList.get(position));

    }

    @Override
    public int getItemCount() {
        return campusCafeList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cafe_name;
        public ImageView cafe_image;

        public MyViewHolder(View view) {
            super(view);
            cafe_name = (TextView) view.findViewById(R.id.cafe_item_name);
            cafe_image = (ImageView) view.findViewById(R.id.cafe_item_image);
        }

        public void bindContent(CampusCafe campusCafe) {

            if(!campusCafe.getCafename().equals("null")) {
                cafe_name.setText(campusCafe.getCafename());
            }

            Picasso.with(context).setLoggingEnabled(true);
            Picasso.with(context).load(campusCafe.getCafeUrl()).fit().into(cafe_image);

        }
    }
}
