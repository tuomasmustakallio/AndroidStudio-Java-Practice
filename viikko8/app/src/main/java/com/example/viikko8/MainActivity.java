package com.example.viikko8;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    BottleDispenser bottleDisp = BottleDispenser.getInstance();
    SeekBar seekBar;
    TextView addAmount;
    TextView show;
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        int seekBarValue= seekBar.getProgress();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addAmount = (TextView) findViewById(R.id.addAmount);
                progressChangedValue = progress;
                double raw;
                double something=progressChangedValue;
                raw = something / 100;
                String value = Double.toString(raw);
                addAmount.setText(value);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
    public void addMoney(View v) {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        show = (TextView) findViewById(R.id.display);
        balance = (TextView) findViewById(R.id.balance);
        addAmount = (TextView) findViewById(R.id.addAmount);
        String info;
        info = addAmount.getText().toString();
        bottleDisp.addMoney(info, show, balance);
        seekBar.setProgress(0);

    }

    public void returnMoney(View v) {
        show = (TextView) findViewById(R.id.display);
        balance = (TextView) findViewById(R.id.balance);
        bottleDisp.returnMoney(show, balance);
    }

    public void listBottles(View v) {
        show = (TextView) findViewById(R.id.display);
        show.setText("");
        bottleDisp.listBottles(show);
    }
    int choice;
    public void buy1(View v) {
        balance = (TextView) findViewById(R.id.balance);
        show = (TextView) findViewById(R.id.display);
        choice = 1;
        bottleDisp.buyBottle(choice, show, balance);
    }

    public void buy2(View v) {
        balance = (TextView) findViewById(R.id.balance);
        show = (TextView) findViewById(R.id.display);
        choice = 2;
        bottleDisp.buyBottle(choice, show, balance);
    }

    public void buy3(View v) {
        balance = (TextView) findViewById(R.id.balance);
        show = (TextView) findViewById(R.id.display);
        choice = 3;
        bottleDisp.buyBottle(choice, show, balance);
    }

    public void buy4(View v) {
        balance = (TextView) findViewById(R.id.balance);
        show = (TextView) findViewById(R.id.display);
        choice = 4;
        bottleDisp.buyBottle(choice, show, balance);
    }

    public void buy5(View v) {
        balance = (TextView) findViewById(R.id.balance);
        show = (TextView) findViewById(R.id.display);
        choice = 5;
        bottleDisp.buyBottle(choice, show, balance);
    }
}