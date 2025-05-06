package com.example.ordertracker;

import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText searchInput;

    private ImageView qrImageView;

    private CardView orderCard;
    private TextView orderNumberText, orderStatusText;
    private Button orderActionButton;
    private String currentOrderNumber = "";
    private String currentOrderStatus = "";
    private String currentOrderDetails = "";

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
        Button contactButton = findViewById(R.id.contactUsButton);


        orderCard.setVisibility(View.VISIBLE);
        orderNumberText.setText("Zamówienie #???");
        orderStatusText.setText("???");
        orderStatusText.setTextColor(getResources().getColor(android.R.color.darker_gray));


        searchButton.setOnClickListener(v -> searchOrder());


        orderActionButton.setOnClickListener(v -> showOrderDetails());


        contactButton.setOnClickListener(v -> startActivity(new Intent(this, ContactActivity.class)));
    }

    private void searchOrder() {
        String orderNumber = searchInput.getText().toString().trim();

        if (TextUtils.isEmpty(orderNumber)) {
            Toast.makeText(this, "Wpisz numer zamówienia", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = null;
        try {
            cursor = dbHelper.getOrder(orderNumber);
            if (cursor != null && cursor.moveToFirst()) {

                currentOrderNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORDER_NUMBER));
                currentOrderStatus = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STATUS));
                currentOrderDetails = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DETAILS));

                orderCard.setVisibility(View.VISIBLE);
                orderNumberText.setText("Zamówienie #" + currentOrderNumber);
                orderStatusText.setText(currentOrderStatus);

                if (currentOrderStatus.equalsIgnoreCase("Dostarczone")) {
                    orderStatusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else if (currentOrderStatus.equalsIgnoreCase("W drodze")) {
                    orderStatusText.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                } else {
                    orderStatusText.setTextColor(getResources().getColor(android.R.color.darker_gray));
                }
            } else {
                orderCard.setVisibility(View.VISIBLE);
                orderNumberText.setText("Zamówienie #???");
                orderStatusText.setText("Nie znaleziono");
                orderStatusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                Toast.makeText(this, "Nie znaleziono zamówienia", Toast.LENGTH_SHORT).show();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    private void showOrderDetails() {
        if (!currentOrderNumber.isEmpty()) {
            Intent intent = new Intent(this, OrderDetailsActivity.class);
            intent.putExtra("orderNumber", currentOrderNumber);
            intent.putExtra("orderStatus", currentOrderStatus);
            intent.putExtra("orderDetails", currentOrderDetails);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Brak danych do wyświetlenia", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
