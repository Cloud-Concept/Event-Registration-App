package RestAPI;

import android.app.Activity;
import android.widget.EditText;

import com.cloudconcept.R;
import com.salesforce.androidsdk.app.SalesforceSDKManager;
import com.salesforce.androidsdk.rest.ClientManager;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import Interface.LongOperationDelegate;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 8/8/2016.
 */
public class SFRequestManager {

    private LongOperationDelegate longOperationDelegate;
    Activity activity;
    String soql;
    private HashMap<String, Object> fields;

    public SFRequestManager(Activity activity, LongOperationDelegate longOperationDelegate) {
        this.longOperationDelegate = longOperationDelegate;
        this.activity = activity;
    }

    public void doFindRegistrationRequest(final String txtSearchCriteria, final String campaignId) {

        Utilities.showloadingDialog(activity);
        new ClientManager(activity, SalesforceSDKManager.getInstance().getAccountType(), SalesforceSDKManager.getInstance().getLoginOptions(), SalesforceSDKManager.getInstance().shouldLogoutWhenTokenRevoked()).getRestClient(activity, new ClientManager.RestClientCallback() {
            @Override
            public void authenticatedRestClient(final RestClient client) {
                String str = client.getAuthToken();
                if (client == null) {
                    Utilities.dismissLoadingDialog();
                    Utilities.showToast(activity, "Client not authenticated");
                    activity.finish();

                } else {
                    soql = SoqlStatements.getSoqlFilteredCampaignMembers(txtSearchCriteria, campaignId);
                    try {
                        RestRequest restRequest = RestRequest.getRequestForQuery(activity.getString(R.string.api_version), soql);
                        client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
                            @Override
                            public void onSuccess(RestRequest request, RestResponse response) {
                                Utilities.dismissLoadingDialog();
                                longOperationDelegate.didFinishLoadWithSuccess(response.toString());
                            }

                            @Override
                            public void onError(Exception exception) {
                                Utilities.dismissLoadingDialog();
                                longOperationDelegate.didFinishLoadWithSuccess(exception.toString());
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void doRegisterNewCampaignMember(final EditText etFirstName, final EditText etLastName, final EditText etEmail, final EditText etCompany, final EditText etJobTitle, final EditText etPhone) {

        Utilities.showloadingDialog(activity, "Registering ...");
        new ClientManager(activity, SalesforceSDKManager.getInstance().getAccountType(), SalesforceSDKManager.getInstance().getLoginOptions(), SalesforceSDKManager.getInstance().shouldLogoutWhenTokenRevoked()).getRestClient(activity, new ClientManager.RestClientCallback() {
            @Override
            public void authenticatedRestClient(RestClient client) {
                if (client == null) {
                    SalesforceSDKManager.getInstance().logout(activity);
                    return;
                } else {
                    fields = new HashMap<>();
                    fields.put("FirstName", etFirstName.getText().toString());
                    fields.put("LastName", Utilities.stringNotNull(etLastName.getText().toString()));
                    fields.put("Title", Utilities.stringNotNull(etJobTitle.getText().toString()));
                    fields.put("Company", Utilities.stringNotNull(etCompany.getText().toString()));
                    fields.put("Email", Utilities.stringNotNull(etEmail.getText().toString()));
                    fields.put("Phone", Utilities.stringNotNull(etPhone.getText().toString()));

                    try {
                        RestRequest restRequest = RestRequest.getRequestForCreate(activity.getString(R.string.api_version), "Lead", fields);
                        client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
                            @Override
                            public void onSuccess(RestRequest request, RestResponse response) {
                                Utilities.dismissLoadingDialog();
                                longOperationDelegate.didFinishLoadWithSuccess(response.toString());
                            }

                            @Override
                            public void onError(Exception exception) {
                                Utilities.dismissLoadingDialog();
                                longOperationDelegate.didFinishLoadWithSuccess(exception.toString());
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public void doCheckInCampaignMember(final String etFirstName, final String etLastName, final String etEmail, final String etCompany, final String etJobTitle, final String campaignId, final String campaignMemberId) {

        Utilities.showloadingDialog(activity, "Checking In ....");
        new ClientManager(activity, SalesforceSDKManager.getInstance().getAccountType(), SalesforceSDKManager.getInstance().getLoginOptions(), SalesforceSDKManager.getInstance().shouldLogoutWhenTokenRevoked()).getRestClient(activity, new ClientManager.RestClientCallback() {
            @Override
            public void authenticatedRestClient(RestClient client) {
                if (client == null) {
                    SalesforceSDKManager.getInstance().logout(activity);
                    return;
                } else {
                    RestRequest restRequest = null;
                    if (campaignMemberId != null) {
                        fields = new HashMap<>();
                        fields.put("CampaignId", campaignId);
                        fields.put("Checked_In__c", true);
                        try {
                            restRequest = RestRequest.getRequestForUpdate(activity.getString(R.string.api_version), "CampaignMember", campaignMemberId, fields);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        fields = new HashMap<>();
                        fields.put("CampaignId", campaignId);
                        fields.put("FirstName", etFirstName);
                        fields.put("LastName", Utilities.stringNotNull(etLastName));
                        if (etJobTitle == null || etJobTitle.equals(""))
                            fields.put("Title", Utilities.stringNotNull(etJobTitle));
                        fields.put("CompanyOrAccount", Utilities.stringNotNull(etCompany));
                        fields.put("Email", Utilities.stringNotNull(etEmail));
                        fields.put("Checked_In__c", true);
                        try {
                            restRequest = RestRequest.getRequestForCreate(activity.getString(R.string.api_version), "CampaignMember", fields);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
                        @Override
                        public void onSuccess(RestRequest request, RestResponse response) {
                            Utilities.dismissLoadingDialog();
                            longOperationDelegate.didFinishLoadWithSuccess(response.toString());
                        }

                        @Override
                        public void onError(Exception exception) {
                            Utilities.dismissLoadingDialog();
                            longOperationDelegate.didFinishLoadWithSuccess(exception.toString());
                        }
                    });
                }
            }
        });
    }

    public void doEditCampaignMemberData(final String ID, final String FirstName, final String LastName, final String JobTitle, final String Company, final String Email, final String Phone) {
        Utilities.showloadingDialog(activity, "Editing .... ");
        new ClientManager(activity, SalesforceSDKManager.getInstance().getAccountType(), SalesforceSDKManager.getInstance().getLoginOptions(), SalesforceSDKManager.getInstance().shouldLogoutWhenTokenRevoked()).getRestClient(activity, new ClientManager.RestClientCallback() {
            @Override
            public void authenticatedRestClient(RestClient client) {
                if (client == null) {
                    SalesforceSDKManager.getInstance().logout(activity);
                    return;
                } else {
                    fields = new HashMap<>();
                    fields.put("FirstName", FirstName);
                    fields.put("LastName", Utilities.stringNotNull(LastName));
                    if (JobTitle == null || JobTitle.equals(""))
                        fields.put("Title", Utilities.stringNotNull(JobTitle));
                    fields.put("CompanyOrAccount", Utilities.stringNotNull(Company));
                    fields.put("Email", Utilities.stringNotNull(Email));
                    fields.put("Phone", Utilities.stringNotNull(Phone));
                    try {
                        RestRequest restRequest = RestRequest.getRequestForUpdate(activity.getString(R.string.api_version), "CampaignMember", ID, fields);
                        client.sendAsync(restRequest, new RestClient.AsyncRequestCallback() {
                            @Override
                            public void onSuccess(RestRequest request, RestResponse response) {
                                Utilities.dismissLoadingDialog();
                                longOperationDelegate.didFinishLoadWithSuccess(response.toString());
                            }

                            @Override
                            public void onError(Exception exception) {
                                Utilities.dismissLoadingDialog();
                                longOperationDelegate.didFinishLoadWithSuccess(exception.toString());
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}