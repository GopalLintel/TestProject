package com.testingmysignletonvolley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleySingleTon {

    ResultInterface mResultCallback;
    RequestQueue queue;

    public VolleySingleTon(Context context, ResultInterface resultInterface) {

       this.mResultCallback=resultInterface;
        queue=Volley.newRequestQueue(context);
    }


    void postDataRequestVolley(final int requestCode, String CallMethod, JSONObject sendObj){
        try {
            String BASE_URL = "http://27.109.18.60";
            String BASE_PATH = "/eNACHAPI/api/Common/";
            String url= BASE_URL + BASE_PATH +CallMethod;
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST,url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null) {
                        mResultCallback.onResult(requestCode, response);
                    }else
                    {
                        Log.e("gopal","request code null "+requestCode);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.onError(requestCode,error);
                }
            });
            queue.add(jsonObj);
        }catch(Exception e){
            Log.e("gopal","sigle ton error "+e);
        }
    }
}
