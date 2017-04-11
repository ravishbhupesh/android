package br.app.ear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity
        extends Activity
        implements View.OnClickListener {

    private static final String TAG = "MA";

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.translateButton:
                Log.d(TAG, "Translate button clicked! Launching TranslateActivity");
                startActivity(new Intent(this, TranslatorActivity.class));
                break;
            case R.id.recordButton:
                Log.d(TAG, "Translate button clicked! Launching RecordActivity");
                startActivity(new Intent(this, RecorderActivity.class));
                break;
            default:
                Log.d(TAG, "Invalid Button!");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button translate = (Button) findViewById(R.id.translateButton);
        translate.setOnClickListener(this);

        Button record = (Button) findViewById(R.id.recordButton);
        record.setOnClickListener(this);
    }
}
