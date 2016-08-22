package dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cloudconcept.R;

import Interface.EditDialogListener;
import model.CampaignMember;

/**
 * Created by Abanoub Wagdy on 8/21/2016.
 */
public class EditDialogFragment extends DialogFragment {

    private static EditDialogListener mListener;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etJobTitle;
    private EditText etCompany;
    private EditText etEmail;
    private EditText etPhone;
    private Button btnEdit, btnCancel;
    CampaignMember campaignMember;

    public static EditDialogFragment newInstance(EditDialogListener listener, CampaignMember campaignMember) {

        mListener = listener;

        EditDialogFragment frag = new EditDialogFragment();

        Bundle args = new Bundle();
        args.putSerializable("campaignMember", campaignMember);
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_done, container, false);
        view.setMinimumWidth(1000);
        view.setMinimumHeight(1000);

        getDialog().setTitle("Edit Member Data");

        campaignMember = (CampaignMember) getArguments().get("campaignMember");

        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etJobTitle = (EditText) view.findViewById(R.id.etJobTitle);
        etCompany = (EditText) view.findViewById(R.id.etCompany);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPhone = (EditText) view.findViewById(R.id.etPhone);

        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mListener.OnEditClick(campaignMember.getID(), etFirstName.getText().toString(), etLastName.getText().toString(), etJobTitle.getText().toString(), etCompany.getText().toString(), etEmail.getText().toString(), etPhone.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dismiss();
    }
}
