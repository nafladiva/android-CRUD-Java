package id.ac.unsyiah.android.sqlite.data;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;

public class DataMahasiswa {

    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;

    public DataMahasiswa(Context context){
        dbhelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException{
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void addMahasiswa(Mahasiswa mahasiswa){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAMA, mahasiswa.getNama());
        values.put(DatabaseHelper.KEY_NIM, mahasiswa.getNim());
        values.put(DatabaseHelper.KEY_JURUSAN, mahasiswa.getJurusan());
        values.put(DatabaseHelper.KEY_IMG, mahasiswa.getImage());

        //inserting row
        database.insert(DatabaseHelper.TABLE_MAHASISWA, null, values);
    }

    public void updateMahasiswa(String nim, String nama, String jurusan, byte[] image) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NIM, nim);
        values.put(DatabaseHelper.KEY_NAMA, nama);
        values.put(DatabaseHelper.KEY_JURUSAN, jurusan);
        values.put(DatabaseHelper.KEY_IMG, image);

        database.update(DatabaseHelper.TABLE_MAHASISWA, values, "nim=?", new String[]{nim});
    }

    public void deteleMahasiswa(String nim) {
        database.delete(DatabaseHelper.TABLE_MAHASISWA, "nim=?", new String[]{nim});
    }

    public ArrayList<String> getAllNIM() {
        ArrayList<String> listNIM = new ArrayList<String>();

        String allNIM = "SELECT nim FROM " + DatabaseHelper.TABLE_MAHASISWA;
        Cursor cursor = database.rawQuery(allNIM, null);

        if (cursor.moveToFirst()) {
            do {
                listNIM.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listNIM;
    }

    public ArrayList<String> getAllNama() {
        ArrayList<String> listNama = new ArrayList<String>();

        String allNama = "SELECT nama FROM " + DatabaseHelper.TABLE_MAHASISWA;
        Cursor cursor = database.rawQuery(allNama, null);

        if (cursor.moveToFirst()) {
            do {
                listNama.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listNama;
    }

    public ArrayList<String> getAllJurusan() {
        ArrayList<String> listJurusan = new ArrayList<String>();

        String allJurusan = "SELECT jurusan FROM " + DatabaseHelper.TABLE_MAHASISWA;
        Cursor cursor = database.rawQuery(allJurusan, null);

        if (cursor.moveToFirst()) {
            do {
                listJurusan.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listJurusan;
    }

    public ArrayList<byte[]> getAllImage() {
        ArrayList<byte[]> listImage = new ArrayList<byte[]>();

        String allImage = "SELECT foto FROM " + DatabaseHelper.TABLE_MAHASISWA;
        Cursor cursor = database.rawQuery(allImage, null);

        if (cursor.moveToFirst()) {
            do {
                listImage.add(cursor.getBlob(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listImage;
    }

    public List<Mahasiswa> getAllMahasiswa(){
        List<Mahasiswa> listMahasiswa = new ArrayList<Mahasiswa>();

        //select all data mahasiswa
        String allMahasiswa = "SELECT * FROM " + DatabaseHelper.TABLE_MAHASISWA;
        Cursor cursor = database.rawQuery(allMahasiswa, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(Integer.parseInt(cursor.getString(0)));
                mahasiswa.setNim(cursor.getString(1));
                mahasiswa.setNama(cursor.getString(2));
                mahasiswa.setJurusan(cursor.getString(3));

                //adding mahasiswa to the list
                listMahasiswa.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listMahasiswa;
    }

}