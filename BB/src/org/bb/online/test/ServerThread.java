package org.bb.online.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.bb.online.bs.player.PlayerInfo;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class ServerThread {
//	public ServerSocket server;
//	public Socket[] clients;
//	public ServerMain(){
//		
//	}
//	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ServerMain sm = new ServerMain();
//		try {
//			sm.server = new ServerSocket(7800);
//			Socket client = sm.server.accept();
//			System.out.println("cliente conectado!");
//			ServerInputThread sit = new ServerInputThread(client);
//			sit.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	
//	 
//	import java.io.IOException;
//	import java.io.ObjectInputStream;
//	import java.io.ObjectOutputStream;
//	import java.lang.ClassNotFoundException;
//	import java.net.ServerSocket;
//	import java.net.Socket;
	 
//	/**
//	 * This class implements java Socket server
//	 * @author pankaj
//	 *
//	 */
	     
	    //static ServerSocket variable
	    private static ServerSocket server;
	    //socket server port on which it will listen
	    private static int port = 9876;
	    
	    
	    public void run(){
	    	try{
	        //create the socket server object
	        server = new ServerSocket(port);
	        PlayerInfo playerInfo;
	        XStream xstream = new XStream(new DomDriver());
			xstream.alias("PlayerInfo", PlayerInfo.class);
			xstream.alias("PlayerData", PlayerInfo.PlayerData.class);
	        //keep listens indefinitely until receives 'exit' call or program terminates
	        while(true){
	        	System.out.println("Waiting for client request");
	        	//creating socket and waiting for client connection
	        	Socket socket = server.accept();
	        	//read from socket to ObjectInputStream object
	        	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	        	//convert ObjectInputStream object to String
	        	String message = (String) ois.readObject();
	        	System.out.println("Message Received: " + message);
	        	//create ObjectOutputStream object
	        	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	        	//write object to Socket
	        	playerInfo = (PlayerInfo) xstream.fromXML(message);
	        	oos.writeObject(playerInfo);
	        	//close resources
	        	ois.close();
	        	oos.close();
	        	socket.close();
	        	//terminate the server if client sends exit request
	        	if(message.equalsIgnoreCase("exit")) break;
	        }
	        System.out.println("Shutting down Socket server!!");
	        //close the ServerSocket object
	        server.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }

}
