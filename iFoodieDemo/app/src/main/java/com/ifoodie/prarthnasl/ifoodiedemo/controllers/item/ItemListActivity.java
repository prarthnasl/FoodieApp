package com.ifoodie.prarthnasl.ifoodiedemo.controllers.item;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.ProgressBar;

import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers.BaseActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.models.CategoryData;
import com.ifoodie.prarthnasl.ifoodiedemo.models.Item;
import com.ifoodie.prarthnasl.ifoodiedemo.viewHelpers.item.ItemAdapter;

import java.util.ArrayList;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class ItemListActivity extends BaseActivity {
    public static final String TAG = "ItemListActivity";

    private RecyclerView recyclerView;
    private CategoryData categoryData;
    private ArrayList<Item> items = new ArrayList<>();
    private ItemAdapter itemAdapter;

    private ProgressBar spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity_layout);

        categoryData = getIntent().getParcelableExtra("category_data");
        items = categoryData.getItems();

        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setRecyclerView() {
        spinner = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemAdapter = new ItemAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
    }
}
