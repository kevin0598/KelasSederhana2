package com.example.kelassederhana;

public class member {

    private String kode,nama,status,kelas,bidang;

    public member(){

    }

    public member(String kode, String nama, String status, String kelas, String bidang) {
        this.kode = kode;
        this.nama = nama;
        this.status = status;
        this.kelas = kelas;
        this.bidang = bidang;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }
}
