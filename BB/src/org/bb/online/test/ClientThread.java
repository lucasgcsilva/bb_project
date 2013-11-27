package org.bb.online.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.bb.online.bs.player.PlayerInfo;
import org.newdawn.slick.Image;

public class ClientThread extends Thread{
	Socket client = null;
	ObjectInputStream ois = null;
	private PlayerInfo playerInfo;
	
	public ClientThread(){
		
	}
	
	public void run(){
		try {
			client = new Socket("172.0.0.1", 7800);
			ois = new ObjectInputStream(client.getInputStream());
			playerInfo = (PlayerInfo) ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	

}
