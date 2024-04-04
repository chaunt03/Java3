/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Bai1Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client is Connecting...");
            Socket socket = new Socket("localhost", 9999);
            System.out.println("Client is Connect");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while(true) {
                System.out.println("Nhập vào số thứ 1: ");
                dos.writeDouble(new Scanner(System.in).nextDouble());
                dos.flush();
                System.out.println("Nhập vào số thứ 2: ");
                dos.writeDouble(new Scanner(System.in).nextDouble());
                dos.flush();
                System.out.println("Tổng 2 số: " + dis.readDouble());
                System.out.println("Tiếp tục? (y/n): ");
                String traloi = new Scanner(System.in).nextLine();
                if(traloi.equals("n") || traloi.equals("N")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
