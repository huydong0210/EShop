package com.group2.eshopfe.cart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.group2.eshopfe.R;
import com.group2.eshopfe.home.activity.HomeActivity;
import com.group2.eshopfe.user_infor.activity.UserInformation;

public class CartActivity extends AppCompatActivity {
    ImageButton imageButtonCartHome, imageButtonCartCart, imageButtonCartUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        addControls();
        addEvents();
    }

    private void addControls() {
        imageButtonCartHome = findViewById(R.id.imageButtonCartHome);
        imageButtonCartCart = findViewById(R.id.imageButtonCartCart);
        imageButtonCartUser = findViewById(R.id.imageButtonCartUser);

    }

    private void addEvents() {
        imageButtonCartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        imageButtonCartUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });
    }
}