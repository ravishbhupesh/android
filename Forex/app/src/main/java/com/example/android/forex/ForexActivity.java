package com.example.android.forex;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Set;

public class ForexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex);

        ArrayAdapter<String>
    }

    private String[] getAllCurrencies(){
        List<String> currencies = new ArrayList<>();
        for(Currency c : Currency.getAvailableCurrencies()){
            currencies.add(c.getCurrencyCode());
        }
        return currencies.toArray(new String[]);
    }
}
