package com.example.win_8.voicemail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler6 extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shopping";
    private static final
    String TABLE_CONTACTS = "shop";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";



    public DBHandler6(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void onUpgrade(SQLiteDatabase db,int j,int i) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addContact(Contacts6 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
    public void deleteList(int x){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="DELETE * FROM "+TABLE_CONTACTS+" WHERE "+KEY_ID+"="+"'"+x+"'";
        db.execSQL(query);
        db.close();
    }
    public void deleteList2(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="DELETE * FROM "+TABLE_CONTACTS;
        db.execSQL(query);
        db.close();
    }
    // code to get the single contact
    Contacts6 getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contacts6 contact = new Contacts6(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return contact;




    }
    /*public String getResult(String name1)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String name = null;
        try
        {
            Cursor c = null;
            c = db.rawQuery("SELECT "+KEY_PH_NO+" FROM "+TABLE_CONTACTS+" WHERE "+KEY_NAME+"="+"'"+name1+"'", null);
            c.moveToFirst();
            name = c.getString(c.getColumnIndex(KEY_PH_NO));
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return name;
    }*/
    // code to get all contacts in a list view
    public List<Contacts6> getAllContacts() {
        List<Contacts6> contactList = new ArrayList<Contacts6>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts6 contact = new Contacts6();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(Contacts6 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contacts6 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM "+TABLE_CONTACTS+ " WHERE "+KEY_NAME+" = "+'"'+contact.getName()+'"';
        db.execSQL(selectQuery);

        db.close();
        /*db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();*/
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}