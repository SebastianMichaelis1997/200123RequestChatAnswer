package com.sebisoftworks.a200123requestchatanswer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    static MessageActivity mThis;
    public ArrayList<Message> mData;
    MyAdapter mMyAdapter;
    String key;
    FloatingActionButton fb;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //Initialize Member Variables
        bundle = savedInstanceState;
        TextView tv_name = findViewById(R.id.textView);
        key = getIntent().getStringExtra("key");
        tv_name.setText(key);
        mData = new ArrayList<>();
        mThis = this;
        fb = findViewById(R.id.floatingActionButton);
        new CloudstoreAccess(mData, CloudstoreAccess.MODE_RETRIEVE_MESSAGE_LIST).execute("https://webtechlecture.appspot.com/cloudstore/get?owner=shortchat&key=" + key);
        mMyAdapter = new MyAdapter(mData);
        RecyclerView resiglerView = findViewById(R.id.recyclerView);
        resiglerView.setLayoutManager(new LinearLayoutManager(this));
        resiglerView.setAdapter(mMyAdapter);
        fb.setOnClickListener(this);
    }

    public void dataSetChanged() {
        mMyAdapter.notifyDataSetChanged();
    }

    public void startResponseActivity(String from, String to) {
        Intent i = new Intent(this, ResponseActivity.class);
        i.putExtra("from", from);
        i.putExtra("to", to);
        startActivity(i);
    }


    @Override
    public void onClick(View v) {

    }
}
