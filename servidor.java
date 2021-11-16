/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosmensajes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author jais1
 */
public class servidor {
    public static void main(String[] args){
        DatagramSocket s=null;
        DatagramPacket in, out;
        InetAddress cliente_addr=null;
        int clien_port;
        byte brecv[] = new byte[100];
        byte bsend[] = new byte[100];
        int num[], res;
        try {
            s= new DatagramSocket(2500);
            in= new DatagramPacket(brecv, 100);
            while (true){
                s.receive(in);
                brecv = in.getData();
                cliente_addr= in.getAddress();
                clien_port= in.getPort();
                ByteArrayInputStream bais= new ByteArrayInputStream(brecv);
                ObjectInputStream dis = new ObjectInputStream(bais);
                num= (int[]) dis.readObject();
                res= num[0]+ num[1];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                dos.writeInt(res);
                bsend = baos.toByteArray();
                out = new DatagramPacket(bsend, bsend.length, cliente_addr, clien_port);
                s.send(out);
            }
            
        } catch (Exception e) {
            System.err.println("Error en recibir datos" +e);
            e.printStackTrace();
        }
    }
    
}
