package com.cloudconcept;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.salesforce.androidsdk.app.SalesforceSDKManager;
import com.salesforce.androidsdk.rest.ClientManager;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import Interface.LongOperationDelegate;
import RestAPI.SFRequestManager;
import utilities.EventRegistrationConfiguration;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 1/17/2016.
 */
public class RegisterNewMemberActivity extends Activity {

    EditText etCompany, etJobTitle, etEmail;
    private EditText etFirstName;
    private EditText etLastName;
    RestClient restClient;
    Button btnCheckin;
    private String result;
    private String txtSearch;
    private HashMap<String, Object> fields;
    private String campaignId;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        txtSearch = getIntent().getStringExtra("txtSearch");
        campaignId = getIntent().getStringExtra("campaignId");

        InitializeViews();
    }

    private void InitializeViews() {

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etJobTitle = (EditText) findViewById(R.id.etJobTitle);
        etCompany = (EditText) findViewById(R.id.etCompany);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);

        btnCheckin = (Button) findViewById(R.id.btnCheckin);

        if (txtSearch.contains("@")) {
            etEmail.setText(txtSearch);
        } else {
            etLastName.setText(txtSearch);
        }

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFirstName.getText().toString().equals("") || etFirstName.getText().toString() == null) {

                    Utilities.showToast(RegisterNewMemberActivity.this, "Please Enter Valid Name Value");

                } else if (etLastName.getText().toString().equals("") || etLastName.getText().toString() == null) {

                    Utilities.showToast(RegisterNewMemberActivity.this, "Please Enter Valid Name Value");

                } else if (etCompany.getText().toString().equals("") || etCompany.getText().toString() == null) {

                    Utilities.showToast(RegisterNewMemberActivity.this, "Please Enter Valid Company Value");

                } else if (etJobTitle.getText().toString().equals("") || etJobTitle.getText().toString() == null) {

                    Utilities.showToast(RegisterNewMemberActivity.this, "Please Enter Valid Phone Value");

                } else if (etEmail.getText().toString().equals("") || etEmail.getText().toString() == null) {

                    Utilities.showToast(RegisterNewMemberActivity.this, "Please Enter Valid Email Value");

                } else if (etPhone.getText().toString().equals("") || etPhone.getText().toString() == null) {

                    Utilities.showToast(RegisterNewMemberActivity.this, "Please Enter Valid Email Value");

                } else {
                    new SFRequestManager(RegisterNewMemberActivity.this, registerNewLead).doRegisterNewCampaignMember(etFirstName, etLastName, etEmail, etCompany, etJobTitle, etPhone);
                }
            }
        });
    }


    private LongOperationDelegate registerNewLead = new LongOperationDelegate() {

        @Override
        public void didFinishLoadWithSuccess(String response) {
            Utilities.showToast(RegisterNewMemberActivity.this, "New Lead registered successfully");
            new SFRequestManager(RegisterNewMemberActivity.this, checkInCampaignMember).doCheckInCampaignMember(etFirstName.getText().toString(), etLastName.getText().toString(), etEmail.getText().toString(), etCompany.getText().toString(), etJobTitle.getText().toString(), campaignId, null);
        }

        @Override
        public void didFinishLoadWithFail(String exception) {
            Utilities.showToast(RegisterNewMemberActivity.this, "Failed to create new Lead ,please try again later");
        }
    };


    private LongOperationDelegate checkInCampaignMember = new LongOperationDelegate() {

        @Override
        public void didFinishLoadWithSuccess(String response) {
            Utilities.showToast(RegisterNewMemberActivity.this, "Member Checked in successfully");
        }

        @Override
        public void didFinishLoadWithFail(String exception) {
            Utilities.showToast(RegisterNewMemberActivity.this, "Failed to check in member ,please try again later");
        }
    };
}
