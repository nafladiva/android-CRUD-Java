package id.ac.unsyiah.android.sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "mahasiswa.db";

    public static final String TABLE_MAHASISWA = "tb_mahasiswa";

    public static final String KEY_ID = "id";
    public static final String KEY_NIM = "nim";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_JURUSAN = "jurusan";
    public static final String KEY_IMG = "foto";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MAHASISWA = "CREATE TABLE " + TABLE_MAHASISWA + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NIM + " TEXT,"
                + KEY_NAMA + " TEXT," + KEY_JURUSAN + " TEXT," + KEY_IMG + " BLOB" + ")";
        db.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tabel jika sudah pernah ada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);

        //create table again
        onCreate(db);
    }

}