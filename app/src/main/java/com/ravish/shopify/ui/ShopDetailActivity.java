package com.ravish.shopify.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ravish.shopify.R;
import com.ravish.shopify.adapter.ProductListAdapter;
import com.ravish.shopify.model.Shop;

public class ShopDetailActivity extends AppCompatActivity {

    private Shop shop;
    private TextView shopName;
    private TextView address;
    private TextView description;
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private ImageView maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        shop = getIntent().getParcelableExtra("Shop");

        shopName = findViewById(R.id.tv_shop_name);
        address = findViewById(R.id.tv_shop_address);
        description = findViewById(R.id.tv_shop_description);
        maps = findViewById(R.id.img_maps);

        recyclerView = findViewById(R.id.products_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        shopName.setText(shop.getName());
        address.setText(shop.getAddress());
        description.setText(shop.getDescription());
        adapter = new ProductListAdapter(this, shop.getProducts());
        recyclerView.setAdapter(adapter);

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:0, 0?q=" + shop.getAddress());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }
}