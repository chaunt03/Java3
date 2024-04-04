/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.dao;

import Assignment.database.JDBCUtil;
import Assignment.model.STUDENT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class STUDENTDAO implements DAOInterface<STUDENT> {

    public static STUDENTDAO getInstance() {
        return new STUDENTDAO();
    }

    @Override
    public int insert(STUDENT t) {
        int ketqua = 0;
        try {
            // Bước 1: tạo kết nối đến csdl
            Connection con = JDBCUtil.getConnection();

            // Bước 2: Tạo ra đối tượng statement
            String sql = "insert into STUDENT values(?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaSV());
            st.setString(2, t.getHoTen());
            st.setString(3, t.getEmail());
            st.setString(4, t.getSoDT());
            st.setBoolean(5, t.getGt());
            st.setString(6, t.getDiaChi());
            st.setString(7, t.getHinh());

            // Bước 3: thực thi câu lệnh sql
            ketqua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketqua + " dòng bị thay đổi!");

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketqua;
    }

    @Override
    public int update(STUDENT t) {
        int ketqua = 0;
        Connection con = JDBCUtil.getConnection();
        String sql = "update STUDENT set HoTen=?, Email=?, SoDT=?, GioiTinh=?, DiaChi=? where MASV=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getHoTen());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getSoDT());
            pst.setBoolean(4, t.getGt());
            pst.setString(5, t.getDiaChi());
            pst.setString(6, t.getMaSV());

            ketqua = pst.executeUpdate();

            System.out.println("Bạn đã thực thi" + sql);
            System.out.println("Có " + ketqua + " dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(STUDENTDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int delete(STUDENT t) {
        int ketqua = 0;
        try {
            // Bước 1: tạo kết nối đến csdl
            Connection con = JDBCUtil.getConnection();

            // Bước 2: Tạo ra đối tượng statement
            Statement st = con.createStatement();

            // Bước 3: thực thi câu lệnh sql
            String sql = "Delete from STUDENT where MASV = '" + t.getMaSV() + "'";

            ketqua = st.executeUpdate(sql);

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketqua + " dòng bị thay đổi!");

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketqua;
    }

    @Override
    public ArrayList<STUDENT> selectAll() {
        ArrayList<STUDENT> ketqua = new ArrayList<STUDENT>();
        try {
            // Bước 1: tạo kết nối đến csdl
            Connection con = JDBCUtil.getConnection();

            // Bước 2: Tạo ra đối tượng statement
            Statement st = con.createStatement();

            // Bước 3: thực thi câu lệnh sql
            String sql = "select * from STUDENT";

            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);
            // Bước 4:
            while (rs.next()) {
                String masv = rs.getString("MASV");
                String hoten = rs.getString("HoTen");
                String sodt = rs.getString("SoDT");
                String email = rs.getString("Email");
                Boolean gt = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");

                STUDENT sv = new STUDENT(masv, hoten, email, sodt, gt, diaChi, hinh);
                ketqua.add(sv);
            }

            // Bước 5:
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketqua;
    }

    @Override
    public STUDENT selectByID(STUDENT t) {
        STUDENT ketqua = null;
        try {
            // Bước 1: tạo kết nối đến csdl
            Connection con = JDBCUtil.getConnection();

            // Bước 2: Tạo ra đối tượng statement
            String sql = "select * from STUDENT where MASV = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaSV());

            // Bước 3: thực thi câu lệnh sql
            ResultSet rs = st.executeQuery();
            System.out.println(sql);

            // Bước 4:
            while (rs.next()) {
                String masv = rs.getString("MASV");
                String hoten = rs.getString("HoTen");
                String email = rs.getString("Email");
                String sodt = rs.getString("SoDT");
                Boolean gt = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");

                ketqua = new STUDENT(masv, hoten, email, sodt, gt, diaChi, hinh);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketqua;
    }

    @Override
    public ArrayList<STUDENT> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
