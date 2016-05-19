package com.ifoodie.prarthnasl.ifoodiedemo.repo;

import android.app.Activity;

import com.google.gson.reflect.TypeToken;
import com.ifoodie.prarthnasl.ifoodiedemo.constants.Constants;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers.ServerResponseInterface;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers.ServerResponseListener;
import com.ifoodie.prarthnasl.ifoodiedemo.helpers.HttpClient;
import com.ifoodie.prarthnasl.ifoodiedemo.models.CategoryResponse;

import java.util.HashMap;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class CategoryRepo {

    Activity activity;
    ServerResponseInterface serverResponseInterface;
    private HttpClient httpClient;

    public CategoryRepo(Activity activity, ServerResponseInterface serverResponseInterface) {
        this.activity = activity;
        this.serverResponseInterface = serverResponseInterface;
        httpClient = new HttpClient();
    }

    public void getCategories(HashMap<String, String> params) {
        httpClient.request(
                HttpClient.RequestType.GET,
                Constants.ABSOLUTE_BASE_URL_GET_ITEMS,
                params,
                TypeToken.get(CategoryResponse.class),
                categoryResponseListener);
    }

    private ServerResponseListener categoryResponseListener = new ServerResponseListener() {
        @Override
        public void handleResponse(Object response) {
            CategoryResponse categoryResponse = (CategoryResponse) response;
            if (categoryResponse != null && categoryResponse.isSuccess()) {
                serverResponseInterface.onResponseSuccess(categoryResponse);
            } else {
                serverResponseInterface.onResponseFailure(categoryResponse);
            }
        }
    };
}