package org.bb.online.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Vector;

import org.bb.online.bs.GameConfiguration;
import org.bb.online.bs.player.PlayerInfo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServerReceiver extends Thread {

	protected Socket conexao;
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	
	public ServerReceiver (Socket s){
		conexao = s;
	}
	
	public void run(){
		try{
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			PlayerInfo playerInfo;
	        String xml;
	        PlayerInfo instance = PlayerInfo.getInstance();
	        XStream xstream = new XStream(new DomDriver());
			xstream.alias("PlayerInfo", PlayerInfo.class);
			xstream.alias("PlayerData", PlayerInfo.PlayerData.class);
			instance.setNumPlayer(gc.getNumPlayer());
			System.out.println("reading a line...");
			String linha = entrada.readLine();
			while (linha != null){
	        	playerInfo = (PlayerInfo) xstream.fromXML(linha);
	        	instance.getPlayersData()[playerInfo.getNumPlayer()-1] = playerInfo.getPlayersData()[playerInfo.getNumPlayer()-1];
	        	instance.setStartGame(playerInfo.isStartGame());
	        	linha = entrada.readLine();
			}
			conexao.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
