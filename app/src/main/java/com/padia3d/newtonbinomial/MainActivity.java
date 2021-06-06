package com.padia3d.newtonbinomial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int lines = 4;
    TextView output, showText;
    EditText inputExponente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        output = findViewById(R.id.mainText);
        showText = findViewById(R.id.showText);
        inputExponente = findViewById(R.id.inputExponente);

        Button buton = findViewById(R.id.button1);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(inputExponente.getText()) != null) {
                    calculate();
                }
            }
        });
        calculate();
    }

    public void calculate() {
        lines = Integer.parseInt(String.valueOf(inputExponente.getText()));
        ArrayList<ArrayList<Integer>> numbersList2 = new ArrayList<>();
        for (int i = 0; i < (lines + 1); i++) {
            List<Integer> numbersList = new ArrayList<>();
            for (int j = 0; j < (i + 1); j++) {
                if (j == 0 || j == i) {
                    numbersList.add(1);
                } else {
                    numbersList.add(numbersList2.get(i - 1).get(j - 1) + numbersList2.get(i - 1).get(j));
                }
            }
            numbersList2.add((ArrayList<Integer>) numbersList);
        }

        String outputString = "";
        for (int x = 0; x < numbersList2.size(); x++) {
            outputString += numbersList2.get(x).toString() + "\n";
        }
        output.setText(outputString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mainActivityItem) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }

        if (item.getItemId() == R.id.lineActivityItem) {
            startActivity(new Intent(MainActivity.this, LineActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}