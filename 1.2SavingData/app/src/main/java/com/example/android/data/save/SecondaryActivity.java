package com.example.android.data.save;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.get_pref), Context.MODE_PRIVATE);
        String msgFrmPref = sharedPref.getString(getString(R.string.edit_message), "Default Message");

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(msgFrmPref);

        ViewGroup viewGroup = (ViewGroup)findViewById(R.id.activity_secondary);
        viewGroup.addView(textView);

    }
}
