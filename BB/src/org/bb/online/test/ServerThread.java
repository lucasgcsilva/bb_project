package org.bb.online.test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.SGL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServerThread extends Thread{
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream oos = null;
	private Image image;
	private Image result = null;
	
	public ServerThread(){
		
	}
	
	public void run(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Image", Image.class);
		xstream.alias("SGL", SGL.class);
		xstream.alias("texture", Texture.class);
		while (true){
//			oos = new ObjectOutputStream(client.getOutputStream());
			String xml = xstream.toXML(image);
			
			result = (Image) xstream.fromXML(xml);
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
	
	public void setImage(Image image){
		this.image = image;
	}
	
	public Image getImage(){
		return this.result;
	}
}
