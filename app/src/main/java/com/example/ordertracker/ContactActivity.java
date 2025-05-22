package com.example.ordertracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    private EditText contactName, contactEmail, contactMessage;
    private Button sendButton, goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactName = findViewById(R.id.contactName);
        contactEmail = findViewById(R.id.contactEmail);
        contactMessage = findViewById(R.id.contactMessage);
        sendButton = findViewById(R.id.sendButton);
        goBack = findViewById(R.id.backToMainButton);


        sendButton.setOnClickListener(v -> sendMsg());
        goBack.setOnClickListener(v -> backMain());
    }

    private void sendMsg() {

        String name = contactName.getText().toString().trim();
        String email = contactEmail.getText().toString().trim();
        String message = contactMessage.getText().toString().trim();


        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Proszę wypełnić wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            contactEmail.setError("Proszę wpisać poprawny adres e-mail");
            return;
        }

        Toast.makeText(this, "Wiadomość wysłana!", Toast.LENGTH_SHORT).show();

        resetForm();
    }

    private void resetForm() {
        contactName.setText("");
        contactEmail.setText("");
        contactMessage.setText("");
    }
    private void backMain() {
        Intent intent = new Intent(ContactActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
