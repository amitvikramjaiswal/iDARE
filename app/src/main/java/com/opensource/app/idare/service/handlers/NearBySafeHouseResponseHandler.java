package com.opensource.app.idare.service.handlers;

import com.opensource.app.idare.data.entities.NearBySafeHouseListEntity;

/**
 * Created by ajaiswal on 4/5/2016.
 */
public interface NearBySafeHouseResponseHandler {

    void onSuccess(NearBySafeHouseListEntity nearBySafeHouses);

    void onError(Exception exception);

}
