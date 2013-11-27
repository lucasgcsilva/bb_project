package org.bb.online.test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.bb.online.bs.player.PlayerInfo;
import org.bb.online.bs.player.PlayerInfo.PlayerData;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.SGL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServerThread extends Thread{
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream oos = null;
	private PlayerInfo playerInfo;
	private PlayerInfo result;
	
	public ServerThread(){
		
	}
	
	public void run(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("PlayerInfo", PlayerInfo.class);
		xstream.alias("PlayerData", PlayerData.class);
		while (true){
//			oos = new ObjectOutputStream(client.getOutputStream());
			playerInfo = PlayerInfo.getInstance();
			String xml = xstream.toXML(playerInfo);
			System.out.println(xml);
			result = (PlayerInfo) xstream.fromXML(xml);
			
//			oos.writeObject(xml);
//			oos.flush();
		}
//		try {
//			
//			server = new ServerSocket(7800);
//			client = server.accept();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
