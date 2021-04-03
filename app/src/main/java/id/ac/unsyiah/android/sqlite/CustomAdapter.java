package id.ac.unsyiah.android.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.unsyiah.android.sqlite.data.DataMahasiswa;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> nim;
    ArrayList<String> nama;
    ArrayList<String> jurusan;
    LayoutInflater inflter;
    private DataMahasiswa db;

    Button editButton, deleteButton;

    public CustomAdapter(Context applicationContext, ArrayList<String> nim, ArrayList<String> nama, ArrayList<String> jurusan) {
        this.context = applicationContext;
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return nim.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.activity_listview, parent, false);

        TextView nimTV = (TextView) convertView.findViewById(R.id.nim);
        nimTV.setText(nim.get(position));

        TextView namaTV = (TextView) convertView.findViewById(R.id.nama);
        namaTV.setText(nama.get(position));

        TextView jurusanTV = (TextView) convertView.findViewById(R.id.jurusan);
        jurusanTV.setText(jurusan.get(position));

        editButton = (Button) convertView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateDataActivity.class);

                intent.putExtra("nim", getNIM(position));
                intent.putExtra("nama", getNama(position));
                intent.putExtra("jurusan", getJurusan(position));

                Toast.makeText(context, "Nim: " + getNIM(position) + "\n Nama: " + getNama(position), Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });

        deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DataMahasiswa(context);
                db.open();
                db.deteleMahasiswa(getNIM(position));
                Toast.makeText(context, "Data deleted!", Toast.LENGTH_LONG).show();
                db.close();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public String getNIM(int position) {
        return nim.get(position);
    }

    public String getNama(int position) {
        return nama.get(position);
    }

    public String getJurusan(int position) {
        return jurusan.get(position);
    }
}
