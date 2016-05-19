package com.ifoodie.prarthnasl.ifoodiedemo.utils;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public class Strings {

    public static final String EMPTY = "";

    public static String nullSafeString(String s) {
        return s == null ? EMPTY : s;
    }

    public static String getFormattedPrice(String value) {
        if (value != null && !value.isEmpty()) {
            return String.format("₹%s", value);
        }
        return Strings.EMPTY;
    }
}