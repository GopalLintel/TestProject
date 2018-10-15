package com.testingmysignletonvolley;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
    VolleyResultListner listner=null;

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
        listner=new mainFragment();
        volleySingleTon = new VolleySingleTon(listner,activity);
        volleySingleTon.postDataRequestVolley(CALL_MAIN_FRAGMENT_REQEST_CODE,"AccountType",new JSONObject());
    }

    @Override
    public void onSuccess(int requestCode, JSONObject response) {

        if(response!=null)
        {
            Log.e("gopal","response fragment "+response);
            tvFragment.setText("Changed The TEXT OF FRAGMENT");
        }
    }

    @Override
    public void onError(int requestCode, VolleyError error) {

    }
}