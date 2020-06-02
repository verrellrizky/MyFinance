package com.example.myfinance;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private OnRecycleClickListener mListener;

    public interface OnRecycleClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnRecycleClickListener listener){
        mListener = listener;
    }

    Context ctx;
    Cursor res;

    public Adapter(Context ctx, Cursor res) {
        this.ctx = ctx;
        this.res = res;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!res.moveToPosition(position)){
            return;
        }
        String type = res.getString(res.getColumnIndex(DataHelper.COL_3));
        String amount = res.getString(res.getColumnIndex(DataHelper.COL_4));
        String date = res.getString(res.getColumnIndex(DataHelper.COL_2));
        String notes = res.getString(res.getColumnIndex(DataHelper.COL_5));
        String wallet = res.getString(res.getColumnIndex(DataHelper.COL_6));
        holder.date.setText(date);
        holder.notes.setText(notes);
        holder.wallet.setText(wallet);
        if(type.equals("Income")){
            holder.amount.setText(amount);
            holder.amount.setTextColor(Color.parseColor("#05a605"));
        }
        else if(type.equals("Expense")){
            holder.amount.setText(amount);
            holder.amount.setTextColor(Color.parseColor("#a60505"));
        }
    }

    @Override
    public int getItemCount() {
        return res.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parent, relativelayoutisi;
        CardView cardView;
        TextView date, amount, wallet, notes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.relativeLayoutparent);
            relativelayoutisi = itemView.findViewById(R.id.relativelayoutContent);

            cardView = itemView.findViewById(R.id.cardview);

            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            wallet = itemView.findViewById(R.id.wallet);
            notes = itemView.findViewById(R.id.notes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
