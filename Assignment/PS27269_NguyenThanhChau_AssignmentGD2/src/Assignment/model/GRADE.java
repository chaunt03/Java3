/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.model;

/**
 *
 * @author Admin
 */
public class GRADE {
    private int ID;
    private String MaSV;
    private int TiengAnh;
    private int TinHoc;
    private int GDTC;

    public GRADE() {
    }

    public GRADE(int ID, String MaSV, int TiengAnh, int TinHoc, int GDTC) {
        this.ID = ID;
        this.MaSV = MaSV;
        this.TiengAnh = TiengAnh;
        this.TinHoc = TinHoc;
        this.GDTC = GDTC;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public int getTiengAnh() {
        return TiengAnh;
    }

    public void setTiengAnh(int TiengAnh) {
        this.TiengAnh = TiengAnh;
    }

    public int getTinHoc() {
        return TinHoc;
    }

    public void setTinHoc(int TinHoc) {
        this.TinHoc = TinHoc;
    }

    public int getGDTC() {
        return GDTC;
    }

    public void setGDTC(int GDTC) {
        this.GDTC = GDTC;
    }

    @Override
    public String toString() {
        return "GRADE{" + "ID=" + ID + ", MaSV=" + MaSV + ", TiengAnh=" + TiengAnh + ", TinHoc=" + TinHoc + ", GDTC=" + GDTC + '}';
    }
    
    public double getDiemTB() {
        return (TiengAnh+TinHoc+GDTC)/3;
    }
}
