package com.example.uasproject;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter {
}

class List extends RecyclerView.ViewHolder {

    TextView kategori, keterangan, jumlahUang;
    ImageButton button;

    public List(@NonNull View itemView) {
        super(itemView);

        kategori = itemView.findViewById(R.id.kategori_id);
        keterangan = itemView.findViewById(R.id.id_listketerangan);
        jumlahUang = itemView.findViewById(R.id.txt_jmlhUang_id);

    }
}