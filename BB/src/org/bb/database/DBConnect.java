package org.bb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.bb.main.Main;
import org.bb.util.Info;

public class DBConnect {

	public static boolean getUserDB(String username, String pwd, Main main){
		
		String sql = "SELECT * FROM " + Info.tableUsr + " WHERE username=? AND password=? ;";
		PreparedStatement selectUsr = null;
		Connection conn = connectDB();//TODO: validar conexão
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
				JOptionPane.showMessageDialog(null, "Falha ao efetuar Login. Nome de Usuário e/ou senha incorreto(s)", "Erro",JOptionPane.ERROR_MESSAGE);
				return false;
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
