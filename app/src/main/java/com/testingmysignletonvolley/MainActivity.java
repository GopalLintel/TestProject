package com.testingmysignletonvolley;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements VolleyResultListner {

     final int CALL_MAIN_ACTIVITY_REQEST_CODE=100;
     final int CALL_MAIN_ACTIVITY_REQEST_CODE2=101;
     final int CALL_MAIN_ACTIVITY_REQEST_CODE3=102;
     final int CALL_MAIN_ACTIVITY_REQEST_CODE4=103;

     VolleySingleTon volleySingleTon;
     VolleyResultListner listner=null;
     FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
        listner=new MainActivity();
        volleySingleTon = new VolleySingleTon(listner,this);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new mainFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        getaccountFromServer();
    }

    public void getaccountFromServer()
    {
        volleySingleTon.postDataRequestVolley(CALL_MAIN_ACTIVITY_REQEST_CODE,"AccountType",new JSONObject());
        volleySingleTon.postDataRequestVolley(CALL_MAIN_ACTIVITY_REQEST_CODE2,"BankMaster",new JSONObject());
        volleySingleTon.postDataRequestVolley(CALL_MAIN_ACTIVITY_REQEST_CODE3,"AccountType",new JSONObject());
        volleySingleTon.postDataRequestVolley(CALL_MAIN_ACTIVITY_REQEST_CODE4,"BankMaster",new JSONObject());
    }



    public void alertDialog(final String content,final Activity activity) {

        LayoutInflater li = LayoutInflater.from(activity);
        View promptsView = li.inflate(R.layout.dialog_alert_message, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                activity);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final TextView tvTitle = (TextView) promptsView
                .findViewById(R.id.tvTitle);
        tvTitle.setText(content);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.dismiss();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

    @Override
    public void onSuccess(int requestCode, JSONObject response) {


        if(response!=null) {
                try {

                    switch (requestCode) {
                        case CALL_MAIN_ACTIVITY_REQEST_CODE:

                            if (response.getBoolean("response")) {

                                //Log.e("gopal","request_code "+requestCode);
                                Log.e("gopal",response.toString());

                            } else {

                                alertDialog("" + response.optString("message"), this);
                            }

                            break;

                        case CALL_MAIN_ACTIVITY_REQEST_CODE2:

                            if (response.getBoolean("response")) {

                                //Log.e("gopal","request_code "+requestCode);
                                Log.e("gopal",response.toString());

                            } else {

                                alertDialog("" + response.optString("message"), this);
                            }

                            break;

                        case CALL_MAIN_ACTIVITY_REQEST_CODE3:

                            if (response.getBoolean("response")) {

                                //Log.e("gopal","request_code "+requestCode);
                                Log.e("gopal",response.toString());

                            } else {

                                alertDialog("" + response.optString("message"), this);
                            }

                            break;

                        case CALL_MAIN_ACTIVITY_REQEST_CODE4:

                            if (response.getBoolean("response")) {

                                //Log.e("gopal","request_code "+requestCode);
                                Log.e("gopal",response.toString());

                            } else {

                                alertDialog("" + response.optString("message"), this);
                            }

                            break;


                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                alertDialog("Server Not Responding!\nPlease try again later",this);
            }

    }




    @Override
    public void onError(int requestCode, VolleyError error) {

        alertDialog("Server Not Responding!\nPlease try again later",this);

    }
}
