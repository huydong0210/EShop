package com.group2.eshopfe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.group2.eshopfe.R;

public class ActivityRegister extends AppCompatActivity {
    public static final String TAG = ActivityRegister.class.getSimpleName();

    private EditText edtUserName;
    private EditText edtPassWord;
    private EditText edtEmail;
    private Button btnRegister;
    private Button btnLogin;
    private ProgressDialog pDialog;


    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }
    private void addEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get data input
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                //Call method register
                // registerUser(username, password, email);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {

        edtUserName =  findViewById(R.id.txtNameDK);
        edtPassWord =  findViewById(R.id.txtPassDK);
        btnRegister =  findViewById(R.id.btnDangkiDK);
        btnLogin =  findViewById(R.id.btnDangnhapDK);
        edtEmail =  findViewById(R.id.txtEmailDK);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * Method register
     *
     * @param username
     * @param password
     * @param email    result json
     */
    private void registerUser(final String username, final String password, final String email) {

    }

    /**
     * Check Input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    /**
     * Check Email
     */
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            edtEmail.setError("Email sai định dạng!");
        }
        return false;
    }
}