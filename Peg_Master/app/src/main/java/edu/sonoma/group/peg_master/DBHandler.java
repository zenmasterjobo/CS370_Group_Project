package edu.sonoma.group.peg_master;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;



public class DBHandler extends SQLiteOpenHelper {

    //db infooo


    private static final int DATABASE_VERSION = 27;
    private static final String DATABASE_NAME = "users.db";
    protected static final String USER_TABLE_NAME = "USER_TABLE";
    //USER TABLE
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HISCORE = "hiscore";
    public static final String COLUMN_TIMEPLAYED = "timeplayed";
    public static final String COLUMN_CHESTSOPENED = "chestsopened";
    public static final String COLUMN_NUMSTARS = "numstars";
    public static final String COLUMN_BMUSIC = "bmusic";
    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE_NAME + " ("
            + COLUMN_UID + " integer primary key autoincrement, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_HISCORE + " INT, "
            + COLUMN_TIMEPLAYED + " INT, "
            + COLUMN_CHESTSOPENED + " INT, "
            + COLUMN_NUMSTARS + " INT, "
            + COLUMN_BMUSIC + " INT);";

    //COMPLETED LEVELS TABLE
    protected static final String COMPLETED_LEVELS_TABLE_NAME = "COMPLETED_LEVELS_TABLE";
    public static final String COLUMN_LID = "lid";
    public static final String COLUMN_STARS = "stars";
    public static final String COLUMN_NUMCHESTS = "numchests";
    public static final String CREATE_COMPLETED_LEVELS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + COMPLETED_LEVELS_TABLE_NAME + " ("
            + COLUMN_LID + " INT, "
            + COLUMN_STARS + " INT, "
            + COLUMN_NUMCHESTS + " INT, "
            + COLUMN_UID + " INT);";





    //constructor
    public DBHandler(Context context){
        // SQLiteDatabase.CursorFactory is the null arg
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_COMPLETED_LEVELS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMPLETED_LEVELS_TABLE_NAME);
        onCreate(db);
    }
}