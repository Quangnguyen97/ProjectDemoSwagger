package com.example.demoswagger.SQLServer.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityObject {

    private int sapXep;
    private String thongTinHangHoa;
    private double soLuong;
    private double soTien;
    private int loai;

    public CommodityObject() {
    }

    public CommodityObject(int sapXep, String thongTinHangHoa, double soLuong, double soTien, int loai) {
        super();
        this.sapXep = sapXep;
        this.thongTinHangHoa = thongTinHangHoa;
        this.soLuong = soLuong;
        this.soTien = soTien;
        this.loai = loai;
    }

    public int getSapXep() {
        return sapXep;
    }

    public void setSapXep(int sapXep) {
        this.sapXep = sapXep;
    }

    public String getThongTinHangHoa() {
        return thongTinHangHoa;
    }

    public void setThongTinHangHoa(String thongTinHangHoa) {
        this.thongTinHangHoa = thongTinHangHoa;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

}
