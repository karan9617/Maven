package com.example.win_8.voicemail;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by win-8 on 05-02-2017.
 */

public class CustomAdapter extends ArrayAdapter<Contacts> implements CompoundButton.OnCheckedChangeListener {
    private final Context context;
    private final ArrayList<Contacts> array;
    TextView ampm,text1;
    Activity a;
    ArrayList<PendingIntent> intentArray = new ArrayList<>();
    int counter=0;
    Switch s;
    public int sum;

    public CustomAdapter(Context context, ArrayList<Contacts> array2,Activity a) {
        super(context , -1 , array2);
        this.context = context;
        this.array = array2;
        this.a=a;
        //this.p=p;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_example, parent, false);
        Contacts cn = array.get(position);
        ampm=(TextView) rowView.findViewById(R.id.ampm);
         s=(Switch) rowView.findViewById(R.id.switch1);
        text1=(TextView) rowView.findViewById(R.id.text1);
        int f=Integer.parseInt(cn.getName());
        if(f>=12){ampm.setText("PM");}
        else{ampm.setText("AM");}
        text1.setText(cn.getName()+":"+cn.getPhoneNumber());
        s.setOnCheckedChangeListener(this);

        return rowView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){
            View v=(View)buttonView.getParent();
           TextView t=(TextView)v.findViewById(R.id.text1);
            String x=t.getText().toString();
              if(x.length()==4){
                  int u=Character.getNumericValue(x.charAt(0));
                  int z=((Character.getNumericValue(x.charAt(2)))*10)+(Character.getNumericValue(x.charAt(3)));
                  addItem(u,z);
              }
        }else{

        }
    }
    public void addItem(int x,int y){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec=cal.get(Calendar.SECOND);


        // Toast.makeText(this,hours+":"+min+":"+sec+"|"+x+":"+y, Toast.LENGTH_LONG).show();
        if (hours < x) {
            sum = ((x * 3600) + (y * 60)) - ((hours * 3600) + (min * 60))-(sec);
        } else if (x == hours) {
            if (min < y) {
                sum = Math.abs((y - min) * 60)-sec;
            } else {
                sum = (23 * 3600) + (y * 60)-sec;
            }
        } else if (hours > x) {
            sum = Math.abs((((23 - x) * 3600) + (59 - y) * 60) + ((x * 3600) + (y * 60)))-sec;
        }

        Toast.makeText(getContext(),sum+"", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getContext(), ActionClass.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getContext(), counter++, intent, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + ((sum) * 1000), pendingIntent);
        intentArray.add(pendingIntent);

    }
}
