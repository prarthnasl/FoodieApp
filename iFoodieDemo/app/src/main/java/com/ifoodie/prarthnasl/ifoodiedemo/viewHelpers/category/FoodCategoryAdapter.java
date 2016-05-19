package com.ifoodie.prarthnasl.ifoodiedemo.viewHelpers.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.category.CategoryListActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.models.CategoryData;

import java.util.ArrayList;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class FoodCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<CategoryData> categoryArrayList;
    private CategoryListActivity activity;

    public FoodCategoryAdapter(CategoryListActivity activity, ArrayList<CategoryData> categoryArrayList) {
        this.activity = activity;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemViewLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_category_layout, parent, false);
        return new FoodCategoryViewHolder(itemViewLayout, activity);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FoodCategoryViewHolder) holder).setup(activity, categoryArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public void setCategoryArrayList(ArrayList<CategoryData> categoryListingArrayList) {
        this.categoryArrayList = categoryListingArrayList;
    }
}

