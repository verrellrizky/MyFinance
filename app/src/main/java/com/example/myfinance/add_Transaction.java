package com.example.myfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class add_Transaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button addTransaction, selectDate;
    EditText editAmount, editNotes;
    DataHelper mdb;
    Calendar calendar;
    DatePicker datePicker;
    TextView date;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__transaction);
        radioGroup = findViewById(R.id.rg_wallet);
        addTransaction = findViewById(R.id.btn_add);
        selectDate = findViewById(R.id.btnCalendar);

        editAmount = findViewById(R.id.editAmount);
        editNotes = findViewById(R.id.editNotes);

        date = findViewById(R.id.txtDate);


        Spinner spinner;

        spinner = findViewById(R.id.selectType);

        mdb = new DataHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(add_Transaction.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String selectedDate = + day + "/" + month + "/" + year;
                                date.setText(selectedDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

    }


    public void addData(){
        Spinner spinner;
        spinner = findViewById(R.id.selectType);
        final String selected = spinner.getSelectedItem().toString();
        String selDate = date.getText()+"";
        String amount = editAmount.getText().toString();
        String notes = editNotes.getText().toString();
        String walletChosen = "";
        int walletId = radioGroup.getCheckedRadioButtonId();
        if(walletId == -1){
            Toast.makeText(add_Transaction.this, "Please input wallet type", Toast.LENGTH_LONG).show();
            return;
        }
        if(walletId != -1){
            RadioButton radioButton = findViewById(walletId);
            walletChosen = radioButton.getText().toString();
        }
        if(selDate.equals("") || selDate.equals("Transaction Date")){
            Toast.makeText(add_Transaction.this, "Please input transaction date", Toast.LENGTH_LONG).show();
            return;
        }
        if(selected.equals("")){
            Toast.makeText(add_Transaction.this, "Please input transaction type", Toast.LENGTH_LONG).show();
            return;
        }
        if(amount.equals("")){
            Toast.makeText(add_Transaction.this, "Please input transaction amount", Toast.LENGTH_LONG).show();
            return;
        }

        boolean isInserted = mdb.insertData(selDate, selected, amount, notes, walletChosen);
        if(isInserted){
            Toast.makeText(add_Transaction.this, "data inserted", Toast.LENGTH_LONG).show();
            Intent toMain = new Intent(add_Transaction.this, MainActivity.class);
            startActivity(toMain);
            finish();
        }
        else {
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