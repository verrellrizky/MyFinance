package com.example.myfinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context ctx;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parent, relativelayoutisi;
        CardView cardView;
        TextView date, income, expenses, txtIncome, txtExpenses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.relativeLayoutparent);
            relativelayoutisi = itemView.findViewById(R.id.relativelayoutContent);

            cardView = itemView.findViewById(R.id.cardview);

            date = itemView.findViewById(R.id.date);
            income = itemView.findViewById(R.id.income);
            expenses = itemView.findViewById(R.id.expense);
            txtIncome = itemView.findViewById(R.id.number_income);
            txtExpenses = itemView.findViewById(R.id.expense);

        }
    }
}
