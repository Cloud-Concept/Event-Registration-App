package RestAPI;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Campaign;
import model.CampaignMember;

/**
 * Created by Abanoub Wagdy on 8/1/2016.
 */
public class SFResponseManager {

    public static ArrayList<Campaign> parseCampaignsResponse(String s) {

        JSONObject jsonResult = null;
        ArrayList<Campaign> campaigns = new ArrayList<>();
        Gson gson;
        Campaign campaign;

        try {
            jsonResult = new JSONObject(s.toString());
            JSONArray jRecords = jsonResult.getJSONArray("records");
            for (int i = 0; i < jRecords.length(); i++) {
                JSONObject jsoncampaign = jRecords.getJSONObject(i);
                gson = new Gson();
                campaign = gson.fromJson(jsoncampaign.toString(), Campaign.class);
                campaigns.add(campaign);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return campaigns;
    }

    public static ArrayList<CampaignMember> parseCampaignMembersResponse(String s) {

        JSONObject jsonResult = null;
        ArrayList<CampaignMember> campaignMembers = new ArrayList<>();
        Gson gson;
        CampaignMember campaignMember;

        try {
            jsonResult = new JSONObject(s.toString());
            JSONArray jRecords = jsonResult.getJSONArray("records");
            for (int i = 0; i < jRecords.length(); i++) {
                JSONObject jsoncampaign = jRecords.getJSONObject(i);
                gson = new Gson();
                campaignMember = gson.fromJson(jsoncampaign.toString(), CampaignMember.class);
                campaignMembers.add(campaignMember);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return campaignMembers;
    }
}
