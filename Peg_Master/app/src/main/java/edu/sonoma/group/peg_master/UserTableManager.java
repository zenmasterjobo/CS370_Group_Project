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
        values.put(COLUMN_NUMSTARS,userData.getNumStars());
        values.put(COLUMN_TIMEPLAYED,userData.getPlayed());
        if(userData.getMusic())
            values.put(COLUMN_BMUSIC,1);
        else
            values.put(COLUMN_BMUSIC,0);
        //put data into db
        db.insert(USER_TABLE_NAME, null, values);


        db.close();
    }


    public void addCompletedLevel(User aUser,int completedLevels){

        ArrayList<CompletedLevel> completedLevelsArray = aUser.getCompletedLevels();
        int size = completedLevelsArray.size();
        //check for already completed levels to update
       // String myQuery = "SELECT * FROM " + COMPLETED_LEVELS_TABLE_NAME + " WHERE +" + COLUMN_UID + "="
         //       +Integer.toString(aUser.getID());
        //Cursor mycursor = db.rawQuery(myQuery, null);
        ArrayList<CompletedLevel> dbLevels = getLevels(aUser);
        SQLiteDatabase db = this.getWritableDatabase();


        for(int i =0;i<dbLevels.size();i++){
            if(dbLevels.get(i).getNumStars() < aUser.getCompletedLevels().get(i).getNumStars()){
                ContentValues cv = new ContentValues();
                //String whereClause = COLUMN_UID + "=" + Integer.toString(aUser.getID()) + "and "
                  //      + COLUMN_LID + "=" + Integer.toString(i +1);
               // String whereClause = COLUMN_LID + " = " + Integer.toString(dbLevels.get(i).getLID());
                String[] args = new String[]{Integer.toString(aUser.getID()),Integer.toString(i +1)};
                String whereClause = COLUMN_UID + "=?" + " AND "
                        + COLUMN_LID + "=?";
                Log.v("myApp",whereClause);

                cv.put(COLUMN_STARS, aUser.getCompletedLevels().get(i).getNumStars());
                db.update(COMPLETED_LEVELS_TABLE_NAME,cv,whereClause,args);

            }

        }


        for(int i =0;i <completedLevels;i++){
            ContentValues values = new ContentValues();
            CompletedLevel aCLevel = completedLevelsArray.get(size -1-i);
            values.put(COLUMN_LID,aCLevel.getLID());
            values.put(COLUMN_STARS,aCLevel.getNumStars());
            values.put(COLUMN_NUMCHESTS,aCLevel.getNumChests());
            values.put(COLUMN_UID,aUser.getID());
            db.insert(COMPLETED_LEVELS_TABLE_NAME,null,values);
            Log.v("myApp","COMPLETED LEVEL: " + Integer.toString(aCLevel.getLID()));


        }


        db.close();

    }

    public void updateUserMusic(User aUser){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean current = aUser.getMusic();
        String TAG = "myApp";
        Log.v(TAG,"BEFORE UPDATE: " + Boolean.toString(aUser.getMusic()));
        int val = 1;
        if(current)
            val =0;
        //String myQuery = "UPDATE " + USER_TABLE_NAME + " SET " + COLUMN_BMUSIC + " = " + val
          //       + " WHERE " + COLUMN_NAME + " = " + '"' + aUser.getName() + '"';
        //db.rawQuery(myQuery, null);
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BMUSIC,val);
        db.update(USER_TABLE_NAME,cv,null,null);
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
                    User aUser = new User(myCursor.getString(1));
                    aUser.setID(myCursor.getInt(0));
                    aUser.setScore(myCursor.getInt(2));
                    aUser.setPlayed(myCursor.getInt(3));
                    aUser.setChestsOpened(myCursor.getInt(4));
                    aUser.setNumStars(myCursor.getInt(5));
                    if(myCursor.getInt(6) != 0)
                        aUser.setMusic(true);
                    else
                        aUser.setMusic(false);
                    Log.d("UserTableManager",aUser.getName());
                    userList.add(aUser);
                }while(myCursor.moveToNext());
            }
        }finally {
            db.close();
        }
        Log.d("UserTableManager", Integer.toString(userList.size()));
        return userList;
    }

    public ArrayList<CompletedLevel> getLevels(User aUser){
        String myQuery = "SELECT * FROM " + COMPLETED_LEVELS_TABLE_NAME + " WHERE " + COLUMN_UID +
                "=" + aUser.getID();

        SQLiteDatabase db = getReadableDatabase();
        Cursor myCursor = db.rawQuery(myQuery, null);
        ArrayList<CompletedLevel> completedLevels = new ArrayList<>();
        try{
            if(myCursor.moveToFirst()){
                do{
                    CompletedLevel aCLevel = new CompletedLevel(myCursor.getInt(0),myCursor.getInt(1),myCursor.getInt(2));
                    completedLevels.add(aCLevel);

                }while(myCursor.moveToNext());
            }
        }finally {
            db.close();
        }
        Log.d("UserTableManager", "CLEVELS: " + Integer.toString(completedLevels.size()));
        db.close();
        return completedLevels;
    }

    //return a user by name
    public User getUser(String name){
        //query for user with name
        String myQuery = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + COLUMN_NAME + "=" + '"'+ name + '"';
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor myCursor = db.rawQuery(myQuery,null);
        if(myCursor.moveToFirst()){
            User aUser = new User(myCursor.getString(1));
            aUser.setID(myCursor.getInt(0));
            aUser.setScore(myCursor.getInt(2));
            aUser.setPlayed(myCursor.getInt(3));
            aUser.setChestsOpened(myCursor.getInt(4));
            aUser.setNumStars(myCursor.getInt(5));
            if(myCursor.getInt(6)!=0)
                aUser.setMusic(true);
            else
                aUser.setMusic(false);
            Log.d("UserTableManager",aUser.getName());
            db.close();
            return aUser;
            }
            else{
                db.close();
            }
        //if we didnt find a user with name, return user with name ERROR
        return new User("ERROR");
    }



}
