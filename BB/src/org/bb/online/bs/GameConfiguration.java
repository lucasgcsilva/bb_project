package org.bb.online.bs;

import org.bb.util.Info;

public class GameConfiguration {
	private static GameConfiguration instance = new GameConfiguration();
	private String[] imgPlayerPath = new String[5];
	private int[] typePlayer = new int[5];
	private boolean[] isControls = new boolean[5];
	private boolean[] isKeyboards = new boolean[5];
	private String mapPath;
	private int mapLevel; 
	private int numPlayer;
	private int typeConn;
	
	
	public static int TYPE_NORMAL = 1;
	public static int TYPE_SEIYA = 2;
	
	public static int TYPE_SERVER = 1;
	public static int TYPE_CLIENT = 2;
	
	private GameConfiguration(){
		//Configurando imagem dos players
		this.imgPlayerPath[0] = Info.SaintBomb;
		this.imgPlayerPath[1] = Info.KuroBomb;
		this.imgPlayerPath[2] = Info.AkaBomb;
		this.imgPlayerPath[3] = Info.AoBomb;
		this.imgPlayerPath[4] = Info.MidoriBomb;
		
		//Configurando o tipo do player
		this.typePlayer[0] = TYPE_SEIYA;
		this.typePlayer[1] = TYPE_NORMAL;
		this.typePlayer[2] = TYPE_NORMAL;
		this.typePlayer[3] = TYPE_NORMAL;
		this.typePlayer[4] = TYPE_NORMAL;
		
		//Configurando se controle está ativo
		this.isControls[0] = false;
		this.isControls[1] = false;
		this.isControls[2] = true;
		this.isControls[3] = true;
		this.isControls[4] = true;
		
		//Configurando se o teclado está ativo
		this.isKeyboards[0] = true;
		this.isKeyboards[1] = true;
		this.isKeyboards[2] = true;
		this.isKeyboards[3] = true;
		this.isKeyboards[4] = true;
		
		//Configurando o caminho do mapa
		mapLevel = 1;
		mapPath = Info.mapPath[mapLevel-1];
		
		//Configurando o numero do player
		numPlayer = 2;
		
		//Configurando o servidor/client
		typeConn = TYPE_CLIENT;
	}
	
	public int getTypeConn() {
		return typeConn;
	}

	public void setTypeConn(int typeConn) {
		this.typeConn = typeConn;
	}
	
	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}

	public int getMapLevel(){
		return mapLevel;
	}
	
	public String getMapPath(){
		return mapPath;
	}
	
	public String getImgPlayerPath(int numPlayer) {
		return imgPlayerPath[numPlayer-1];
	}



	public void setImgPlayerPath(String imgPlayerPath, int numPlayer) {
		this.imgPlayerPath[numPlayer-1] = imgPlayerPath;
	}



	public int getTypePlayer(int numPlayer) {
		return typePlayer[numPlayer-1];
	}



	public void setTypePlayer(int typePlayer, int numPlayer) {
		this.typePlayer[numPlayer-1] = typePlayer;
	}



	public boolean getIsControls(int numPlayer) {
		return isControls[numPlayer-1];
	}



	public void setIsControls(boolean isControls, int numPlayer) {
		this.isControls[numPlayer-1] = isControls;
	}



	public boolean getIsKeyboards(int numPlayer) {
		return isKeyboards[numPlayer-1];
	}



	public void setIsKeyboards(boolean isKeyboards, int numPlayer) {
		this.isKeyboards[numPlayer-1] = isKeyboards;
	}



	public static GameConfiguration getGameConfiguration(){
		return instance;
	}
}
