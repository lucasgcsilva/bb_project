package org.bb.online.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer extends Thread{
	private int port = 9876;
	private ServerSocket server;
	
	public StartServer(){
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void run(){
		try{
			while (true){
				System.out.println("Waiting for connection...");
				Socket conexao = server.accept();
				System.out.println("Client "+conexao.getRemoteSocketAddress()+" conectado.");
				ServerReceiver sr = new ServerReceiver (conexao);
				ServerWriter sw = new ServerWriter (conexao);
				sr.start();
				sw.start();
			}
		}catch(Exception e){
			
		}
	}

}
