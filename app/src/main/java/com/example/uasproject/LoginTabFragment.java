package com.example.uasproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginTabFragment extends Fragment {

    EditText username, password;
    boolean passwordVisible;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        Button btn_log = view.findViewById(R.id.login_button);

        username = view.findViewById(R.id.login_username_id);
        password = view.findViewById(R.id.login_password_id);

        //Visibility Password
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if(passwordVisible){
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visibility, 0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        // Mendapatkan referensi database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");



        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()){

                } else {
                    checkUser();
                }
            }
        });

        return view;

    }

    //Validasi username
    public boolean validateUsername(){
        String val = username.getText().toString().trim();
        if (val.isEmpty()){
            username.setError("Username tidak boleh kosong");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    //Validasi password
    public boolean validatePassword(){
        String val = password.getText().toString().trim();
        if (val.isEmpty()){
            password.setError("Password tidak boleh kosong");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    public void checkUser(){
        String userUsername = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

       // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        // Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        Query checkUserDatabase = databaseReference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null && snapshot.exists()) {
                    username.setError(null);
                  //  String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);


                        if (passwordFromDB != null && passwordFromDB.equals(userPassword)) {
                        username.setError(null);
//                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
//                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                        String namaMasjidFromDB = snapshot.child(userUsername).child("namaMasjid").getValue(String.class);
                            String usernameFromDB = userSnapshot.child("username").getValue(String.class);
                            String emailFromDB = userSnapshot.child("email").getValue(String.class);
                            String namaMasjidFromDB = userSnapshot.child("namaMasjid").getValue(String.class);

                            // Simpan data pengguna saat ini ke dalam SharedPreferences
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", usernameFromDB);
                            editor.putString("email", emailFromDB);
                            editor.putString("namaMasjid", namaMasjidFromDB);
                            editor.putString("password", passwordFromDB);
                            editor.putString("confirmPassword", passwordFromDB);
                            editor.apply();

                            Intent home = new Intent(getContext(), MainActivity.class);

                            home.putExtra("username", usernameFromDB);
                            home.putExtra("email", emailFromDB);
                            home.putExtra("namaMasjid", namaMasjidFromDB);
                            home.putExtra("password", passwordFromDB);
                            home.putExtra("confirmPassword", passwordFromDB);

                            startActivity(home);
                            return;

                        } else {
                            password.setError("Tidak valid");
                            password.requestFocus();
                            return;
                        }
                    }
                } else {
                    username.setError("Pengguna tidak ada");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}