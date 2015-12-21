package com.orange.gws;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Edwin on 18/01/2015.
 */

public class ServicesCardAdapter extends RecyclerView.Adapter<ServicesCardAdapter.ViewHolder> {

    List<ServicesImageItem> mItems;

    public ServicesCardAdapter() {
        super();
        mItems = new ArrayList<ServicesImageItem>();
        ServicesImageItem nature = new ServicesImageItem();
        nature.setName("Computers and Laptops");
        nature.setDes("Repair for all your PCs, Notebooks, Laptops and the likes.");
        nature.setThumbnail(R.drawable.cp1);
        mItems.add(nature);

        nature = new ServicesImageItem();
        nature.setName("Hardware Upgrade");
        nature.setDes("In the era of evolving hardware, we upgrade all parts of your Personal Assisting Machines.");
        nature.setThumbnail(R.drawable.cp5);
        mItems.add(nature);

        nature = new ServicesImageItem();
        nature.setName("Software Solutions");
        nature.setDes("Installation, Removal and Configuration of software done at either your doorstep.");
        nature.setThumbnail(R.drawable.cp9);
        mItems.add(nature);

        nature = new ServicesImageItem();
        nature.setName("System Security");
        nature.setDes("All kinds of software and hardware viruses and spyware removal and ensuring robust systems at homes and enterprises");
        nature.setThumbnail(R.drawable.cp12);
        mItems.add(nature);


        nature = new ServicesImageItem();
        nature.setName("On-Site Services");
        nature.setDes("For small enterprises and business to business companies on the site service is provided with works including networking");
        nature.setThumbnail(R.drawable.cp10);
        mItems.add(nature);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ServicesImageItem nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getDes());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.tv_nature);
            tvDesNature = (TextView)itemView.findViewById(R.id.tv_des_nature);
        }
    }
}


