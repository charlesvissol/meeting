package org.angrybee.meet.screen.client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;

import org.angrybee.meet.utils.screen.ScreenUtils;

/**
 * 
 * @author Charles Vissol
 *
 */
public class ScreenClientSender implements Runnable {

	/**
	 * Thread to run client in order to receive data
	 */
	private Thread runner;

	/**
	 * Status of the client: mute or not
	 * If Mute, client does not receive data
	 * By default false
	 */
	private boolean isMute = true;
	
	/**
	 * Client Socket to receive data
	 */
	private Socket socket;
	
	/**
	 * Represents the public IP address of the server
	 */
	private String ipServer;
	
	/**
	 * Represents the listening port of the server
	 */
	private int portServer;

	/**
	 * Rectangle corresponding to area selected
	 */
	private Rectangle rectangle;	
	
	
	
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Thread getRunner() {
		return runner;
	}

	public void setRunner(Thread runner) {
		this.runner = runner;
	}

	public boolean isMute() {
		return isMute;
	}

	public void setMute(boolean isMute) {
		this.isMute = isMute;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getIpServer() {
		return ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	public int getPortServer() {
		return portServer;
	}

	public void setPortServer(int portServer) {
		this.portServer = portServer;
	}
	
	/**
	 * Simple constructor without initialization of IP address/Port of the server
	 */
	public ScreenClientSender() {
		this.setMute(false);
	}
	
	/**
	 * Constructor with server parameters
	 * @param ipServer Public IP address of the server
	 * @param portServer Port of the server
	 */
	public ScreenClientSender(String ipServer, int portServer) {
		
		this.setMute(false);//By default accept receiving audio data
		this.ipServer = ipServer;
		this.portServer = portServer;
		
	}
	
	/**
	 * Initiate the Thread to receive data to server
	 * Thread synchronized to accept several audio from several servers
	 */
	public void init() {
		synchronized(this) {
			this.runner = new Thread(this);
		}
	}

	/**
	 * Start the Thread to send data to server
	 */
	public void start() {
		this.runner.start();
	}
	
	/**
	 * Stop the client sending data to server
	 */
	public void stop() {
		this.runner.interrupt();
		
	}

	/**
	 * Force the client to sleep
	 * @param millis interval in ms during which the client is sleeping
	 */
	public void sleep(long millis) {
		try {
			
			this.runner.sleep(millis);
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void run() {

		Robot robot = ScreenUtils.getRobot();
    	DataOutputStream out = null;
    	BufferedImage image = null;
    	ByteArrayOutputStream byteArrayO = null;
		
		
		
		try {
			
			this.socket = new Socket(this.getIpServer(), this.getPortServer());
			this.socket.setKeepAlive(true);
			out = new DataOutputStream(this.socket.getOutputStream());
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return;
		
		}

		while (socket.isConnected()) {			
			
			
			try {
				
				if(!isMute) {

	            	image = ScreenUtils.getScreenshot(robot, this.rectangle);
	            	ScreenUtils.drawMousePointer(image);//write mouse pointer
	            	
	            	byteArrayO = new ByteArrayOutputStream();
	                
	                ImageIO.write(image,"PNG",byteArrayO);
	                
	                byte [] byteArray = byteArrayO.toByteArray();
	                
	                //byteArrayO.close();
	                
	                out.writeInt(byteArray.length);
	                out.write(byteArray);
	 					
				}
				
			
			} catch (Exception e) {
				
				System.err.println("Could not send data to server:"+ e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}

}
