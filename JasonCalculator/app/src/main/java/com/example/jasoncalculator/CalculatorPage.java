package com.example.jasoncalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorPage extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private double operand1 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //display = findViewById(R.id.display);
        //GridLayout buttonsGrid = findViewById(R.id.buttonsGrid);

        // Set click listeners for number buttons
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNumberButtonClick(view);
                }
            });
        }

       /* findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "+");
            }
        });

        findViewById(R.id.btnMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "+");
            }
        });

        findViewById(R.id.btnMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "+");
            }
        });

        findViewById(R.id.btnDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "+");
            }
        });

        findViewById(R.id.btnExponent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "+");
            }
        });


        // Implement click listener for enter button
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEqualsButtonClick(view);
            }
        });
*/
    }

    public void onNumberButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        currentInput += buttonText;
        display.setText(currentInput);
    }

    // Implement onClick methods for operator buttons (+, -, *, /)
    public void onOperatorButtonClick(View view, String newOperator) {
        if (!currentInput.isEmpty()) {
            if (!operator.isEmpty()) {
                // An operator was already selected, perform the previous calculation
                performCalculation();
            }
            operand1 = Double.parseDouble(currentInput);
            operator = newOperator;
            currentInput = "";
            display.setText("");
        }
    }


    private void performCalculation() {
        double result = 0;
        double operand2 = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    // Handle division by zero error
                    display.setText("Error");
                    currentInput = "";
                    operator = "";
                    return;
                }
                break;
            case "^":
                result = Math.pow(operand1,operand2);
                break;
        }

        display.setText(String.valueOf(result));
        currentInput = "";
        operator = "";
    }

}