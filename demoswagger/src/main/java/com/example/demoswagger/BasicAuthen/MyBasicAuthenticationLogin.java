package com.example.demoswagger.BasicAuthen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBasicAuthenticationLogin {

    private String maNguoiDung;
    private String matKhau;

    public MyBasicAuthenticationLogin() {
    }

    public MyBasicAuthenticationLogin(String maNguoiDung, String matKhau) {
        super();
        this.maNguoiDung = maNguoiDung;
        this.matKhau = matKhau;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
