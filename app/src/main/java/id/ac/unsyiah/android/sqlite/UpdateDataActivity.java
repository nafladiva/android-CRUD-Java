package id.ac.unsyiah.android.sqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import id.ac.unsyiah.android.sqlite.data.DataMahasiswa;

public class UpdateDataActivity extends AppCompatActivity {

    private EditText editTextNIM, editTextNama, editTextJurusan;
    private Button buttonAdd, buttonInsert;
    private ImageView insertIV;
    private DataMahasiswa db;
    private String nim, nama, jurusan;
    private byte[] image;

    private final int REQUEST_CODE_GALLERY = 345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editTextNIM = findViewById(R.id.editTextNIM);
        editTextNama = findViewById(R.id.editTextNama);
        editTextJurusan = findViewById(R.id.editTextJurusan);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonInsert = findViewById(R.id.buttonInsert);

        insertIV = findViewById(R.id.insertIV);

        db = new DataMahasiswa(UpdateDataActivity.this);

        nim = getIntent().getStringExtra("nim");
        nama = getIntent().getStringExtra("nama");
        jurusan = getIntent().getStringExtra("jurusan");
        image = getIntent().getByteArrayExtra("foto");

        editTextNIM.setText(nim);
        editTextNama.setText(nama);
        editTextJurusan.setText(jurusan);

        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        insertIV.setImageBitmap(bitmap);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                db.updateMahasiswa(editTextNIM.getText().toString(), editTextNama.getText().toString(), editTextJurusan.getText().toString(), getImageInByte(insertIV));
                Toast.makeText(UpdateDataActivity.this, "Data updated!", Toast.LENGTH_LONG).show();
                db.close();
                Intent intent = new Intent(UpdateDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(UpdateDataActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "Permission denied!", Toast.LENGTH_LONG).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                insertIV.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] getImageInByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}