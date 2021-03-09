package com.example.testtrainee.models;

import android.text.TextUtils;

public class User {
    private String emai;
    private String password;

    public User(String emai, String password) {
        this.emai = emai;
        this.password = password;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidPass(){
        boolean valid = true;
        String numbers = "(.*[0-9].*)";
        String specialChars = "(.*[,~,!, ,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (!password.matches(numbers ) || !password.matches(specialChars ) || password.length() < 6 || TextUtils.isEmpty(password))
        {
            valid = false;
        }
        return  valid;
    }
}
