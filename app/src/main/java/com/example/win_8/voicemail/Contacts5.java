package com.example.win_8.voicemail;

/**
 * Created by win-8 on 24-12-2016.
 */
public class Contacts5 {
    int _id;
    String _name;
    public Contacts5(){   }
    public Contacts5(int id, String name){
        this._id = id;
        this._name = name;
        //this._zone=_zone;
        //this._unique=_zone;
    }

    public Contacts5(String name){
        this._name = name;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }
}