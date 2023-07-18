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
import com.group2.eshopfe.adapter.HousewearAdapter;
import com.group2.eshopfe.modul.Product;

import java.util.ArrayList;

public class ActivityHouseware extends AppCompatActivity {

    private ListView listViewHousewear;
    private ArrayList<Product> arrayListHousewear;
    private HousewearAdapter adapterHousewear;
    private View footerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housewear);
        ConnectAdapter();
        //getData();
        LoadMoreData();
        BackButton();
    }

    private void BackButton() {
        ImageView btnBack = findViewById(R.id.btn_back_dgd);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void ConnectAdapter(){
        listViewHousewear = findViewById(R.id.listviewdogiadung);
        arrayListHousewear = new ArrayList<>();
        adapterHousewear = new HousewearAdapter(getApplicationContext(),arrayListHousewear);
        listViewHousewear.setAdapter(adapterHousewear);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.loading,null);
    }
    private void LoadMoreData(){
        listViewHousewear.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ActivityProductDetail.class);
                intent.putExtra("ProductInfo",arrayListHousewear.get(i));
                startActivity(intent);
            }
        });
    }
}