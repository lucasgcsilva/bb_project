package org.bb.online.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.bb.online.bs.GameConfiguration;
import org.bb.online.bs.player.PlayerInfo;
import org.bb.online.bs.player.PlayerInfo.PlayerData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class ServerThread extends Thread {
	    //static ServerSocket variable
	    private static ServerSocket server;
	    //socket server port on which it will listen
	    private static int port = 9876;
		private PlayerInfo instance = PlayerInfo.getInstance();
		private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	    private boolean isSendMessage = false;
	    private String sendMessage = null;
	    private PlayerInfo playerInfo = PlayerInfo.getInstance();
	    public ServerThread (){
	    }
	    
	    public void run(){
	    	try{
	        //create the socket server object
	        server = new ServerSocket(port);
	        Socket socket = null;
	        //keep listens indefinitely until receives 'exit' call or program terminates
        	ObjectInputStream ois = null;
        	ObjectOutputStream oos = null;
        	BufferedInputStream bis = null;
        	BufferedOutputStream bos = null;
			while(true){
				
	        	try{
	        	System.out.println("Waiting for client request");
	        	//creating socket and waiting for client connection
	        	
	        	if(socket == null) {
	        		socket = server.accept();
//		        	ois = new ObjectInputStream(socket.getInputStream());
		        	oos = new ObjectOutputStream(socket.getOutputStream());
		        	BufferedInputStream bi = new BufferedInputStream(socket.getInputStream());
		        	ois = new ObjectInputStream(bi);
	        	}
	        	//read from socket to ObjectInputStream object
	        	String message = null;

//	        	if(socket.getInputStream() !=null){
	 	        	System.out.println("Sending request to Socket Server");
		        	//convert ObjectInputStream object to String
		        	message = (String) ois.readObject();
//		        	playerInfo = (PlayerInfo) xstream.fromXML(message);
//		        	instance = playerInfo;
//		        	instance.getPlayersData()[playerInfo.getNumPlayer()-1] = playerInfo.getPlayersData()[playerInfo.getNumPlayer()-1];
//		        	instance.setStartGame(playerInfo.isStartGame());
		        	System.out.println("Message Received: " + message);
		        	if (message != null){
						String[] linhaSplit = message.split("\\|");
						int opt = Integer.parseInt(linhaSplit[1]);
						int numplayer = Integer.parseInt(linhaSplit[0]);
						switch (opt){
							case 1 : playerInfo.getPlayersData()[numplayer-1].setKeyUp(true);
								break;
							case 2 : playerInfo.getPlayersData()[numplayer-1].setKeyDown(true);
								break;
							case 3 : playerInfo.getPlayersData()[numplayer-1].setKeyLeft(true);
								break;
							case 4 : playerInfo.getPlayersData()[numplayer-1].setKeyRight(true);
								break;
							case 5 : playerInfo.getPlayersData()[numplayer-1].setKeyBomb(true);
								break;
							case 6 : playerInfo.getPlayersData()[numplayer-1].setKeyUp(false);
									 playerInfo.getPlayersData()[numplayer-1].setKeyDown(false);
									 playerInfo.getPlayersData()[numplayer-1].setKeyLeft(false);
									 playerInfo.getPlayersData()[numplayer-1].setKeyRight(false);
									 playerInfo.getPlayersData()[numplayer-1].setKeyBomb(false);
									 break;
						}
					
					}
//		        	ois.reset();
//	        	}
	        	//create ObjectOutputStream object
//	        	if(socket.getOutputStream() != null){

		        	System.out.println("Reading request from Socket Server");
		        	//write object to Socket
		        	instance.setNumPlayer(gc.getNumPlayer());
		        	message = null;
		        	
		        	if (isSendMessage){
		        		message = sendMessage;
		        	}
		        	
//		        	xml = xstream.toXML(instance);
		        	oos.writeObject(message);
		        	oos.flush();
		        	isSendMessage = false;
//		        	oos.reset();
//	        	}
//	        	sleep(1000);
	        	ois.close();
	        	oos.close();
	        	socket.close();
	        	socket = null;
	        	ois = null;
	        	oos = null;
	        	//close resources
	        	//terminate the server if client sends exit request
	        	if(message.equalsIgnoreCase("exit")){
	        		socket.close();
	        		break;
	        	}
	        	}catch(Exception e){}
	        }
	        System.out.println("Shutting down Socket server!!");
	        //close the ServerSocket object
	        server.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    public void setIsSendMessage(boolean send){
	    	this.isSendMessage = send;
	    }
	    
	    public void setSendMessage(String msg){
	    	this.sendMessage = msg;
	    }

}
