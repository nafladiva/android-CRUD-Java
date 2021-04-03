package id.ac.unsyiah.android.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.unsyiah.android.sqlite.data.DataMahasiswa;

public class UpdateDataActivity extends AppCompatActivity {

    private EditText editTextNIM, editTextNama, editTextJurusan;
    private Button buttonAdd;
    private DataMahasiswa db;
    String nim, nama, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editTextNIM = findViewById(R.id.editTextNIM);
        editTextNama = findViewById(R.id.editTextNama);
        editTextJurusan = findViewById(R.id.editTextJurusan);

        buttonAdd = findViewById(R.id.buttonAdd);

        db = new DataMahasiswa(UpdateDataActivity.this);

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
                db.updateMahasiswa(editTextNIM.getText().toString(), editTextNama.getText().toString(), editTextJurusan.getText().toString());
                Toast.makeText(UpdateDataActivity.this, "Data updated!", Toast.LENGTH_LONG).show();
                db.close();
                Intent intent = new Intent(UpdateDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}