package com.ifoodie.prarthnasl.ifoodiedemo.controllers.baseControllers;

/**
 * Created by prarthnasl on 4/30/2016.
 */
public interface ServerResponseInterface {
    void onResponseSuccess(Object response);

    void onResponseFailure(Object response);
}
