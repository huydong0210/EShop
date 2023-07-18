package com.group2.eshopfe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group2.eshopfe.R;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLogin extends AppCompatActivity {
    public static final String TAG = ActivityLogin.class.getSimpleName();
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    private Button btnRegister;
    private ProgressDialog pDialog;
//    UserLocalStore userLocalStore;

    /**
     * URL : URL_LOGIN
     * param : KEY_USERNAME KEY_PASSWORD
     */
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value input
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                // Call method
                loginAccount(username, password);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        edtUserName =  findViewById(R.id.txtEmail);
        edtPassWord =  findViewById(R.id.txtPass);
        btnLogin =  findViewById(R.id.btnDangnhap);
        btnRegister =  findViewById(R.id.btnDangki);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * Method login
     *
     * @param username
     * @param password result json
     */
    public void loginAccount(final String username, final String password) {

        if (checkEditText(edtUserName) && checkEditText(edtPassWord)) {
            pDialog.show();
            // if login successful
            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
            //intent.putExtra("login", account);
            startActivity(intent);
        }
    }

    /**
     * Check input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
}

