package com.example.testtrainee.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.testtrainee.BR;
import com.example.testtrainee.LogoutAtivity;
import com.example.testtrainee.MainActivity;
import com.example.testtrainee.models.User;

public class LoginViewModel extends BaseObservable {
    private String email;
    private String password;
    private boolean isChecked;
    private MainActivity mainActivity;
    SharedPreferences sharedPreferences;

    public LoginViewModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        sharedPreferences = mainActivity.getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        password = sharedPreferences.getString("password","");
        isChecked = sharedPreferences.getBoolean("checked", false);
    }

    public ObservableField<String> messageLogin = new ObservableField<>();

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyPropertyChanged(BR.checked);
    }
    public void onClickLogin(){
        User user = new User(getEmail(), getPassword());
        if(user.isValidPass()){
            if(isChecked()){
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("email",email);
                editor.putString("password",password);
                editor.putBoolean("checked",true);
                editor.commit();
            }else{
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.remove("checked");
                editor.commit();
            }
            Intent intent = new Intent(mainActivity, LogoutAtivity.class);
            mainActivity.startActivity(intent);
        }
        else{
            messageLogin.set("Password must be at least 6 characters and contain both numbers and special characters");
        }
    }
}
