package org.bb.online.test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.newdawn.slick.Image;

public class ServerThread extends Thread{
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream oos = null;
	private Image image;
	
	public ServerThread(){
		
	}
	
	public void run(){
		try {
			server = new ServerSocket(7800);
			client = server.accept();
			while (true){
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(image);
				oos.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setImage(Image image){
		this.image = image;
	}
}
