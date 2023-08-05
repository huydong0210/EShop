package com.group2.eshopfe.cart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group2.eshopfe.R;

public class OrderActivity extends AppCompatActivity {
    Button buttonOrderConfirm, buttonOrderBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        addControls();
        addEvents();
    }

    public void addControls() {
        buttonOrderConfirm = findViewById(R.id.buttonOrderConfirm);
        buttonOrderBack = findViewById(R.id.buttonOrderBack);

    }

    public void addEvents() {
        buttonOrderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonOrderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}