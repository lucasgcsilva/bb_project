package org.bb.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.bb.database.DBConnect;
import org.bb.main.Main;
import org.bb.sound.MusicPlayer;

import com.mysql.jdbc.ResultSetMetaData;

public class Score extends JPanel {
	private Main main;
	private Image background = new ImageIcon("resources/images/bg2.jpg").getImage();
	private MusicPlayer player;
	private JLabel lblScore = new JLabel("PONTUACAO");
	private JButton btnVoltar = new JButton("«VOLTAR");
	private JTabbedPane tpTables = new JTabbedPane();
	private int posUsr=0, posUsr1=0, posUsr2=0;
	
	public Score(Main m){
		main = m;
		setSize(main.width, main.height);
		setLayout(new BorderLayout());
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.red);
		lblScore.setFont(main.getFonte(main.fonteBomberman, 40));
		add(lblScore, BorderLayout.NORTH);
		
		//BattleStadium Single-Match
		ResultSet rs = DBConnect.getHighscoreBattlestadiumSM();
		String[] tableNames = {"Posição", "Nome", "Vitórias", "Derrotas", "Qtde. Trofeus", "Bombersaldo"};
		JTable tablePad = new JTable() {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
			
			@Override
			public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){  
			    Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);  

			    if(rowIndex == posUsr) {  
			       componenet.setBackground(Color.BLUE);  
			    }else{
			    	componenet.setBackground(Color.WHITE);
			    }
			    return componenet;
			} 
		};
		DefaultTableModel tm = (DefaultTableModel)tablePad.getModel();
		tm.setColumnIdentifiers(tableNames);
		int j=0;
		try{
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int col = rsmd.getColumnCount();
			rs.beforeFirst();
			while(rs.next()){
				Object[] objects = new Object[col];
				for (int i=0; i<col; i++){
					objects[i] = rs.getObject(i+1);
					if (i == 0){
						objects[i]=String.valueOf(j+1);
					}
					if (i == 1){
						if (main.sp.usrname.equals(objects[i].toString())){
							posUsr = j;
						}
					}
				}
				tm.addRow(objects);
				j++;
			}
			
		}catch(Exception e){
			
		}
		System.out.println(posUsr);
		tablePad.setModel(tm);
		JScrollPane sp = new JScrollPane(tablePad);
		
		//BattleStadium Tag-Match
		ResultSet rs2 = DBConnect.getHighscoreBattlestadiumTM();
		String[] tableNames2 = {"Posição", "Nome", "Vitórias", "Derrotas", "Qtde. Trofeus", "Bombersaldo"};
		JTable tablePad2 = new JTable() {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
			
			@Override
			public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){  
			    Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);  

			    if(rowIndex == posUsr1) {  
			       componenet.setBackground(Color.BLUE);  
			    }else{
			    	componenet.setBackground(Color.WHITE);
			    }
			    return componenet;
			} 
		};
		DefaultTableModel tm2 = (DefaultTableModel)tablePad2.getModel();
		tm2.setColumnIdentifiers(tableNames2);
		j=0;
		try{
			ResultSetMetaData rsmd2 = (ResultSetMetaData) rs2.getMetaData();
			int col2 = rsmd2.getColumnCount();
			rs2.beforeFirst();
			while(rs2.next()){
				Object[] objects2 = new Object[col2];
				for (int i=0; i<col2; i++){
					objects2[i] = rs2.getObject(i+1);
					if (i == 0){
						objects2[i]=String.valueOf(j+1);
					}
					if (i == 1){
						if (main.sp.usrname.equals(objects2[i].toString())){
							posUsr1 = j;
						}
					}
				}
				tm2.addRow(objects2);
				j++;
			}
			
		}catch(Exception e){
			
		}
		System.out.println(posUsr1);
		tablePad2.setModel(tm2);
		JScrollPane sp2 = new JScrollPane(tablePad2);
	
		//Arena
		ResultSet rs3 = DBConnect.getArena();
		String[] tableNames3 = {"Posição", "Nome", "Qtde. Kills", "Qtde. Mortes", "Bombersaldo"};
		JTable tablePad3 = new JTable() {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
			
			@Override
			public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){  
			    Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);  

			    if(rowIndex == posUsr2) {  
			       componenet.setBackground(Color.BLUE);  
			    }else{
			    	componenet.setBackground(Color.WHITE);
			    }
			    return componenet;
			} 
		};
		DefaultTableModel tm3 = (DefaultTableModel)tablePad3.getModel();
		tm3.setColumnIdentifiers(tableNames3);
		j=0;
		try{
			ResultSetMetaData rsmd3 = (ResultSetMetaData) rs3.getMetaData();
			int col = rsmd3.getColumnCount();
			rs3.beforeFirst();
			while(rs3.next()){
				Object[] objects3 = new Object[col];
				for (int i=0; i<col; i++){
					objects3[i] = rs3.getObject(i+1);
					if (i == 0){
						objects3[i]=String.valueOf(j+1);
					}
					if (i == 1){
						if (main.sp.usrname.equals(objects3[i].toString())){
							posUsr2 = j;
						}
					}
				}
				tm3.addRow(objects3);
				j++;
			}
			
		}catch(Exception e){
			
		}
		System.out.println(posUsr2);
		tablePad3.setModel(tm3);
		JScrollPane sp3 = new JScrollPane(tablePad3);
		
		tpTables.addTab("BattleStadium Single-Match", sp);
		tpTables.addTab("Battlestadium Tag-Match", sp2);
		tpTables.addTab("Arena", sp3);
		add(tpTables, BorderLayout.CENTER);
		
		btnVoltar.setForeground(Color.red);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 40));
		btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
		btnVoltar.setBackground(Color.BLUE);
		printScore();
		setVisible(true);
		player = new MusicPlayer("resources/musics/highscore.wav", true);//TODO: Alterar m�sica
		player.start();
	}
	
	public void printScore(){
		JPanel pnl = new JPanel(new GridLayout(5, 5, 30, 10));
		pnl.setOpaque(false);
		pnl.setSize(main.width, main.height);
		btnVoltar.setBackground(Color.yellow);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				player.stopMusic();
				main.addMenu();
			}
		});
		add(btnVoltar, BorderLayout.SOUTH);
	}
	
	protected void paintComponent(Graphics g)  {          
	    super.paintComponent(g);  
	    Graphics2D g2d = (Graphics2D)g.create();  
	    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
	    g2d.dispose();          
	} 
}
