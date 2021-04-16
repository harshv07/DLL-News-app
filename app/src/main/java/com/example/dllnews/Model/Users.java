package com.example.dllnews.Model;

public class Users {

    private String Email,pasword,name;

    public Users() {
    }

    public Users(String Email, String pasword , String name) {
        this.Email = Email;
        this.pasword = pasword;
        this.name =name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String pasword) {
        this.name = name;
    }
}
