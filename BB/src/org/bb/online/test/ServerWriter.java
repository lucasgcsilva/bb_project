package org.bb.online.test;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

import org.bb.online.bs.GameConfiguration;
import org.bb.online.bs.player.PlayerInfo;
import org.bb.online.bs.player.PlayerInfo.PlayerData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServerWriter extends Thread{

	protected Socket conexao;
//	private static Vector<PrintStream> clientWriters;
	private PlayerData pData;
	private PlayerInfo instance = PlayerInfo.getInstance();
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	
	public ServerWriter (Socket s){
		conexao = s;
		pData = instance.getPlayersData()[gc.getNumPlayer()-1];
	}
	
	public void run(){
		try{
			PrintStream saida = new PrintStream(conexao.getOutputStream());
//			clientWriters.add(saida);
			while (true){
				sendToAll(saida);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendToAll (PrintStream send){
		System.out.println("Send data...");
//		Enumeration<PrintStream> e = clientWriters.elements();
//		String xml;
//	    
//	    XStream xstream = new XStream(new DomDriver());
//		xstream.alias("PlayerInfo", PlayerInfo.class);
//		xstream.alias("PlayerData", PlayerInfo.PlayerData.class);
//		while (e.hasMoreElements()){
//			PrintStream send = (PrintStream) e.nextElement();
//			xml = xstream.toXML(instance);
//			send.println(xml);
//		}
		
		if (pData.isKeyUp() != instance.getPlayersData()[gc.getNumPlayer()-1].isKeyUp()){
			send.println(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyUp());
			pData.setKeyUp(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyUp());
		}else if (pData.isKeyDown() != instance.getPlayersData()[gc.getNumPlayer()-1].isKeyDown()){
			send.println(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyDown());
			pData.setKeyDown(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyDown());
		}else if (pData.isKeyLeft() != instance.getPlayersData()[gc.getNumPlayer()-1].isKeyLeft()){
			send.println(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyLeft());
			pData.setKeyLeft(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyLeft());
		}else if (pData.isKeyRight() != instance.getPlayersData()[gc.getNumPlayer()-1].isKeyRight()){
			send.println(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyRight());
			pData.setKeyRight(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyRight());
		}else if (pData.isKeyBomb() != instance.getPlayersData()[gc.getNumPlayer()-1].isKeyBomb()){
			send.println(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyBomb());
			pData.setKeyBomb(instance.getPlayersData()[gc.getNumPlayer()-1].isKeyBomb());
		}
	}
}
