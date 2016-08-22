package utilities;

import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import Interface.EditDialogListener;
import dialogs.EditDialogFragment;
import model.CampaignMember;

/**
 * Created by Abanoub Wagdy on 8/21/2016.
 */
public class DialogManager {

    public static void showDoneDialog(EditDialogListener listener, CampaignMember campaignMember, FragmentActivity fragmentActivity) {

        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        removeShowingDialogIfExist(fragmentActivity);

        EditDialogFragment dialogFragment = EditDialogFragment.newInstance(listener, campaignMember);

        dialogFragment.show(ft, "dialog");
    }

    public static void removeShowingDialogIfExist(FragmentActivity fragmentActivity) {
        Fragment prev = fragmentActivity.getSupportFragmentManager().findFragmentByTag("dialog");

        if (prev != null) {

            fragmentActivity.getSupportFragmentManager().beginTransaction().remove(prev).commit();

        }
    }
}
