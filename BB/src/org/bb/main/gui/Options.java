package org.bb.main.gui;
import java.awt.BasicStroke;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.LinkedList;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.Keymap;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.bb.main.Main;
import org.bb.sound.MusicPlayer;
import org.bb.sound.VolumeControl;
import org.bb.util.SavedPreferences;

public class Options extends JPanel {
	private static boolean isFullSize = false;
	private Main main;
	private Image background = new ImageIcon("resources/images/bg3.jpg").getImage();
	private MusicPlayer player;
	private JButton btnVoltar = new JButton("VOLTAR");
	private JLabel lblOpt = new JLabel("OPCOES");
	private JPanel pnlConfig = new JPanel(new GridBagLayout());
	private JLabel lblRes = new JLabel ("Tamanho da tela:");
	private JLabel lblVol = new JLabel ("Volume:");
	private JLabel lblFS = new JLabel ("Full Screen:");
	private JLabel lblPad = new JLabel("Controles:");
	private JButton b800x600;
	private JButton b1024x728;
	private JButton b1280x1024;
	private JSlider sliderVol = new JSlider(0, 100, (int)(VolumeControl.getMasterOutputVolume()*100));
	private JCheckBox cbxFull = new JCheckBox();
	private JTabbedPane tblPad = new JTabbedPane();
	private boolean keyChangePad = false;
	private String pad;
	private String pad2;
	private String[] keyPad2;
	private String[] keyPad1;
	
	
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
		isFullSize = Boolean.parseBoolean(main.sp.fullScreen);
		System.out.println(isFullSize);
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
		
		//Pad
		pad = main.sp.pad;
		keyPad1 = pad.split("\\|");
		String data[][]={{"CIMA", KeyEvent.getKeyText(Integer.valueOf(keyPad1[0]))},
				{"BAIXO", KeyEvent.getKeyText(Integer.valueOf(keyPad1[1]))},
				{"DIREITA", KeyEvent.getKeyText(Integer.valueOf(keyPad1[2]))},
				{"ESQUERDA", KeyEvent.getKeyText(Integer.valueOf(keyPad1[3]))},
				{"BOMBA", KeyEvent.getKeyText(Integer.valueOf(keyPad1[4]))}};
		String fields[]={ "Descrição", "Atalho"};
		JTable tablePad = new JTable(data, fields) {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		tablePad.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 2) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				      if (column != 0){
				    	  if (!keyChangePad){
				    		  keyChangePad=true;
				    		  target.getModel().setValueAt("Aperte a tecla", row, column);
				    		  target.addKeyListener(new KeyAdapter() {
				    			  @Override
				    			  public void keyPressed(KeyEvent e){
				    				  JTable target = (JTable)e.getSource();
				    				  if (target.getSelectedColumn() != 0){
				    					  target.getModel().setValueAt(KeyEvent.getKeyText(e.getKeyCode()),
				    							  target.getSelectedRow(), target.getSelectedColumn());
				    					  keyPad1[target.getSelectedRow()] = String.valueOf(e.getKeyCode());
				    					  pad = keyPad1[0]+"|"+keyPad1[1]+"|"+keyPad1[2]+"|"+keyPad1[3]+"|"+keyPad1[4];
				    					  keyChangePad = false;				    					  
				    				  }
				    			  }
							});
				    	  }
				      }
				    }
				  }
				});
		JScrollPane sp = new JScrollPane(tablePad);
		
		
		pad2 = main.sp.pad2;
		keyPad2 = pad2.split("\\|");
		String data2[][]={{"CIMA", KeyEvent.getKeyText(Integer.valueOf(keyPad2[0]))},
				{"BAIXO", KeyEvent.getKeyText(Integer.valueOf(keyPad2[1]))},
				{"DIREITA", KeyEvent.getKeyText(Integer.valueOf(keyPad2[2]))},
				{"ESQUERDA", KeyEvent.getKeyText(Integer.valueOf(keyPad2[3]))},
				{"BOMBA", KeyEvent.getKeyText(Integer.valueOf(keyPad2[4]))}};
		String fields2[]={ "Descrição", "Atalho"};
		JTable tablePad2 = new JTable(data2, fields2) {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		tablePad2.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 2) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				      if (column != 0){
				    	  if (!keyChangePad){
				    		  keyChangePad=true;
				    		  target.getModel().setValueAt("Aperte a tecla", row, column);
				    		  target.addKeyListener(new KeyAdapter() {
				    			  @Override
				    			  public void keyPressed(KeyEvent e){
				    				  JTable target = (JTable)e.getSource();
				    				  if (target.getSelectedColumn() != 0){
				    					  target.getModel().setValueAt(KeyEvent.getKeyText(e.getKeyCode()),
				    							  target.getSelectedRow(), target.getSelectedColumn());
				    					  keyPad2[target.getSelectedRow()] = String.valueOf(e.getKeyCode());
				    					  pad2 = keyPad2[0]+"|"+keyPad2[1]+"|"+keyPad2[2]+"|"+keyPad2[3]+"|"+keyPad2[4];
				    					  keyChangePad = false;	
				    				  }
				    			  }
							});
				    	  }
				      }
				    }
				  }
				});
		JScrollPane sp2 = new JScrollPane(tablePad2);
		
		
		
		
		
		tblPad.addTab("PAD 1", sp);
		tblPad.addTab("PAD 2", sp2);
		
	
		
		lblPad.setForeground(Color.black);
		lblPad.setFont(main.getFonte(main.fonteBomberman, 30));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		pnlConfig.add(lblPad, gbc);
		gbc.gridy = 4;
		gbc.gridheight = 8;
		pnlConfig.add(tblPad, gbc);
		
		
		
		add(pnlConfig, BorderLayout.CENTER);
		
		btnVoltar.setForeground(Color.BLUE);
		btnVoltar.setFont(main.getFonte(main.fonteBomberman, 40));
		btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
		btnVoltar.setBackground(Color.GREEN);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				main.sp.setSavedPreference(SavedPreferences.PREF_PAD, pad);
				main.sp.setSavedPreference(SavedPreferences.PREF_PAD2, pad2);
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
