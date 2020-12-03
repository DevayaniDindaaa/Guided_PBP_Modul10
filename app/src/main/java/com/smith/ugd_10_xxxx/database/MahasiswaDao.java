package com.smith.ugd_10_xxxx.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.smith.ugd_10_xxxx.model.FacultyCountSummary;
import com.smith.ugd_10_xxxx.model.Mahasiswa;
import com.smith.ugd_10_xxxx.model.MajorAverageGPA;

import java.util.List;

@Dao
public interface MahasiswaDao {

    @Query("SELECT * FROM mahasiswa")
    List<Mahasiswa> getAll();

    @Insert
    void insert(Mahasiswa user);

    @Update
    void update(Mahasiswa user);

    @Delete
    void delete(Mahasiswa user);

    @Query("SELECT fakultas, COUNT(*) as jumlah FROM mahasiswa GROUP BY fakultas")
    List<FacultyCountSummary> getFacultyCountSummary();

    @Query("SELECT jurusan, AVG(ipk) as ipk FROM mahasiswa GROUP BY jurusan")
    List<MajorAverageGPA> getMajorAverageGPA();

}
