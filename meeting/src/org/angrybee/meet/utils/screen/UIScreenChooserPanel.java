package org.angrybee.meet.utils.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class to display panel with a screenshot: this panel is used to choose 
 * the screen to display during meeting
 * @author Charles Vissol
 */
public class UIScreenChooserPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private UIScreenChooserContainer screen;	
	
	/**
	 * Constructor with title at the bottom of the panel
	 * This constructor allows using the panel to choose a screen
	 * @param title Title of the screen
	 */
	public UIScreenChooserPanel(String title) {
	  		
		this.screen = new UIScreenChooserContainer(this);
		this.setLayout(new BorderLayout());
		this.add(this.screen, BorderLayout.CENTER);
		
		JLabel screenTitle = new JLabel(title,JLabel.CENTER);
		
		this.add(screenTitle, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	/**
	 * Update image displayed in the container
	 * @param img Image to update in the panel
	 */
	public void reload(BufferedImage img) {
		
		ImageIcon imageIcon = new ImageIcon(img);
		
		this.screen.setIcon(imageIcon);
		
	}
	
	
	public static void main(String[] args) {

	     try {
	         
	    	 UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
	      } catch (ClassNotFoundException ex) {
	             Logger.getLogger(UIScreenChooserPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (InstantiationException ex) {
	             Logger.getLogger(UIScreenChooserPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (IllegalAccessException ex) {
	             Logger.getLogger(UIScreenChooserPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      } catch (UnsupportedLookAndFeelException ex) {
	             Logger.getLogger(UIScreenChooserPanel.class.getName())
	                 .log(Level.SEVERE, null, ex);
	      }
		
		
		
		JFrame frame = new JFrame();
		
		frame.setSize(400, 250);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UIScreenChooserPanel display = new UIScreenChooserPanel("Screen 1");
		
		BufferedImage[] array = ScreenUtils.getScreens();
		
		frame.add(display, BorderLayout.CENTER);
		display.reload(array[0]);
		
		
		frame.setVisible(true);
		
	}


}

/**
 * Internal class representing the container where the Screen to select is displayed
 * @author Charles Vissol
 *
 */
class UIScreenChooserContainer extends JLabel {

	/**
	 * By default value is false because UIScreenChooserContainer is not selected by the user.
	 * If UIScreenChooserContainer is selected, value is true
	 */
	private boolean selected;
	
	/**
	 * Setter for UIScreenChooserContainer status (selected or not by the user)
	 * @return True or Flase
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Set the status of the component
	 * @param selected Boolean value
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Parent Panel where UIScreenChooserContainer is placed
	 */
	private JPanel parent;
	

	/**
	 * Constructor that gets parent JPanel in parameter to register it and add a property change 
	 * event on background color: when user select UIScreenChooserContainer, that turns background 
	 * color to black for UIScreenChooserContainer but also its parent panel
	 * @param parent
	 */
	public UIScreenChooserContainer(JPanel parent){
		
		this.parent = parent;
		
		/**
		 * Add a property change event to ensure that parent JPanel 
		 * listen to background property change when user select the screen
		 */
		this.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				
				String property = event.getPropertyName();

				if ("background".equals(property)) {
					
					
					
					
					if(selected) {//Black background if screen selected
						parent.setBackground(Color.darkGray);
						setBackground(Color.darkGray);
						selected = false;


					} else {//Dark gray background if screen deselected
						parent.setBackground(Color.black);
						setBackground(Color.black);
						selected = true;

					}

				}
				
			}
			
		});
		this.addMouseListener(new MouseListener() {

			/**
			 * On click event, the selected screen turns background JLabel and JPanel to black 
			 * to see graphically thet
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected) {//By default, dark gray color for background
					setBackground(Color.darkGray);
					selected = false;
				} else {//If selected, black color for background
					setBackground(Color.black);
					selected = true;
				}
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * When component is resized, image size is updated
	 */
    protected void paintComponent(Graphics g) {
    	
        ImageIcon icon = (ImageIcon) getIcon();
        if (icon != null) {
        	/**
        	 * Call image utility to adapt the image size to the parent component
        	 */
            ScreenUtils.drawScaledImage(icon.getImage(), this, g);
        }
    }


	
}



