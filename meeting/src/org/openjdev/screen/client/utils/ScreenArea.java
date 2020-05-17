package org.openjdev.screen.client.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 * Represents a selected area of the screen device 
 * @author Charles Vissol (Openjdev)
 *
 */
public class ScreenArea {

	
	/**
	 * Area selected to represent the part of the screen selected
	 */
	private Rectangle area;

	/**
	 * Screenshot of the area selected
	 */
	private BufferedImage screenImage;
	
	/**
	 * Returns the area selected of the screen
	 * @return {@link java.awt.Rectangle} instance
	 */
	public Rectangle getArea() {
		return area;
	}

	/**
	 * Set the screen area selected 
	 * @param area Instance of {@link java.awt.Rectangle}
	 */
	public void setArea(Rectangle area) {
		this.area = area;
	}

	/**
	 * Get buffered image of the screen screenshot area selected
	 * @return {@link java.awt.image.BufferedImage} object
	 */
	public BufferedImage getScreenImage() {
		return screenImage;
	}

	/**
	 * Set the buffered image of the screen area screenshot
	 * @param screenImage {@link java.awt.image.BufferedImage} object
	 */
	public void setScreenImage(BufferedImage screenImage) {
		this.screenImage = screenImage;
	}

	

}
