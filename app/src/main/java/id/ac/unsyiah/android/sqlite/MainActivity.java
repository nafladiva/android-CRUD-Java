package id.ac.unsyiah.android.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.ac.unsyiah.android.sqlite.data.DataMahasiswa;
import id.ac.unsyiah.android.sqlite.data.DatabaseHelper;
import id.ac.unsyiah.android.sqlite.data.Mahasiswa;

public class MainActivity extends AppCompatActivity {

    private DataMahasiswa db;
    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;

    private ArrayList<String> nim;
    private ArrayList<String> nama;
    private ArrayList<String> jurusan;

    private ListView listView;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataMahasiswa(this);
        db.open();

        listView = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, db.getAllNIM(), db.getAllNama(), db.getAllJurusan());
        listView.setAdapter(customAdapter);

        db.close();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddDataActivity.class));
            }
        });
    }

}