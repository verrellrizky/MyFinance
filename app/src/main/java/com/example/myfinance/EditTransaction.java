package com.example.myfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class EditTransaction extends AppCompatActivity {
    Button editTransaction, selectDate;
    EditText editAmount, editNotes;
    DataHelper mdb;
    Calendar calendar;
    DatePicker datePicker;
    TextView date;
    RadioGroup radioGroup;
    String id;
    Spinner spinner;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        deleteData();
        return super.onOptionsItemSelected(item);
    }

    private void deleteData() {
        if(mdb.deleteData(id) > 0){
            Toast.makeText(EditTransaction.this, "Delete success", Toast.LENGTH_LONG).show();
            Intent toMain = new Intent(EditTransaction.this, MainActivity.class);
            startActivity(toMain);
        }
        else{
            Toast.makeText(EditTransaction.this, "Delete unsuccessful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);
        editTransaction = findViewById(R.id.btn_edit);
        selectDate = findViewById(R.id.btnCalendar);
        editAmount = findViewById(R.id.editAmount);
        editNotes = findViewById(R.id.editNotes);
        mdb = new DataHelper(this);
        date = findViewById(R.id.txtDate);
        radioGroup = findViewById(R.id.rg_wallet);
        spinner = findViewById(R.id.selectType);
        Intent fromMain = getIntent();
        id = fromMain.getStringExtra("leId");
        String strDate = fromMain.getStringExtra("leDate");
        String strType = fromMain.getStringExtra("leType");
        String strAmount = fromMain.getStringExtra("leAmount");
        String strNotes = fromMain.getStringExtra("leNotes");
        String strWallet = fromMain.getStringExtra("leWallet");

        selectDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTransaction.this,
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

        if(strWallet.equals("Bank")){
            radioGroup.check(R.id.bank);
        }
        else if(strWallet.equals("Cash")){
            radioGroup.check(R.id.cash);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(strType.equals("Income")){
            spinner.setSelection(0);
        }
        else{
            spinner.setSelection(1);
        }

        date.setText(strDate);
        editNotes.setText(strNotes);
        editAmount.setText(strAmount);
        editTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner;
                spinner = findViewById(R.id.selectType);
                final String selected = spinner.getSelectedItem().toString();
                String selDate = date.getText()+"";
                String amount = editAmount.getText().toString();
                String notes = editNotes.getText().toString();
                String walletChosen = "";
                int walletId = radioGroup.getCheckedRadioButtonId();

                if(walletId == -1){
                    Toast.makeText(EditTransaction.this, "Please input wallet type", Toast.LENGTH_LONG).show();
                    return;
                }
                if(walletId != -1){
                    RadioButton radioButton = findViewById(walletId);
                    walletChosen = radioButton.getText().toString();
                }
                if(selDate.equals("") || selDate.equals("Transaction Date")){
                    Toast.makeText(EditTransaction.this, "Please input transaction date", Toast.LENGTH_LONG).show();
                    return;
                }
                if(selected.equals("")){
                    Toast.makeText(EditTransaction.this, "Please input transaction type", Toast.LENGTH_LONG).show();
                    return;
                }
                if(amount.equals("")){
                    Toast.makeText(EditTransaction.this, "Please input transaction amount", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isUpdated = mdb.updateData(id, selDate, selected, amount, notes, walletChosen);

                if(isUpdated){
                    Toast.makeText(EditTransaction.this, "data updated", Toast.LENGTH_LONG).show();
                    Intent toMain = new Intent(EditTransaction.this, MainActivity.class);
                    startActivity(toMain);
                    finish();
                }
                else {
                    Toast.makeText(EditTransaction.this, "data not updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
