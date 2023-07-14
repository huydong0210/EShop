package com.group2.eshopfe.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.group2.eshopfe.R;

public class ActivityInfomation extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    AutoCompleteTextView autoCompleteTextView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        Anhxa();
        actionBar();
    }
    private void actionBar(){
        setSupportActionBar(toolbar);
        get
    }
    private void Anhxa(){
        toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        autoCompleteTextView = (AutoCompleteTextView)  findViewById(R.id.editSearch);
        imageView = (ImageView) findViewById(R.id.search);
    }
}