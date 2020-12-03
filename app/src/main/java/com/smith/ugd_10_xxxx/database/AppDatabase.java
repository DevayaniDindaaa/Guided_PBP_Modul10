package com.smith.ugd_10_xxxx.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.smith.ugd_10_xxxx.model.Mahasiswa;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MahasiswaDao userDao();
}
