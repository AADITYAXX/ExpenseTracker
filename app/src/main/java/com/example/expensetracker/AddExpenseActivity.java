package com.example.expensetracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;


import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {
    EditText titleInput, amountInput, dateInput;
    Button saveButton;
    ExpenseDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        titleInput = findViewById(R.id.titleInput);
        amountInput = findViewById(R.id.amountInput);
        dateInput = findViewById(R.id.dateInput);
        saveButton = findViewById(R.id.saveButton);

        dbHelper = new ExpenseDatabaseHelper(this);

        dateInput.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String dateString = getString(R.string.date_format, dayOfMonth, month + 1, year);
                dateInput.setText(dateString);

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String amountStr = amountInput.getText().toString().trim();
            String date = dateInput.getText().toString().trim();

            if (title.isEmpty() || amountStr.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int amount = Integer.parseInt(amountStr);
                dbHelper.addExpense(title, amount, date);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Enter a valid number for amount", Toast.LENGTH_SHORT).show();
            }
        });

    }
}