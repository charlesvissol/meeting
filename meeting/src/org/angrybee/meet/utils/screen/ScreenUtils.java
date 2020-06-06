package org.angrybee.meet.utils.screen;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;



/**
 * 
 * @author Charles Vissol (Openjdev)
 *
 */
public class ScreenUtils {
	
	
	public static int[] getDefaultScreenSize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		
		int size[] = {gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight()};
		
		return size;
	}
	
	

	/**
	 * Returns the image of each screen connected to the current user Computer
	 * @return List of buffered images representing the connected screens of the users computer
	 */
	public static BufferedImage[] getScreens() {
		
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
		
		BufferedImage[] array = screens.toArray(new BufferedImage[0]);
		
		return array;
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
	
	/**
	 * Draw a pointer in the screen area. For instance 
	 * TODO Control position of cursor if out of the rectangle area
	 * @param screenCapture Image buffered
	 * @throws IOException
	 * @throws URISyntaxException
	 */
    public static void drawMousePointer(BufferedImage screenCapture) throws IOException, URISyntaxException {

        InputStream arrow = ScreenUtils.class.getResourceAsStream("mouse-pointer.png");
        Image cursor = ImageIO.read(arrow);
    	
        Graphics graphics2D = screenCapture.createGraphics();
        Point p = MouseInfo.getPointerInfo().getLocation();
        graphics2D.drawImage(cursor, p.x-12, p.y-12, 25, 25, null);
    }

    /**
     * Draw a scaled image to adapt its size to its parent graphic component
     * @param image image object
     * @param canvas canvas where image is displayed
     * @param g Graphical environment
     */
    public static void drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);
         
        double imgAspect = (double) imgHeight / imgWidth;
 
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        
        //System.out.println("width=" + canvasWidth + ", height=" + canvasHeight);
        
        double canvasAspect = (double) canvasHeight / canvasWidth;
 
        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position
         
        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;
             
        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }
 
        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }    
	
}
