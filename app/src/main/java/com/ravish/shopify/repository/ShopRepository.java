package com.ravish.shopify.repository;


import com.ravish.shopify.model.Shop;
import com.ravish.shopify.networking.ShopsApiInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class ShopRepository {

    private ShopsApiInterface apiInterface;

    @Inject
    public ShopRepository(ShopsApiInterface apiInterface){
        this.apiInterface = apiInterface;
    }

    public Call<List<Shop>> getAllShops(){
        return apiInterface.getAllShops();
    }
}
