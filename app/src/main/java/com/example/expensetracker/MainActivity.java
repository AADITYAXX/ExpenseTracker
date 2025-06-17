package com.example.expensetracker;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView totalText;
    Button addButton;
    ExpenseAdapter adapter;
    com.example.expensetracker.ExpenseDatabaseHelper dbHelper;
    ArrayList<Expense> expenseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        totalText = findViewById(R.id.totalText);
        addButton = findViewById(R.id.addButton);

        dbHelper = new ExpenseDatabaseHelper(this);
        expenseList = dbHelper.getAllExpenses();

        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateTotal();

        addButton.setOnClickListener(v -> startActivity(new Intent(this, AddExpenseActivity.class)));
    }


    @Override
    protected void onResume() {
        super.onResume();
        List<Expense> latestExpenses = dbHelper.getAllExpenses();
        adapter.updateData(latestExpenses);
        updateTotal();
    }


    void updateTotal() {
        int total = 0;
        for (Expense e : expenseList) {
            total += e.getAmount();
        }
        totalText.setText(getString(R.string.total_text, total));

    }
}
