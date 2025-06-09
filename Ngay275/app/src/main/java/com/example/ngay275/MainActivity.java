package com.example.ngay275;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView mytextView = findViewById(R.id.mytextView);

        button = findViewById(R.id.button);

        // lay input từ cái testviewgfhnjm
        EditText edittextView = findViewById(R.id.editTextName);
        button.setOnClickListener(v -> {
            String inputClick = edittextView.getText().toString();
            mytextView.setText(inputClick);
            button.setEnabled(false);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            mytextView.setText("Welcome K29 to the Mobile Class");

            return insets;
        });

        TextView trang = findViewById(R.id.textView2);
        trang.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, tranng.class);
            startActivity(intent);
        });

    }
}