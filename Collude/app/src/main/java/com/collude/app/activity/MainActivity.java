package com.collude.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.collude.app.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        Log.d(TAG, "onStart");
        super.onStart();
        LoginButton fbLoginButton = (LoginButton) findViewById(R.id.login_button_fb);
        if(loginManager != null){
            loginManager.registerCallback(callbackManager, getFBCallback());
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();

        callbackManager = null;
        loginManager = null;
    }

        /**
         * This method will Call the callback manager after the Login is done via any channel.
         *  1. Login via FB will result in call to FacebookCallback.
         *
         * @param requestCode
         * @param resultCode
         * @param data
         */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private FacebookCallback<LoginResult> getFBCallback() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "FacebookCallback::onSuccess");
                Intent mapsActivityIntent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapsActivityIntent);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "FacebookCallback::onCancel" + "Login was cancelled!");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "FacebookCallback::onError - " + error.getMessage());
            }
        };
    }
}
