package com.example.thinhnbph36492_mob2041_ass.Model;

public class ThanhVien {
    private int maTV;
    private String hoTen, namSinh, gioiTinh; // Thêm trường giới tính

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String namSinh, String gioiTinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh; // Thêm vào constructor
    }

    public ThanhVien(String hoTen, String namSinh, String gioiTinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh; // Thêm vào constructor
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                '}';
    }
}
