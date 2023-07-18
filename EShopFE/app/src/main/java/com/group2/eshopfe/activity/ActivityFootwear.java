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
import com.group2.eshopfe.adapter.FootwearAdapter;
import com.group2.eshopfe.modul.Product;

import java.util.ArrayList;

public class ActivityFootwear extends AppCompatActivity {

    private ListView listViewFootwear;
    private ArrayList<Product> arrayListFootwear;
    private FootwearAdapter adapterFootwear;
    private View footerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footwear);
        ConnectAdapter();
        // getData();
        LoadMoreData();
        BackButton();
    }

    private void BackButton() {
        ImageView btnBack = findViewById(R.id.btn_back_dep);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void LoadMoreData() {
        listViewFootwear.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ActivityProductDetail.class);
                intent.putExtra("ProductInfo",arrayListFootwear.get(i));
                startActivity(intent);
            }
        });
    }

    private void ConnectAdapter() {
        listViewFootwear = findViewById(R.id.listviewdep);
        arrayListFootwear = new ArrayList<>();
        adapterFootwear = new FootwearAdapter(getApplicationContext(),arrayListFootwear);
        listViewFootwear.setAdapter(adapterFootwear);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.loading,null);
    }
}