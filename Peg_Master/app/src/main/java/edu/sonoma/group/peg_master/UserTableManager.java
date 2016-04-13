package edu.sonoma.group.peg_master;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 4/12/16.
 */
public class UserTableManager extends DBHandler {

    public UserTableManager(Context context){
        super(context);
    }

    public void addUserData(User userData){
        //get db
        SQLiteDatabase db = this.getWritableDatabase();
        //create contentvalues to store data we want in db
        ContentValues values = new ContentValues();
        //store all data from userData into values
        values.put(COLUMN_NAME,userData.getName());
        values.put(COLUMN_HISCORE,userData.getScore());
        values.put(COLUMN_TIMEPLAYED, userData.getPlayed());
        //put data into db
        db.insert(USER_TABLE_NAME, null, values);


        db.close();
    }


    //return a list of all users
    public List<User> getAllUsers(){
        //list to store each row of db in a User obj
        List<User> userList = new ArrayList<User>();
        String myQuery = "SELECT * FROM " + USER_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor is required to store result from myQuery
        Cursor myCursor = db.rawQuery(myQuery,null);
        try{
            //if theres userdata create User obj to store data
            if(myCursor.moveToFirst()){
                do {
                    User aUser = new User();
                    aUser.setName(myCursor.getString(0));
                    aUser.setScore(myCursor.getInt(1));
                    aUser.setPlayed(myCursor.getInt(2));
                    aUser.setChestsOpened(myCursor.getInt(3));
                    aUser.setNumStars(myCursor.getInt(4));
                    Log.d("UserTableManager",aUser.getName());
                    userList.add(aUser);
                }while(myCursor.moveToNext());
            }
        }finally {
            db.close();
        }
        Log.d("UserTableManager",Integer.toString(userList.size()));
        return userList;
    }



}
