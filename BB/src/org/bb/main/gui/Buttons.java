package org.bb.main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bb.main.Main;

public class Buttons extends JPanel {
	
	Main main;
	
	JButton btnBS = new JButton(new ImageIcon("resources/images/buttons/btn_bb_o.png"));
//	JButton btnArena = new JButton(new ImageIcon("resources/images/buttons/btn_ar_o.png"));
	JButton btnHighs = new JButton(new ImageIcon("resources/images/buttons/btn_hi_o.png"));
	JButton btnArch = new JButton(new ImageIcon("resources/images/buttons/btn_co_o.png"));
	JButton btnOpt = new JButton(new ImageIcon("resources/images/buttons/btn_op_o.png"));
	JButton btnExt = new JButton(new ImageIcon("resources/images/buttons/btn_ex_o.png"));
	
	private boolean loginKey = false;
	
	public Buttons(Main m){
		main = m;
		setSize(350, main.height);
		createButtons();
		setOpaque(false);
		setVisible(true);		
	}
	
	private void createButtons(){
		btnBS.setPreferredSize(new Dimension(350, 90));
		
		btnExt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				main.sp.exportPref();
				main.exit();
			}
		});
		
//		btnArena.setEnabled(false);
		btnHighs.setEnabled(false);
		btnArch.setEnabled(false);
		//Alterar Fonte
		btnBS.setFont(new Font("Arial", Font.BOLD, 25));
//		btnArena.setFont(new Font("Arial", Font.BOLD, 25));
		btnHighs.setFont(new Font("Arial", Font.BOLD, 25));
		btnArch.setFont(new Font("Arial", Font.BOLD, 25));
		btnOpt.setFont(new Font("Arial", Font.BOLD, 25));
		btnExt.setFont(new Font("Arial", Font.BOLD, 25));		
		
		btnBS.setOpaque(false);
		btnBS.setContentAreaFilled(false);
		btnBS.setBorderPainted(false);
		btnBS.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_bb_e.png"));
		btnBS.setPressedIcon(new ImageIcon("resources/images/buttons/btn_bb_s.png"));
		
//		btnArena.setOpaque(false);
//		btnArena.setContentAreaFilled(false);
//		btnArena.setBorderPainted(false);
		
		btnHighs.setOpaque(false);
		btnHighs.setContentAreaFilled(false);
		btnHighs.setBorderPainted(false);
		
		btnArch.setOpaque(false);
		btnArch.setContentAreaFilled(false);
		btnArch.setBorderPainted(false);
		
		btnOpt.setOpaque(false);
		btnOpt.setContentAreaFilled(false);
		btnOpt.setBorderPainted(false);
		btnOpt.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_op_e.png"));
		btnOpt.setPressedIcon(new ImageIcon("resources/images/buttons/btn_op_s.png"));
		
		btnExt.setOpaque(false);
		btnExt.setContentAreaFilled(false);
		btnExt.setBorderPainted(false);
		btnExt.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_ex_e.png"));
		btnExt.setPressedIcon(new ImageIcon("resources/images/buttons/btn_ex_s.png"));
		
		btnBS.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		setLayout(new GridLayout(6, 1));
		btnBS.setMargin(new Insets(0, 0, 0, 0));
//		btnArena.setMargin(new Insets(0, 0, 0, 0));
		btnHighs.setMargin(new Insets(0, 0, 0, 0));
		btnArch.setMargin(new Insets(0, 0, 0, 0));
		btnOpt.setMargin(new Insets(0, 0, 0, 0));
		btnExt.setMargin(new Insets(0, 0, 0, 0));
		
		add(btnBS);
//		add(btnArena);
		add(btnHighs);
		add(btnArch);
		add(btnOpt);
		add(btnExt);	
		
		//Listeners
		btnBS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.addBBOptions();
			}
		});
		
		btnHighs.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				main.addScore();
			}
		});
		
		btnArch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.addArchievments();
			}
		});
		
		btnOpt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.addOptions();
			}
		});
	}
	
	public void activeButtons(){
//		btnArena.setEnabled(true);
//		btnArena.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_ar_e.png"));
//		btnArena.setPressedIcon(new ImageIcon("resources/images/buttons/btn_ar_s.png"));
		btnHighs.setEnabled(true);
		btnHighs.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_hi_e.png"));
		btnHighs.setPressedIcon(new ImageIcon("resources/images/buttons/btn_hi_s.png"));
		btnArch.setEnabled(true);
		btnArch.setRolloverIcon(new ImageIcon("resources/images/buttons/btn_co_e.png"));
		btnArch.setPressedIcon(new ImageIcon("resources/images/buttons/btn_co_s.png"));
	}
	
	public void desactiveButtons(){
//		btnArena.setEnabled(false);
		btnHighs.setEnabled(false);
		btnArch.setEnabled(false);
	}
	
	public void setEnabledButtons(Boolean b){
		btnBS.setEnabled(b);
//		btnArena.setEnabled(b);
		btnHighs.setEnabled(b);
		btnArch.setEnabled(b);
		btnOpt.setEnabled(b);
		btnExt.setEnabled(b);	
	}
}
