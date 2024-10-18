package com.example.second_assignment_dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnConvert, btnFinish;
    EditText editText;
    TextView textView;
    RadioButton upperCase, lowerCase, intermigent;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }
    private void initializeView() {
        editText = findViewById(R.id.editText);
        btnConvert = findViewById(R.id.btnConvert);
    }
    private void initListeners() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(editText.getText().toString());
            }
        });
    }
    private void showCustomDialog(final String userName) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);

        final TextView textViewRs = dialog.findViewById(R.id.textView);
        final RadioButton radioButtonUpper = dialog.findViewById(R.id.upperCase);
        final RadioButton radioButtonLowerCase = dialog.findViewById(R.id.lowerCase);
        final RadioButton radioButtonCapitalisation = dialog.findViewById(R.id.intermigent);
        final CheckBox cbReverseText = dialog.findViewById(R.id.checkbox);
        Button btnFinish = dialog.findViewById(R.id.btnFinish);


        View.OnClickListener updateText = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultText = userName;

                if (radioButtonUpper.isChecked()) {
                    resultText = resultText.toUpperCase();
                } else if (radioButtonLowerCase.isChecked()) {
                    resultText = resultText.toLowerCase();
                } else if (radioButtonCapitalisation.isChecked()) {
                    resultText = properCase(resultText);
                }

                if (cbReverseText.isChecked()) {
                    resultText = new StringBuilder(resultText).reverse().toString();
                }

                textViewRs.setText(resultText);
            }
        };

        radioButtonUpper.setOnClickListener(updateText);
        radioButtonLowerCase.setOnClickListener(updateText);
        radioButtonCapitalisation.setOnClickListener(updateText);
        cbReverseText.setOnClickListener(updateText);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private String properCase(String text) {
        String[] words = text.split(" ");
        StringBuilder properCaseText = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                properCaseText.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return properCaseText.toString().trim();
    }
}