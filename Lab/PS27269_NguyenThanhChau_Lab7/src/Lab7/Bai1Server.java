/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Bai1Server {
    public static void main(String[] args) {
        try {
            ServerSocket serversocket = new ServerSocket(9999);
            System.out.println("Server is Connecting...");
            Socket socket = serversocket.accept();
            System.out.println("Server is Connect");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while(true) {
                double num1 = dis.readDouble();
                double num2 = dis.readDouble();
                System.out.println("2 số nhận được từ Client là: "+num1+ " " + num2);
                double sum = num1 + num2;
                dos.writeDouble(sum);
                dos.flush();
                System.out.println("Tổng 2 số là :"+sum);
            }
        } catch (Exception e) {
        }
    }
}
