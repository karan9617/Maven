package com.example.win_8.voicemail;

import android.app.Activity;

/**
 * Created by win-8 on 15-02-2017.
 */
public class Reminder extends Activity {
    String text,str1,str2,response=null;
    int x,y;
    int c=0;
    public Reminder(String text){
        this.text=text;
    }
    public void extract(){
        String[] arr = text.split(" ");
        for(String a:arr){
            if(a.contains("0")||a.contains("1")||a.contains("2")||a.contains("3")||a.contains("4")||
                    a.contains("5")||a.contains("6")||a.contains("7")||a.contains("8")||a.contains("9")) {
                if(c==0) {
                    str1 = a;
                    c++;
                }else{
                    str2=a;
                }
            }

        }
        if(str1==null&&str2==null){
            response="Please enter the reminder with time";
            x=0;
            y=0;
        }
        if(str1!=null&&str2==null){
            if(str1.length()==4){
                x = Character.getNumericValue(str1.charAt(0));
                y= ((Character.getNumericValue(str1.charAt(2)))*10)+(Character.getNumericValue(str1.charAt(3)));


            }
            if(str1.length()==5){
                x = ((Character.getNumericValue(str1.charAt(0)))*10)+Character.getNumericValue(str1.charAt(1));
                y= ((Character.getNumericValue(str1.charAt(3)))*10)+(Character.getNumericValue(str1.charAt(4)));

            }
        }
        if(str1!=null&&str2!=null){
            if(str1.length()==1){
                x = ((Character.getNumericValue(str1.charAt(0))));
                y = ((Character.getNumericValue(str2.charAt(0))));
            }
            if(str1.length()==2){
                x = ((Character.getNumericValue(str1.charAt(0)))*10)+Character.getNumericValue(str1.charAt(1));
                y = ((Character.getNumericValue(str2.charAt(0))));
            }

        }
        //response logic
            if(arr[0].toLowerCase().equals("remind"))
            {
                if(arr[1].toLowerCase().equals("me"))
                {
                    StringBuffer bf=new StringBuffer();
                    for(int i=2;i<=((arr.length)-1);i++){
                       bf= bf.append(arr[i]+" ");
                    }
                    response=bf.toString();
                }
               else if(arr[1].toLowerCase().equals("that"))
                {
                    StringBuffer bf=new StringBuffer();
                    for(int i=2;i<=((arr.length)-1);i++){
                        bf.append(arr[i]);
                    }
                    response=bf.toString();
                }
            }
            if(arr[0].toLowerCase().equals("set"))
            {
                StringBuffer bf=new StringBuffer();
                for(int i=3;i<=((arr.length)-1);i++){
                    bf.append(arr[i]);
                }
                response=bf.toString();
            }



    }
    public int getHours(){
       return x;
    }
    public int getMinutes(){
        return y;
    }
    public String getResponse(){
        return response;
    }
}
