package com.forex.app.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.forex.app.util.ForexResult;
import com.forex.app.util.ForexSpinner;
import com.example.android.forex.R;
import com.forex.app.util.ForexSpinnerComparator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ForexActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final String TAG = "ForexActivity";

    private static final String PREFFERED_CURR_EUR = "EUR";

    private static final String PREFFERED_CURR_INR = "INR";

    private String fromCurrency;
    private String toCurrency;

    private static ForexSpinner[] getAllCurrencies() {
        Log.i(TAG, "getAllCurrencies()");
        List<ForexSpinner> currencies = new ArrayList<>();
        for (Currency c : Currency.getAvailableCurrencies()) {
            currencies.add(new ForexSpinner(c.getCurrencyCode(), c.getDisplayName()));
        }
        currencies.sort(new ForexSpinnerComparator());
        return currencies.toArray(new ForexSpinner[currencies.size()]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex);

        ArrayAdapter<ForexSpinner> currencyAdapter = new ArrayAdapter<ForexSpinner>(this, android.R.layout.simple_spinner_item, getAllCurrencies());

        Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerFrom.setAdapter(currencyAdapter);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerFrom.setSelection(currencyAdapter.getPosition(new ForexSpinner(PREFFERED_CURR_EUR)));

        Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        spinnerTo.setAdapter(currencyAdapter);
        spinnerTo.setOnItemSelectedListener(this);
        spinnerTo.setSelection(currencyAdapter.getPosition(new ForexSpinner(PREFFERED_CURR_INR)));

        final Button convertButton = (Button) findViewById(R.id.convert);
        convertButton.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        Log.i(TAG, "onItemSelected()");
        switch (adapterView.getId()) {
            case R.id.spinnerFrom:
                Log.i(TAG, "From Currency Selected");
                ForexSpinner from = (ForexSpinner) adapterView.getItemAtPosition(pos);
                fromCurrency = from.getSymbol();
                break;
            case R.id.spinnerTo:
                Log.i(TAG, "To Currency Selected");
                ForexSpinner to = (ForexSpinner) adapterView.getItemAtPosition(pos);
                toCurrency = to.getSymbol();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.i(TAG, "onNothingSelected()");
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick()");
        try {
            ForexResult result = (ForexResult) new ForexHttpRequest().execute().get();
            if (null != result && result.getResultCode() != -1) {
                EditText editText = (EditText) findViewById(R.id.edittextAmount);
                String from = editText.getText().toString();

                Double resultTo = new Double(from) * result.getFactor();

                TextView textView = (TextView) findViewById(R.id.result);
                textView.setText(resultTo.toString());
            } else {
                Toast.makeText(getApplicationContext(), "For Some unknown reasons, we could not get the response. Is you phone connected to the internet? Else Try searching for a different combination!", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    private class ForexHttpRequest extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i(TAG, "ForexHttpRequest::doInBackground()");
            ForexResult result = new ForexResult();
            StringBuilder sb = new StringBuilder("http://api.fixer.io/latest?base=");
            sb.append(fromCurrency);
            Log.i(TAG, sb.toString());
            if (sb.toString().length() > 0) {
                URL url = null;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(sb.toString());
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    Log.i(TAG, "Response Code: " + httpURLConnection.getResponseCode());
                    InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                    String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
                    Log.i(TAG, response);
                    JSONObject responseObject = new JSONObject(response);
                    result.setFrom(fromCurrency);
                    result.setTo(toCurrency);
                    JSONObject rates = responseObject.getJSONObject("rates");
                    Log.i(TAG, rates.getString(toCurrency));
                    result.setFactor(new Double(rates.getString(toCurrency)));
                    result.setResultCode(0);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                    result.setResultCode(-1);
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                    result.setResultCode(-1);
                }
            }
            Log.i(TAG, result.toString());
            return result;
        }
    }
}
