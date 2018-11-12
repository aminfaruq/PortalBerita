package id.co.aminfaruq.portalberita.adapter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BeritaModel implements Parcelable {
    //TODO 1  Membuat variable untuk menampung data yang kita inginkan
    String judul,penulis,tanggalPosting,isiBerita,gambar;

    //TODO 2 Generate pilih Getter dan Setter
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTanggalPosting() {
        return tanggalPosting;
    }

    public void setTanggalPosting(String tanggalPosting) {
        this.tanggalPosting = tanggalPosting;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    //TODO 3 Generate lalu pilih parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.penulis);
        dest.writeString(this.tanggalPosting);
        dest.writeString(this.isiBerita);
        dest.writeString(this.gambar);
    }

    public BeritaModel() {
    }

    protected BeritaModel(Parcel in) {
        this.judul = in.readString();
        this.penulis = in.readString();
        this.tanggalPosting = in.readString();
        this.isiBerita = in.readString();
        this.gambar = in.readString();
    }

    public static final Creator<BeritaModel> CREATOR = new Creator<BeritaModel>() {
        @Override
        public BeritaModel createFromParcel(Parcel source) {
            return new BeritaModel(source);
        }

        @Override
        public BeritaModel[] newArray(int size) {
            return new BeritaModel[size];
        }
    };
}
