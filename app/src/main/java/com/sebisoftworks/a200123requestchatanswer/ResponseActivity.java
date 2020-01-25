package com.sebisoftworks.a200123requestchatanswer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResponseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Button btSend;
    TextView tv_from;
    TextView tv_to;
    EditText et_message;
    Button bt_send;
    String to;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_response);
        tv_from = findViewById(R.id.tv_from);
        tv_to = findViewById(R.id.tv_to);
        et_message = findViewById(R.id.et_message);
        bt_send = findViewById(R.id.bt_send);

        Intent i = getIntent();
        from = i.getStringExtra("from");
        to = i.getStringExtra("to");

        tv_from.setText("From: " + from);
        tv_to.setText("To: " + to);

    }


    @Override
    public void onClick(View view) {
        Message m = new Message(from, "1578994420215", et_message.getText().toString());
        ArrayList<Message> messages = MessageAcitvity.mThis.mData;
        messages.add(m);
        try {
            JSONArray jsonArray = new JSONArray();
            for (Message current : messages) {
                jsonArray.put(current.toJSONObject());
            }
            JSONObject jo = new JSONObject();
            jo.put("messages", jsonArray);
            CloudstoreAcces cl = new CloudstoreAcces(CloudstoreAcces.MODE_SEND_MESSAGE);
            String url = "https://webtechlecture.appspot.com/cloudstore/add?owner=shortchat&key=" + to + "&jsonstring=" + jo.toString();
            cl.execute(url);

        } catch (Exception e) {

        }

    }

    public void messageSucces() {

    }
}
