/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author sesana.federico
 */
public class Client {
    
    public static void main(String args[]) throws IOException {
        Socket server = new Socket("localhost", 42069);
        char[] list = new char[10];
        for(int i = 0; i < 10; i++) 
            list[i] = (char) (int) (Math.random()*23 + 65);
        
        OutputStream toServer = server.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(toServer);
        
        out.writeObject(list);
        
        toServer.flush();
        toServer.close();
        out.flush();
        out.close();
        
        
    }
}
