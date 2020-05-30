package com.example.myfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    Button add;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.btn_to_add);


        add.setOnClickListener(new View.OnClickListener() {
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
