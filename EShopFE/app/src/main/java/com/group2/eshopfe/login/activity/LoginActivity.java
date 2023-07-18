package com.group2.eshopfe.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group2.eshopfe.R;
import com.group2.eshopfe.api.ApiService;
import com.group2.eshopfe.login.model.LoginRequest;
import com.group2.eshopfe.login.service.LoginService;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnSignUp;
    EditText editTextUsername, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }
    private void addEvents(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }
    private void addControls(){
        btnLogin = findViewById(R.id.buttonLoginLogin);
        btnSignUp = findViewById(R.id.buttonLoginSignUp);

        editTextUsername =findViewById(R.id.editTextLoginUsername);
        editTextPassword = findViewById(R.id.editTextLoginPassword);
    }
    private void login(){
        LoginRequest loginRequest = new LoginRequest(editTextUsername.getText().toString(), editTextPassword.getText().toString());
        ResponseObject response = LoginService.login(loginRequest);
        if (response.getStatus().equals(ResponseObject.FAIL)){
            Toast toast = Toast.makeText(this, response.getMessage(),Toast.LENGTH_SHORT);
            toast.show();
        } else {
            ApiService.apiService.authenticate(loginRequest).enqueue(new Callback<ResponseObject>() {
                @Override
                public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                    if (response.errorBody() != null){
                        Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseObject> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    private void signUp(){

    }


}