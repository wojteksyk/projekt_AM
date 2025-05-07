package com.example.ordertracker;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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


        String orderNumber = getIntent().getStringExtra("orderNumber");
        String orderStatus = getIntent().getStringExtra("orderStatus");
        String orderDetails = getIntent().getStringExtra("orderDetails");


        detailOrderNumber.setText("Zamówienie #" + orderNumber);
        detailOrderStatus.setText("Status: " + orderStatus);
        detailOrderInfo.setText(orderDetails);

        if ("2137".equals(orderNumber)){
            String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=https://www.youtube.com/watch?v=xvFZjo5PgG0&size=200x200";
            Log.d("QR_DEBUG", "QR URL: " + qrUrl);
            Picasso.get().load(qrUrl).into(qrImageView);
        }
        else {
            String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + orderNumber + "&size=200x200";
            Log.d("QR_DEBUG", "QR URL: " + qrUrl);
            Picasso.get().load(qrUrl).into(qrImageView);
        }
        Button howItWorksButton = findViewById(R.id.howItWorksButton);
        howItWorksButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderDetailsActivity.this, qrExplanation.class);
            startActivity(intent);
        });



        backButton.setOnClickListener(v -> finish());
    }
}
