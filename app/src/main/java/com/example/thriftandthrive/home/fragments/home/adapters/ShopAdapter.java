package com.example.thriftandthrive.home.fragments.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.response.Product;
import com.example.thriftandthrive.singleProductPage.SingleProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter <ShopAdapter.ShopViewholder>{
    List<Product> productDataList;
    LayoutInflater layoutInflater;
    Context context;
    Boolean isCart = false;
    CartItemClick cartItemclick;
    Boolean removeEnabled = true;


    public ShopAdapter(List<Product> productDataList, Context context, Boolean isCart) { //then yo type garney
        this.productDataList = productDataList;
        this.context = context;
       layoutInflater = LayoutInflater.from(context); //inflater vaneko tasney
        this.isCart = isCart;
   }



    public void setCartItemClick(CartItemClick cartItemClick) {
        this.cartItemclick = cartItemClick;
    }

    public void setRemoveEnabled(Boolean removeEnabled) {
        this.removeEnabled = removeEnabled;
    }



    @NonNull
    @Override
    public ShopViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isCart)
            return new ShopViewholder(layoutInflater.inflate(R.layout.item_cart, parent, false));
        else
            return new ShopViewholder(layoutInflater.inflate(R.layout.products, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewholder holder, int position) {
        holder.nameTV.setText(productDataList.get(position).getName());
        if(productDataList.get(position).getDiscountPrice() == null || productDataList.get(position).getDiscountPrice()== 0){
            holder.priceTv.setVisibility(View.GONE);
            holder.discountPrice.setText("Rs. "+productDataList.get(position).getPrice()+"");
        }
        else
            holder.discountPrice.setText("Rs. "+productDataList.get(position).getDiscountPrice()+"");
        holder.priceTv.setText("Rs. "+productDataList.get(position).getPrice()+"");

        Picasso.get().load(productDataList.get(position).getImages().get(0)).into(holder.productIV);
        holder.mainLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productPage = new Intent(context, SingleProductActivity.class);
                productPage.putExtra(SingleProductActivity.key, productDataList.get(holder.getAdapterPosition()));
                context.startActivity(productPage);
            }
        });
        if (isCart){
            if(removeEnabled)
                holder.removeCartIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartItemclick.onRemoveCart(holder.getAdapterPosition());
                    }
                });
            else {

                holder.removeCartIV.setVisibility(View.GONE);
                holder.mainLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                setMargins(holder.mainLL, 0, 0, 16, 0);
            }

        }
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    public class ShopViewholder extends RecyclerView.ViewHolder{
        ImageView productIV, removeCartIV;
        LinearLayout mainLL;
        TextView nameTV, priceTv, discountPrice, discountPercent;
        public ShopViewholder(View itemView){
            super(itemView);
            productIV = itemView.findViewById(R.id.productIV);
            nameTV = itemView.findViewById(R.id.productNameTV);
            priceTv = itemView.findViewById(R.id.oldPriceTV);
            discountPrice = itemView.findViewById(R.id.discountPriceTV);
            mainLL = itemView.findViewById(R.id.mainLL);

            if (isCart){
                removeCartIV = itemView.findViewById(R.id.removeCartIV);

            }
        }
    }

    public interface CartItemClick {
        public void onRemoveCart(int position);
    }


}

//    @Override
//    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
//        holder.nameTV.setText(productDataList.get(position).getName());
//        if(productDataList.get(position).getDiscountPrice() == null || productDataList.get(position).getDiscountPrice()== 0){
//            holder.priceTv.setVisibility(View.GONE);
//            holder.discountPrice.setText(productDataList.get(position).getPrice()+"");
//        }
//        else
//            holder.discountPrice.setText(productDataList.get(position).getDiscountPrice()+"");
//        holder.priceTv.setText(productDataList.get(position).getPrice()+"");
//
//        Picasso.get().load(productDataList.get(position).getImages().get(0)).into(holder.productIV);
//
//    }
