package com.example.a1stapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edt_num_1, edt_num_2;
    TextView text_resultado;
    Button btn_calcular;
    Spinner spinner_operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_num_1 = findViewById(R.id.edt_num_1);
        edt_num_2 = findViewById(R.id.edt_num_2);
        spinner_operations = findViewById(R.id.spinner_operations);
        text_resultado = findViewById(R.id.text_resultado);
        btn_calcular = findViewById(R.id.btn_calcular);

        // Set up Spinner options
        String[] operations = {"+", "-", "*", "/"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, operations);
        spinner_operations.setAdapter(adapter);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calcular() {
        String num1 = edt_num_1.getText().toString();
        String num2 = edt_num_2.getText().toString();

        if (num1.isEmpty() || num2.isEmpty()) {
            Toast.makeText(MainActivity.this, "POR FAVOR LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            return;
        }

        double number1 = Double.parseDouble(num1);
        double number2 = Double.parseDouble(num2);
        String operation;
        operation = spinner_operations.getSelectedItem().toString();
        double result = 0;

        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    Toast.makeText(MainActivity.this, "No se puede dividir por 0", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
        }
        text_resultado.setText(String.valueOf(result));
    }
}