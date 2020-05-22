package org.openjdev.utils.screen;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * 
 * @author Charles Vissol (Openjdev)
 *
 */
public class ScreenUtils {

	/**
	 * Returns the image of each screen connected to the current user Computer
	 * @return List of buffered images representing the connected screens of the users computer
	 */
	public ArrayList<BufferedImage> getScreens() {
		
		ArrayList<BufferedImage> screens = new ArrayList<BufferedImage>();
		Robot robot = null;
		
		try {
			
			robot = new Robot();
			
		} catch (AWTException e) {
			
			Logger.getLogger(ScreenUtils.class.getName()).log(Level.SEVERE, "<"+ ScreenUtils.class.getName() + "> Unable to instanciate Robot object");
			e.printStackTrace();
		
		}
		
		GraphicsDevice[] graphicDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		
		int posXofScreen = 0;//Position X to start the screen capture image on each new screen device position
		/**
		 * Iterate over screens devices captured
		 */
		for (int i = 0; i < graphicDevice.length; i++) {
			
			Rectangle screenRectangle = new Rectangle(posXofScreen, 0, graphicDevice[i].getDisplayMode().getWidth(), graphicDevice[i].getDisplayMode().getHeight());
			
			screens.add(robot.createScreenCapture(screenRectangle));
			
			posXofScreen = graphicDevice[i].getDisplayMode().getWidth();
		}
		
		
		
		return screens;
	}
	
	/**
	 * Take a screenshot of the device screen selected
	 * @param robot {@link java.awt.Robot} instance 
	 * @param rectangle {@link java.awt.Rectangle} instance
	 * @return buffered image representation of the device screen
	 */
	public static BufferedImage getScreenshot(Robot robot, Rectangle rectangle) {
		
		return robot.createScreenCapture(rectangle);
	}

	/**
	 * Returns the {@link java.awt.Robot} instance to access the current screen device
	 * @return {@link java.awt.Robot} instance
	 */
	public static Robot getRobot() {
		Robot robot = null;
		
		try {
			
			robot = new Robot();
			
		} catch (AWTException e) {
			
			Logger.getLogger(ScreenUtils.class.getName()).log(Level.SEVERE, "<"+ ScreenUtils.class.getName() + "> Unable to instanciate Robot object");
			e.printStackTrace();
		
		}

		return robot;
	}
	
	
	
}
