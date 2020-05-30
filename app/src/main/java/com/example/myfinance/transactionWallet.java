package com.example.myfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class transactionWallet extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_wallet);

        Spinner spinnerWallet, spinnerType;

        spinnerWallet = findViewById(R.id.selectWallet);
        spinnerType = findViewById(R.id.selectType_2);

        ArrayAdapter<CharSequence> adapter_Type = ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_wallet = ArrayAdapter.createFromResource(this, R.array.Wallet, android.R.layout.simple_spinner_item);

        adapter_Type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_wallet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerType.setAdapter(adapter_Type);
        spinnerWallet.setAdapter(adapter_wallet);

        spinnerType.setOnItemSelectedListener(this);
        spinnerWallet.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
