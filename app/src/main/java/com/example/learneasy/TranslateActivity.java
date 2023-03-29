package com.example.learneasy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.Objects;

public class TranslateActivity extends AppCompatActivity {

    TextInputEditText sourceText;
    AppCompatButton translateBtn;
    TextView translateTV, goBack;

    RelativeLayout translateContainer;

    ProgressBar progressBar;

    LinearLayout mainLayout;


    String[] fromLanguage = {"From", "English", "French", "Belarusian", "Russian", "Ukrainian", "Czech" , "Arabic", "Hindi"};

    String[] toLanguage = {"To", "English", "French", "Belarusian", "Russian", "Ukrainian", "Czech" , "Arabic", "Hindi"};

    private static final int REQUEST_PERMISSION_CODE = 1;
    int languageCode, fromLanguageCode, toLanguageCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        Spinner fromSpinner = findViewById(R.id.id_from_spinner);
        Spinner toSpinner = findViewById(R.id.id_to_spinner);
        sourceText = findViewById(R.id.id_edit_source);
        translateBtn = findViewById(R.id.id_btn_translation);
        translateTV = findViewById(R.id.id_translated_tv);
        goBack = findViewById(R.id.back);
        mainLayout = findViewById(R.id.main_layout);
        translateContainer = findViewById(R.id.translation_container);
        progressBar = findViewById(R.id.progress_bar);

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fromLanguageCode = getLanguageCode(fromLanguage[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter fromAdapter = new ArrayAdapter(this, R.layout.spinner_item, fromLanguage);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                toLanguageCode = getLanguageCode(fromLanguage[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter toAdapter = new ArrayAdapter(this, R.layout.spinner_item, toLanguage);
        toAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        toSpinner.setAdapter(toAdapter);


        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translateTV.setVisibility(View.VISIBLE);
                translateTV.setText("");
                if (Objects.requireNonNull(sourceText.getText()).toString().isEmpty()){
                    Toast.makeText(TranslateActivity.this, "Please enter text to translate", Toast.LENGTH_SHORT).show();
                } else if (fromLanguageCode == 0){
                    Toast.makeText(TranslateActivity.this, "Please select source language", Toast.LENGTH_SHORT).show();
                } else if (toLanguageCode == 0){
                    Toast.makeText(TranslateActivity.this, "Please select the language to make translation", Toast.LENGTH_SHORT).show();
                } else {
                    translateText(fromLanguageCode, sourceText.getText().toString());
                }
            }
        });

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void translateText(int fromLanguageCode, String source) {
        progressBar.setVisibility(View.VISIBLE);
        translateContainer.setVisibility(View.VISIBLE);
        //translateTV.setText("Translating...");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();
        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                //translateTV.setText("Translation...");

                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        translateTV.setText(s);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TranslateActivity.this, "Failed to translate!! Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TranslateActivity.this, "Failed to translate!! Check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //String[] fromLanguage = {"From", "English", "France", "Belarusian", "Russian", "Ukrainian", "Czech" , "Arabic", "Indian"};
    private int getLanguageCode(String language) {
        int languageCode = 0;
        switch (language){
            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;
            case "French":
                languageCode = FirebaseTranslateLanguage.FR;
                break;
            case "Belarusian":
                languageCode = FirebaseTranslateLanguage.BE;
                break;
            case "Ukrainian":
                languageCode = FirebaseTranslateLanguage.UK;
                break;
            case "Russian":
                languageCode = FirebaseTranslateLanguage.RU;
                break;
            case "Czech":
                languageCode = FirebaseTranslateLanguage.CS;
                break;
            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;
            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;

            default:
                languageCode = 0;
        }
        return languageCode;


    }

}