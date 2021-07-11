package com.ravish.shopify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ravish.shopify.R;
import com.ravish.shopify.model.Product;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>{

    private Context mContext;
    private List<Product> products;

    public ProductListAdapter(Context mContext, List<Product> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_product_view, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_product_name);
        }
    }
}
