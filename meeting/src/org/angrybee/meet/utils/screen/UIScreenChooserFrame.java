package org.angrybee.meet.utils.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.angrybee.meet.utils.ui.SpringUtilities;

public class UIScreenChooserFrame extends JFrame {
	
	private static ResourceBundle resources;

	
	public static int getGridLayoutRows(int components) {
		
		switch (components) {
		case 1:
			return 1;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 2;
		case 5:
			return 3;
		case 6:
			return 3;
		default:
			return 1;
		}
		
	}

	public UIScreenChooserFrame() {
		
		resources = ResourceBundle.getBundle(UIScreenChooserFrame.class.getName(), Locale.getDefault());

		ArrayList<UIScreenChooserPanel> panelList = new ArrayList<UIScreenChooserPanel>();
		
		
		BufferedImage[] array = ScreenUtils.getScreens();
		
		int rows = getGridLayoutRows(array.length);
		
		Container contentPane = this.getContentPane();
		
		GridLayout layout = new GridLayout(rows, 2);
		
		contentPane.setLayout(layout);
				
		
		
		for (int i = 0; i < array.length; i++) {
			UIScreenChooserPanel panel = new UIScreenChooserPanel(resources.getString("screen.title") + " - " + i);
			panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,10));
			
			panel.reload(array[i]);

			contentPane.add(panel);

			panelList.add(panel);
			
		}
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        //Display the window.
        this.pack();
        this.setVisible(true);    
        //this.setSize(800, 350);
        
        int size[] = ScreenUtils.getDefaultScreenSize();
        
        //Appears in the top + right position inside the default screen
        this.setBounds(size[0]-800, 0, 800, 350);
        
	}
	
	
	
	
	public static void main(String[] args) {
	     try {
	    	 UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
	      } catch (ClassNotFoundException ex) {
	          ex.printStackTrace();
	      } catch (InstantiationException ex) {
	    	  ex.printStackTrace();
	      } catch (IllegalAccessException ex) {
	    	  ex.printStackTrace();
	      } catch (UnsupportedLookAndFeelException ex) {
	    	  ex.printStackTrace();
	      }
     	
    	
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new UIScreenChooserFrame();
            }
        });

	}

}
