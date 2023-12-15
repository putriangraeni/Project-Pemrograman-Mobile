package com.example.uasproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFragment extends Fragment {

    private Spinner spinnerJenis;
    private Spinner spinnerKategori;
    private EditText edtDate;
    private EditText edtJumlah;
    private EditText edtKet;
    private Button simpanButton;

    private DatabaseReference databaseReference;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // Mengambil data pengguna saat ini dari SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "");

        // Inisialisasi views
        spinnerJenis = view.findViewById(R.id.jenis_id);
        spinnerKategori = view.findViewById(R.id.kategori_id);
        edtDate = view.findViewById(R.id.edtDate);
        edtJumlah = view.findViewById(R.id.edtJumlah);
        edtKet = view.findViewById(R.id.edtKet);
        simpanButton = view.findViewById(R.id.simpan_data);

        // Mengatur adapter untuk spinner Jenis
        ArrayAdapter<CharSequence> jenisAdapter = ArrayAdapter.createFromResource(context,
                R.array.Jenis, android.R.layout.simple_spinner_item);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenis.setAdapter(jenisAdapter);

        // Mengatur adapter untuk spinner Kategori
        ArrayAdapter<CharSequence> kategoriAdapter = ArrayAdapter.createFromResource(context,
                R.array.Kategori, android.R.layout.simple_spinner_item);
        kategoriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKategori.setAdapter(kategoriAdapter);

        // Mendapatkan referensi database
        databaseReference = FirebaseDatabase.getInstance().getReference("data");

        // Menambahkan listener untuk tombol Simpan
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanTransaksi(userID);
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Mengatur tanggal yang dipilih pada EditText 'edtDate'
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                edtDate.setText(selectedDate);
            }
        };

        // Mendapatkan tanggal saat ini
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Membuat dialog DatePicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);

        // Menampilkan dialog DatePicker
        datePickerDialog.show();
    }

    private void simpanTransaksi(String userID) {
        // Mendapatkan nilai yang diinputkan pengguna
        String jenis = spinnerJenis.getSelectedItem().toString();
        String kategori = spinnerKategori.getSelectedItem().toString();
        String date = edtDate.getText().toString().trim();
        String jumlah = edtJumlah.getText().toString().trim();
        String ket = edtKet.getText().toString().trim();

        // Validasi input
        if (jenis.isEmpty() || kategori.isEmpty() || date.isEmpty() || jumlah.isEmpty() || ket.isEmpty()) {
            showErrorAlert("Data tidak boleh kosong");
            return;
        }

        // Membuat objek transaksi
        Transaksi transaksi = new Transaksi(jenis, kategori, date, jumlah, ket);

        // Menyimpan transaksi ke database Firebase
        databaseReference.child(userID).push().setValue(transaksi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showSuccessAlert("Transaksi berhasil disimpan");
                        resetForm();

                        // Kembali ke CatatanFragment
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, new CatatanFragment());
                        transaction.commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showErrorAlert("Terjadi kesalahan saat menyimpan transaksi");
                    }
                });
    }

    private void resetForm() {
        spinnerJenis.setSelection(0);
        spinnerKategori.setSelection(0);
        edtDate.setText("");
        edtJumlah.setText("");
        edtKet.setText("");
    }

    private void showSuccessAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sukses")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showErrorAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void sendDataToCatatanFragment (DataItem dataItem) {
        CatatanFragment catatanFragment = new CatatanFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data_item", dataItem);
        catatanFragment.setArguments(bundle);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, catatanFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}