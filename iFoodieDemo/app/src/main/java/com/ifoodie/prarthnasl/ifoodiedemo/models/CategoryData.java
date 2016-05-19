package com.ifoodie.prarthnasl.ifoodiedemo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ifoodie.prarthnasl.ifoodiedemo.utils.Strings;

import java.util.ArrayList;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class CategoryData implements Parcelable {

    @SerializedName("ButtonTheme")
    @Expose
    private String buttonTheme;

    @SerializedName("Font")
    @Expose
    private String font;

    @SerializedName("HeaderTheme")
    @Expose
    private String headerTheme;

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Image")
    @Expose
    private String image;

    @SerializedName("IsBasic")
    @Expose
    private String isBasic;

    @SerializedName("Items")
    @Expose
    private ArrayList<Item> items = new ArrayList<Item>();

    @SerializedName("Logo")
    @Expose
    private String logo;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("RestDescription")
    @Expose
    private String restDescription;

    @SerializedName("Tax")
    @Expose
    private String tax;

    public String getButtonTheme() {
        return Strings.nullSafeString(buttonTheme);
    }

    public void setButtonTheme(String buttonTheme) {
        this.buttonTheme = buttonTheme;
    }

    public String getFont() {
        return Strings.nullSafeString(font);
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getHeaderTheme() {
        return Strings.nullSafeString(headerTheme);
    }

    public void setHeaderTheme(String headerTheme) {
        this.headerTheme = headerTheme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return Strings.nullSafeString(image);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsBasic() {
        return Strings.nullSafeString(isBasic);
    }

    public void setIsBasic(String isBasic) {
        this.isBasic = isBasic;
    }

    public ArrayList<Item> getItems() {
        return items == null ? new ArrayList<Item>() : items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getLogo() {
        return Strings.nullSafeString(logo);
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return Strings.nullSafeString(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestDescription() {
        return Strings.nullSafeString(restDescription);
    }

    public void setRestDescription(String restDescription) {
        this.restDescription = restDescription;
    }

    public String getTax() {
        return Strings.nullSafeString(tax);
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.buttonTheme);
        dest.writeString(this.font);
        dest.writeString(this.name);
        dest.writeString(this.headerTheme);
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.isBasic);
        dest.writeTypedList(this.items);
        dest.writeString(this.logo);
        dest.writeString(this.name);
        dest.writeString(this.restDescription);
        dest.writeString(this.tax);
    }

    public CategoryData() {}

    protected CategoryData(Parcel in) {
        this.buttonTheme = in.readString();
        this.font = in.readString();
        this.name = in.readString();
        this.headerTheme = in.readString();
        this.id = in.readInt();
        this.image = in.readString();
        this.isBasic = in.readString();
        this.items = in.createTypedArrayList(Item.CREATOR);
        this.logo = in.readString();
        this.name = in.readString();
        this.restDescription = in.readString();
        this.tax = in.readString();
    }

    public static final Creator<CategoryData> CREATOR = new Creator<CategoryData>() {
        public CategoryData createFromParcel(Parcel source) {
            return new CategoryData(source);
        }

        public CategoryData[] newArray(int size) {
            return new CategoryData[size];
        }
    };
}

