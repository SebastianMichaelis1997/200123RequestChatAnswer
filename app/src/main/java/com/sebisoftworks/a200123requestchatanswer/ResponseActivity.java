package com.sebisoftworks.a200123requestchatanswer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ResponseActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_from;
    TextView tv_to;
    EditText et_message;
    Button bt_send;
    String to;
    String from;
    static ResponseActivity mThis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_response);
        tv_from = findViewById(R.id.tv_from);
        tv_to = findViewById(R.id.tv_to);
        et_message = findViewById(R.id.et_message);
        bt_send = findViewById(R.id.bt_send);
        mThis = this;

        Intent i = getIntent();
        from = i.getStringExtra("from");
        to = i.getStringExtra("to");

        tv_from.setText("From: " + from);
        tv_to.setText("To: " + to);

    }


    @Override
    public void onClick(View view) {
        Message m = new Message(from, new Date(), et_message.getText().toString());
        et_message.setEnabled(false);
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(m);
        try {
            new CloudstoreAccess(messages, CloudstoreAccess.MODE_RETRIEVE_RECIPIENT_MESSAGE_LIST).execute("https://webtechlecture.appspot.com/cloudstore/get?owner=shortchat&key=" + to);
        } catch (Exception e) {
        }
    }

    public void sendMessages(ArrayList<Message> allMessages) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Message current : allMessages) {
                jsonArray.put(current.toJSONObject());
            }
            JSONObject jo = new JSONObject();
            jo.put("messages", jsonArray);
            new CloudstoreAccess(CloudstoreAccess.MODE_SEND_MESSAGE).execute("https://webtechlecture.appspot.com/cloudstore/add?owner=shortchat&key=" + to + "&jsonstring=" + jo.toString());
        } catch (Exception e) {
        }
    }

    public void messageSuccess(Boolean success) {
        if (success) {
            Toast.makeText(this, "Message successfully delivered", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Message delivery failed", Toast.LENGTH_LONG).show();
            et_message.setEnabled(true);
        }


    }
}
