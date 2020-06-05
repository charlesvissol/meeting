package org.angrybee.meet.utils.screen;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Graphical container to display image
 * The image size is adapted to the parent graphical component 
 * @author Charles Vissol
 */
public class UIScreenContainer extends JLabel {

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
