package com.example.myhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table users(username text, email text, password text)";
        db.execSQL(query);
        String query2 = "create table cart(username text, product text,price float, otype text)";
        db.execSQL(query2);
        String query3 = "create table orderplace(username text, fullname text,address text,contactno text, pincode int,date text, time text,amount float, otype text)";
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("users", null, contentValues);
        sqLiteDatabase.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase(); // to fetch data from database
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username=? and password=?", str);
        if (cursor.moveToFirst()) result = 1;
        return result;
    }

    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase(); // to fetch data from database
        Cursor cursor = sqLiteDatabase.rawQuery("select * from cart where username=? and product=?", str);
        if (cursor.moveToFirst()) result = 1;
        sqLiteDatabase.close();
        return result;
    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase(); // to fetch data from database
        sqLiteDatabase.delete("cart", "username=? and otype=?", str);
        sqLiteDatabase.close();

    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = sqLiteDatabase.rawQuery("select * from cart where username=? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arrayList.add(product + "$" + price);
            }
            while (c.moveToNext());

            sqLiteDatabase.close();
        }
        return arrayList;
    }

    public void addOrder(String username, String fullname,String address, String contact, int pincode, String date, String time, float amount, String otype ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("fullname",fullname);
        contentValues.put("address",address);
        contentValues.put("contactno",contact);
        contentValues.put("pincode",pincode);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("amount", amount);
        contentValues.put("otype",otype);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("orderplace",null,contentValues);
        sqLiteDatabase.close();
    }
    public ArrayList getOrderData(String username){
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String [] string = new String[1];
        string[0] = username;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from orderplace where username = ?",string);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(1)+"$"+cursor.getString(2)+"$"+cursor.getString(3)+"$"+cursor.getString(4)+"$"+cursor.getString(5)+"$"+cursor.getString(6)+"$"+cursor.getString(7)+"$"+cursor.getString(8));
            }
            while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return arrayList;
    }
    public int checkAppointment(String username, String fullname, String address, String contact, String date, String time ){
        int result = 0;
        String [] str = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from orderplace where username=? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?",str);
        if(cursor.moveToFirst()) result = 1;

        sqLiteDatabase.close();
        return  result;
    }
}