package com.ssict.jsonexample.utils;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
 
public class JSONParser {
 
    static JSONObject jObj = null;
    static String json = "";
	private Context mContext;
 
    // constructor
    public JSONParser(Context context) {
    	mContext = context;
    }
 
    public JSONObject getJSONFromFile(String file) {
    	InputStream is = getClass().getResourceAsStream(file);
    	return parseObject(is);
    }
    
    public JSONObject getJSONFromResource(int resId) {
    	InputStream is = mContext.getResources().openRawResource(resId);
    	return parseObject(is);
    }
    
    
    public JSONObject parseObject(InputStream is) {
    	
    	 try {
             BufferedReader reader = new BufferedReader(new InputStreamReader(
                     is, "iso-8859-1"), 8);
             StringBuilder sb = new StringBuilder();
             String line = null;
             while ((line = reader.readLine()) != null) {
                 sb.append(line + "\n");
             }
             is.close();
             json = sb.toString();
         } catch (Exception e) {
             Log.e("Buffer Error", "Error converting result " + e.toString());
         }
  
         // try parse the string to a JSON object
         try {
             jObj = new JSONObject(json);
         } catch (JSONException e) {
             Log.e("JSON Parser", "Error parsing data " + e.toString());
         }
         
         return jObj;
    	
    }
    
    public JSONObject getJSONFromUrl(String url) {
 
    	InputStream is = null;
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseObject(is);
       
 
    }
}