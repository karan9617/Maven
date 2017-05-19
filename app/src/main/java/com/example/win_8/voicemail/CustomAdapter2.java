package com.example.win_8.voicemail;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter2 extends ArrayAdapter<Contacts2> {
    ArrayList<Contacts2> contactList;
    Context context;
    TextView text,link,title;
    LayoutInflater inflater;
    Activity a;

    public CustomAdapter2(Context c,int resource,ArrayList<Contacts2> contactList,Activity a) {
        super(c,resource,contactList);
        this.contactList = contactList;
        this.context = c;
        this.a=a;
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_example2, parent, false);
        Contacts2 cn=contactList.get(position);
        text=(TextView) rowView.findViewById(R.id.text);
        link=(TextView) rowView.findViewById(R.id.link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TextView t=(TextView)v;
                String d=t.getText().toString();
                Toast.makeText(context,d+"",Toast.LENGTH_LONG).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(d+""));
                a.startActivity(browserIntent);
              // Intent i = new Intent(Intent.ACTION_VIEW);
               // i.setData(Uri.parse(d+""));
                //context.startActivity(i);
            }
        });
        title=(TextView) rowView.findViewById(R.id.title);
        //link.setMovementMethod(LinkMovementMethod.getInstance());
        text.setText(cn.getLast());
        link.setText(cn.getPhoneNumber());
        title.setText(cn.getName());
       // Linkify.addLinks(link, Linkify.WEB_URLS);
        return rowView;
    }



}