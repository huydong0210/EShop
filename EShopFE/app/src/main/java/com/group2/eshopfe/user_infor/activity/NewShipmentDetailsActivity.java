package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.group2.eshopfe.R;

public class NewShipmentDetailsActivity extends AppCompatActivity {
    Button buttonNewShipmentDetailsSave, buttonNewShipmentDetailsBack;
    EditText editTextNewShipmentDetailsName, editTextNewShipmentDetailsPhone, editTextNewShipmentDetailsAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shipment_details);
        addControls();
        addEvents();

    }

    public void addControls() {
        editTextNewShipmentDetailsName = findViewById(R.id.editTextNewShipmentDetailsName);
        editTextNewShipmentDetailsAddress = findViewById(R.id.editTextNewShipmentDetailsAddress);
        editTextNewShipmentDetailsPhone = findViewById(R.id.editTextNewShipmentDetailsPhone);

        buttonNewShipmentDetailsSave = findViewById(R.id.buttonNewShipmentDetailsSave);
        buttonNewShipmentDetailsBack = findViewById(R.id.buttonNewShipmentDetailsBack);


    }

    public void addEvents() {
        buttonNewShipmentDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(NewShipmentDetailsActivity.this, ShipmentDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}