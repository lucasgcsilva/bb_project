package org.bb.online.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.newdawn.slick.Image;

public class ClientThread extends Thread{
	Socket client = null;
	ObjectInputStream ois = null;
	private Image imagem;
	
	public ClientThread(){
		
	}
	
	public void run(){
		try {
			client = new Socket("172.20.222.150", 7800);
			ois = new ObjectInputStream(client.getInputStream());
			imagem = (Image) ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Image getImage(){
		return this.imagem;
	}

}
