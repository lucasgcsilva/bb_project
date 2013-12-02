package org.bb.online.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import org.bb.online.bs.GameConfiguration;
import org.bb.online.bs.player.PlayerInfo;
import org.bb.online.bs.player.PlayerInfo.PlayerData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class StartClient extends Thread{
	private int port = 9876;
	private Socket client;
	private GameConfiguration gc = GameConfiguration.getGameConfiguration();
	private PrintStream saida;
	private BufferedReader entrada;
	private PlayerInfo playerInfo = PlayerInfo.getInstance();
	
	public StartClient(){
		try {
			client = new Socket (gc.getIp(), port);
			saida = new PrintStream(client.getOutputStream());
			entrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void run(){
		try{
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("PlayerInfo", PlayerInfo.class);
			xstream.alias("PlayerData", PlayerData.class);
			String xml;
			playerInfo.setNumPlayer(GameConfiguration.getGameConfiguration()
					.getNumPlayer());
			String catLinha = "";
			String linha = "";
			while (true) {
				linha = entrada.readLine();
				if (linha.trim().equals("</PlayerInfo>")){
					catLinha = catLinha + linha;
					System.out.println(catLinha);
					playerInfo.setStartGame(true);
					xml = xstream.toXML(playerInfo);
					saida.println(xml);
					PlayerInfo aux = (PlayerInfo) xstream.fromXML(catLinha);
					if (aux.getNumPlayer() != 0){
						playerInfo.getPlayersData()[aux.getNumPlayer()-1] = aux.getPlayersData()[aux.getNumPlayer()-1];
					}
					catLinha = "";
				}else {
					catLinha = catLinha + linha;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	

}
