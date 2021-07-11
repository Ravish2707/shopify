package com.ravish.shopify.di;

import com.ravish.shopify.constants.ConstantsUrls;
import com.ravish.shopify.networking.ShopsApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){

        return new Retrofit.Builder()
                .baseUrl(ConstantsUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ShopsApiInterface provideShopsApiInterface(Retrofit retrofit){
        return retrofit.create(ShopsApiInterface.class);
    }
}
