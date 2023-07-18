package com.group2.eshopfe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.group2.eshopfe.R;
import com.group2.eshopfe.adapter.HatAdapter;
import com.group2.eshopfe.modul.Product;

import java.util.AbstractList;
import java.util.ArrayList;

public class ActivityHat extends AppCompatActivity {
    private ListView listViewHat;
    private ArrayList<Product> arrayListHat;

    private HatAdapter adapterHat;
    private View footerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hat);
        ConnectAdapter();
        // getData();
        LoadMoreData();
        BackButton();
    }
    private void ConnectAdapter(){
        listViewHat = findViewById(R.id.listviewmu);
        arrayListHat = new ArrayList<>();
        adapterHat = new HatAdapter(getApplicationContext(),arrayListHat);
        listViewHat.setAdapter(adapterHat);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.loading,null);
    }
    private void LoadMoreData(){
        listViewHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ActivityProductDetail.class);
                intent.putExtra("ProductInfo",arrayListHat.get(i));
                startActivity(intent);
            }
        });
    }
    private void BackButton(){
        ImageView btnBack = findViewById(R.id.btn_back_mu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}