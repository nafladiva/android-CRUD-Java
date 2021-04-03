package id.ac.unsyiah.android.sqlite.data;

public class Mahasiswa {

    int _id;
    String _nim;
    String _nama;
    String _jurusan;
    byte[] _image;

    //Empty constructor
    //Default constructor
    public Mahasiswa(){

    }

    //Constructor
    public Mahasiswa(String nim, String nama, String jurusan, byte[] image){
        this._nim = nim;
        this._nama = nama;
        this._jurusan = jurusan;
        this._image = image;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getNama(){
        return this._nama;
    }

    public void setNama(String nama){
        this._nama = nama;
    }

    public String getNim(){
        return this._nim;
    }

    public void setNim(String nim){
        this._nim = nim;
    }

    public String getJurusan() {
        return this._jurusan;
    }

    public void setJurusan(String jurusan) {
        this._jurusan = jurusan;
    }

    public byte[] getImage() {
        return _image;
    }

    public void setImage(byte[] _image) {
        this._image = _image;
    }
}

