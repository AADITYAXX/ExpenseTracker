package com.example.expensetracker;

public class Expense {
    final private String title, date;
    final private int amount;

    public Expense(String title, int amount, String date) {
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public String getTitle() { return title; }
    public int getAmount() { return amount; }
    public String getDate() { return date; }
}