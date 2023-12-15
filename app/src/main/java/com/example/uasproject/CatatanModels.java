package com.example.uasproject;

public class CatatanModels {
    private String jenis;
    private String jumlah;
    private String kategori;
    private String keterangan;
    private String tanggal;

    public CatatanModels() {
        // Diperlukan konstruktor kosong untuk Firebase
    }

    public CatatanModels(String jenis, String jumlah, String kategori, String keterangan, String tanggal) {
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.kategori = kategori;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    // Buatlah getter dan setter untuk setiap properti
    // ...
}

