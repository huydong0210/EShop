package com.group2.eshopfe.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.R;
import com.group2.eshopfe.DTO.ProductDTO;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.common.Utils;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeProductAdapter extends ArrayAdapter<ProductDTO> {
    private Activity context;
    private int resource;

    public HomeProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View homeProductItemView = layoutInflater.inflate(this.resource, null);

        ImageView imageViewHomeProduct= homeProductItemView.findViewById(R.id.imageViewHomeProduct);
        TextView textViewHomeProductPrice= homeProductItemView.findViewById(R.id.textViewHomeProductName);
        TextView textViewHomeProductProductName = homeProductItemView.findViewById(R.id.textViewHomeProductProductPrice);
        Button buttonHomeProductAddToCart = homeProductItemView.findViewById(R.id.buttonCartOrderDetailsDeleteItem);


        ProductDTO sp = getItem(position);
        textViewHomeProductProductName.setText(sp.getProductName());
        textViewHomeProductPrice.setText(sp.getPrice() + "");

        imageViewHomeProduct.setImageBitmap(Utils.convertBytesToBitMap(sp.getImage()));

        buttonHomeProductAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewOrderDetailsInCartButtonHandler(sp.getId());
            }
        });

        return homeProductItemView;
    }
    public void addNewOrderDetailsInCartButtonHandler(Long id){
        ApiHomeServiceImpl.getInstances().setSharedPreferences(this.context.getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().addNewOrderDetailsInCart(id).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody() ==null) {
                    toast("Thêm sản phẩm vào giỏ hàng thành công");
                } else {
                    toast("Thêm sản phẩm vào giỏ hàng thất bại ");
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });

    }
    public void toast(String message){
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
    }
}
