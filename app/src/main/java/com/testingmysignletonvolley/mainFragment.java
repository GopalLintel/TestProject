package com.testingmysignletonvolley;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class mainFragment extends Fragment implements VolleyResultListner {

    TextView tvFragment;
    Activity activity;
    VolleySingleTon volleySingleTon;

    int CALL_MAIN_FRAGMENT_REQEST_CODE=236;

    public static mainFragment newInstance() {
        return new mainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity=getActivity();
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        tvFragment=view.findViewById(R.id.tvFragment);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final VolleyResultListner listner= new mainFragment();
        volleySingleTon = new VolleySingleTon(listner,getContext());
        final Handler handler = new Handler();

        tvFragment.setText("TExt changed before calling");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                volleySingleTon.postDataRequestVolley(CALL_MAIN_FRAGMENT_REQEST_CODE,"AccountType",new JSONObject());
            }
        }, 6000);

    }

    @Override
    public void onSuccess(int requestCode, JSONObject response) {

        if(response!=null)
        {
            Log.e("gopal","request code "+requestCode);
            Log.e("gopal","response fragment "+response);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvFragment.setText("Changed The TEXT OF FRAGMENT after completion fo call");
                }
            }, 10000);

            //view of fragment got null while accessing the interface method
        }
    }

    @Override
    public void onError(int requestCode, VolleyError error) {

    }
}
