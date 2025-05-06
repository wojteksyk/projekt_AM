package com.example.ordertracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView detailOrderNumber, detailOrderStatus, detailOrderInfo;
    private Button backButton;
    private ImageView qrImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        detailOrderNumber = findViewById(R.id.detailOrderNumber);
        detailOrderStatus = findViewById(R.id.detailOrderStatus);
        detailOrderInfo = findViewById(R.id.detailOrderInfo);
        backButton = findViewById(R.id.backToMainButton);
        qrImageView = findViewById(R.id.qrImageView);

        String number = getIntent().getStringExtra("orderNumber");
        String status = getIntent().getStringExtra("orderStatus");
        String details = getIntent().getStringExtra("orderDetails");

        if (number != null && status != null && details != null) {
            detailOrderNumber.setText("Zamówienie #" + number);
            detailOrderStatus.setText(status);
            detailOrderInfo.setText(details);

          //
           //  String qrData = "Kod odbioru: " + number;
          //  String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + qrData + "&size=200x200";
           // Picasso.get().load(qrUrl).into(qrImageView);
//
     backButton.setOnClickListener(v -> finish());
    }
}
}