package com.example.win_8.voicemail;

/**
 * Created by win-8 on 05-02-2017.
 */
public class Dictionary {
    public String text;
    public String mainWord;

    public Dictionary(String text){

        this.text=text;
    }

    public void decide(){
        if(text.toLowerCase().contains("define")) {
            mainWord=text.substring(text.indexOf(" ")+1);
        }else{
            mainWord = text.substring(text.lastIndexOf(" ") + 1);

        }
    }
    public String getMainWord(){
        return mainWord;
    }
}
