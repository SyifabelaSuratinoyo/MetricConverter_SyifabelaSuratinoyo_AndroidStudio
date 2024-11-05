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

public class WeightActivity extends AppCompatActivity {

    private Spinner fromUnitSpinner, toUnitSpinner;
    private EditText inputWeight;
    private TextView outputWeight;
    private String fromUnit, toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        fromUnitSpinner = findViewById(R.id.from_unit_spinner);
        toUnitSpinner = findViewById(R.id.to_unit_spinner);
        inputWeight = findViewById(R.id.inputWeight);
        outputWeight = findViewById(R.id.outputWeight);
        Button convertButton = findViewById(R.id.convertButton);
        ImageButton backButton = findViewById(R.id.backButton);

        // Array adapter for weight units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        convertButton.setOnClickListener(v -> {
            String input = inputWeight.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a weight", Toast.LENGTH_SHORT).show();
            } else {
                double value = Double.parseDouble(input);
                double result = convertWeight(value, fromUnit, toUnit);
                outputWeight.setText(String.format("%.2f %s", result, toUnit));
            }
        });

        // Back button logic
        backButton.setOnClickListener(v -> finish()); // This will close the current activity
    }

    private double convertWeight(double value, String fromUnit, String toUnit) {
        // Example conversion logic (assume input is in kilograms)
        if (fromUnit.equals("kilograms")) {
            switch (toUnit) {
                case "grams":
                    return value * 1000;
                case "milligrams":
                    return value * 1000000;
                case "pounds":
                    return value * 2.20462;
                case "ounces":
                    return value * 35.274;
                default:
                    return value; // already in kilograms
            }
        }
        // Add other cases for conversions from other units as needed.
        return value;
    }
}
