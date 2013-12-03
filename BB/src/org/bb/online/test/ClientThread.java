package org.bb.online.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.bb.online.bs.GameConfiguration;
import org.bb.online.bs.player.PlayerInfo;
import org.bb.online.bs.player.PlayerInfo.PlayerData;
import org.newdawn.slick.Image;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ClientThread extends Thread{
//	Socket client = null;
//	ObjectInputStream ois = null;
//	private PlayerInfo playerInfo;
//	private PlayerInfo instance = PlayerInfo.getInstance();
//	
//	private BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//	
//	public ClientThread(){
//		
//	}
//	
//	public void run(){
//		String line = "null";
//		try {
//			client = new Socket("127.0.0.1", 7800);
//			PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
//			BufferedReader inreader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//			System.out.println("Conectado ao servidor");
//			String str;
//			while ((str = stdIn.readLine()) != null){
//				pw.println(str);
//				System.out.println("echo: "+str);
//			}
//		
////			ois = new ObjectInputStream(client.getInputStream());
////			XStream xstream = new XStream(new DomDriver());
////			xstream.alias("PlayerInfo", PlayerInfo.class);
////			xstream.alias("PlayerData", PlayerData.class);
//			
//			while (true){
//				line = inreader.readLine();
//				inreader.close();
////				playerInfo = null;
//				System.out.println(line);
////				String xml = ois.readUTF();
////				playerInfo = (PlayerInfo) xstream.fromXML(xml);
////				System.out.println(xml);
////				if (playerInfo != null){
////					instance.setInstance(playerInfo);	
////				}
//				
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
//
//	public PlayerInfo getPlayerInfo() {
//		return playerInfo;
//	}
//
//	
	private PlayerInfo playerInfo = PlayerInfo.getInstance();
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	private boolean isSendMessage = false;
    private String sendMessage = null;
	
	public ClientThread(){
		
	}
	//get the localhost IP address, if server is running on some other IP, you need to use that
	public void run (){
		try{
			InetAddress host = InetAddress.getByName(gc.getIp());
			Socket socket = null;
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			String message;
			playerInfo.setNumPlayer(GameConfiguration.getGameConfiguration().getNumPlayer());
			while (true) {
				// establish socket connection to server
				socket = new Socket(host.getHostName(), 9876);
				playerInfo.setStartGame(true);
				// write to socket using ObjectOutputStream
				oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("Sending request to Socket Server");
//				if (i == 4)
//					oos.writeObject("exit");
//				else
				message = null;
	        	
	        	if (isSendMessage){
	        		message = sendMessage;
	        	}
				oos.writeObject(message);
				
				// read the server response message
				ois = new ObjectInputStream(socket.getInputStream());
				message = (String) ois.readObject();
				System.out.println("Message: " + message);
//				aux = (PlayerInfo) xstream.fromXML(message);
//				if (aux.getNumPlayer() != 0){
//					playerInfo.getPlayersData()[aux.getNumPlayer()-1] = aux.getPlayersData()[aux.getNumPlayer()-1];
//				}
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
						case 7 : playerInfo.setTime(Integer.parseInt(linhaSplit[2]));
								 playerInfo.setStartGame(true);
						 		 break;
					}
				
				}
				// close resources
				ois.close();
				oos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	public void setIsSendMessage(boolean send){
    	this.isSendMessage = send;
    }
    
    public void setSendMessage(String msg){
    	this.sendMessage = msg;
    }
}
