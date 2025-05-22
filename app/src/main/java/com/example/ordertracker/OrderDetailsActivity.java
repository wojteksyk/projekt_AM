package com.example.ordertracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView detailOrderNumber, detailOrderStatus, detailOrderInfo, ratingLabel;
    private Button backButton, howItWorksButton, RaitingBar1;
    private RatingBar orderRatingBar;
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
        ratingLabel = findViewById(R.id.ratingLabel);
        orderRatingBar = findViewById(R.id.orderRatingBar);
        RaitingBar1 = findViewById(R.id.RaitingBar1);
        howItWorksButton = findViewById(R.id.howItWorksButton);


        String orderNumber = getIntent().getStringExtra("orderNumber");
        String orderStatus = getIntent().getStringExtra("orderStatus");
        String orderDetails = getIntent().getStringExtra("orderDetails");


        detailOrderNumber.setText("Zamówienie #" + orderNumber);
        detailOrderStatus.setText("Status: " + orderStatus);
        detailOrderInfo.setText(orderDetails);


        if ("Dostarczone".equalsIgnoreCase(orderStatus)) {
            qrImageView.setVisibility(View.GONE);
            howItWorksButton.setVisibility(View.GONE);
            ratingLabel.setVisibility(View.VISIBLE);
            orderRatingBar.setVisibility(View.VISIBLE);
            RaitingBar1.setVisibility(View.VISIBLE);


            RaitingBar1.setOnClickListener(v -> {
                float rating = orderRatingBar.getRating();
                if (rating > 0) {
                    Toast.makeText(this, "Dziękujemy za ocenę: " + rating + "★", Toast.LENGTH_SHORT).show();

                    orderRatingBar.setRating(0);
                } else {
                    Toast.makeText(this, "Proszę wystawić ocenę.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {

            qrImageView.setVisibility(View.VISIBLE);
            howItWorksButton.setVisibility(View.VISIBLE);
            ratingLabel.setVisibility(View.GONE);
            orderRatingBar.setVisibility(View.GONE);
            RaitingBar1.setVisibility(View.GONE);

            String qrUrl;
            if ("2137".equals(orderNumber)) {
                qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=https://www.youtube.com/watch?v=xvFZjo5PgG0&ab_channel=Duran&size=200x200";
            } else {
                qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + orderNumber + "&size=200x200";
            }

            Log.d("QR_DEBUG", "QR URL: " + qrUrl);
            Picasso.get().load(qrUrl).into(qrImageView);
        }


        howItWorksButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderDetailsActivity.this, qrExplanation.class);
            startActivity(intent);
        });


        backButton.setOnClickListener(v -> finish());
    }
}
