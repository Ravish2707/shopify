package com.ravish.shopify.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ravish.shopify.R;
import com.ravish.shopify.adapter.ShopListAdapter;
import com.ravish.shopify.model.Shop;
import com.ravish.shopify.viewModel.ShopViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ShopViewModel mViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ShopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.shop_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ShopListAdapter(this);
        recyclerView.setAdapter(adapter);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        mViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        getAllShops();
    }

    private void getAllShops(){
        mViewModel.getAllShops();

        mViewModel.getAllShopsList().observe(this, new Observer<List<Shop>>() {
            @Override
            public void onChanged(List<Shop> shops) {
                adapter.setShopsList(shops);
                progressBar.setVisibility(View.GONE);
            }
        });

        mViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item =menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

}