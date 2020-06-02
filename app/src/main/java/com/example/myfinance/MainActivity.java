package com.example.myfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Adapter adapter;
    DataHelper dataHelper;
    Spinner spinner;
    ArrayList<Integer> tempId;
//TEST BARU BANGETT ANJ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempId = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fabAddTrx);
        dataHelper = new DataHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, dataHelper.getData());
        recyclerView.setAdapter(adapter);

        Cursor cursor = dataHelper.getData();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DataHelper.COL_1));
            tempId.add(id);
        }

        adapter.setItemClickListener(new Adapter.OnRecycleClickListener() {
            @Override
            public void onItemClick(int position) {

                String pos = String.valueOf(tempId.get(position));
                Cursor c = dataHelper.getSpecificData(pos);
                if(c.moveToFirst()){

                    String id = c.getString(c.getColumnIndex(DataHelper.COL_1));
                    String date = c.getString(c.getColumnIndex(DataHelper.COL_2));
                    String type = c.getString(c.getColumnIndex(DataHelper.COL_3));
                    String amount = c.getString(c.getColumnIndex(DataHelper.COL_4));
                    String notes = c.getString(c.getColumnIndex(DataHelper.COL_5));
                    String wallet = c.getString(c.getColumnIndex(DataHelper.COL_6));
                    Intent toEdit = new Intent(MainActivity.this, EditTransaction.class);
                    toEdit.putExtra("leId", id);
                    toEdit.putExtra("leDate", date);
                    toEdit.putExtra("leType", type);
                    toEdit.putExtra("leAmount", amount);
                    toEdit.putExtra("leWallet", wallet);
                    toEdit.putExtra("leNotes", notes);

                    startActivity(toEdit);
                }
                else{
                    Toast.makeText(MainActivity.this, "hm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_addTransaction();
            }
        });
    }

    public void to_addTransaction() {
        Intent intent = new Intent(this, add_Transaction.class);
        startActivity(intent);
    }

}
