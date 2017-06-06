package vnits.vn.quanlysinhvien.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vnits.vn.quanlysinhvien.model.Users;


public class MyDatabaseAccess extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static String DB_NAME = "mydataa.db";
    private static SQLiteDatabase db = null;
    //Table
    private static final String table_User = "Users";
    private static final String table_Login = "Logins";
    private static final String col_User_mssv = "Mssv";
    private static final String col_User_pass = "Password";
    private static final String col_User_tokens = "Tokens";
    private static final String col_User_trangthai = "Status";
    private static final String Create_table_User = "create table " + table_User + "(" + col_User_mssv + " char(50) , " + col_User_pass + " nvarchar(50), "  + col_User_trangthai + " char(10) )";
    private static final String Create_tabale_Login ="create table " + table_Login + "(" + col_User_mssv + " char(50) , " +col_User_tokens + " char(50))";
    Context context;

    public MyDatabaseAccess(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("test", Create_table_User);
        db.execSQL(Create_table_User);
        Log.d("test", Create_tabale_Login);
        db.execSQL(Create_tabale_Login);
    }

    public boolean addUser(Users users) {
        DeleteUser();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_User_mssv, users.getMssv());
        values.put(col_User_pass, users.getPassword());
        values.put(col_User_trangthai, users.getTrangthai());
        Log.d("test","DAta : User"+users.getMssv() + "pass" + users.getPassword() + "trangthai" + users.getTrangthai() );
        long rowId = db.insert(table_User, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public Users getUser() {
        Users tmp = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM " + table_User;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String mssv = cursor.getString(cursor.getColumnIndex(col_User_mssv));
                    String Pass = cursor.getString(cursor.getColumnIndex(col_User_pass));
                    String trangthai = cursor.getString(cursor.getColumnIndex(col_User_trangthai));
                    Log.d("test","hung " + trangthai);
                    tmp.setMssv(mssv);
                    tmp.setPassword(Pass);
                    tmp.setTrangthai(trangthai);
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        return tmp;
    }

    public void DeleteUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Delete  FROM " + table_User;
        db.execSQL(Query);
        db.close();
    }
    public int getCountUser() {
        String sql = "select * from " + table_User;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }
    public Users GetToken() {
        Users tmp = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM " + table_Login;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String mssv = cursor.getString(cursor.getColumnIndex(col_User_mssv));
                    String token = cursor.getString(cursor.getColumnIndex(col_User_tokens));
                    tmp.setMssv(mssv);
                    tmp.setTokens(token);
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        return tmp;
    }
    public boolean addToken(String mssv, String tokens) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_User_mssv, mssv);
        values.put(col_User_tokens, tokens);

        Log.d("test","DAta : User"+mssv + "tokens " + tokens );
        long rowId = db.insert(table_Login, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    public void DeleteTokens() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Delete  FROM " + table_Login;
        db.execSQL(Query);
        db.close();
    }
    public int getCountToken() {
        String sql = "select "+col_User_tokens + " from " + table_Login;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_User);
        onCreate(db);
    }
    boolean isDatabaseExist() {
        return (db != null);
    }
}
