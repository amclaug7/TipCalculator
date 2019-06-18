package com.example.andrew.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    public final static String MA = "MainActivity";
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;
    private EditText guestEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f,100.0f, 1f );
        setContentView(R.layout.activity_main);

        Configuration config = getResources().getConfiguration();
        modifyLayout(config);

        billEditText = (EditText) findViewById(R.id.amount_bill);
        tipEditText = (EditText) findViewById(R.id.amount_tip_percent);
        guestEditText = (EditText) findViewById(R.id.amount_tip_per_guest);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
        guestEditText.addTextChangedListener(tch);
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }

    public void modifyLayout(Configuration newConfig){
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        }
    }

    public void calculate(){

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        String guestString = guestEditText.getText().toString();

        TextView tipTextView =
                (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView =
                (TextView) findViewById(R.id.amount_total);
        TextView guestTextView =
                (TextView) findViewById(R.id.guest_total);
        try {
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            int guestNumber = Integer.parseInt(guestString);
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            tipCalc.setGuest(guestNumber);
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            float guest = tipCalc.tipAmountPerGuest();
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
            guestTextView.setText(money.format(guest));
        } catch(NumberFormatException nfe){

        }
    }

    private class TextChangeHandler implements TextWatcher{
        public void afterTextChanged(Editable e){
            calculate();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after){

        }

        public void onTextChanged(CharSequence s, int start, int before, int after){

        }
    }
}
