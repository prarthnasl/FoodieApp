package com.ifoodie.prarthnasl.ifoodiedemo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ifoodie.prarthnasl.ifoodiedemo.utils.Strings;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class Item implements Parcelable {
    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Price")
    @Expose
    private String price;

    @SerializedName("Type")
    @Expose
    private String type;

    public String getDescription() {
        return Strings.nullSafeString(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Strings.nullSafeString(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Strings.nullSafeString(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return Strings.nullSafeString(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.type);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.description = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readString();
        this.type = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
