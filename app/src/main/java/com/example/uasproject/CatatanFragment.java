package com.example.uasproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class CatatanFragment extends Fragment {

        private DatabaseReference databaseReference;
        private FloatingActionButton floatingButton;
        private RecyclerView recyclerView;
        private List<CatatanModels> dataList;
        private DataAdapter adapter;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_catatan, container, false);

            floatingButton = view.findViewById(R.id.floatingButton);

            // Mendapatkan referensi ke Firebase Database
            databaseReference = FirebaseDatabase.getInstance().getReference("data");

            // Inisialisasi daftar data dan adapter
            dataList = new ArrayList<>();
            adapter = new DataAdapter(dataList);

            recyclerView = view.findViewById(R.id.recyclerViews);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            // Menambahkan listener untuk mengambil data dari Firebase Database
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataList.clear();

                    // Iterasi melalui setiap child pada root node
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Mendapatkan data pada setiap child node
                        CatatanModels data = childSnapshot.getValue(CatatanModels.class);
                        if (data != null) {
                            dataList.add(data);
                        }
                    }
                    // Membalikkan urutan data list
                    Collections.reverse(dataList);

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Menangani error yang terjadi saat mengambil data
                    // ...
                }
            });

            floatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new AddFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            return view;
        }

        private class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
            private List<CatatanModels> dataList;

            public DataAdapter(List<CatatanModels> dataList) {
                this.dataList = dataList;
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                CatatanModels data = dataList.get(position);

                // Mengatur nilai untuk elemen-elemen dalam layout card
                holder.textViewKategori.setText(data.getKategori());
                holder.textViewKeterangan.setText(data.getKeterangan());
                holder.textViewJumlah.setText(data.getJumlah());
                holder.textViewTanggal.setText(data.getTanggal());
            }


            @Override
            public int getItemCount() {
                return dataList.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {


                TextView textView;
                TextView textViewKategori;
                TextView textViewKeterangan;
                TextView textViewJumlah;
                TextView textViewTanggal;


                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    textView = itemView.findViewById(android.R.id.text1);
                    textViewKategori = itemView.findViewById(R.id.id_listkategori);
                    textViewKeterangan = itemView.findViewById(R.id.id_listketerangan);
                    textViewJumlah = itemView.findViewById(R.id.txt_jmlhUang_id);
                    textViewTanggal = itemView.findViewById(R.id.txt_tanggal_id);

                }
            }
        }
    }



