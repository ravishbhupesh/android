package br.app.locationservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showLocationButton = (Button) findViewById(R.id.button);
        showLocationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                invokeDisplayLastLocationActivity();
                break;
            case R.id.button2:
                invokeChangeLocationActivity();
                break;
            case R.id.button3:
                invokeReceiveLocationUpdatesActivity();
                break;
            default:
                Log.i(TAG, "Invalid Option!");
                break;
        }
    }

    private void invokeReceiveLocationUpdatesActivity() {
        Intent intent = new Intent(this, ReceiveLocationUpdatesActivity.class);
        startActivity(intent);
    }

    private void invokeChangeLocationActivity(){
        Intent intent = new Intent(this, ChangeLocationActivity.class);
        startActivity(intent);
    }

    private void invokeDisplayLastLocationActivity(){
        Intent intent = new Intent(this, DisplayLastLocationActivity.class);
        startActivity(intent);
    }
}
