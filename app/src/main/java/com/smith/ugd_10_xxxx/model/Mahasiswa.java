package com.smith.ugd_10_xxxx.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.smith.ugd_10_xxxx.BR;

import java.io.Serializable;

@Entity
public class Mahasiswa extends BaseObservable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "npm")
    public String npm;
    @ColumnInfo(name = "nama")
    public String nama;
    @ColumnInfo(name = "fakultas")
    public String fakultas;
    @ColumnInfo(name = "jurusan")
    public String jurusan;
    @ColumnInfo(name = "ipk")
    public double ipk;
    @ColumnInfo(name = "image_url")
    public String image_url;

    public Mahasiswa(){}

    public Mahasiswa(int id, String npm, String nama, String fakultas, String jurusan, double ipk, String hobi) {
        this.id = id;
        this.npm = npm;
        this.nama = nama;
        this.fakultas = fakultas;
        this.jurusan = jurusan;
        this.ipk = ipk;
        this.image_url = image_url;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
        notifyPropertyChanged(BR.npm);
    }

    @Bindable
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        notifyPropertyChanged(BR.nama);
    }

    @Bindable
    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
        notifyPropertyChanged(BR.fakultas);
    }

    @Bindable
    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
        notifyPropertyChanged(BR.jurusan);
    }

    @Bindable
    public double getIpk() {
        return ipk;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
        notifyPropertyChanged(BR.ipk);
    }

    @Bindable
    public String getStringIpk() {
        if(this.ipk==0){
            return "";
        }else{
            return String.valueOf(ipk);
        }
    }

    public void setStringIpk(String ipk) {
        if(!ipk.isEmpty()){
            this.ipk = Double.parseDouble(ipk);
        }else{
            this.ipk = 0;
        }
        notifyPropertyChanged(BR.ipk);
    }

    @Bindable
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
        notifyPropertyChanged(BR.image_url);
    }
}

