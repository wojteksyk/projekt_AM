package com.example.ordertracker;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText searchInput;
    private CardView orderCard;
    private TextView orderNumberText, orderStatusText;
    private Button orderActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);


        searchInput = findViewById(R.id.searchOrderInput);
        Button searchButton = findViewById(R.id.searchButton);
        orderCard = findViewById(R.id.orderCard);
        orderNumberText = findViewById(R.id.orderNumberText);
        orderStatusText = findViewById(R.id.orderStatus);
        orderActionButton = findViewById(R.id.orderActionButton);

        searchButton.setOnClickListener(v -> searchOrder());
        orderActionButton.setOnClickListener(v -> showOrderDetails());
    }

    private void searchOrder() {
        String orderNumber = searchInput.getText().toString().trim();
        if (orderNumber.isEmpty()) {
            Toast.makeText(this, "Wpisz numer zamówienia", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = dbHelper.searchOrder(orderNumber);
        if (cursor != null && cursor.moveToFirst()) {

            orderCard.setVisibility(View.VISIBLE);
            orderNumberText.setText("Zamówienie #" + cursor.getString(0));
            orderStatusText.setText(cursor.getString(1));


            if (cursor.getString(1).equals("Dostarczone")) {
                orderStatusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                orderStatusText.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
            }
        } else {
            orderCard.setVisibility(View.GONE);
            Toast.makeText(this, "Nie znaleziono zamówienia", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void showOrderDetails() {
       
        Toast.makeText(this, "Szczegóły zamówienia", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}