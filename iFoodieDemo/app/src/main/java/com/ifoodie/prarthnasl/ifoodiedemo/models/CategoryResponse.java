package com.ifoodie.prarthnasl.ifoodiedemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ifoodie.prarthnasl.ifoodiedemo.utils.Strings;

import java.util.ArrayList;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class CategoryResponse {

    @SerializedName("Error")
    @Expose
    private Integer error;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Result")
    @Expose
    private ArrayList<CategoryData> result;

    @SerializedName("Success")
    @Expose
    private Boolean success;


    public Integer getError() {
        return error;
    }

    public void setError(Integer Error) {
        this.error = error;
    }

    public String getMessage() {
        return Strings.nullSafeString(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CategoryData> getResult() {
        return result == null ? new ArrayList<CategoryData>() : result;
    }

    public void setResult(ArrayList<CategoryData> result) {
        this.result = result;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
