package org.angrybee.meet.utils.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main graphical container to display an image.
 * This image is the screen obtained from other attendee of the meeting.
 * The principle of this display is to adapt the size of the image to its parent graphical container.
 * @author Charles Vissol
 *
 */
public class UIScreenDisplayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private UIScreenContainer label;

	/**
	 * Base constructor to display presenter screen in a panel
	 * No title
	 */
	public UIScreenDisplayerPanel() {

		//this.setBackground(Color.darkGray);
		
		this.label = new UIScreenContainer();
		this.setLayout(new BorderLayout());
		this.add(this.label, BorderLayout.CENTER);
		
		/*
		JScrollPane scrollPane = new JScrollPane(label);
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER);
		*/
		setVisible(true);
	}

	/**
	 * Update image displayed in the container
	 * @param img Image to update in the panel
	 */
	public void reload(BufferedImage img) {
		
		ImageIcon imageIcon = new ImageIcon(img);
		
		this.label.setIcon(imageIcon);
		
	}


	
	public static void main(String argv[]) {
	     try {
	    	 UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
	      } catch (ClassNotFoundException ex) {
	             Logger.getLogger(UIScreenDisplayerPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (InstantiationException ex) {
	             Logger.getLogger(UIScreenDisplayerPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (IllegalAccessException ex) {
	             Logger.getLogger(UIScreenDisplayerPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (UnsupportedLookAndFeelException ex) {
	             Logger.getLogger(UIScreenDisplayerPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      }
		
		JFrame frame = new JFrame();
		frame.setSize(700, 700);
		frame.setLayout(new BorderLayout());
		
		UIScreenDisplayerPanel display = new UIScreenDisplayerPanel();
		
		BufferedImage[] array = ScreenUtils.getScreens();
		
		frame.add(display, BorderLayout.CENTER);
		display.reload(array[0]);
		
		frame.setVisible(true);
		
	}
	
	
	
}