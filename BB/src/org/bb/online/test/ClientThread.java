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
	public ClientThread(){
		
	}
	//get the localhost IP address, if server is running on some other IP, you need to use that
	public void run (){
		try{
			InetAddress host = InetAddress.getByName(gc.getIp());
			Socket socket = null;
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("PlayerInfo", PlayerInfo.class);
			xstream.alias("PlayerData", PlayerData.class);
			String xml;
			PlayerInfo aux;
			playerInfo.setNumPlayer(GameConfiguration.getGameConfiguration().getNumPlayer());
			while (true) {
				// establish socket connection to server
				socket = new Socket(host.getHostName(), 9876);
				playerInfo.setStartGame(true);
				xml = xstream.toXML(playerInfo);
				// write to socket using ObjectOutputStream
				oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("Sending request to Socket Server");
//				if (i == 4)
//					oos.writeObject("exit");
//				else
				oos.writeObject(xml);
				
				// read the server response message
				ois = new ObjectInputStream(socket.getInputStream());
				String message = (String) ois.readObject();
				System.out.println("Message: " + message);
				aux = (PlayerInfo) xstream.fromXML(message);
				if (aux.getNumPlayer() != 0){
					playerInfo.getPlayersData()[aux.getNumPlayer()-1] = aux.getPlayersData()[aux.getNumPlayer()-1];
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
}
