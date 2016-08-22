package com.cloudconcept;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import utilities.ActivitiesLauncher;

/**
 * Created by Abanoub Wagdy on 8/11/2016.
 */
public class ThankYouActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you);
    }

    @Override
    public void onBackPressed() {
        ActivitiesLauncher.openHomeActivity(getApplicationContext());
    }
}
