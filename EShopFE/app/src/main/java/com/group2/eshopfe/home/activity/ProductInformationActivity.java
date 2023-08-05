package com.group2.eshopfe.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.Mapper;
import com.group2.eshopfe.DTO.ProductDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.common.Utils;
import com.group2.eshopfe.home.service.ApiHomeService;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInformationActivity extends AppCompatActivity {
    ObjectMapper objectMapper = new ObjectMapper();
    ImageView imageViewProductInformationImage;
    TextView textViewProductInformationName, textViewProductInformationPrice, textViewProductInformationQuantity, textViewProductInformationDescription;
    Button buttonProductInformationAddToCart, buttonProductInformationHome;
    ProductDTO productDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        Intent intent =getIntent();
        Long productId = intent.getLongExtra("productID",0);
        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getProductDTOByID(productId).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody() == null){
                    try {
                        ProductDTO product = objectMapper.readValue(response.body().getData().toString(), ProductDTO.class);
                        imageViewProductInformationImage.setImageBitmap(Utils.convertBytesToBitMap(product.getImage()));
                        textViewProductInformationName.setText(product.getProductName());
                        textViewProductInformationPrice.setText(product.getPrice() + "");
                        textViewProductInformationQuantity.setText("Số lượng còn lại : " + product.getInventory());
                        textViewProductInformationDescription.setText(product.getDescription());

                    }
                    catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });

        addControls();
        addEvents();

    }

    public void addControls() {
        imageViewProductInformationImage = findViewById(R.id.imageViewProductInforImage);
        textViewProductInformationName = findViewById(R.id.textViewProductInforName);
        textViewProductInformationPrice = findViewById(R.id.textViewProductInforPrice);
        textViewProductInformationQuantity = findViewById(R.id.textViewProductInforQuantity);
        textViewProductInformationDescription = findViewById(R.id.textViewProductInforDescription);

        buttonProductInformationAddToCart = findViewById(R.id.buttonProductInforAddToCart);
        buttonProductInformationHome = findViewById(R.id.buttonProductInforHome);
    }

    public void addEvents() {
        buttonProductInformationHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProductInformationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}