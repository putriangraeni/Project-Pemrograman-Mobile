package com.example.uasproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private List<DataItem> dataList;

    public DataAdapter(List<DataItem> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem dataItem = dataList.get(position);
        holder.bind(dataItem);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Deklarasikan tampilan item di dalam ViewHolder
        private TextView txtKategori, txtKeterangan, txtJumlahUang, txtTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inisialisasi tampilan item
            txtKategori = itemView.findViewById(R.id.id_listkategori);
            txtKeterangan = itemView.findViewById(R.id.id_listketerangan);
            txtJumlahUang = itemView.findViewById(R.id.txt_jmlhUang_id);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal_id);
        }

        public void bind(DataItem dataItem) {
            // Set nilai data ke tampilan item
            txtKategori.setText(dataItem.getKategori());
            txtKeterangan.setText(dataItem.getKet());
            txtJumlahUang.setText(dataItem.getJumlahUang());
            txtTanggal.setText(dataItem.getTanggal());
        }
    }
}
