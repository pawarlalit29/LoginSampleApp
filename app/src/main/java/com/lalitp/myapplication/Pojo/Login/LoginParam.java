package com.lalitp.myapplication.Pojo.Login;

/**
 * Created by atulsia on 31/10/16.
 */

public class LoginParam {
    private transient  String uName;
    private transient String password;



    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
