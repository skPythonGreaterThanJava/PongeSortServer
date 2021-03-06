/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesana.federico
 */
public class Server {
    
    public static void main(String args[]){
        try {
            ServerSocket server = new ServerSocket(42069);
            Socket client = server.accept();
            
            InputStream fromClient = client.getInputStream();
            ObjectInputStream in = new ObjectInputStream(fromClient);
            
            char[] list = (char[]) in.readObject();
            System.out.println(list);
            
            char[] randls = new char[10]; 
            
            for(int i = 0; i < 10; i++){
                int j = 0;
                while(true) {
                    j = (int) Math.random()*9;
                    if(list[j] != -1)
                        break;
                }
                
                randls[i] = list[j];
            }
            
            OutputStream toClient = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(toClient);
            
            out.writeObject(randls);
            
            toClient.flush();
            out.flush();
            toClient.close();
            out.close();
            
            fromClient.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
