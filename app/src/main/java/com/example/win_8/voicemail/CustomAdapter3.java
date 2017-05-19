package com.example.win_8.voicemail;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by win-8 on 16-02-2017.
 */
public class CustomAdapter3 extends ArrayAdapter<Contacts3> {
    ArrayList<Contacts3> contactList;
    Context context;
    TextView reminder,alarm,message,ampm;

    public CustomAdapter3(Context c,ArrayList<Contacts3> contactList) {
        super(c,-1,contactList);
        this.contactList = contactList;
        this.context = c;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_example3, parent, false);

        Contacts3 cn=contactList.get(position);
        reminder=(TextView) rowView.findViewById(R.id.reminder);
        Typeface custom6 = Typeface.createFromAsset(context.getAssets(),"fonts/ptsans.ttf");
        reminder.setTypeface(custom6);
        alarm=(TextView) rowView.findViewById(R.id.alarm);
        message=(TextView) rowView.findViewById(R.id.message);
        Typeface custom7 = Typeface.createFromAsset(context.getAssets(),"fonts/ptsans2.ttf");
        message.setTypeface(custom7);
      ampm=(TextView) rowView.findViewById(R.id.ampm);
        alarm.setText(cn.getName()+":"+cn.getPhoneNumber());
        if(Integer.parseInt(cn.getName())>=12){ampm.setText("P.M");}
        else{ampm.setText("A.M");}
        message.setText(cn.getLast());

       // link.setOnClickListener(new View.OnClickListener() {
        /*    @Override
            public void onClick(View v) {
                TextView t=(TextView)v;
                String d=t.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(d + ""));
                context.startActivity(i);
            }
        });
        title=(TextView) rowView.findViewById(R.id.title);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        text.setText(cn.getLast());
        link.setText(cn.getPhoneNumber());
        title.setText(cn.getName());
*/
        return rowView;
    }

}
