package com.example.sqlitetestapp;

public class ClientData {

    private String fName;
    private String lName;
    private int phone;
    private String email;


    public ClientData(){}

    public ClientData(String fName, String lName, int phone, String email) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
