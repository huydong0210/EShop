package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.activity.CartActivity;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.activity.HomeActivity;
import com.group2.eshopfe.login.activity.LoginActivity;

public class UserInformation extends AppCompatActivity {
    ObjectMapper objectMapper = new ObjectMapper();
    ImageButton imageButtonUser, imageButtonHome, imageButtonCart, imageButtonLogout;
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

        imageButtonLogout = findViewById(R.id.imageButtonLogout);
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
        imageButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("authToken");
                editor.apply();
                Intent intent = new Intent(UserInformation.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}