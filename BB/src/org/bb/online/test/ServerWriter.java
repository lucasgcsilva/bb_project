package org.bb.online.test;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

import org.bb.online.bs.player.PlayerInfo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServerWriter extends Thread{

	protected Socket conexao;
//	private static Vector<PrintStream> clientWriters;
	
	public ServerWriter (Socket s){
		conexao = s;
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
		String xml;
	    PlayerInfo instance = PlayerInfo.getInstance();
	    XStream xstream = new XStream(new DomDriver());
		xstream.alias("PlayerInfo", PlayerInfo.class);
		xstream.alias("PlayerData", PlayerInfo.PlayerData.class);
//		while (e.hasMoreElements()){
//			PrintStream send = (PrintStream) e.nextElement();
			xml = xstream.toXML(instance);
			send.println(xml);
//		}
	}
}
