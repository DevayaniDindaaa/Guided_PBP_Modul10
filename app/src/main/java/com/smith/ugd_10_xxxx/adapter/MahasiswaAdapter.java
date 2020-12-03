package com.smith.ugd_10_xxxx.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smith.ugd_10_xxxx.R;
import com.smith.ugd_10_xxxx.databinding.AdapterMahasiswaBinding;
import com.smith.ugd_10_xxxx.model.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    AdapterMahasiswaBinding binding;
    private Context context;
    private List<Mahasiswa> result;

    public MahasiswaAdapter(){}

    public MahasiswaAdapter(Context context, List<Mahasiswa> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_mahasiswa, parent, false);
        final MyViewHolder holder = new MyViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Mahasiswa mhs = result.get(position);

        holder.binding.setMahasiswa(mhs);
//        Glide.with(context)
//                .load(mhs.image_url)
//                .centerCrop()
//                .into(holder.binding.ivFotoProfil);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AdapterMahasiswaBinding binding;

        public MyViewHolder(@NonNull AdapterMahasiswaBinding bind){
            super(bind.getRoot());
            this.binding = bind;
        }
        public void onClick(View view) {
        }
    }
}