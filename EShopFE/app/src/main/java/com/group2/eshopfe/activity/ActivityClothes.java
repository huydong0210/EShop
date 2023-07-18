package com.group2.eshopfe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group2.eshopfe.R;
import com.group2.eshopfe.adapter.ClothesAdapter;
import com.group2.eshopfe.adapter.FootwearAdapter;
import com.group2.eshopfe.modul.Product;

import java.util.ArrayList;

public class ActivityClothes extends AppCompatActivity {
    private ListView listViewClothes;
    private ArrayList<Product> arrayListClothes;

    private ClothesAdapter adapterClothes;
    private View footerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        ConnectAdapter();
        // getData();
        LoadMoreData();
        BackButton();
    }
    private void BackButton() {
        ImageView btnBack = findViewById(R.id.btn_back_qa);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void LoadMoreData() {
        listViewClothes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ActivityProductDetail.class);
                intent.putExtra("ProductInfo",arrayListClothes.get(i));
                startActivity(intent);
            }
        });
    }

    private void ConnectAdapter() {
        listViewClothes = findViewById(R.id.listviewao);
        arrayListClothes = new ArrayList<>();
        adapterClothes = new ClothesAdapter(getApplicationContext(),arrayListClothes);
        listViewClothes.setAdapter(adapterClothes);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.loading,null);
    }
}
