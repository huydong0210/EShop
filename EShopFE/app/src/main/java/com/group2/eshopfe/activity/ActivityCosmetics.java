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
import com.group2.eshopfe.adapter.CometicsAdapter;
import com.group2.eshopfe.modul.Product;

import java.util.ArrayList;

public class ActivityCosmetics extends AppCompatActivity {
    private ListView listViewCometics;
    private ArrayList<Product> arrayListCometics;

    private CometicsAdapter adapterCometics;
    private View footerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetics);
        ConnectAdapter();
        // getData();
        LoadMoreData();
        BackButton();
    }
    private void ConnectAdapter(){
        listViewCometics = findViewById(R.id.listviewmypham);
        arrayListCometics = new ArrayList<>();
        adapterCometics = new CometicsAdapter(getApplicationContext(),arrayListCometics);
        listViewCometics.setAdapter(adapterCometics);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.loading,null);
    }
    private void LoadMoreData(){
        listViewCometics.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ActivityProductDetail.class);
                intent.putExtra("ProductInfo",arrayListCometics.get(i));
                startActivity(intent);
            }
        });
    }
    private void BackButton(){
        ImageView btnBack = findViewById(R.id.btn_back_mp);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}