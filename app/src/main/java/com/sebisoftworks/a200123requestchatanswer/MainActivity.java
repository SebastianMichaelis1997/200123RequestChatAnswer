package com.sebisoftworks.a200123requestchatanswer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static MainActivity mThis;
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThis = this;
        mData = new ArrayList<String>();
        CloudstoreAccess cl = new CloudstoreAccess(mData, CloudstoreAccess.MODE_GET_KEYS);

        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        ListView list = findViewById(R.id.list);
        list.setAdapter(mArrayAdapter);
        cl.execute("https://webtechlecture.appspot.com/cloudstore/listkeys?owner=shortchat");
        list.setOnItemClickListener(this);
    }

    public void dataSetChanged() {
        mArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, MessageActivity.class);
        String key = (String) mData.get(position);
        i.putExtra("key", key);
        startActivity(i);
    }
}
