package com.smith.ugd_10_xxxx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.smith.ugd_10_xxxx.database.DatabaseClient;
import com.smith.ugd_10_xxxx.databinding.ActivityAddBinding;
import com.smith.ugd_10_xxxx.model.Mahasiswa;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {
    //DEKLARASI OBJEK DAN VARIABLE
    Mahasiswa mhs;
    ActivityAddBinding binding; //DATA BINDING VARIABLE

    private String[] major_list = new String[] {"Arsitektur", "Teknik Sipil", "Manajemen", "Akuntansi",
            "Hukum", "Teknik Industri", "Informatika", "Biologi", "Ilmu Komunikasi", "Sosiologi", "Ekonomi Pembangunan", "Sistem Informasi"};
    private String[] faculty_list = new String[] {"FT", "FBE", "FH", "FTI", "FTB", "FISIP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        //INISIALISASI OBJEK DAN VARIABEL
        mhs = new Mahasiswa();
        binding.setMahasiswa(mhs);
        binding.setActivity(this);

        final ArrayAdapter<String> faculty_adapter = new ArrayAdapter<>(AddActivity.this,
                R.layout.dropdown_menu_item, R.id.item_dropdown, faculty_list);
        binding.inputFakultas.setAdapter(faculty_adapter);

        final ArrayAdapter<String> major_adapter = new ArrayAdapter<>(AddActivity.this,
                R.layout.dropdown_menu_item, R.id.item_dropdown, major_list);
        binding.inputJurusan.setAdapter(major_adapter);
    }

    //MEMBUAT RESPONSE CLICKLISTENER SAAT MENGEKLIK BUTTON SIMPAN
    public View.OnClickListener buttonSaveMahasiswa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println(mhs.nama);
            class AddMahasiswa extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {

                    DatabaseClient.getInstance(getApplicationContext()).getDatabase()
                            .userDao()
                            .insert(mhs);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "Mahasiswa saved", Toast.LENGTH_SHORT).show();
                    Mahasiswa empty_mhs = new Mahasiswa();
                    mhs = empty_mhs;
                    binding.setMahasiswa(mhs);

                    //MEMBUAT INTENT
                    Intent mainAcivity = new Intent(AddActivity.this, MainActivity.class);
                    mainAcivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainAcivity); //START ACTIVITY UNTUK MEMULAI HALAMAN SELANJUTNYA
                }
            }

            AddMahasiswa add = new AddMahasiswa();
            add.execute();
        }
    };
}