package edu.sonoma.group.peg_master;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;



public class DBHandler extends SQLiteOpenHelper {

    //db infooo
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    protected static final String USER_TABLE_NAME = "USER_TABLE";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HISCORE = "hiscore";
    public static final String COLUMN_TIMEPLAYED = "timeplayed";
    public static final String COLUMN_CHESTSOPENED = "chestsopened";
    public static final String COLUMN_NUMSTARS = "numstars";
    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE_NAME + " ("
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_HISCORE + " INT, "
            + COLUMN_TIMEPLAYED + " INT, "
            + COLUMN_CHESTSOPENED + " INT, "
            + COLUMN_NUMSTARS + " INT);";



    //constructor
    public DBHandler(Context context){
        // SQLiteDatabase.CursorFactory is the null arg
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
    }
}