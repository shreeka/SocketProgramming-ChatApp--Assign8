/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shreeka.socketprogramming.listener;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Decode
 */
public class ClientHandler {
    private List<Client> clients=new ArrayList<>();
    
    public void addClient(Client c){
    clients.add(c);
    
    }
    
    public List<Client> getClients(){
            return clients;
} 
    public Client getByUsername(String userName){
    for(Client c:clients)
    {
        if(c.getUserName().equalsIgnoreCase(userName)){
        return c;
        }
        
    }
    return null;
    }
    
     public Client getBySocket(Socket socket){
    for(Client c:clients)
    {
        if(c.getSocket().equals(socket)){
        return c;
        }
        
    }
    return null;
    }
     
     public void broadcastMessage(String userName ,String msg) throws IOException
     {
         for(Client client:clients)
         {
             if(client!=getByUsername(userName))
             {
                 PrintStream ps=new PrintStream(client.getSocket().getOutputStream());
                 ps.println(msg);
             }
         }
     }
     
     public void privateMessage(String userName,String msg) throws IOException
     {
         Client client=getByUsername(userName);
         if(client!=null)
         {
             PrintStream ps=new PrintStream(client.getSocket().getOutputStream());
                 ps.println(msg);
         }
     }
}
