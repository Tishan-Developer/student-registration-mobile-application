package com.example.dbapp.models;

public class StudentModels {
    private final String reg_no, fName, bDay, email;

    public StudentModels(String reg_no, String fName, String bDay, String email) {
        this.reg_no = reg_no;
        this.fName = fName;
        this.bDay = bDay;
        this.email = email;
    }

    public String getReg_no() {
        return reg_no;
    }

    public String getfName() {
        return fName;
    }

    public String getbDay() {
        return bDay;
    }

    public String getEmail() {
        return email;
    }
}
