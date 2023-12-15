package com.example.uasproject;

import android.os.Parcel;
import android.os.Parcelable;

public class DataItem implements Parcelable {
    private String jenis, kategori, ket, jumlahUang, tanggal;

    public DataItem (){

    }

    public DataItem(String jenis, String kategori, String ket, String jumlahUang, String tanggal) {
        this.jenis = jenis;
        this.kategori = kategori;
        this.ket = ket;
        this.jumlahUang = jumlahUang;
        this.tanggal = tanggal;
    }

    protected DataItem(Parcel in) {
        jenis = in.readString();
        kategori = in.readString();
        ket = in.readString();
        jumlahUang = in.readString();
        tanggal = in.readString();
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getJumlahUang() {
        return jumlahUang;
    }

    public void setJumlahUang(String jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jenis);
        dest.writeString(kategori);
        dest.writeString(ket);
        dest.writeString(jumlahUang);
        dest.writeString(tanggal);
    }
}
