/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

/**
 *
 * @author luuvi
 */
public class HocVien {

    private int maHV;
    private int maKH;
    private String maNH;
    private double diemTB = -1;

    public HocVien() {
    }

    public HocVien(int maHV, int maKH, String maNH, double diemTB) {
        this.maHV = maHV;
        this.maKH = maKH;
        this.maNH = maNH;
        this.diemTB = diemTB;
    }

    public int getMaHV() {
        return maHV;
    }

    public void setMaHV(int maHV) {
        this.maHV = maHV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    @Override
    public String toString() {
        return "HocVien{" + "maHV=" + maHV + ", maKH=" + maKH + ", maNH=" + maNH + ", diemTB=" + diemTB + '}';
    }

}
