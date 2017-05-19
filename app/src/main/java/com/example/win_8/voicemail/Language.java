package com.example.win_8.voicemail;

import android.content.Context;
import android.speech.tts.TextToSpeech;

/**
 * Created by win-8 on 23-02-2017.
 */

public class Language  {
 TextToSpeech t1;
    String text,s,s1,lang;
    Context context;
    public Language(String text,Context context){
        this.text=text;
        this.context=context;
    }
    public void process(){
        s=text.substring(4);
        if(s.toLowerCase().contains("german")){
            s1=s.replaceAll(" in german","");
            lang ="german";
        }

    }
    public String getText(){
        return s1;
    }
    public String getLanguage(){
        return lang;
    }
}
