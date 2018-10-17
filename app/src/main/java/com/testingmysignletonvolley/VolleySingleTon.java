package com.testingmysignletonvolley;

import android.app.Fragment;
import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleySingleTon {

    private static final String TAG = "gopal";
    private static VolleySingleTon instance = null;

    public static String BASE_URL = "http://27.109.18.60";
    public static String BASE_PATH = "/eNACHAPI/api/Common/";

    RequestQueue queue;

    VolleyResultListner mResultCallback = null;
    Context mContext;

    VolleySingleTon(VolleyResultListner resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
        queue = Volley.newRequestQueue(mContext);
    }
    public void postDataRequestVolley(final int requestCode, String CallMethod,JSONObject sendObj){
        try {
            String url=BASE_URL+BASE_PATH+CallMethod;
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST,url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.onSuccess(requestCode,response);
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
        }
    }
    public void postDataRequestVolleyWithHeadersAUTH(final int requestCode, String CallMethod,JSONObject sendObj){
        try {
            String url=BASE_URL+BASE_PATH+CallMethod;
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST,url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.onSuccess(requestCode,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.onError(requestCode,error);
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    String credentials = "username:password";
                    String auth = "Basic "
                            + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    //headers.put("Accept", "application/json");
                    headers.put("content-type", "application/json");
                    headers.put("authorization", auth);
                    return headers;
                }
            }
                    ;
            queue.add(jsonObj);
        }catch(Exception e){
        }
    }

    public void getDataRequestVolley(final int requestCode, String CallMethod){
        try {

            String url=BASE_URL+BASE_PATH+CallMethod;
            JsonObjectRequest jsonObj =new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.onSuccess(requestCode, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.onError(requestCode, error);
                }
            });
            queue.add(jsonObj);
        }catch(Exception e){
        }
    }

}
