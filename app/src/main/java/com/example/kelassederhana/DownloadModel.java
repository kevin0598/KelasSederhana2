package com.example.kelassederhana;

public class DownloadModel {
    String link;
    String name;
    String jenis;
    String namapelajaaran;

    public String getNamapelajaaran() {
        return namapelajaaran;
    }

    public void setNamapelajaaran(String namapelajaaran) {
        this.namapelajaaran = namapelajaaran;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamajenis() {
        return namajenis;
    }

    public void setNamajenis(String namajenis) {
        this.namajenis = namajenis;
    }

    String namajenis;

    public DownloadModel() {

    }

    public DownloadModel(String link, String name,String jenis,String namajenis,String namapelajaaran) {
        this.jenis=jenis;
        this.namapelajaaran=namapelajaaran;
        this.namajenis=namajenis;
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
