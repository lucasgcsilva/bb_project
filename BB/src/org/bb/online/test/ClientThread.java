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

public class ClientThread extends Thread {
	private PlayerInfo playerInfo = PlayerInfo.getInstance();
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	private boolean isSendMessage = false;
	private String sendMessage = null;

	public ClientThread() {

	}

	public void run() {
		try {
			InetAddress host = InetAddress.getByName(gc.getIp());
			Socket socket = null;
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;
			String message;
			playerInfo.setNumPlayer(GameConfiguration.getGameConfiguration()
					.getNumPlayer());
			socket = new Socket(host.getHostName(), 9876);
			System.out.println("Connected to server!");
			playerInfo.setStartGame(true);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			while (true) {
				message = null;

				if (isSendMessage) {
					message = sendMessage;
				}
				oos.writeObject(message);
				message = (String) ois.readObject();
				System.out.println("Message: " + message);
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
				oos.reset();
//				ois.reset();
//				ois.close();
//				oos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void setIsSendMessage(boolean send) {
		this.isSendMessage = send;
	}

	public void setSendMessage(String msg) {
		this.sendMessage = msg;
	}
}
