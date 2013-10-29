package org.bb.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bb.main.Main;

import sas.swing.MultiLineLabel;
import sas.swing.plaf.MultiLineLabelUI;

public class ArchDetails extends JDialog{
	
	public static int MY_FIRST_TROPHY = 1;
	private JButton btnOK = new JButton("OK");
	private MultiLineLabel lblTitle = new MultiLineLabel();
	private MultiLineLabel lblDesc = new MultiLineLabel();
	private ArchDetails ad;
	private int arch;
	
	public ArchDetails(int arch, Main main){
		this.arch = arch;
		ad = this;
		setModal(true);
		setUndecorated(true);
		setSize(400, 400);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel(){
			protected void paintComponent(Graphics g)  {          
			    super.paintComponents(g);  
			    Graphics2D g2d = (Graphics2D)g.create();
			    g2d.drawImage(new ImageIcon("resources/images/archievments/first.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), null);  
			    g2d.setColor(new Color(255, 255, 255, 180));
			    g2d.fillRect(0, 0, getWidth(), getHeight());
			    g2d.dispose();          
			} 
		};
		panel.setLayout(new BorderLayout());
		lblTitle.setForeground(Color.black);
		lblTitle.setFont(main.getFonte(main.fonteBomberman, 40));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setHorizontalTextAlignment(JLabel.CENTER);
		lblTitle.setHorizontalTextPosition(JLabel.CENTER);
		lblDesc.setForeground(Color.black);
		lblDesc.setFont(main.getFonte(main.fonteBomberman, 35));
		lblDesc.setHorizontalAlignment(JLabel.CENTER);
		lblDesc.setHorizontalTextAlignment(JLabel.CENTER);
		lblDesc.setHorizontalTextPosition(JLabel.CENTER);
		
		panel.add(lblTitle, BorderLayout.NORTH);
		panel.add(lblDesc, BorderLayout.CENTER);
		panel.add(btnOK, BorderLayout.SOUTH);
		
		if (arch == MY_FIRST_TROPHY){
			lblTitle.setText("Meu Primeiro Trofeu");
			lblDesc.setText("Parabens! Voce obteve seu primeiro trofeu!");
		}
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				// TODO Auto-generated method stub
				ad.closeArchDetails();
			}
		});
		add(panel);
	}
    protected void closeArchDetails(){
    	this.setVisible(false);
    }
    
}
