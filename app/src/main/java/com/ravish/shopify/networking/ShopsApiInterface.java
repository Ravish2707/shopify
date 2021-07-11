package com.ravish.shopify.networking;

import com.ravish.shopify.constants.ConstantsUrls;
import com.ravish.shopify.model.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopsApiInterface {

    @GET(ConstantsUrls.END_POINT_URL)
    Call<List<Shop>> getAllShops();

}
