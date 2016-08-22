package com.cloudconcept;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.salesforce.androidsdk.app.SalesforceSDKManager;
import com.salesforce.androidsdk.rest.ClientManager;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;
import com.salesforce.androidsdk.ui.SalesforceActivity;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import RestAPI.SFResponseManager;
import RestAPI.SoqlStatements;
import adapter.EventsAdapter;
import model.Campaign;
import utilities.ActivitiesLauncher;
import utilities.EventRegistrationConfiguration;
import utilities.ExceptionHandler;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 12/27/15.
 */
public class HomeActivity extends SalesforceActivity {

    private RestClient client;

    ListView lstEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.home);

        lstEvents = (ListView) findViewById(R.id.lstEvents);

        Utilities.showloadingDialog(this);

        new ClientManager(this, SalesforceSDKManager.getInstance().getAccountType(), SalesforceSDKManager.getInstance().getLoginOptions(), SalesforceSDKManager.getInstance().shouldLogoutWhenTokenRevoked()).getRestClient(this, new ClientManager.RestClientCallback() {
            @Override
            public void authenticatedRestClient(final RestClient client) {
                if (client == null) {
                    SalesforceSDKManager.getInstance().logout(HomeActivity.this);
                    return;
                } else {
                    try {
                        RestRequest restRequest = RestRequest.getRequestForQuery(getString(R.string.api_version), SoqlStatements.getSoqlActiveCampaigns());
                        client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
                            @Override
                            public void onSuccess(RestRequest request, RestResponse response) {
                                Utilities.dismissLoadingDialog();
                                final ArrayList<Campaign> campaigns = SFResponseManager.parseCampaignsResponse(response.toString());
                                lstEvents.setAdapter(new EventsAdapter(getApplicationContext(), campaigns));
                                lstEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        ActivitiesLauncher.openFindRegistrationActivity(getApplicationContext(), campaigns.get(position));
                                    }
                                });
                                Log.d("success", response.toString());
                            }

                            @Override
                            public void onError(Exception exception) {
                                Log.d("exception", ExceptionHandler.getSalesforceStringError(exception));
                                Utilities.dismissLoadingDialog();
                            }
                        });

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onResume(RestClient client) {
        this.client = client;
    }

    //   public class DoCampaignsRequest extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            Utilities.showloadingDialog(HomeActivity.this);
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            String attUrl = client.getClientInfo().resolveUrl(EventRegistrationConfiguration.MOBILE_SERVICE_UTILITY_URL).toString();
//            HttpClient tempClient = new DefaultHttpClient();
//            URI theUrl = null;
//
//            try {
//                theUrl = new URI(attUrl);
//                HttpPost getRequest = new HttpPost();
//                getRequest.setURI(theUrl);
//                getRequest.setHeader("Authorization", "Bearer " + client.getAuthToken());
//                HttpResponse httpResponse = null;
////                StringEntity se = null;
////                try {
////                    se = new StringEntity(parent.toString());
////                } catch (UnsupportedEncodingException e) {
////                    e.printStackTrace();
////                }
////                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
////                getRequest.setEntity(se);
//                try {
//                    httpResponse = tempClient.execute(getRequest);
//                    StatusLine statusLine = httpResponse.getStatusLine();
//                    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//                        HttpEntity httpEntity = httpResponse.getEntity();
//                        Log.d("response", httpEntity.toString());
//                        if (httpEntity != null) {
//                            result = EntityUtils.toString(httpEntity);
//                        }
//                    } else {
//                        httpResponse.getEntity().getContent().close();
//                        throw new IOException(statusLine.getReasonPhrase());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            Utilities.dismissLoadingDialog();
//            parseResult(result);
//        }
    //   }

//    private void parseResult(String s) {
//        ArrayList<Campaign> campaigns = new ArrayList<>();
//        try {
//            JSONObject jsonrecords = new JSONObject(s);
//            JSONArray jArrayRecords = jsonrecords.getJSONArray("records");
//            for (int i = 0; i < jArrayRecords.length(); i++) {
//                Campaign campaign = new Campaign();
//                JSONObject record = jArrayRecords.getJSONObject(i);
//                campaign.setId(record.getString("Id"));
//                campaign.setIsAllDayEvent(record.getBoolean("IsAllDayEvent"));
//                campaign.setOwnerId(record.getJSONObject("Owner").getString("Id"));
//                campaign.setOwnerName(record.getJSONObject("Owner").getString("Name"));
//                campaign.setDurationInMinutes(record.getString("DurationInMinutes"));
//                campaign.setWhatId(record.getJSONObject("What").getString("Id"));
//                campaign.setWhatName(record.getJSONObject("What").getString("Name"));
//                campaign.setStartDateTime(record.getString("StartDateTime"));
//                campaign.setActivityDateTime(record.getString("ActivityDateTime"));
//                campaign.setSubject(record.getString("Subject"));
//                campaigns.add(campaign);
//            }
//
//            ArrayList<Campaign> DisplayedCampaigns = new ArrayList<>();
//            for (int i = 0; i < campaigns.size(); i++) {
//                if (campaigns.get(i).getIsActive()) {
//                    DisplayedCampaigns.add(campaigns.get(i));
//                }
//            }
//
////            if (DisplayedCampaigns.size() == 1) {
////                ActivitiesLauncher.openEventDetailsActivity(getApplicationContext(), DisplayedCampaigns.get(0));
////            }
//
//            adapter = new EventsAdapter(getApplicationContext(), DisplayedCampaigns);
//            lstEvents.setAdapter(adapter);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}