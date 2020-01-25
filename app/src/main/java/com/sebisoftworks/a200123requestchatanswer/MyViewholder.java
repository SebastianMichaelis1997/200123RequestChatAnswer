package com.sebisoftworks.a200123requestchatanswer;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_from;
    TextView tv_message;
    TextView tv_date;

    public MyViewholder(@NonNull View itemView) {
        super(itemView);
        tv_from = itemView.findViewById(R.id.tv_from);
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_message = itemView.findViewById(R.id.tv_message);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MessageAcitvity.mThis.startResponseActivity(MessageAcitvity.mThis.key, tv_from.getText().toString());
    }
}


