package com.khoenguyen.androidbasic.Model.LogIn;

/**
 * Created by Admin on 5/13/2017.
 */

public class Account {
    String name, pass;

    public Account(){
        name = "android";
        pass = "basic";
    }
    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public String getPass() {
        return pass;
    }

    /*public void setPass(String pass) {
        this.pass = pass;
    }*/
}
