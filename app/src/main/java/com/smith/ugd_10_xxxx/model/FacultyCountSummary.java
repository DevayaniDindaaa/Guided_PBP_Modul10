package com.smith.ugd_10_xxxx.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity
public class FacultyCountSummary implements Serializable {
    @ColumnInfo(name = "fakultas")
    private String fakultas;

    @ColumnInfo(name = "jumlah")
    private int jumlah;

    public FacultyCountSummary(String fakultas, int jumlah) {
        this.fakultas = fakultas;
        this.jumlah = jumlah;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
