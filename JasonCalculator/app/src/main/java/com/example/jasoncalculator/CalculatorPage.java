package com.example.jasoncalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorPage extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double operand1 = 0;
    private String operator = "";

    private StringBuilder totalInputs = new StringBuilder();

    private boolean canDeleteTotal = true;
    private boolean justEntered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);
        display = findViewById(R.id.display);

        setupNumberButtons();
        setupOperatorButtons();
        setupControlButtons();
    }

    private void setupNumberButtons() {
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setOnClickListener(view -> onNumberButtonClick(view));
        }
    }

    private void setupOperatorButtons() {
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "+");
            }
        });

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, ".");
            }
        });
        findViewById(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "-");
            }
        });

        findViewById(R.id.btnMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "*");
            }
        });

        findViewById(R.id.btnDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "/");
            }
        });

        findViewById(R.id.btnExpo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "^");
            }
        });

        findViewById(R.id.btnNeg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view, "(-)");
            }
        });
    }

    private void setupControlButtons() {
        findViewById(R.id.btnEnter).setOnClickListener(view -> performCalculation());
        findViewById(R.id.btnCl).setOnClickListener(view -> clearInputs());
        findViewById(R.id.btnDel).setOnClickListener(view -> deleteLastCharacter());
    }

    public void onNumberButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if (justEntered) {
            currentInput = new StringBuilder(buttonText);
            justEntered = false;
        } else {
            currentInput.append(buttonText);
        }
        totalInputs.append(buttonText);
        display.setText(totalInputs.toString());
    }

    public void onOperatorButtonClick(View view, String newOperator) {
        if (currentInput.length() > 0) {
            if (!operator.isEmpty()) {
                performCalculation();
            }
            operand1 = Double.parseDouble(currentInput.toString());
            operator = newOperator;
            currentInput.setLength(0);
            totalInputs.append(operator);
            display.setText(totalInputs.toString());
        }
    }

    private void clearInputs() {
        display.setText("");
        currentInput.setLength(0);
        operand1 = 0;
        operator = "";
        totalInputs.setLength(0);
    }

    private void deleteLastCharacter() {
        if (currentInput.length() > 0 && canDeleteTotal) {
            currentInput.setLength(currentInput.length() - 1);
            totalInputs.setLength(totalInputs.length() - 1);
            display.setText(totalInputs.toString());
        }
    }

    private void performCalculation() {
        if (currentInput.length() == 0 || operator.isEmpty()) {
            display.setText("Error: Invalid Input");
            return;
        }

        double result = 0;
        double operand2 = Double.parseDouble(currentInput.toString());

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
                    currentInput.setLength(0);
                    operator = "";
                    return;
                }
                break;
            case "^":
                result = Math.pow(operand1,operand2);
                break;
        }

        operand1 = 0;
        operator = "";
        currentInput.setLength(0);
        totalInputs.append("=").append(result).append("\n");
        display.setText(totalInputs.toString());
        canDeleteTotal = false;
        justEntered = true;
    }

}





