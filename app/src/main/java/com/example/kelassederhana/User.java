package com.example.kelassederhana;

public class User {

    private String nama;
    private String id;
    private String email;
    private String pass1;
    private String pass2;
    private String status;
    private String bidang;
    private String kelas;
    private String jk;
    private String bidangkelas;

    public String getBidangkelas() {
        return bidangkelas;
    }

    public void setBidangkelas(String bidangkelas) {
        this.bidangkelas = bidangkelas;
    }

    public User(){

    }

    public User(String nama,String id,String email,String pass1,String pass2,String status,String jk,String bidang,String kelas,String bidangkelas){
        this.nama=nama;
        this.id=id;
        this.email=email;
        this.pass1=pass1;
        this.pass2=pass2;
        this.status=status;
        this.jk=jk;
        this.kelas=kelas;
        this.bidang=bidang;
        this.bidangkelas=bidangkelas;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUser(String username) {
        this.email = username;
    }

    public String getUser() {
        return email;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getPass2() {
        return pass2;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getJk() {
        return jk;
    }
}
