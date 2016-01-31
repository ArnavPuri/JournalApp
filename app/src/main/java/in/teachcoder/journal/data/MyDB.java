package in.teachcoder.journal.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import in.teachcoder.journal.Constants;

/**
 * Created by Arnav on 1/30/2016.
 */
public class MyDB {
    private final Context context;
    private final DBHelper dbhelper;
    private SQLiteDatabase db;
    public MyDB(Context c){
        context=c;
        dbhelper=new DBHelper(c,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
    }
    public void open() throws SQLiteException{
        db = dbhelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public long insertEntry(String title,String content,Long date){
        ContentValues cv=new ContentValues();
        cv.put(Constants.TITLE_NAME,title);
        cv.put(Constants.CONTENT_NAME,content);
        cv.put(Constants.DATE_NAME, date);
        return db.insert(Constants.TABLE_NAME,null,cv);
    }
    public Cursor getDiaries(){
        Cursor c = dbhelper.getWritableDatabase().query(Constants.TABLE_NAME,null,null,null,null,null,null);
        return c;
    }


}
