package com.example.uasproject;

public class HelperClass {

    String username, email, namaMasjid, password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaMasjid() {
        return namaMasjid;
    }

    public void setName(String namaMasjid) {
        this.namaMasjid = namaMasjid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public HelperClass(String username, String email, String namaMasjid, String password) {
        this.username = username;
        this.email = email;
        this.namaMasjid = namaMasjid;
        this.password = password;
//        this.confirmPassword = confirmPassword;

    }

    public HelperClass() {
    }
}
