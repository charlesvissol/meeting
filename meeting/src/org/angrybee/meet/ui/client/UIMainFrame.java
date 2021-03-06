package org.angrybee.meet.ui.client;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.angrybee.meet.utils.screen.ScreenUtils;

public class UIMainFrame {
	
	
	public static void initComponents() {
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

			JFrame mainFrame;

			UIMainToolbar tools = new UIMainToolbar();

			UIMainContainer container = new UIMainContainer();

			// Create and set up the window.
			mainFrame = new JFrame("Meeting");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Container panel = mainFrame.getContentPane();

			panel.setLayout(new BorderLayout());

			panel.add(tools, BorderLayout.NORTH);
			panel.add(container, BorderLayout.CENTER);

			// Display the window.
			int[] size = ScreenUtils.getDefaultScreenSize();
			
			mainFrame.setBounds(0, 0, size[0], size[1]);
			//mainFrame.pack();
	        mainFrame.setVisible(true);	     
	     
	     
	     
	     
	     
	}
	
	
	

	public static void main(String[] args) {

		//Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            	UIManager.put("swing.boldMetal", Boolean.FALSE);
            	
            	initComponents();
            		
            	
            	
            }
        });

	}

}
