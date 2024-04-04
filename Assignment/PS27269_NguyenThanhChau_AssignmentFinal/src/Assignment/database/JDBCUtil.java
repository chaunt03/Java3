/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class JDBCUtil {

    public static Connection getConnection() {
        Connection c = null;
        //Đăng kí SQLServer với DriverManager
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            //Các thông số
            String url = "jdbc:sqlserver://localhost:1433;databaseName=AssignmentJAVA;user=sa;password=123456;encrypt=true;trustServerCertificate=true";

            //Tạo kết nối
            c = DriverManager.getConnection(url);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(Connection c) {
        try {
            DatabaseMetaData mtdt = c.getMetaData();
            System.out.println(mtdt.getDatabaseProductName());
            System.out.println(mtdt.getDatabaseProductVersion());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
