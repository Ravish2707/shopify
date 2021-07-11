package com.ravish.shopify.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ravish.shopify.model.Shop;
import com.ravish.shopify.repository.ShopRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ShopViewModel extends ViewModel {

    private ShopRepository repository;
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<List<Shop>> allShopsList = new MutableLiveData<>();

    @Inject
    public ShopViewModel(ShopRepository repository){
        this.repository = repository;
    }

    public void getAllShops(){
        repository.getAllShops().enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                try {
                    if (response.body() != null){
                        allShopsList.postValue(response.body());
                    }
                }catch (Exception e){
                    errorMessage.postValue(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                errorMessage.postValue(t.getMessage());
            }
        });
    }

    public LiveData<List<Shop>> getAllShopsList(){
        return allShopsList;
    }

    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }
}
