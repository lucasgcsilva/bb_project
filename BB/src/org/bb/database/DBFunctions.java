package org.bb.database;

import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.bb.main.Main;
import org.bb.util.Info;

import com.mysql.jdbc.ResultSetMetaData;

public class DBFunctions {
	
	public static void setHighscoreBattlestadiumSM(int vitorias, int derrotas, int trofeus, int id){
		String sql1 = "UPDATE highscoreBattlestadium SET s_vitorias = s_vitorias + ?, " +
				"s_derrotas = s_derrotas + ?, s_qtde_trofeus = s_qtde_trofeus + ?, "+
				"s_bombersaldo = s_vitorias * 3 - s_derrotas + s_qtde_trofeus * 2 WHERE "+
				"id = (SELECT hBattlestadium_id FROM highscore WHERE usr_id = ?);";
		PreparedStatement updateScore = null;
		Connection conn = connectDB();
		ResultSet rs = null;
		try{
			updateScore = conn.prepareStatement(sql1);
			updateScore.setInt(1, vitorias);
			updateScore.setInt(2, derrotas);
			updateScore.setInt(3, trofeus);
			updateScore.setInt(4, id);
			System.out.println(sql1);
			updateScore.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao gravar score no Single Match", "Erro ao gravar score",JOptionPane.ERROR_MESSAGE);
		}finally{
			try{
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static ResultSet getArena(){
		
		String sql = "set @num=0";
		String sql1 = "select (@num:=@num+1) as pos, users.username, hsa.qtde_kills, hsa.qtde_death, hsa.bombersaldo"+
				" from highscore inner join users on highscore.usr_id=users.usr_id inner join highscoreArena"+
				" as hsa on hsa.id=highscore.hArena_id order by hsa.bombersaldo desc;";
		PreparedStatement insertUsr = null;
		Connection conn = connectDB();
		ResultSet rs = null;
		try{
			insertUsr = conn.prepareStatement(sql);
			insertUsr.execute();
			insertUsr = conn.prepareStatement(sql1);
			rs = insertUsr.executeQuery();
			if (rs.next()){
				System.out.println(rs.getString(1));
				return rs;
			}else { return null; }
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Obrigado por tentar jogar!", "Erro ao inserir usuário",JOptionPane.ERROR_MESSAGE);
			return null;
		}finally{
			try{
				//if(conn != null){conn.close();}
//				if(insertUsr != null){insertUsr.close();}
//				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static ResultSet getHighscoreBattlestadiumTM(){
		String sql = "set @num=0";
		String sql1 = "select (@num:=@num+1) as pos, users.username, hsb.t_vitorias, hsb.t_derrotas, hsb.t_qtde_trofeus,"+
				" hsb.t_bombersaldo from highscore inner join users on highscore.usr_id=users.usr_id inner join highscoreBattlestadium"+
				" as hsb on hsb.id=highscore.hBattlestadium_id order by hsb.t_bombersaldo desc;";
		PreparedStatement insertUsr = null;
		Connection conn = connectDB();
		ResultSet rs = null;
		try{
			insertUsr = conn.prepareStatement(sql);
			insertUsr.execute();
			insertUsr = conn.prepareStatement(sql1);
			rs = insertUsr.executeQuery();
			if (rs.next()){
				System.out.println(rs.getString(1));
				return rs;
			}else { return null; }
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Tag-macth", "Erro ao inserir usuário",JOptionPane.ERROR_MESSAGE);
			return null;
		}finally{
			try{
				//if(conn != null){conn.close();}
//				if(insertUsr != null){insertUsr.close();}
//				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public static ResultSet getHighscoreBattlestadiumSM(){
		String sql = "set @num=0";
		String sql1 = "select (@num:=@num+1) as pos, users.username, hsb.s_vitorias, hsb.s_derrotas, hsb.s_qtde_trofeus,"+
				" hsb.s_bombersaldo from highscore inner join users on highscore.usr_id=users.usr_id inner join highscoreBattlestadium"+
				" as hsb on hsb.id=highscore.hBattlestadium_id order by hsb.s_bombersaldo desc;";
		PreparedStatement insertUsr = null;
		Connection conn = connectDB();
		ResultSet rs = null;
		try{
			insertUsr = conn.prepareStatement(sql);
			insertUsr.execute();
			insertUsr = conn.prepareStatement(sql1);
			rs = insertUsr.executeQuery();
			if (rs.next()){
				System.out.println(rs.getString(1));
				return rs;
			}else { return null; }
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Arena", "Erro ao inserir usuário",JOptionPane.ERROR_MESSAGE);
			return null;
		}finally{
			try{
				//if(conn != null){conn.close();}
//				if(insertUsr != null){insertUsr.close();}
//				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static boolean insertUser(String username, String senha, String email){
		String url = "http://200.236.3.203:8080/bbServer/cadastroUsr";
		 
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
	 
	 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", senha));
		urlParameters.add(new BasicNameValuePair("email", email));
	 
		try {
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
	 
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());
	 
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));
	 
		StringBuffer result = new StringBuffer();
		String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		return true;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isNewUser(String user){
		String sql = "SELECT * FROM "+Info.tableUsr+" WHERE username=?";
		System.out.println(sql);
		PreparedStatement selectUsr = null;
		Connection conn = connectDB();//TODO: validar conex�o
		ResultSet rs = null;
		try{
			selectUsr = conn.prepareStatement(sql);
			selectUsr.setString(1, user);
			rs = selectUsr.executeQuery();
			if(rs.next()){
				return false;
			} else {
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Servidor Temporariamente Indisponível. Tente novamente mais tarde", "Erro",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally{
			try{
				if(conn != null){conn.close();}
				if(selectUsr != null){selectUsr.close();}
				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isNewEmail(String email){
		String sql = "SELECT * FROM "+Info.tableUsr+" WHERE email=?";
		System.out.println(sql);
		PreparedStatement selectUsr = null;
		Connection conn = connectDB();//TODO: validar conex�o
		ResultSet rs = null;
		try{
			selectUsr = conn.prepareStatement(sql);
			selectUsr.setString(1, email);
			rs = selectUsr.executeQuery();
			if(rs.next()){
				return false;
			} else {
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Servidor Temporariamente Indispon�vel. Tente novamente mais tarde", "Erro",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally{
			try{
				if(conn != null){conn.close();}
				if(selectUsr != null){selectUsr.close();}
				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static boolean getUserDB(String username, String pwd, Main main){
		
		String sql = "SELECT * FROM " + Info.tableUsr + " WHERE username=? AND password=? ;";
		PreparedStatement selectUsr = null;
		Connection conn = connectDB();//TODO: validar conex�o
		ResultSet rs = null;
		try{
			selectUsr = conn.prepareStatement(sql);
			selectUsr.setString(1, username);
			selectUsr.setString(2, pwd);
			rs = selectUsr.executeQuery();
			if(rs.next()){
				main.setUserConnected (rs.getInt("usr_id"), rs.getString("username"),rs.getString("email"));
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao efetuar Login. Nome de Usu�rio e/ou senha incorreto(s)", "Erro",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Servidor Temporariamente Indispon�vel. Tente novamente mais tarde", "Erro",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally{
			try{
				if(conn != null){conn.close();}
				if(selectUsr != null){selectUsr.close();}
				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static Connection connectDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(Info.url, Info.DBusr, Info.DBsenha);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet getUsrHigsBattleStadiumSM(int id){
		String sql1 = "select hsb.s_vitorias, hsb.s_derrotas, hsb.s_qtde_trofeus,"+
				" hsb.s_bombersaldo, hsb.t_vitorias, hsb.t_derrotas, hsb.t_qtde_trofeus, hsb.t_bombersaldo"+
				" from highscore inner join highscoreBattlestadium as hsb on highscore.hBattlestadium_id=hsb.id inner join users on highscore.usr_id=?";
		PreparedStatement insertUsr = null;
		Connection conn = connectDB();
		ResultSet rs = null;
		try{
			insertUsr = conn.prepareStatement(sql1);
			insertUsr.setInt(1, id);
			rs = insertUsr.executeQuery();
			if (rs.next()){
				System.out.println(rs.getString(1));
				return rs;
			}else { return null; }
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao obter highscore do usuário!", "Erro ao inserir usuário",JOptionPane.ERROR_MESSAGE);
			return null;
		}finally{
			try{
				//if(conn != null){conn.close();}
//				if(insertUsr != null){insertUsr.close();}
//				if(rs != null){rs.close();}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
