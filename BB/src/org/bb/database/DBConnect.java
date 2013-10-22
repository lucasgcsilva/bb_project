package org.bb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.bb.main.Main;
import org.bb.util.Info;

public class DBConnect {
	
	public static boolean insertUser(String username, String senha, String email){
		String sql1 = "INSERT INTO "+Info.tableUsr+" (username, password, email) VALUES (?, ?, ?)";
		String sql2 = "INSERT INTO highscoreBattlestadium(s_vitorias, s_derrotas, s_qtde_trofeus, s_bombersaldo, t_vitorias, t_derrotas, "+
				"t_qtde_trofeus, t_bombersaldo) VALUES (0, 0, 0, 0, 0, 0, 0, 0)";
		String sql3 = "INSERT INTO highscoreArena(qtde_kills, qtde_death, bombersaldo) VALUES (0, 0, 0)";
		String sql4 = "INSERT INTO highscore VALUES ((SELECT usr_id FROM users WHERE email=?), LAST_INSERT_ID(), LAST_INSERT_ID())";
		PreparedStatement insertUsr = null;
		Connection conn = connectDB();;
		try{
			conn.setAutoCommit(false);
			insertUsr = conn.prepareStatement(sql1);
			insertUsr.setString(1, username);
			insertUsr.setString(2, senha);
			insertUsr.setString(3, email);
			if (insertUsr.execute()){
				System.out.println(sql1+" OK!");
			}
			insertUsr = conn.prepareStatement(sql2);
			if (insertUsr.execute()){
				System.out.println(sql2+" OK!");
			}
			insertUsr = conn.prepareStatement(sql3);
			if (insertUsr.execute()){
				System.out.println(sql3+" OK!");
			}
			insertUsr = conn.prepareStatement(sql4);
			insertUsr.setString(1, email);
			if (insertUsr.execute()){
				System.out.println(sql4+" OK!");
			}
			
			conn.commit();
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Servidor Temporariamente Indisponível. Tente novamente mais tarde", "Erro ao inserir usuário",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally{
			try{
				if(conn != null){conn.close();}
				if(insertUsr != null){insertUsr.close();}
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
	
	public static Connection connectDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(Info.url, Info.DBusr, Info.DBsenha);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}