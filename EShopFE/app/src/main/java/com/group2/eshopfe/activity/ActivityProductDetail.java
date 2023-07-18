package com.group2.eshopfe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.group2.eshopfe.R;
import com.group2.eshopfe.modul.Product;

import java.text.DecimalFormat;

public class ActivityProductDetail extends AppCompatActivity {
private ImageView imageViewDetail;
private TextView textViewNameDetail, textViewPriceDetail, textViewDescribe;
private Spinner spinnerDetail;
private Button buttonBuy;

private int IdTypeDetail=0;
private String NameDetail="";
private int PriceDetail=0;
private String ImageDetail="";
private String Describle="";
private int Id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();
        ClickBackButton();
        ClickBuyButton();
        getInfomation();
        CatchEventSpiner();
    }

    private void getInfomation() {
        Product product = (Product) getIntent().getSerializableExtra("ProductInfo");
        IdTypeDetail =product.getIdType();
        NameDetail=product.getNameProduct();
        PriceDetail=product.getPriceProduct();
        ImageDetail=product.getImageProduct();
        Describle=product.getDetailsProduct();
        Id=product.getIdProduct();
        textViewNameDetail.setText(NameDetail);
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        textViewPriceDetail.setText("Giá: "+ decimalFormat.format(PriceDetail)+ "VNĐ");
        textViewPriceDetail.setText(Describle);
    }

    private void ClickBuyButton() {
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityCard.class);
                startActivity(intent);
            }
        });
    }

    private void ClickBackButton() {
        ImageView btnBack = findViewById(R.id.btn_back_ctsp);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void Mapping() {
        imageViewDetail = findViewById(R.id.imagechitietsanpham);
        textViewNameDetail = findViewById(R.id.textviewtenchitietsanpham);
        textViewPriceDetail = findViewById(R.id.textviewgiasanpham);
        textViewDescribe = findViewById(R.id.textviewmotachitietsanpham);
        buttonBuy = findViewById(R.id.buttondatmua);
        spinnerDetail = findViewById(R.id.spiner);
    }
    private void CatchEventSpiner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter =new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong );
        spinnerDetail.setAdapter(arrayAdapter);
    }
}