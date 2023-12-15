package com.example.uasproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {

    EditText signUsername, signEmail, signNamaMasjid, signPassword, signConfirmPass;
    Button btn_Signup;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);

        signUsername = view.findViewById(R.id.signup_username_id);
        signEmail = view.findViewById(R.id.signup_email_id);
        signNamaMasjid = view.findViewById(R.id.signup_namaMasjid_id);
        signPassword = view.findViewById(R.id.signup_password_id);
        signConfirmPass = view.findViewById(R.id.signup_confirm_id);

        btn_Signup = view.findViewById(R.id.signup_button_id);

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = signUsername.getText().toString();
                String email = signEmail.getText().toString();
                String namaMasjid = signNamaMasjid.getText().toString();
                String password = signPassword.getText().toString();
                String confirmPassword = signConfirmPass.getText().toString();

                if (validateFields(username, email, namaMasjid, password, confirmPassword)) {
                    registerUser(username, email, namaMasjid, password);
                }
            }
        });

        return view;

    }

    private boolean validateFields(String username, String email, String namaMasjid, String password, String confirmPassword) {
        if (username.isEmpty()) {
            signUsername.setError("Username tidak boleh kosong");
            return false;
        }
        if (email.isEmpty()) {
            signEmail.setError("Email tidak boleh kosong");
            return false;
        }
        if (namaMasjid.isEmpty()) {
            signNamaMasjid.setError("Nama Masjid tidak boleh kosong");
            return false;
        }
        if (password.isEmpty()) {
            signPassword.setError("Password tidak boleh kosong");
            return false;
        }
        if (confirmPassword.isEmpty()) {
            signConfirmPass.setError("Confirm Password tidak boleh kosong");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "Passwords tidak cocok", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void registerUser(String username, String email, String namaMasjid, String password) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        HelperClass helperClass = new HelperClass(username, email, namaMasjid, password);
        reference.child(username).setValue(helperClass);

        Toast.makeText(getContext(), "Anda telah berhasil mendaftar!", Toast.LENGTH_SHORT).show();
    }
}