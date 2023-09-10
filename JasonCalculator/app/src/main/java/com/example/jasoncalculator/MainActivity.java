package com.example.jasoncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener((view) -> {switchScreens(view);});

    }

    public void switchScreens(View view){
        Intent intent = new Intent(this, CalculatorPage.class);
        startActivity(intent);
    }
}

