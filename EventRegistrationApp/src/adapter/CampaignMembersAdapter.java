package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudconcept.R;

import java.util.ArrayList;

import model.CampaignMember;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 8/8/2016.
 */
public class CampaignMembersAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<CampaignMember> campaignMembers;

    public CampaignMembersAdapter(Activity activity, ArrayList<CampaignMember> campaignMembers) {
        this.activity = activity;
        this.campaignMembers = campaignMembers;
    }

    @Override
    public int getCount() {
        return campaignMembers.size();
    }

    @Override
    public Object getItem(int position) {
        return campaignMembers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String inflater = Context.LAYOUT_INFLATER_SERVICE;

        TextView tvOwner, tvEventName;
        LayoutInflater li = (LayoutInflater) activity.getApplicationContext().getSystemService(inflater);

        if (convertView == null) {
            convertView = li.inflate(R.layout.campaign_member_row, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);

        tvName.setText(campaignMembers.get(position).getFirstName() + " " + campaignMembers.get(position).getLastName());
        tvEmail.setText(Utilities.stringNotNull(campaignMembers.get(position).getEmail()));
        tvPhone.setText(Utilities.stringNotNull(campaignMembers.get(position).getPhone()));

        return convertView;

    }
}
