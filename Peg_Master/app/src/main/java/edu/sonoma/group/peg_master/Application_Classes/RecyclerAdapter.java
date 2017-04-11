package edu.sonoma.group.peg_master.Application_Classes;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sonoma.group.peg_master.R;


/**
 * Created by jordanbergero on 4/10/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] islands = {"Island One", "Island Two", "Island Three", "Island Four", "Island Five",
            "Island Six", "Island Seven", "Island Eight", "Island Nine", "Island Ten", "Island Eleven", "Island Twelve",
            "Island Thirteen", "Island Fourteen", "Island Fifteen", "Island Sixteen", "Island Seventeen", "Island Eighteen",
            "Island NineTeen", "Island Twenty", "Island Twenty-One", "Island Twenty-Two", "Island Twenty-Three", "Island Twenty-Four",
            "Island Twenty-Five", "Island Twenty-Six", "Island Twenty-Seven", "Island Twenty-Eight",
            "Island Twenty-Nine", "Island Thirty", "Island Thirty-One", "Island Thirty-Two"};

    private int[] images = {R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,
            R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,
            R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,
            R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,
            R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,
            R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,
            R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,R.drawable.gamechestdemo,};


    class ViewHolder extends RecyclerView.ViewHolder {
        public int currentItem;
        public ImageView itemImage;
        public TextView itemIsland;


        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemIsland = (TextView)itemView.findViewById(R.id.item_island);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

    }
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_view_overworld, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position) {

            viewHolder.itemIsland.setText(islands[position]);
            viewHolder.itemImage.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return islands.length;
        }
    }
