package com.example.kelassederhana;

public class POJOmatapelajaran {
    private String namapelajaran;
    private String namaguru;
    private String kelas;
    private String bidang;
    private String kode;
    private String idguru;
    private String bidangkelas;

    public String getBidangkelas() {
        return bidangkelas;
    }

    public void setBidangkelas(String bidangkelas) {
        this.bidangkelas = bidangkelas;
    }

    public String getIdguru() {
        return idguru;
    }

    public void setIdguru(String idguru) {
        this.idguru = idguru;
    }

    public POJOmatapelajaran(){

    }

    public POJOmatapelajaran(String namapelajaran, String namaguru, String kelas, String bidang, String kode,String idguru,String bidangkelas) {
        this.namapelajaran = namapelajaran;
        this.namaguru = namaguru;
        this.kelas = kelas;
        this.bidang = bidang;
        this.kode = kode;
        this.idguru=idguru;
        this.bidangkelas=bidangkelas;
    }

    public String getNamapelajaran() {
        return namapelajaran;
    }

    public void setNamapelajaran(String namapelajaran) {
        this.namapelajaran = namapelajaran;
    }

    public String getNamaguru() {
        return namaguru;
    }

    public void setNamaguru(String namaguru) {
        this.namaguru = namaguru;
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

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
