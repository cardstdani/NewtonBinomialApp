package com.padia3d.newtonbinomial;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class LineActivity extends AppCompatActivity {

    public int lines = 4;
    TextView output, showText;
    EditText inputExponente;
    ImageButton copyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        output = findViewById(R.id.mainText);
        showText = findViewById(R.id.showText);
        inputExponente = findViewById(R.id.inputExponente);
        copyButton = findViewById(R.id.copyButton);
        copyButton.setVisibility(View.INVISIBLE);

        Button buton = findViewById(R.id.button1);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(inputExponente.getText()) != null) {
                    calculate();
                }
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Line" + inputExponente.getText(), output.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
        calculate();
    }

    public void calculate() {
        copyButton.setVisibility(View.VISIBLE);
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
        output.setText(numbersList2.get(numbersList2.size() - 1).toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mainActivityItem) {
            startActivity(new Intent(LineActivity.this, MainActivity.class));
        }

        if (item.getItemId() == R.id.lineActivityItem) {
            startActivity(new Intent(LineActivity.this, LineActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}