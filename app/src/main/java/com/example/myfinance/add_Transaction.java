package com.example.myfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

public class add_Transaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button addWallet, addTransaction;
    EditText editAmount, editNotes;
    DataHelper mdb;
    Calendar calendar;
    TextView datenow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__transaction);

        addWallet = findViewById(R.id.btn_add_Wallet);
        addTransaction = findViewById(R.id.btn_add);

        editAmount = findViewById(R.id.editAmount);
        editNotes = findViewById(R.id.editNotes);

        calendar = Calendar.getInstance();
        String dateNow = DateFormat.getDateInstance().format(calendar.getTime());

        datenow = findViewById(R.id.datenow);

        datenow.setText(dateNow);

        Spinner spinner;

        spinner = findViewById(R.id.selectType);

        mdb = new DataHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        addWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransactionWallet();
            }
        });

        calendar = Calendar.getInstance();

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    public void addData(){
        Spinner spinner;
        spinner = findViewById(R.id.selectType);
        final String selected = spinner.getSelectedItem().toString();

        boolean isInserted = mdb.insertData(
                datenow.getText().toString(),
                selected,
                editAmount.getText().toString(),
                editNotes.getText().toString());

        if(isInserted == true){
            Toast.makeText(add_Transaction.this, "data inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(add_Transaction.this, "data not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void openTransactionWallet(){
        Intent intent = new Intent(this, transactionWallet.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}