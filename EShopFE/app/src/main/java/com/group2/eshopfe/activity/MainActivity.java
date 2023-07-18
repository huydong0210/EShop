package com.group2.eshopfe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.group2.eshopfe.R;
import com.group2.eshopfe.adapter.ProductTypeAdapter;
import com.group2.eshopfe.modul.Cart;
import com.group2.eshopfe.modul.ProductType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ProductType> arrayListProductType;
    private ProductTypeAdapter productTypeAdapter;
    private ViewFlipper viewFlipper;
    public static ArrayList<Cart> arrayListCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}