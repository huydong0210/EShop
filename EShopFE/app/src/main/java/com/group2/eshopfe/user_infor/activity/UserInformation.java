package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.UserDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.activity.CartActivity;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.common.Utils;
import com.group2.eshopfe.home.activity.HomeActivity;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.login.activity.LoginActivity;
import com.group2.eshopfe.payload.ResponseObject;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInformation extends AppCompatActivity {
    ObjectMapper objectMapper = new ObjectMapper();
    ImageButton imageButtonUser, imageButtonHome, imageButtonCart, imageButtonLogout;
    TextView textViewUserName, textViewUserEmail, textViewUserPhone;
    View viewUserLogout, viewShipmentDetails;
    ImageView imageViewUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getCurrentUserDTO", "success");
                if (response.errorBody()!=null){
                    Intent intent =new Intent(UserInformation.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        UserDTO userDTO = objectMapper.readValue(response.body().getData().toString(), UserDTO.class);
                        textViewUserName.setText(userDTO.getUsername());
                        textViewUserEmail.setText(userDTO.getEmail());
                        textViewUserPhone.setText(userDTO.getPhone());

                        imageViewUserAvatar.setImageBitmap(Utils.convertBytesToBitMap(userDTO.getImage()));
                    } catch (Exception e){

                    }

                }

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.i("getCurrentUserDTO", t.toString());

            }
        });ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getCurrentUserDTO", "success");

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.i("getCurrentUserDTO", t.toString());

            }
        });

        addControls();
        addEvents();
    }
    private void addControls(){
        imageButtonHome = findViewById(R.id.imageButtonHomeUser);
        imageButtonUser = findViewById(R.id.imageButtonUserUser);
        imageButtonCart = findViewById(R.id.imageButtonUserCart);

        viewUserLogout = findViewById(R.id.viewUserLogOut);
        viewShipmentDetails = findViewById(R.id.viewShipmentDetails);

        textViewUserName = findViewById(R.id.textViewUserName);
        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewUserPhone =findViewById(R.id.textViewUserPhone);

        imageViewUserAvatar = findViewById(R.id.imageViewUserAvatar);

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

        viewUserLogout.setOnClickListener(new View.OnClickListener() {
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
        viewShipmentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(UserInformation.this, ShipmentDetailsActivity.class);
                startActivity(intent);
            }
        });



    }
}