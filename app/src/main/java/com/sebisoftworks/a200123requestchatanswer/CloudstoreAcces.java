package com.sebisoftworks.a200123requestchatanswer;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class CloudstoreAcces extends AsyncTask<String, Integer, String> {
    ArrayList mData;
    int mode;
    public static final int MODE_GET_KEYS = 0;
    public static final int MODE_RETRIEVE_MESSAGE_LIST = 1;
    public static final int MODE_SEND_MESSAGE = 2;

    public CloudstoreAcces(ArrayList aData, int aMode) {
        mData = aData;
        mode = aMode;
    }

    public CloudstoreAcces(int aMode) {
        mode = aMode;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    response = response + line;
                }
            } while (line != null);
        } catch (IOException e) {

        }
        return response;
    }

    protected void onPostExecute(String aResponse) {
        try {
            switch (mode) {
                case MODE_GET_KEYS: {
                    JSONArray jsonArray = new JSONArray(aResponse);
                    if (jsonArray.length() == 0) {
                        mData.add("Invalid User Key");
                        MainActivity.mThis.dataSetChanged();
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        mData.add(jsonArray.getJSONObject(i).getString("key"));
                    }
                    MainActivity.mThis.dataSetChanged();
                }
                break;
                case MODE_RETRIEVE_MESSAGE_LIST: {
                    //Response is list of messages
                    JSONArray jsonArray = new JSONObject(aResponse).getJSONArray("messages");
                    if (jsonArray.length() == 0) {
                        mData.add("Invalid User Key");
                        MessageAcitvity.mThis.dataSetChanged();
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject current = jsonArray.getJSONObject(i);
                        if (current.has("sender") && current.has("timestamp") && current.has("text")) {
                            String from = current.getString("sender");
                            String text = current.getString("text");
                            String date = current.getString("timestamp");
                            mData.add(new Message(from, date, text));
                        }
                    }
                    MessageAcitvity.mThis.dataSetChanged();
                }
                break;
                case MODE_SEND_MESSAGE: {
                    JSONObject jo = new JSONObject(aResponse);
                    if (jo.has("status")) {
                        if (jo.get("status").toString().equals("ok")) {
                            ResponseActivity.mThis.messageSuccess(true);
                        } else {
                            ResponseActivity.mThis.messageSuccess(false);

                        }
                    } else {
                        ResponseActivity.mThis.messageSuccess(false);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
