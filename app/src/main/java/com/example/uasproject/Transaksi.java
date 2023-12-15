package com.example.uasproject;

public class Transaksi {
    private String jenis;
    private String kategori;
    private String tanggal;
    private String jumlah;
    private String keterangan;

    public Transaksi() {
        // Diperlukan oleh Firebase Realtime Database
    }

    public Transaksi(String jenis, String kategori, String tanggal, String jumlah, String keterangan) {
        this.jenis = jenis;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
