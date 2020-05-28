package org.angrybee.meet.utils.screen;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UIImgDisplayer extends JPanel {

	private JLabel label;

	public UIImgDisplayer() {
		// setSize(1000, 750); <---- do not do it
		// setResizable(false); <----- do not do it either, unless any good reason

	     try {
	         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	      } catch (ClassNotFoundException ex) {
	             Logger.getLogger(UIImgDisplayer.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (InstantiationException ex) {
	             Logger.getLogger(UIImgDisplayer.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (IllegalAccessException ex) {
	             Logger.getLogger(UIImgDisplayer.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (UnsupportedLookAndFeelException ex) {
	             Logger.getLogger(UIImgDisplayer.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      }
	  		
		
		
		this.label = new JLabel("Img to load");
		
		JScrollPane scrollPane = new JScrollPane(label);
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}

	public void reload(BufferedImage img) {
		ImageIcon imageIcon = new ImageIcon(img);
		this.label.setIcon(imageIcon);
		
	}

}