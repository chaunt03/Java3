/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.dao;

import Assignment.database.JDBCUtil;
import Assignment.model.GRADE;
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
public class GRADEDAO implements DAOInterface<GRADE> {

    public static GRADEDAO getInstance() {
        return new GRADEDAO();
    }

    @Override
    public int insert(GRADE t) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "insert into GRADE " + "values("+t.getID()+",'" +t.getMaSV()+"', "+t.getTiengAnh()+", "+t.getTinHoc()+", "+t.getGDTC()+")";
            System.out.println(sql);
            System.out.println("SUCCESS");
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int update(GRADE t) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "update GRADE " +
                    "set TiengAnh = "+t.getTiengAnh()+", "
                    +"TinHoc = " + t.getTinHoc() + ", "
                    +"GDTC = " + t.getGDTC()+" where MASV = '" + t.getMaSV()+"'";
            System.out.println(sql);
            System.out.println("SUCCESS");
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int delete(GRADE t) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "delete from GRADE where MASV = '" +t.getMaSV()+"'";
            System.out.println(sql);
            System.out.println("SUCCESS");
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<GRADE> selectAll() {
        ArrayList<GRADE> ketqua = new ArrayList<GRADE>();
        // Bước 1: tạo kết nối đến csdl
        Connection con = JDBCUtil.getConnection();

        // Bước 2: Tạo ra đối tượng statement
        String sql = "select * from GRADE";
        try {
            Statement st = con.createStatement();

            // Bước 3: thực thi câu lệnh sql
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);
            // Bước 4:
            while (rs.next()) {
                int id = rs.getInt(1);
                String masv = rs.getString(2);
                int ta = rs.getInt(3);
                int th = rs.getInt(4);
                int gdtc = rs.getInt(5);

                GRADE gr = new GRADE(id, masv, th, th, gdtc);
                ketqua.add(gr);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(GRADEDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketqua;
    }

    @Override
    public GRADE selectByID(GRADE t) {
        GRADE ketqua = null;
        try {
            // Bước 1: tạo kết nối đến csdl
            Connection con = JDBCUtil.getConnection();

            // Bước 2: Tạo ra đối tượng statement
            String sql = "select * from GRADE where MASV = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaSV());

            // Bước 3: thực thi câu lệnh sql
            ResultSet rs = st.executeQuery();
            System.out.println(sql);

            // Bước 4:
            while (rs.next()) {
                int Id = rs.getInt("ID");
                String masv = rs.getString("MASV");
                int tienganh = rs.getInt("TiengAnh");
                int tinhoc = rs.getInt("TinHoc");
                int gdtc = rs.getInt("GDTC");

                ketqua = new GRADE(Id, masv, tienganh, tinhoc, gdtc);
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
    public ArrayList<GRADE> selectByCondition(String condition) {
        ArrayList<GRADE> ketqua = new ArrayList<GRADE>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "select * from GRADE where " + condition;
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String masv = rs.getString("MASV");
                int tienganh = rs.getInt("TiengAnh");
                int tinhoc = rs.getInt("TinHoc");
                int gdtc = rs.getInt("GDTC");

                GRADE gr = new GRADE(id, masv, tienganh, tinhoc, gdtc);
                ketqua.add(gr);
            }

            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return ketqua;
    }

}
