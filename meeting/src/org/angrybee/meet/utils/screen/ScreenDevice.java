package org.angrybee.meet.utils.screen;



/**
 * Utility class to represent a screen device
 * @author Charles Vissol
 * 
 */
public class ScreenDevice extends ScreenArea {

	/**
	 * Index of the screen device. Starts by 0.
	 */
	private int screenPosition;
	
	/**
	 * Get the index of the current screen device
	 * @return Position of the current screen device
	 */
	public int getScreenPosition() {
		return screenPosition;
	}

	/**
	 * Set the index of the current screen device
	 * @param screenPosition Position of the current screen device
	 */
	public void setScreenPosition(int screenPosition) {
		this.screenPosition = screenPosition;
	}

}
