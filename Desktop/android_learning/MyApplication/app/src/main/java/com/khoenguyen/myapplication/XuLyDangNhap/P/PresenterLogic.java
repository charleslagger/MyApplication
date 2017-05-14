package com.khoenguyen.myapplication.XuLyDangNhap.P;

import com.khoenguyen.myapplication.XuLyDangNhap.M.Model;
import com.khoenguyen.myapplication.XuLyDangNhap.V.ViewXL;

/**
 * Created by Admin on 5/13/2017.
 */

public class PresenterLogic implements Presenter{
    Model model;
    ViewXL viewXL;
    public PresenterLogic(ViewXL viewXL){
        this.viewXL = viewXL;
    }
    @Override
    public void KiemTraDangNhap(String name, String pass) {
        //Goi toi model de lay du lieu
        model = new Model();
        if(name.equals(model.getName()) && pass.equals(model.getPass())){
            //Tra ve View dang nhap thanh cong
            viewXL.DangNhapThanhCong();
        }
        else{
            //tra ve View dang nhap that bai
            viewXL.DangNhapThatBai();
        }
    }
}
