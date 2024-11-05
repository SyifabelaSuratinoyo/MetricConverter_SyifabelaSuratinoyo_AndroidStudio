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

public class LengthActivity extends AppCompatActivity {

    private Spinner fromUnitSpinner, toUnitSpinner;
    private EditText inputLength;
    private TextView outputLength;
    private String fromUnit, toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        // Initialize UI components
        fromUnitSpinner = findViewById(R.id.from_unit_spinner);
        toUnitSpinner = findViewById(R.id.to_unit_spinner);
        inputLength = findViewById(R.id.inputLength);
        outputLength = findViewById(R.id.outputLength);
        Button convertButton = findViewById(R.id.convertButton);
        ImageButton backButton = findViewById(R.id.backButton); // Add this line

        // Set up the spinner adapters
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Set spinner listeners
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

        // Convert button logic
        convertButton.setOnClickListener(v -> {
            String input = inputLength.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a length", Toast.LENGTH_SHORT).show();
            } else {
                double value = Double.parseDouble(input);
                double result = convertLength(value, fromUnit, toUnit);
                outputLength.setText(String.format("%.2f %s", result, toUnit));
            }
        });

        // Back button logic
        backButton.setOnClickListener(v -> finish()); // This will close the current activity
    }

    private double convertLength(double value, String fromUnit, String toUnit) {
        // Sample conversion logic (meters to other units)
        if (fromUnit.equals("meters")) {
            switch (toUnit) {
                case "centimeters":
                    return value * 100;
                case "millimeters":
                    return value * 1000;
                case "kilometers":
                    return value / 1000;
                case "inches":
                    return value * 39.3701;
                case "feet":
                    return value * 3.28084;
                default:
                    return value;
            }
        }
        // Add other cases for conversions between different units.
        return value;
    }
}
