package com.maps.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.maps.app.R;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button googleMapsButton = (Button)findViewById(R.id.button);
        googleMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick::googleMapsButton");
                Intent googleMapsActivityIntent = new Intent(getApplicationContext(), GoogleMapsActivity.class);
                startActivity(googleMapsActivityIntent);
            }
        });

        Button currentLocButton = (Button) findViewById(R.id.button_current_location);
        currentLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick::currentLocButton");
                Intent intent = new Intent(getApplicationContext(), CurrentLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}
