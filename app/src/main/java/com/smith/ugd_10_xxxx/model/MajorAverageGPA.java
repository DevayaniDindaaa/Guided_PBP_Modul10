package com.smith.ugd_10_xxxx.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity
public class MajorAverageGPA implements Serializable {
    @ColumnInfo(name = "jurusan")
    private String jurusan;

    @ColumnInfo(name = "ipk")
    private float ipk;

    public MajorAverageGPA(String jurusan, float ipk) {
        this.jurusan = jurusan;
        this.ipk = ipk;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public float getIpk() {
        return ipk;
    }

    public void setIpk(float ipk) {
        this.ipk = ipk;
    }
}
