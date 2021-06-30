package com.example.viikko10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    WebView web;
    EditText addressInput;
    String addressString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        web = findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);

        ImageButton search = (ImageButton)findViewById(R.id.searchBtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find();
            }
        });

        ImageButton refresh = (ImageButton)findViewById(R.id.refreshBtn);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl(web.getUrl());
            }
        });

        ImageButton prev = (ImageButton)findViewById(R.id.prevBtn);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressGoBack();
            }
        });

        ImageButton next = (ImageButton)findViewById(R.id.nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressNext();
            }
        });

        addressInput = findViewById(R.id.address);

        addressInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addressString = addressInput.getText().toString();
                System.out.println(addressString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void find(){
        if (addressString.equals("index.html")){
            web.loadUrl("file:///android_asset/index.html");
        }
        else{
            web.loadUrl("http://" + addressString);
        }
    }

    public void pressGoBack(){
        if (web.canGoBack()){
            web.goBack();
        }
    }

    public void pressNext(){
        if (web.canGoForward()){
            web.goForward();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void executeJavascript(View v){
        web.evaluateJavascript("javascript:shoutOut()",null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void initializeJavascript(View v){
        web.evaluateJavascript("javascript:initialize()",null);
    }
}