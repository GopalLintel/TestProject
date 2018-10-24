package com.testingmysignletonvolley;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ResultInterface  {

     final int CALL_MAIN_ACTIVITY_REQEST_CODE=100;
     final int CALL_MAIN_ACTIVITY_REQEST_CODE2=101;
     final int CALL_MAIN_ACTIVITY_REQEST_CODE3=102;
     final int CALL_MAIN_ACTIVITY_REQEST_CODE4=103;

     Context context;

     VolleySingleTon volleySingleTon;
     FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);

        volleySingleTon=new VolleySingleTon(MainActivity.this,MainActivity.this);
        context=this;


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


    @Override
    public void onResult(int requestCode, JSONObject response) {
        if(response!=null) {
            try {

                switch (requestCode) {
                    case CALL_MAIN_ACTIVITY_REQEST_CODE:

                        if (response.getBoolean("response")) {

                            //Log.e("gopal","request_code "+requestCode);
                            Log.e("gopal",response.toString());
                            if(context!=null)
                                Log.e("gopal"," do no nulllll");

                        }

                        break;

                    case CALL_MAIN_ACTIVITY_REQEST_CODE2:

                        if (response.getBoolean("response")) {

                            // Log.e("gopal","request_code "+requestCode);
                            Log.e("gopal",response.toString());

                        }

                        break;

                    case CALL_MAIN_ACTIVITY_REQEST_CODE3:

                        if (response.getBoolean("response")) {

                            //Log.e("gopal","request_code "+requestCode);
                            Log.e("gopal",response.toString());

                        }

                        break;

                    case CALL_MAIN_ACTIVITY_REQEST_CODE4:

                        if (response.getBoolean("response")) {

                            //Log.e("gopal","request_code "+requestCode);
                            Log.e("gopal",response.toString());

                        }
                        break;


                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onError(int reqId, VolleyError error) {

    }
}
