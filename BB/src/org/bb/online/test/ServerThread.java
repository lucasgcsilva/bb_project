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
	private static ServerSocket server;
	private static int port = 9876;
	private PlayerInfo instance = PlayerInfo.getInstance();
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	private boolean isSendMessage = false;
	private String sendMessage = null;
	private PlayerInfo playerInfo = PlayerInfo.getInstance();

	public ServerThread() {

	}

	public void run() {
		try {
			server = new ServerSocket(port);
			Socket socket = null;
			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			while (true) {
				try {
					if (socket == null) {
						socket = server.accept();
						System.out.println("Client connected!");
						oos = new ObjectOutputStream(socket.getOutputStream());
						BufferedInputStream bi = new BufferedInputStream(
								socket.getInputStream());
						ois = new ObjectInputStream(bi);
					}

					String message = null;
					message = (String) ois.readObject();
					System.out.println("Message Received: " + message);
					if (message != null) {
						String[] linhaSplit = message.split("\\|");
						int opt = Integer.parseInt(linhaSplit[1]);
						int numplayer = Integer.parseInt(linhaSplit[0]);
						switch (opt) {
						case 1:
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyUp(true);
							break;
						case 2:
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyDown(true);
							break;
						case 3:
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyLeft(true);
							break;
						case 4:
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyRight(true);
							break;
						case 5:
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyBomb(true);
							break;
						case 6:
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyUp(false);
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyDown(false);
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyLeft(false);
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyRight(false);
							playerInfo.getPlayersData()[numplayer - 1]
									.setKeyBomb(false);
							break;
						case 7:
							playerInfo.setTime(Integer.parseInt(linhaSplit[2]));
							playerInfo.setStartGame(true);
							break;

						}

					}

					instance.setNumPlayer(gc.getNumPlayer());
					message = null;

					if (isSendMessage) {
						message = sendMessage;
					}
					oos.writeObject(message);
					oos.flush();
					isSendMessage = false;
					oos.reset();
//					ois.close();
//					oos.close();
//					socket.close();
//					socket = null;
//					ois = null;
//					oos = null;
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	    
	    public void setIsSendMessage(boolean send){
	    	this.isSendMessage = send;
	    }
	    
	    public void setSendMessage(String msg){
	    	this.sendMessage = msg;
	    }

	    public void closeServer(){
	    	try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
