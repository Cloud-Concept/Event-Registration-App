package utilities;

import android.content.Context;
import android.content.Intent;

import com.cloudconcept.CheckInActivity;
import com.cloudconcept.FindRegistrationActivity;
import com.cloudconcept.HomeActivity;
import com.cloudconcept.RegisterNewMemberActivity;
import com.cloudconcept.ThankYouActivity;
import com.google.gson.Gson;

import model.Campaign;
import model.CampaignMember;

/**
 * Created by Bibo on 12/27/15.
 */
public class ActivitiesLauncher {

    private static Intent intent;

    public static void openHomeActivity(Context applicationContext) {

        intent = new Intent(applicationContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(intent);

    }

    public static void openAddNewMemberActivity(Context applicationContext) {

        intent = new Intent(applicationContext, RegisterNewMemberActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(intent);
    }

    public static void openFindRegistrationActivity(Context applicationContext, Campaign campaign) {
        intent = new Intent(applicationContext, FindRegistrationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("campaignId", campaign.getID());
        applicationContext.startActivity(intent);
    }

    public static void openCheckInActivity(Context applicationContext, CampaignMember campaignMember, String campaignId) {
        intent = new Intent(applicationContext, CheckInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("campaignMember", campaignMember);
        intent.putExtra("campaignId", campaignId);
        applicationContext.startActivity(intent);

    }

    public static void openRegisterNewMemberActivity(Context applicationContext, String txtSearch, String campaignId) {
        intent = new Intent(applicationContext, RegisterNewMemberActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("txtSearch", txtSearch);
        intent.putExtra("campaignId", campaignId);
        applicationContext.startActivity(intent);
    }

    public static void openThankyouActivity(Context applicationContext) {
        intent = new Intent(applicationContext, ThankYouActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(intent);
    }
}
