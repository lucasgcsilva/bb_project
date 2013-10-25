package org.bb.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bb.database.DBFunctions;
import org.bb.main.Main;
import org.bb.sound.MusicPlayer;

import com.mysql.jdbc.ResultSetMetaData;

public class Archievments extends JPanel{
	private Main main;
	private Image background = new ImageIcon("resources/images/bg2.jpg").getImage();
	private MusicPlayer player;
	private JLabel lblArch = new JLabel("CONQUISTAS");
	private JButton btnVoltar = new JButton("«VOLTAR");
	private JButton btnArch1 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch2 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch3 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch4 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch5 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch6 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch7 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch8 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch9 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch10 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch11 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch12 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch13 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch14 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch15 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch16 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch17 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch18 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch19 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch20 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch21 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch22 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch23 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch24 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private JButton btnArch25 = new JButton(new ImageIcon("resources/images/arch_bomb.png"));
	private int s_vitorias = 0,
			s_derrotas = 0,
		    s_qtde_trofeus = 0,
		    s_bombersaldo = 0,
		    t_vitorias = 0,
			t_derrotas = 0,
			t_qtde_trofeus = 0,
			t_bombersaldo = 0;
	
	public Archievments(Main m){
		main = m;
		setSize(main.width, main.height);
		setLayout(new BorderLayout());
		lblArch.setHorizontalAlignment(SwingConstants.CENTER);
		lblArch.setForeground(Color.red);
		lblArch.setFont(main.getFonte(main.fonteBomberman, 40));
		add(lblArch, BorderLayout.NORTH);
		btnVoltar.setForeground(Color.red);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 40));
		btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
		btnVoltar.setBackground(Color.BLUE);
		printArchievments();
		setVisible(true);
		player = new MusicPlayer("resources/musics/archievments.wav", true);
		player.start();
	}
	
	public void printArchievments(){
		JPanel pnl = new JPanel(new GridLayout(5, 5, 30, 10));
		pnl.setOpaque(false);
		pnl.setSize(main.width, main.height);
		
		btnArch1.setOpaque(false);
		btnArch1.setContentAreaFilled(false);
		btnArch2.setOpaque(false);
		btnArch2.setContentAreaFilled(false);
		btnArch3.setOpaque(false);
		btnArch3.setContentAreaFilled(false);
		btnArch4.setOpaque(false);
		btnArch4.setContentAreaFilled(false);
		btnArch5.setOpaque(false);
		btnArch5.setContentAreaFilled(false);
		btnArch6.setOpaque(false);
		btnArch6.setContentAreaFilled(false);
		btnArch7.setOpaque(false);
		btnArch7.setContentAreaFilled(false);
		btnArch8.setOpaque(false);
		btnArch8.setContentAreaFilled(false);
		btnArch9.setOpaque(false);
		btnArch9.setContentAreaFilled(false);
		btnArch10.setOpaque(false);
		btnArch10.setContentAreaFilled(false);
		btnArch11.setOpaque(false);
		btnArch11.setContentAreaFilled(false);
		btnArch12.setOpaque(false);
		btnArch12.setContentAreaFilled(false);
		btnArch13.setOpaque(false);
		btnArch13.setContentAreaFilled(false);
		btnArch14.setOpaque(false);
		btnArch14.setContentAreaFilled(false);
		btnArch15.setOpaque(false);
		btnArch15.setContentAreaFilled(false);
		btnArch16.setOpaque(false);
		btnArch16.setContentAreaFilled(false);
		btnArch17.setOpaque(false);
		btnArch17.setContentAreaFilled(false);
		btnArch18.setOpaque(false);
		btnArch18.setContentAreaFilled(false);
		btnArch19.setOpaque(false);
		btnArch19.setContentAreaFilled(false);
		btnArch20.setOpaque(false);
		btnArch20.setContentAreaFilled(false);
		btnArch21.setOpaque(false);
		btnArch21.setContentAreaFilled(false);
		btnArch22.setOpaque(false);
		btnArch22.setContentAreaFilled(false);
		btnArch23.setOpaque(false);
		btnArch23.setContentAreaFilled(false);
		btnArch24.setOpaque(false);
		btnArch24.setContentAreaFilled(false);
		btnArch25.setOpaque(false);
		btnArch25.setContentAreaFilled(false);
		setActionListener();
		pnl.add(btnArch1);
		pnl.add(btnArch2);
		pnl.add(btnArch3);
		pnl.add(btnArch4);
		pnl.add(btnArch5);
		pnl.add(btnArch6);
		pnl.add(btnArch7);
		pnl.add(btnArch8);
		pnl.add(btnArch9);
		pnl.add(btnArch10);
		pnl.add(btnArch11);
		pnl.add(btnArch12);
		pnl.add(btnArch13);
		pnl.add(btnArch14);
		pnl.add(btnArch15);
		pnl.add(btnArch16);
		pnl.add(btnArch17);
		pnl.add(btnArch18);
		pnl.add(btnArch19);
		pnl.add(btnArch20);
		pnl.add(btnArch21);
		pnl.add(btnArch22);
		pnl.add(btnArch23);
		pnl.add(btnArch24);
		pnl.add(btnArch25);
		add(pnl, BorderLayout.CENTER);
		verifyArch();
		//btnVoltar.setOpaque(false);
		//btnVoltar.setContentAreaFilled(false);
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
	
	protected void verifyArch(){
		ResultSet rs = DBFunctions.getUsrHigsBattleStadiumSM(main.sp.id);
		
		try{
			rs.beforeFirst();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()){
				s_vitorias = rs.getInt(1);
				s_derrotas = rs.getInt(2);
				s_qtde_trofeus = rs.getInt(3);
				s_bombersaldo = rs.getInt(4);
				t_vitorias = rs.getInt(5);
				t_derrotas = rs.getInt(6);
				t_qtde_trofeus = rs.getInt(7);
				t_bombersaldo = rs.getInt(8);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if (s_qtde_trofeus > 0 || t_qtde_trofeus > 0){
			btnArch1.setIcon(new ImageIcon("resources/images/archievments/first.png"));
		}
		
	}
	
	protected void setActionListener(){
		btnArch1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (s_qtde_trofeus > 0 || t_qtde_trofeus > 0){
					ArchDetails ad = new ArchDetails(ArchDetails.MY_FIRST_TROPHY, main);
					ad.setVisible(true);
				}
			}
		});
	}
	
	protected void paintComponent(Graphics g)  {          
	    super.paintComponent(g);  
	    Graphics2D g2d = (Graphics2D)g.create();  
	    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
	    g2d.dispose();          
	} 
}
