package id.ac.unsyiah.android.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.unsyiah.android.sqlite.data.DataMahasiswa;
import id.ac.unsyiah.android.sqlite.data.Mahasiswa;

public class AddDataActivity extends AppCompatActivity {

    private EditText editTextNIM, editTextNama, editTextJurusan;
    private Button buttonAdd;
    private DataMahasiswa db;
    String nim, nama, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        editTextNIM = findViewById(R.id.editTextNIM);
        editTextNama = findViewById(R.id.editTextNama);
        editTextJurusan = findViewById(R.id.editTextJurusan);

        buttonAdd = findViewById(R.id.buttonAdd);

        db = new DataMahasiswa(AddDataActivity.this);

        nim = getIntent().getStringExtra("nim");
        nama = getIntent().getStringExtra("nama");
        jurusan = getIntent().getStringExtra("jurusan");

        editTextNIM.setText(nim);
        editTextNama.setText(nama);
        editTextJurusan.setText(jurusan);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                db.addMahasiswa(new Mahasiswa(editTextNIM.getText().toString(), editTextNama.getText().toString(), editTextJurusan.getText().toString()));
//                db.addMahasiswa(editTextNIM.getText().toString(), editTextNama.getText().toString(), editTextJurusan.getText().toString());
                db.close();
                Toast.makeText(AddDataActivity.this, "Data added!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}