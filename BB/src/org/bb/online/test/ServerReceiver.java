package org.bb.online.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
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
	
	public synchronized void run(){
		try{
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			PlayerInfo playerInfo;
	        PlayerInfo instance = PlayerInfo.getInstance();
	        XStream xstream = new XStream(new DomDriver());
			xstream.alias("PlayerInfo", PlayerInfo.class);
			xstream.alias("PlayerData", PlayerInfo.PlayerData.class);
			instance.setNumPlayer(gc.getNumPlayer());
			System.out.println("reading a line...");
			String linha = "";
			String catLinha = "";
			while (true){
				if (entrada != null){
					entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
				}else {
					linha = entrada.readLine();
					if (linha.trim().equals("</PlayerInfo>")){
						catLinha = catLinha + linha;
						playerInfo = (PlayerInfo) xstream.fromXML(catLinha);
						instance.getPlayersData()[playerInfo.getNumPlayer()-1] = playerInfo.getPlayersData()[playerInfo.getNumPlayer()-1];
						instance.setStartGame(playerInfo.isStartGame());
						catLinha = "";
						Thread.sleep(100);
						entrada.close();
						entrada = null;
					}else {
						catLinha = catLinha + linha;
					}					
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
