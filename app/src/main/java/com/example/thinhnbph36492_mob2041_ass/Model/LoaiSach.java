package com.example.thinhnbph36492_mob2041_ass.Model;

public class LoaiSach {
    private int maLoai ;
    private String tenLoai;

    private String ten;

    public LoaiSach() {
    }

    public LoaiSach(int maLoai, String tenLoai,String ten) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.ten = ten;
    }

    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
