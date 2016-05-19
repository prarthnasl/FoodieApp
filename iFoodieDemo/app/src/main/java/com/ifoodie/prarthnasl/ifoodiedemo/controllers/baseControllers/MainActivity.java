package com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.category.CategoryListActivity;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private Toolbar toolbar;

    private EditText restaurantId;
    private Button submitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setListeners();
    }

    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        restaurantId = (EditText) findViewById(R.id.restaurant_id);
        submitButton = (Button) findViewById(R.id.submit_button);
    }

    private void setListeners() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(restaurantId)) {
                    Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
                    intent.putExtra("restaurant_id", restaurantId.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isNotEmpty(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.error_field_required));
            return false;
        }
        return true;
    }
}
