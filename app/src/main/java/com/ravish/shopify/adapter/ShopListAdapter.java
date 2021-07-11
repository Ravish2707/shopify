package com.ravish.shopify.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravish.shopify.R;
import com.ravish.shopify.model.Shop;
import com.ravish.shopify.ui.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder> implements Filterable {

    private Context mContext;
    private List<Shop> shops = new ArrayList<>();
    private List<Shop> shopList;

    public void setShopsList(List<Shop> shops){
        this.shops = shops;
        shopList = new ArrayList<>(shops);
        notifyDataSetChanged();
    }

    public ShopListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ShopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_shop_view, parent, false);
        return new ShopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListViewHolder holder, int position) {
        Shop shop = shops.get(position);

        holder.name.setText(shop.getName());
        holder.address.setText(shop.getAddress());
        holder.productsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.productsRecyclerView.setHasFixedSize(true);

        ProductListAdapter adapter = new ProductListAdapter(mContext, shop.getProducts());
        holder.productsRecyclerView.setAdapter(adapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShopDetailActivity.class);

                Shop shop1 = new Shop();
                shop1.setName(shop.getName());
                shop1.setDescription(shop.getDescription());
                shop1.setAddress(shop.getAddress());
                shop1.setProducts(shop.getProducts());

                intent.putExtra("Shop", shop1);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Shop> filteredData = new ArrayList<>();

            if (constraint.toString().isEmpty()){
                filteredData.addAll(shopList);
            }else {
                for (Shop shop : shopList){
                    for (int i=0; i<shop.getProducts().size(); i++){
                        if (shop.getProducts().get(i).getName().toLowerCase().equals(constraint.toString().toLowerCase())){
                            filteredData.add(shop);
                        }
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            shops.clear();
            shops.addAll((List<Shop>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ShopListViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView address;
        private TextView products;
        private RecyclerView productsRecyclerView;

        public ShopListViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            address = itemView.findViewById(R.id.tv_address);
            products = itemView.findViewById(R.id.tv_products);
            productsRecyclerView = itemView.findViewById(R.id.products_recyclerview);
        }
    }
}
