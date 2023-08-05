package com.group2.eshopfe.sign_up.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.group2.eshopfe.R;
import com.group2.eshopfe.login.activity.LoginActivity;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.sign_up.model.SignUpRequest;
import com.group2.eshopfe.sign_up.service.ApiSignUpService;
import com.group2.eshopfe.sign_up.service.SignUpService;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword, editTextEmail, editTextPhone;
    RelativeLayout btnSignUp;
    TextView  btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        addControls();
        addEvents();
    }
    public void addControls(){
        editTextUsername = findViewById(R.id.editTextSignUpUsername);
        editTextPassword = findViewById(R.id.editTextSignUpPassword);
        editTextEmail = findViewById(R.id.editTextSignUpEmail);
        editTextPhone = findViewById(R.id.editTextSignUpPhone);

        btnSignUp = findViewById(R.id.buttonSignUpSignUp);
        btnLogin = findViewById(R.id.buttonSignUpLogin);

    }
    public void addEvents(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }
    public void login(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void signUp(){
        SignUpRequest signUpRequest = new SignUpRequest(
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextEmail.getText().toString(),
                editTextPhone.getText().toString()
        );
        ResponseObject responseObject = SignUpService.signUp(signUpRequest);
        if (responseObject.getStatus().equals(ResponseObject.FAIL)){
            Toast.makeText(this, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            ApiSignUpService.apiSignUpService.register(signUpRequest).enqueue(new Callback<ResponseObject>() {
                @Override
                public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                    if (response.errorBody()!=null){
                        Toast.makeText(SignUpActivity.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseObject> call, Throwable t) {
                    Log.i("testSignUp","fail");

                }
            });
        }
    }
}