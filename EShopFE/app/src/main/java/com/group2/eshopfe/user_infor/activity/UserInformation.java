package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.activity.CartActivity;
import com.group2.eshopfe.home.activity.HomeActivity;

public class UserInformation extends AppCompatActivity {
    ObjectMapper objectMapper = new ObjectMapper();
    ImageButton imageButtonUser, imageButtonHome, imageButtonCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        addControls();
        addEvents();
    }
    private void addControls(){
        imageButtonHome = findViewById(R.id.imageButtonCartHome);
        imageButtonUser = findViewById(R.id.imageButtonCartUser);
        imageButtonCart = findViewById(R.id.imageButtonCartCart);
    }
    private void addEvents(){
        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformation.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        imageButtonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(UserInformation.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }
}