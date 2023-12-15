package com.example.uasproject;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

public class LaporanFragment extends Fragment {

    EditText edtTgglAwal, edtTgglAkhir;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_laporan, container, false);

     edtTgglAwal = view.findViewById(R.id.id_tgglAwal);
     edtTgglAkhir = view.findViewById(R.id.id_tgglAkhir);

     edtTgglAwal.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             showDatePickerDialog1();
         }
     });

     edtTgglAkhir.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             showDatePickerDialog2();
         }
     });

        // Inflate the layout for this fragment
        return view;
    }

    // PENGATURAN TANGGAL START
    private void showDatePickerDialog1() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Mengatur tanggal yang dipilih pada EditText 'edtDate'
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                edtTgglAwal.setText(selectedDate);
            }
        };

        // Mendapatkan tanggal saat ini
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Membuat DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void showDatePickerDialog2() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Mengatur tanggal yang dipilih pada EditText 'edtDate'
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                edtTgglAkhir.setText(selectedDate);
            }
        };

        // Mendapatkan tanggal saat ini
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Membuat DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), dateSetListener, year, month, day);
        datePickerDialog.show();
    }
    // PENGATURAN TANGGAL FINISH
}