/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import Assignment.dao.GRADEDAO;
import Assignment.dao.STUDENTDAO;
import Assignment.model.GRADE;
import Assignment.model.STUDENT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QuanLyDiemSinhVien extends javax.swing.JFrame {

    private int indexSv = -1;
    ArrayList<GRADE> list = GRADEDAO.getInstance().selectAll();
    DefaultTableModel tblModel;
    int index = -1;

    /**
     * Creates new form QuanLyDiemSinhVien
     */
    public QuanLyDiemSinhVien() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Quản lý điểm sinh viên");
        fillToTable();
    }

    public boolean validateForm() {
        if (txtMaSVup.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã SV không được để trống !");
            return false;
        }
        if (txtMaSVdown.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã SV không được để trống !");
            return false;
        }
        if (txtTA.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Điểm Tiếng Anh không được để trống !");
            return false;
        }
        if (txtTinHoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Điểm Tin học không được để trống !");
            return false;
        }
        if (txtGDTC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Điểm GDTC không được để trống !");
            return false;
        }
        return true;
    }

    public void fillToTable() {
        tblModel = (DefaultTableModel) tblStudent.getModel();
        if (list.size() > 0) {
            tblModel.setRowCount(0);
            for (GRADE diem : list) {
                STUDENT find = new STUDENT();
                find.setMaSV(diem.getMaSV());
                STUDENT sv2 = STUDENTDAO.getInstance().selectByID(find);
                Object[] e = {sv2.getMaSV(), sv2.getHoTen(), diem.getTiengAnh(), diem.getTinHoc(), diem.getGDTC(), diem.getDiemTB()};
                tblModel.addRow(e);
            }
        } else {
            tblModel.setRowCount(0);
        }
    }

    public void ShowDetail(int index) {
        STUDENT svTam = new STUDENT();
        svTam.setMaSV(list.get(index).getMaSV());
        STUDENT sv = STUDENTDAO.getInstance().selectByID(svTam);
        lblHoTen.setText(sv.getHoTen());
        txtMaSVdown.setText(list.get(index).getMaSV());
        txtTA.setText(String.valueOf(list.get(index).getTiengAnh()));
        txtTinHoc.setText(String.valueOf(list.get(index).getTinHoc()));
        txtGDTC.setText(String.valueOf(list.get(index).getGDTC()));
        lblDiemTB.setText(String.valueOf(list.get(index).getDiemTB()));
    }

    public void searchStudent() {
        if (!txtMaSVup.getText().equals(txtMaSVdown.getText())) {
            STUDENT find = new STUDENT();
            find.setMaSV(txtMaSVup.getText());
            STUDENT sv = STUDENTDAO.getInstance().selectByID(find);
            GRADE gradeTemp = new GRADE();
            gradeTemp.setMaSV(sv.getMaSV());
            GRADE grade = GRADEDAO.getInstance().selectByID(gradeTemp);
            lblHoTen.setText(sv.getHoTen());
            txtMaSVdown.setText(sv.getMaSV());
            txtTA.setText(grade.getTiengAnh() + "");
            txtTinHoc.setText(grade.getTinHoc() + "");
            txtGDTC.setText(grade.getGDTC() + "");
            lblDiemTB.setText(grade.getDiemTB() + "");
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên !");
        }
    }

    public void Delete() {
        GRADE find = new GRADE();
        find.setMaSV(txtMaSVdown.getText());
        GRADEDAO.getInstance().delete(find);
        JOptionPane.showMessageDialog(this, "Xóa thành công sinh viên " + txtMaSVdown.getText());
        fillToTable();
    }

    public int findStuIndex(String MaSV) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaSV().contains(MaSV)) {
                indexSv = i;
                return indexSv;
            }
        }
        return -1;
    }

    public GRADE readFormGrade() {
        GRADE diem = new GRADE();
        diem.setMaSV(txtMaSVdown.getText());
        diem.setTiengAnh(Integer.parseInt(txtTA.getText()));
        diem.setTinHoc(Integer.parseInt(txtTinHoc.getText()));
        diem.setGDTC(Integer.parseInt(txtGDTC.getText()));
        return diem;
    }

    public boolean addGrade(GRADE diem1) {
        int i = findStuIndex(diem1.getMaSV());
        System.out.println(i);
        if (i != -1) {
            int choose = JOptionPane.showConfirmDialog(null, "Đã tìm thấy Sinh Viên " + diem1.getMaSV() + ", xác nhận ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                list.set(i, diem1);
                GRADEDAO.getInstance().insert(diem1);
                fillToTable();
                JOptionPane.showMessageDialog(this, "Lưu thành công");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Giữ nguyên");
                return false;
            }
        }
        JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên " + diem1.getMaSV());
        return false;
    }

    public boolean update(GRADE diem2) {
        int i = findStuIndex(diem2.getMaSV());
        System.out.println(i);
        if (i != -1) {
            int choose = JOptionPane.showConfirmDialog(null, "Đã tìm thấy Sinh Viên " + diem2.getMaSV() + ", xác nhận ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                list.set(i, diem2);
                GRADEDAO.getInstance().update(diem2);
                fillToTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Giữ nguyên");
                return false;
            }
        }
        JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên " + diem2.getMaSV());
        return false;
    }

    public void MLeft() {
        index = 0;
        tblStudent.setRowSelectionInterval(index, index);
        ShowDetail(index);
    }

    public void MRight() {
        index = list.size() - 1;
        tblStudent.setRowSelectionInterval(index, index);
        ShowDetail(index);
    }

    public void Left() {
        try {
            if (index == 0) {
                MRight();
            } else {
                index--;
            }
            tblStudent.setRowSelectionInterval(index, index);
            ShowDetail(index);
        } catch (Exception e) {
            ShowDetail(index = list.size() - 1);
        }
    }

    public void Right() {
        try {
            if (index == list.size() - 1) {
                MLeft();
            } else {
                index++;
            }
            tblStudent.setRowSelectionInterval(index, index);
            ShowDetail(index);
        } catch (Exception e) {
            ShowDetail(index = 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSVup = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaSVdown = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTA = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTinHoc = new javax.swing.JTextField();
        txtGDTC = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblDiemTB = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnMLeft = new javax.swing.JButton();
        btnLeft = new javax.swing.JButton();
        btnRight = new javax.swing.JButton();
        btnMRight = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Quản Lý Điểm Sinh Viên");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel3.setText("Mã SV:");

        btnSearch.setForeground(new java.awt.Color(0, 102, 204));
        btnSearch.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive\\Hình ảnh\\Java\\search.png")); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSVup, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSVup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tìm Kiếm");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Họ tên SV:");

        lblHoTen.setForeground(new java.awt.Color(0, 102, 204));

        jLabel6.setText("Mã SV:");

        jLabel7.setText("Tiếng anh:");

        jLabel8.setText("Tin học:");

        jLabel9.setText("Giáo dục TC:");

        jLabel10.setText("Điểm TB:");

        lblDiemTB.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDiemTB.setForeground(new java.awt.Color(0, 102, 204));
        lblDiemTB.setText("9.0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHoTen)
                    .addComponent(txtMaSVdown, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(txtTA)
                    .addComponent(txtTinHoc)
                    .addComponent(txtGDTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(lblDiemTB))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblHoTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaSVdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTinHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblDiemTB)))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGDTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new java.awt.GridLayout(4, 1, 1, 1));

        btnNew.setForeground(new java.awt.Color(0, 102, 204));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel3.add(btnNew);

        btnSave.setForeground(new java.awt.Color(0, 102, 204));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel3.add(btnSave);

        btnDelete.setForeground(new java.awt.Color(0, 102, 204));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete);

        btnUpdate.setForeground(new java.awt.Color(0, 102, 204));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(btnUpdate);

        jPanel4.setLayout(new java.awt.GridLayout(1, 1, 1, 1));

        btnMLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/next-button - Copy.png"))); // NOI18N
        btnMLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMLeftActionPerformed(evt);
            }
        });
        jPanel4.add(btnMLeft);

        btnLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/right.png"))); // NOI18N
        btnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });
        jPanel4.add(btnLeft);

        btnRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/right - Copy.png"))); // NOI18N
        btnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });
        jPanel4.add(btnRight);

        btnMRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assignment/Anh/next-button.png"))); // NOI18N
        btnMRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMRightActionPerformed(evt);
            }
        });
        jPanel4.add(btnMRight);

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Họ tên", "Tiếng Anh", "Tin học", "GDTC", "Điểm TB"
            }
        ));
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudent);

        jLabel12.setForeground(new java.awt.Color(0, 102, 204));
        jLabel12.setText("3 sinh viên có điểm cao nhất:");

        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLogout)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtMaSVup.setText("");
        lblHoTen.setText("");
        txtMaSVdown.setText("");
        txtTA.setText("");
        txtTinHoc.setText("");
        txtGDTC.setText("");
        lblDiemTB.setText("");
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchStudent();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int choose = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất khỏi tài khoản ?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Đăng xuất thành công");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                Login frame = new Login();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnMLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMLeftActionPerformed
        // TODO add your handling code here:
        MLeft();
    }//GEN-LAST:event_btnMLeftActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        // TODO add your handling code here:
        index = tblStudent.getSelectedRow();
        ShowDetail(index);
    }//GEN-LAST:event_tblStudentMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        Delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            if (txtMaSVdown.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã sinh viên");
            } else {
                update(readFormGrade());
                fillToTable();
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
//        if(validateForm()) {
//            if(txtMaSVdown.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã sinh viên");
//            }else {
//                addGrade(readFormGrade());
//                fillToTable();
//            }
//        }
        if (!validateForm()) {
            GRADE diem = readFormGrade();
            GRADEDAO.getInstance().insert(diem);
            fillToTable();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        // TODO add your handling code here:
        Left();
    }//GEN-LAST:event_btnLeftActionPerformed

    private void btnMRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMRightActionPerformed
        // TODO add your handling code here:
        MRight();
    }//GEN-LAST:event_btnMRightActionPerformed

    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        // TODO add your handling code here:
        Right();
    }//GEN-LAST:event_btnRightActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyDiemSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyDiemSinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLeft;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMLeft;
    private javax.swing.JButton btnMRight;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRight;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiemTB;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTextField txtGDTC;
    private javax.swing.JTextField txtMaSVdown;
    private javax.swing.JTextField txtMaSVup;
    private javax.swing.JTextField txtTA;
    private javax.swing.JTextField txtTinHoc;
    // End of variables declaration//GEN-END:variables
}
