package com.example.win_8.voicemail;

public class Contacts2 {
    int _id;
    String _name;
    String _phone_number;
    String last;
    public Contacts2(){   }
    public Contacts2(int id, String name, String _phone_number,String last){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this.last=last;
    }

    public Contacts2(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
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

    public String getPhoneNumber(){
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
    public String getLast(){
        return this.last;
    }

    public void setLast(String last){
        this.last = last;
    }
}
