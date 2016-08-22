package com.cloudconcept;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.os.Handler;
import android.os.Message;

import java.util.logging.LogRecord;

import Interface.EditDialogListener;
import Interface.LongOperationDelegate;
import RestAPI.SFRequestManager;
import model.CampaignMember;
import printer.MainActivity;
import utilities.ActivitiesLauncher;
import utilities.DialogManager;
import utilities.Utilities;

/**
 * Created by Abanoub Wagdy on 8/8/2016.
 */
public class CheckInActivity extends FragmentActivity {

    CampaignMember campaignMember;
    String campaignId;
    Button btnCheckin, btnEdit;
    TextView tvFirstName, tvLastName, tvEmail, tvCompany, tvPhone;
    //
//    protected static final String TAG = "TAG";
//    private static final int REQUEST_CONNECT_DEVICE = 1;
//    private static final int REQUEST_ENABLE_BT = 2;
//
//    BluetoothAdapter mBluetoothAdapter;
//    private UUID applicationUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//    private ProgressDialog mBluetoothConnectProgressDialog;
//    private BluetoothSocket mBluetoothSocket;
//    BluetoothDevice mBluetoothDevice;
    private Button btnPrint;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin);

        campaignMember = (CampaignMember) getIntent().getSerializableExtra("campaignMember");
        campaignId = getIntent().getStringExtra("campaignId");

        btnCheckin = (Button) findViewById(R.id.btnCheckIn);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnPrint = (Button) findViewById(R.id.btnPrint);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvCompany = (TextView) findViewById(R.id.tvCompany);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);

        tvFirstName.setText(Utilities.stringNotNull(campaignMember.getFirstName()));
        tvLastName.setText(Utilities.stringNotNull(campaignMember.getLastName()));
        tvPhone.setText(Utilities.stringNotNull(campaignMember.getPhone()));
        tvCompany.setText(Utilities.stringNotNull(campaignMember.getLeadSource()));
        tvEmail.setText(Utilities.stringNotNull(campaignMember.getEmail()));

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SFRequestManager(CheckInActivity.this, checkInCampaignMember).doCheckInCampaignMember(campaignMember.getFirstName(), campaignMember.getLastName(), campaignMember.getEmail(), campaignMember.getLeadSource(), "", campaignId, campaignMember.getID());
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEdit();
            }
        });
    }

    private void doEdit() {

        DialogManager.showDoneDialog(editDialogListener, campaignMember, this);
    }

    private LongOperationDelegate checkInCampaignMember = new LongOperationDelegate() {

        @Override
        public void didFinishLoadWithSuccess(String response) {
            Utilities.showToast(CheckInActivity.this, "Member Checked in successfully");
            btnPrint.setVisibility(View.VISIBLE);
            btnPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    doPrint();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("campaignMember", campaignMember));
                }
            });
        }

        @Override
        public void didFinishLoadWithFail(String exception) {
            Utilities.showToast(CheckInActivity.this, "Failed to check in member ,please try again later");
        }
    };


    private EditDialogListener editDialogListener = new EditDialogListener() {

        @Override
        public void OnEditClick(String ID, String FirstName, String LastName, String JobTitle, String Company, String Email, String Phone) {
            new SFRequestManager(CheckInActivity.this, new LongOperationDelegate() {
                @Override
                public void didFinishLoadWithSuccess(String response) {
                    Utilities.showToast(CheckInActivity.this, "Success");
                }

                @Override
                public void didFinishLoadWithFail(String exception) {
                    Utilities.showToast(CheckInActivity.this, "Fail");
                }
            }).doEditCampaignMemberData(ID, FirstName, LastName, JobTitle, Company, Email, Phone);
        }
    };

    private void doPrint() {
//
//        // Get a PrintManager instance
//        PrintManager printManager = (PrintManager)
//                getSystemService(Context.PRINT_SERVICE);
//
//        // Set job name, which will be displayed in the print queue
//        String jobName = getString(R.string.app_name) + " Document";
//
//        // Start a print job, passing in a PrintDocumentAdapter implementation
//        // to handle the generation of a print document
//        printManager.print(jobName, new MyPrintDocumentAdapter(getActivity()),
//                null); //
    }

//    private void ListPairedDevices() {
//        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter.getBondedDevices();
//        if (mPairedDevices.size() > 0) {
//            for (BluetoothDevice mDevice : mPairedDevices) {
//                Log.v(TAG, "PairedDevices: " + mDevice.getName() + " " + mDevice.getAddress());
//            }
//        }
//    }
//
//    @Override
//    public void onActivityResult(int mRequestCode, int mResultCode, Intent mDataIntent) {
//        super.onActivityResult(mRequestCode, mResultCode, mDataIntent);
//
//        switch (mRequestCode) {
//            case REQUEST_CONNECT_DEVICE:
//                if (mResultCode == Activity.RESULT_OK) {
//                    Bundle mExtra = mDataIntent.getExtras();
//                    String mDeviceAddress = mExtra.getString("DeviceAddress");
//                    Log.v(TAG, "Coming incoming address " + mDeviceAddress);
//                    mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(mDeviceAddress);
//                    mBluetoothConnectProgressDialog = ProgressDialog.show(this, "Connecting...", mBluetoothDevice.getName() + " : " + mBluetoothDevice.getAddress(), true, false);
//                    Thread mBlutoothConnectThread = new Thread(this);
//                    mBlutoothConnectThread.start();
//                }
//                break;
//
//            case REQUEST_ENABLE_BT:
//                if (mResultCode == Activity.RESULT_OK) {
//                    ListPairedDevices();
//                    Intent connectIntent = new Intent(CheckInActivity.this, BTDeviceList.class);
//                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
//                } else {
//                    Toast.makeText(CheckInActivity.this, "Message", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
//
//
//    public void run() {
//        try {
//            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(applicationUUID);
//            mBluetoothAdapter.cancelDiscovery();
//            mBluetoothSocket.connect();
//            mHandler.sendEmptyMessage(0);
//        } catch (IOException eConnectException) {
//            Log.d(TAG, "CouldNotConnectToSocket", eConnectException);
//            closeSocket(mBluetoothSocket);
//            return;
//        }
//    }
//
//    private void closeSocket(BluetoothSocket nOpenSocket) {
//        try {
//            nOpenSocket.close();
//            Log.d(TAG, "SocketClosed");
//        } catch (IOException ex) {
//            Log.d(TAG, "CouldNotCloseSocket");
//        }
//    }
//
//
//    private Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            mBluetoothConnectProgressDialog.dismiss();
//            Toast.makeText(CheckInActivity.this, "DeviceConnected", Toast.LENGTH_SHORT).show();
//        }
//    };

}
