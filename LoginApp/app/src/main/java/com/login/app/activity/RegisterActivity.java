package com.login.app.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.login.app.R;
import com.login.app.db.DBReaderContract;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button mEmailRegisterButton = (Button) findViewById(R.id.email_register_button);
        mEmailRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText usernameTextField = (EditText) findViewById(R.id.username);
                final String username = usernameTextField.getText().toString();
                EditText passwordTextField = (EditText) findViewById(R.id.password);
                final String password = passwordTextField.getText().toString();
                Log.i(TAG, "Registering new User - " + username);
                DBReaderContract mDbHelper = new DBReaderContract(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                // New value for one column
                ContentValues values = new ContentValues();
                values.put(DBReaderContract.DBEntry.COLUMN_NAME_USERNAME, username);
                values.put(DBReaderContract.DBEntry.COLUMN_NAME_PASSWORD, password);
                values.put(DBReaderContract.DBEntry.COLUMN_NAME_ACTIVE, "Y");
                values.put(DBReaderContract.DBEntry.COLUMN_NAME_SOURCE, "APP");
                values.put(DBReaderContract.DBEntry.COLUMN_NAME_DATE_OF_REGISTERATION, new java.util.Date(System.currentTimeMillis()).toString());
                values.put(DBReaderContract.DBEntry.COLUMN_NAME_DATE_OF_UNREGISTERATION, "31-12-9999");

                long newRowId = db.insert(DBReaderContract.DBEntry.TABLE_NAME, "", values);
                Log.i(TAG, username + " registered! Row Id - " + newRowId);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
