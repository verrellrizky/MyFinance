package com.example.myfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Adapter adapter;
    DataHelper dataHelper;
    Spinner spinner;
//TESTT BARUUUUUUUUUUU

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fabAddTrx);
        dataHelper = new DataHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, dataHelper.getData());
        recyclerView.setAdapter(adapter);
        //add = findViewById(R.id.btn_to_add);


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
