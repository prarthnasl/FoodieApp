package com.ifoodie.prarthnasl.ifoodiedemo.controllers.category;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.constants.Constants;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers.BaseActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers.ServerResponseInterface;
import com.ifoodie.prarthnasl.ifoodiedemo.models.CategoryData;
import com.ifoodie.prarthnasl.ifoodiedemo.models.CategoryResponse;
import com.ifoodie.prarthnasl.ifoodiedemo.repo.CategoryRepo;
import com.ifoodie.prarthnasl.ifoodiedemo.viewHelpers.category.FoodCategoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class CategoryListActivity extends BaseActivity implements ServerResponseInterface {

    public static final String TAG = "CategoryListActivity";

    private RecyclerView foodCategoryRecyclerView;
    private FoodCategoryAdapter foodCategoryAdapter;
    private ArrayList<CategoryData> categoryArrayList = new ArrayList<>();

    private String restaurantId;

    private SwipeRefreshLayout refreshLayout;
    private SmoothProgressBar smoothProgressBar;

    private TextView loadingTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_list_layout);

        restaurantId = getIntent().getStringExtra("restaurant_id");
        initUI();
        setListeners();

        if (categoryArrayList.size() == 0) {
            loadCategoryList();
        }
    }

    private void loadCategoryList() {
        smoothProgressBar.setVisibility(View.VISIBLE);
        loadingTextView.setVisibility(View.VISIBLE);
        CategoryRepo categoryRepo = new CategoryRepo(this, this);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.RESTAURANT_ID, restaurantId);
        categoryRepo.getCategories(params);
    }

    private void initUI() {
        loadingTextView = (TextView) findViewById(R.id.loading_text_view);

        smoothProgressBar = (SmoothProgressBar) findViewById(R.id.smooth_progress_bar);
        foodCategoryRecyclerView = (RecyclerView) findViewById(R.id.food_category_recycler_view);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
    }

    private void setListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryArrayList.clear();
                loadCategoryList();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void setRecyclerView() {
        foodCategoryAdapter = new FoodCategoryAdapter(this, categoryArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        foodCategoryRecyclerView.setLayoutManager(linearLayoutManager);
        foodCategoryRecyclerView.setAdapter(foodCategoryAdapter);
        foodCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onResponseSuccess(Object response) {
        smoothProgressBar.setVisibility(View.GONE);
        loadingTextView.setVisibility(View.GONE);
        CategoryResponse categoryResponse = (CategoryResponse) response;
        categoryArrayList.clear();
        for (CategoryData category : categoryResponse.getResult()) {
            if (category != null && !category.getId().toString().isEmpty()) {
                categoryArrayList.add(category);
            }
        }
        if (categoryArrayList.size() > 0) {
            foodCategoryAdapter.setCategoryArrayList(categoryArrayList);
            foodCategoryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseFailure(Object response) {
        Toast.makeText(CategoryListActivity.this, "There was an error processing your request. Try Again", Toast.LENGTH_SHORT).show();
        smoothProgressBar.setVisibility(View.GONE);
        loadingTextView.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecyclerView();
    }
}
