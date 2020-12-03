package com.smith.ugd_10_xxxx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.smith.ugd_10_xxxx.adapter.MahasiswaAdapter;
import com.smith.ugd_10_xxxx.database.DatabaseClient;
import com.smith.ugd_10_xxxx.databinding.ActivityMainBinding;
import com.smith.ugd_10_xxxx.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //DEKLARASI OBJEK DAN VARIABLE
    ActivityMainBinding binding; //DATA BINDING VARIABLE

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //INISIALISASI OBJEK DAN VARIABEL
        binding.setActivity(this);

        //recycler view
        recyclerView = findViewById(R.id.recycler_view_mahasiswa);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMahasiswa();
                binding.swipeRefresh.setRefreshing(false);
            }
        });

        getMahasiswa();
    }

    //MEMBUAT RESPONSE CLICKLISTENER SAAT MENGEKLIK BUTTON SIMPAN
    public View.OnClickListener buttonAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //MEMBUAT INTENT
            Intent addActivity = new Intent(MainActivity.this, AddActivity.class);
            startActivity(addActivity); //START ACTIVITY UNTUK MEMULAI HALAMAN SELANJUTNYA
        }
    };

    //MEMBUAT RESPONSE CLICKLISTENER SAAT MENGEKLIK BUTTON SIMPAN
    public View.OnClickListener buttonChartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //MEMBUAT INTENT
            Intent chartActivity = new Intent(MainActivity.this, ChartActivity.class);
            startActivity(chartActivity); //START ACTIVITY UNTUK MEMULAI HALAMAN SELANJUTNYA
        }
    };

    public void getMahasiswa(){
        class GetMahasiswa extends AsyncTask<Void, Void, List<Mahasiswa>> {

            @Override
            protected List<Mahasiswa> doInBackground(Void... voids) {
                List<Mahasiswa> mahasiswaList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getAll();
                return mahasiswaList;
            }

            @Override
            protected void onPostExecute(List<Mahasiswa> ListMahasiswa) {
                super.onPostExecute(ListMahasiswa);

                adapter = new MahasiswaAdapter(MainActivity.this, ListMahasiswa);
                recyclerView.setAdapter(adapter);
                //System.out.println(ListMahasiswa.get(0).nama);
                for (Mahasiswa new_mhs:ListMahasiswa
                ) {
                    System.out.println(new_mhs.nama);
                };
//                UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(MainActivity.this, users);
//                recyclerView.setAdapter(adapter);
                if (ListMahasiswa.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetMahasiswa get = new GetMahasiswa();
        get.execute();
    }
}