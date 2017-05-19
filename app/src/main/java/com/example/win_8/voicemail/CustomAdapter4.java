package com.example.win_8.voicemail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by win-8 on 16-02-2017.
 */
public class CustomAdapter4 extends ArrayAdapter<String> {
    Context context;
    ImageView imageView;
    TextView textView;
    ArrayList<String> names;

    public CustomAdapter4(Context c,ArrayList<String> names) {
        super(c,-1);
       this.names=names;
        this.context = c;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_example4, parent, false);
        textView=(TextView)rowView.findViewById(R.id.restaurant_text);
        imageView=(ImageView)rowView.findViewById(R.id.restaurant_image);
        imageView.setImageResource(R.drawable.blue);
            String n=names.get(position);
        textView.setText(n);
        //new LoadImageTask(this).execute(image);
        return rowView;
    }

}