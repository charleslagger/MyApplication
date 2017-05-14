package com.khoenguyen.androidbasic.Presenter.LogIn;

import com.khoenguyen.androidbasic.Model.LogIn.Account;
import com.khoenguyen.androidbasic.View.LogIn.ViewLogIn;

/**
 * Created by Admin on 5/13/2017.
 */

public class PresenterLogic implements PresenterImp{
    //Lay du lieu tai khoan tu Model
    Account account = new Account();
    ViewLogIn viewLogIn;

    public PresenterLogic(ViewLogIn v){
        this.viewLogIn = v;
    }
    @Override
    public void KiemTraDangNhap(String name, String pass) {
        if(name.equals(account.getName()) && pass.equals(account.getPass())){
            //dang nhap thanh cong
            viewLogIn.LoginSuccess();
        }else{
            //dang nhap that bai
            viewLogIn.LoginFailed();
        }
    }
}
