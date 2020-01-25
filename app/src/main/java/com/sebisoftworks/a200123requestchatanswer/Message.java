package com.sebisoftworks.a200123requestchatanswer;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    private String sender;
    private String date;
    private String text;


    public Message(String sender, String date, String text) {
        this.sender = sender;
        this.date = date;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String toJsonString() {
        String json = "{\"sender\":\"" + sender + "\",\"text\":\"" + text + "\",\"timestamp\":\"" + date + "\"}";
        return json;
    }

    public JSONObject toJSONObject() {
        try {
            return new JSONObject(toJsonString());
        } catch (JSONException e) {
            return null;
        }
    }
}
