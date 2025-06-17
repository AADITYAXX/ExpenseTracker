package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class ExpenseDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "expenses.db";
    private static final int DB_VERSION = 1;

    public ExpenseDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE expenses (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, amount INTEGER, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }

    public void addExpense(String title, int amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("amount", amount);
        values.put("date", date);
        db.insert("expenses", null, values);
        db.close();
    }

    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expenses", null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                int amount = cursor.getInt(2);
                String date = cursor.getString(3);
                list.add(new Expense(title, amount, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
