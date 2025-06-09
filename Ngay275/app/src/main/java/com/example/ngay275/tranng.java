package com.example.ngay275;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class tranng extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangvd1);

        TextView trang = findViewById(R.id.textView);
        trang.setOnClickListener(v -> {
            Intent intent = new Intent(tranng.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
