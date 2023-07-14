package com.group2.eshopfe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group2.eshopfe.R;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static class ActivityCustomer extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_customer);
        }
    }
}