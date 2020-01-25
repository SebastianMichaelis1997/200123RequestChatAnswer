package com.sebisoftworks.a200123requestchatanswer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        String message = et_message.getText().toString();
        et_message.setText("To: " + to + "\nFrom: " + from + "\nMessage: " + message);
        CloudstoreAcces cl = new CloudstoreAcces(0);
        //cl.execute()
    }


}
