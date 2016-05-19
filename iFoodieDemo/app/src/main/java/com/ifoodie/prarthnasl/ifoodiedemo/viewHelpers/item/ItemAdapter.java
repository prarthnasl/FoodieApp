package com.ifoodie.prarthnasl.ifoodiedemo.viewHelpers.item;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.item.ItemListActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.models.Item;

import java.util.ArrayList;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ItemListActivity activity;

    private ArrayList<Item> items;

    private View itemLayoutView;

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ItemAdapter(Activity activity, ArrayList<Item> items) {
        this.items = items;
        this.activity = (ItemListActivity) activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (items.get(position) != null) {
            ((ItemViewHolder) viewHolder).setup(items.get(position), activity);
        }
        itemLayoutView.setVisibility(items.get(position) == null ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

