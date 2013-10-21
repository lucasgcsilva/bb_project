package org.bb.main.gui;
import java.awt.BasicStroke;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.bb.sound.VolumeControl;
import org.bb.util.SavedPreferences;

public class Options extends JPanel {
	private static boolean isFullSize = false;
	Main main;
	Image background = new ImageIcon("resources/images/bg3.jpg").getImage();
	MusicPlayer player;
	JButton btnVoltar = new JButton("VOLTAR");
	JLabel lblOpt = new JLabel("OPCOES");
	JPanel pnlConfig = new JPanel(new GridBagLayout());
	JLabel lblRes = new JLabel ("Tamanho da tela:");
	JLabel lblVol = new JLabel ("Volume:");
	JLabel lblFS = new JLabel ("Full Screen:");
	JButton b800x600;
	JButton b1024x728;
	JButton b1280x1024;
	JSlider sliderVol = new JSlider(0, 100, (int)(VolumeControl.getMasterOutputVolume()*100));
	JCheckBox cbxFull = new JCheckBox();
	
	public static String SIZE_800x600 = "800x600";
	public static String SIZE_1024x728 = "1024x728";
	public static String SIZE_1280x1024 = "1280x1024";
	
	public Options(Main m){
		main = m;
		setSize(main.width, main.height);
		setLayout(new BorderLayout());
		lblOpt.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpt.setForeground(Color.green);
		lblOpt.setFont(main.getFonte(main.fonteBomberman, 40));
		add(lblOpt, BorderLayout.NORTH);
		
		
		pnlConfig.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		Insets insets = new  Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		lblRes.setForeground(Color.black);
		lblRes.setFont(main.getFonte(main.fonteBomberman, 30));
		pnlConfig.add(lblRes, gbc);
		b800x600 =  new JButton("800x600");
		b800x600.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.width = 800;
				main.height = 600;
				main.setSize(main.width, main.height);
				main.sp.setSavedPreference(SavedPreferences.PREF_SCREEN, SIZE_800x600);
				main.sp.updateSavedPreferences();
				revalidate();
				main.validate();
			}
		});
		gbc.gridx = 1;
		pnlConfig.add(b800x600, gbc);
		b1024x728 =  new JButton("1024x728");
		b1024x728.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.width = 1024;
				main.height = 728;
				main.setSize(main.width, main.height);
				main.sp.setSavedPreference(SavedPreferences.PREF_SCREEN, SIZE_1024x728);
				main.sp.updateSavedPreferences();
				revalidate();
				main.validate();
			}
		});
		gbc.gridx = 2;
		pnlConfig.add(b1024x728, gbc);
		b1280x1024 =  new JButton("1280x1024");
		b1280x1024.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.width = 1280;
				main.height = 1024;
				main.setSize(main.width, main.height);
				main.sp.setSavedPreference(SavedPreferences.PREF_SCREEN, SIZE_1280x1024);
				main.sp.updateSavedPreferences();
				revalidate();
				main.validate();
			}
		});
		gbc.gridx = 3;
		pnlConfig.add(b1280x1024, gbc);
		
		//Volume
		gbc.gridx = 0;
		gbc.gridy = 1;
		lblVol.setForeground(Color.black);
		lblVol.setFont(main.getFonte(main.fonteBomberman, 30));
		pnlConfig.add(lblVol, gbc);
		
		sliderVol.setOpaque(false);
		sliderVol.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				float f = (float) sliderVol.getValue()/100;
				System.out.println(f);
				VolumeControl.setMasterOutputVolume(f);
				main.sp.setSavedPreference(SavedPreferences.PREF_VOLUME, String.valueOf(f));
				main.sp.updateSavedPreferences();
			}
		});
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		pnlConfig.add(sliderVol, gbc);
		
		//Full Screen
		cbxFull.setSelected(isFullSize);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		lblFS.setForeground(Color.black);
		lblFS.setFont(main.getFonte(main.fonteBomberman, 30));
		pnlConfig.add(lblFS, gbc);
		
		cbxFull.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (cbxFull.isSelected()){
					main.setExtendedState(Frame.MAXIMIZED_BOTH);
					isFullSize = true;
				}else{
					main.setExtendedState(Frame.NORMAL);
					isFullSize = false;
				}
				main.sp.setSavedPreference(SavedPreferences.PREF_FULLSCREEN, Boolean.toString(cbxFull.isSelected()));
				main.sp.updateSavedPreferences();
			}
		});
		cbxFull.setOpaque(false);
		gbc.gridx = 1;
		pnlConfig.add(cbxFull, gbc);
		
		
		add(pnlConfig, BorderLayout.CENTER);
		
		btnVoltar.setForeground(Color.BLUE);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 40));
		btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
		btnVoltar.setBackground(Color.GREEN);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				player.stopMusic();
				main.addMenu();
			}
		});
		add(btnVoltar, BorderLayout.SOUTH);
		
		
		setVisible(true);
		player = new MusicPlayer("resources/musics/options.wav", true);
		player.start();
	}
	
	protected void paintComponent(Graphics g)  {          
	    super.paintComponent(g);  
	    Graphics2D g2d = (Graphics2D)g.create();  
	    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
	    g2d.dispose();          
	} 
}
