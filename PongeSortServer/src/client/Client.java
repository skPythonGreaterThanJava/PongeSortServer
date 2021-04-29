/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesana.federico
 */
public class Client {
    
    public static void main(String args[]) {
        try {
            Socket server = new Socket("localhost", 42069);
            char[] list = new char[10];
            for(int i = 0; i < 10; i++)
                list[i] = (char) (int) (Math.random()*23 + 65);
            
            OutputStream toServer = server.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(toServer);
            
            out.writeObject(list);
            
            InputStream fromServer = server.getInputStream();
            ObjectInputStream in = new ObjectInputStream(fromServer);
            
            char[] randls = (char[]) in.readObject();
            
            fromServer.close();
            in.close();
            
            toServer.flush();
            toServer.close();
            out.flush();
            out.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
