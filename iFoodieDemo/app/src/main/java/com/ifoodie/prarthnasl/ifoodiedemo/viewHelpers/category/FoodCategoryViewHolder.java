package com.ifoodie.prarthnasl.ifoodiedemo.viewHelpers.category;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.constants.Constants;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.category.CategoryListActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.item.ItemListActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.models.CategoryData;

/**
 * Created by prarthnasl on 4/30/2016.
 */

class FoodCategoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView foodCategoryImage;
    private TextView foodCategoryText;
    private RelativeLayout foodCategoryLayout;

    public FoodCategoryViewHolder(View itemView, Activity activity) {
        super(itemView);
        foodCategoryImage = (ImageView) itemView.findViewById(R.id.food_category_image);
        foodCategoryText = (TextView) itemView.findViewById(R.id.food_category_text);
        foodCategoryLayout = (RelativeLayout) itemView.findViewById(R.id.food_category_parent_layout);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) foodCategoryLayout.getLayoutParams();
        //layoutParams.height = getImageHeight(activity, 0);
        foodCategoryLayout.setLayoutParams(layoutParams);
    }

    public void setup(final CategoryListActivity activity, final CategoryData categoryData) {
        Glide.with(activity).load(String.format(Constants.ABSOLUTE_BASE_URL + categoryData.getImage())).into(foodCategoryImage);
        foodCategoryText.setText(categoryData.getName());
        foodCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ItemListActivity.class);
                intent.putExtra("category_data", categoryData);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);
            }
        });
    }
}
