package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private final ArrayList<Expense> expenses;

    public ExpenseAdapter(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.titleText.setText(expense.getTitle());
        holder.amountText.setText(
                holder.itemView.getContext().getString(R.string.amount_format, expense.getAmount())
        );
        holder.dateText.setText(expense.getDate());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Expense> newExpenses) {
        expenses.clear();
        expenses.addAll(newExpenses);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, amountText, dateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            amountText = itemView.findViewById(R.id.amountText);
            dateText = itemView.findViewById(R.id.dateText);
        }
    }
}
