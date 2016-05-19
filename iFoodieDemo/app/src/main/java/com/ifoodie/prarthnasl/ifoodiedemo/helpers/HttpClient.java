package com.ifoodie.prarthnasl.ifoodiedemo.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.ifoodie.prarthnasl.ifoodiedemo.IFoodieApplication;
import com.ifoodie.prarthnasl.ifoodiedemo.constants.Constants;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers.ServerResponseListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class HttpClient {

    public enum RequestType {
        GET,
        GET_FULL_URL;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getClient() {
        return client;
    }

    public void request(RequestType requestType, String relativeUrl, HashMap<String, String> params, final TypeToken responseObjectType, final ServerResponseListener serverResponseListener) {

        if (!isNetworkConnected()) {
            Toast.makeText(IFoodieApplication.getContext(), "Seems like you are offline.", Toast.LENGTH_SHORT).show();
            serverResponseListener.handleResponse(null);
            return;
        }

        RequestParams requestParams = createRequestParams(params);
        client.setURLEncodingEnabled(true);

        switch (requestType) {
            case GET_FULL_URL:
                client.get(relativeUrl, requestParams, new TextHttpResponseHandler(responseObjectType, serverResponseListener));
                break;
            case GET:
                client.get(Constants.ABSOLUTE_BASE_URL_GET_ITEMS, requestParams, new TextHttpResponseHandler(responseObjectType, serverResponseListener));
                break;

        }
    }

    private RequestParams createRequestParams(HashMap<String, String> params) {
        RequestParams requestParams = new RequestParams();

        for (String key : params.keySet()) {
            String value = params.get(key);
            requestParams.add(key, value);
        }

        return requestParams;
    }

    private String getAbsoluteUrl(String relativeUrl) {
        String absoluteUrl;
        absoluteUrl = Constants.ABSOLUTE_BASE_URL + relativeUrl;

        return absoluteUrl;
    }

    class TextHttpResponseHandler extends com.loopj.android.http.TextHttpResponseHandler {
        private TypeToken responseObjectType;
        private ServerResponseListener serverResponseListener;

        public TextHttpResponseHandler(TypeToken responseObjectType, ServerResponseListener serverResponseListener) {
            super();
            this.responseObjectType = responseObjectType;
            this.serverResponseListener = serverResponseListener;
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String response) {

            if (responseObjectType == null) {
                serverResponseListener.handleResponse(response);
            } else {
                parseResponseAndCallServerResponseHandler(response, responseObjectType, serverResponseListener);
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
            serverResponseListener.handleResponse(null);
        }
    }

    public void parseResponseAndCallServerResponseHandler(String response, TypeToken responseObjectType, ServerResponseListener serverResponseListener) {
        if (response != null) {
            Gson gson = new GsonBuilder().create();
            JsonReader reader = new JsonReader(new StringReader(response));

            try {
                Object responseObject = gson.fromJson(reader, responseObjectType.getType());
                serverResponseListener.handleResponse(responseObject);
            } catch (JsonSyntaxException jse) {
                jse.printStackTrace();
                serverResponseListener.handleResponse(null);
                return;
            } catch (Exception e) {
                e.printStackTrace();

                serverResponseListener.handleResponse(null);
            }
        } else {
            serverResponseListener.handleResponse(null);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) com.ifoodie.prarthnasl.ifoodiedemo.IFoodieApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
