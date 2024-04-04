/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.model;

import Assignment.*;

/**
 *
 * @author Admin
 */
public class STUDENT {
    private String MaSV;
    private String hoTen;
    private String Email;
    private String soDT;
    private Boolean gt;
    private String diaChi;
    private String hinh;

    public STUDENT(String MaSV, String hoTen, String Email, String soDT, Boolean gt, String diaChi, String hinh) {
        this.MaSV = MaSV;
        this.hoTen = hoTen;
        this.Email = Email;
        this.soDT = soDT;
        this.gt = gt;
        this.diaChi = diaChi;
        this.hinh = hinh;
    }

    public STUDENT() {
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public Boolean getGt() {
        return gt;
    }

    public void setGt(Boolean gt) {
        this.gt = gt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    @Override
    public String toString() {
        return "STUDENT{" + "MaSV=" + MaSV + ", hoTen=" + hoTen + ", Email=" + Email + ", soDT=" + soDT + ", gt=" + gt + ", diaChi=" + diaChi + ", hinh=" + hinh + '}';
    }
    
    
}
