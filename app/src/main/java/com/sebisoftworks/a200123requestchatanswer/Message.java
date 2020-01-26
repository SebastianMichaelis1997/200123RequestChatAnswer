package com.sebisoftworks.a200123requestchatanswer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Message {
    private String sender;
    private Date date;
    private String text;


    public Message(String sender, Date date, String text) {
        this.sender = sender;
        this.date = date;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String toJsonString() {
        String json = "{\"sender\":\"" + sender + "\",\"text\":\"" + text + "\",\"timestamp\":\"" + date.getTime() + "\"}";
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
