package com.example.metricconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TemperatureActivity extends AppCompatActivity {

    private Spinner fromUnitSpinner, toUnitSpinner;
    private EditText inputTemperature;
    private TextView outputTemperature;
    private String fromUnit, toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        // Initialize UI components
        fromUnitSpinner = findViewById(R.id.from_unit_spinner);
        toUnitSpinner = findViewById(R.id.to_unit_spinner);
        inputTemperature = findViewById(R.id.inputTemperature);
        outputTemperature = findViewById(R.id.outputTemperature);
        Button convertButton = findViewById(R.id.convertButton);
        ImageButton backButton = findViewById(R.id.backButton); // Added back button

        // Set up the adapter for the spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperature_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Set listeners for spinners
        fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        toUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Set convert button logic
        convertButton.setOnClickListener(v -> {
            String input = inputTemperature.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show();
            } else {
                double value = Double.parseDouble(input);
                double result = convertTemperature(value, fromUnit, toUnit);
                outputTemperature.setText(String.format("%.2f %s", result, toUnit));
            }
        });

        // Set back button logic
        backButton.setOnClickListener(v -> finish());
    }

    // Conversion method for temperature units
    private double convertTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals("Celsius")) {
            if (toUnit.equals("Fahrenheit")) {
                return (value * 9 / 5) + 32;
            } else if (toUnit.equals("Kelvin")) {
                return value + 273.15;
            }
        } else if (fromUnit.equals("Fahrenheit")) {
            if (toUnit.equals("Celsius")) {
                return (value - 32) * 5 / 9;
            } else if (toUnit.equals("Kelvin")) {
                return (value - 32) * 5 / 9 + 273.15;
            }
        } else if (fromUnit.equals("Kelvin")) {
            if (toUnit.equals("Celsius")) {
                return value - 273.15;
            } else if (toUnit.equals("Fahrenheit")) {
                return (value - 273.15) * 9 / 5 + 32;
            }
        }
        return value; // If the units are the same, return the input value
    }
}
