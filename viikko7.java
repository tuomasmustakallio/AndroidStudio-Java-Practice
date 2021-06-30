package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context;

    String text, filename;
    EditText textInput, fileInput;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        System.out.println(context.getFilesDir());

        Button helloBtn =  (Button) findViewById(R.id.hwButton);
        Button btnSave =  (Button) findViewById(R.id.btnSave);
        Button btnLoad = (Button) findViewById(R.id.btnLoad);
        fileInput = (EditText) findViewById(R.id.fileInput);
        textInput = (EditText) findViewById(R.id.textInput);
        show = (TextView) findViewById(R.id.textView);
        text = textInput.getText().toString();

        helloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello World!");
            }
        });

        fileInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filename = fileInput.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text = textInput.getText().toString();
                show.setText(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void readFile(View v){
        try {
            InputStream ins = context.openFileInput(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                show.setText(s);
                textInput.setText(s);
            }
            ins.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Luettu");
        }
    }

    public void writeFile(View v){
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));

            String s = "";

            s = text;
            ows.write(s);
            ows.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Kirjoitettu");
        }
    }

}
