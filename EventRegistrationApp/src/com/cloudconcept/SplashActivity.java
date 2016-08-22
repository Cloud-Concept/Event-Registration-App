package com.cloudconcept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cloudconcept.R;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.ui.SalesforceActivity;

import utilities.ActivitiesLauncher;
import utilities.ExceptionHandler;

/**
 * Created by Abanoub Wagdy on 12/15/2015.
 */
public class SplashActivity extends SalesforceActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    @Override
    public void onResume(RestClient client) {
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               ActivitiesLauncher.openHomeActivity(getApplicationContext());
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
