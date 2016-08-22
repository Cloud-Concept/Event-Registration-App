package com.cloudconcept;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.ui.SalesforceActivity;

import java.util.ArrayList;
import java.util.List;

import Interface.LongOperationDelegate;
import RestAPI.SFRequestManager;
import RestAPI.SFResponseManager;
import adapter.CampaignMembersAdapter;
import model.CampaignMember;
import utilities.ActivitiesLauncher;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 8/1/2016.
 */
public class FindRegistrationActivity extends SalesforceActivity {

    EditText etSearchCriteria;
    Button btnFind;
    String campaignId;
    SFRequestManager requestManager;
    ListView lstCampaignMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_registration);

        etSearchCriteria = (EditText) findViewById(R.id.etSearchCriteria);
        btnFind = (Button) findViewById(R.id.btnFind);
        lstCampaignMembers = (ListView) findViewById(R.id.lstCampaignMembers);
        campaignId = getIntent().getStringExtra("campaignId");

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestManager = new SFRequestManager(FindRegistrationActivity.this, longOperationDelegate);
                requestManager.doFindRegistrationRequest(etSearchCriteria.getText().toString(), campaignId);
            }
        });
    }

    @Override
    public void onResume(RestClient client) {

    }

    private LongOperationDelegate longOperationDelegate = new LongOperationDelegate() {

        @Override
        public void didFinishLoadWithSuccess(String response) {

            final ArrayList<CampaignMember> campaignMembers = SFResponseManager.parseCampaignMembersResponse(response.toString());
            if (campaignMembers.size() > 0) {
                lstCampaignMembers.setAdapter(new CampaignMembersAdapter(FindRegistrationActivity.this, campaignMembers));
                lstCampaignMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ActivitiesLauncher.openCheckInActivity(getApplicationContext(), campaignMembers.get(position),campaignId);
                    }
                });
            } else {

                ActivitiesLauncher.openRegisterNewMemberActivity(getApplicationContext(), etSearchCriteria.getText().toString(), campaignId);
            }

        }

        @Override
        public void didFinishLoadWithFail(String exception) {
            Utilities.showToast(FindRegistrationActivity.this, "Fail");
        }
    };
}
