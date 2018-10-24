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


public class mainFragment extends Fragment implements ResultInterface {

    TextView tvFragment;
    VolleySingleTon volleySingleTon;
    Activity activity;

    public static mainFragment newInstance(String param1, String param2) {
        mainFragment fragment = new mainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        tvFragment=view.findViewById(R.id.tvFragment);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity=getActivity();
        volleySingleTon=new VolleySingleTon(activity,mainFragment.this);
        tvFragment.setText("Start text changed");
        volleySingleTon.postDataRequestVolley(190,"AccountType",new JSONObject());


    }

    @Override
    public void onResult(int requestCode, JSONObject response) {
        Log.e("gopal","req code "+requestCode);
        Log.e("gopal","fragment response  "+response);
        tvFragment.setText("got the response");
    }

    @Override
    public void onError(int reqId, VolleyError error) {

    }
}
