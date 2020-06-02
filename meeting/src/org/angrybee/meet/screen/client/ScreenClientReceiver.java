package org.angrybee.meet.screen.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;

import org.angrybee.meet.sound.server.VoipServerHandler;
import org.angrybee.meet.utils.screen.UIImgDisplayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenClientReceiver implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ScreenClientReceiver.class);
	
	/**
	 * Panel to display image from server
	 */
	private UIImgDisplayer displayImage;


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
	public ScreenClientReceiver() {
		this.setMute(false);
	}
	
	/**
	 * Constructor with server parameters
	 * @param ipServer Public IP address of the server
	 * @param portServer Port of the server
	 */
	public ScreenClientReceiver(String ipServer, int portServer) {
		
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
			logger.info("Client receiver initialized to accept screens");
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
		try {
			
			this.socket = new Socket(this.getIpServer(), this.getPortServer());
			this.socket.setKeepAlive(true);
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return;
		
		}

		logger.info("Client receiver is alive");
		
		while (socket.isConnected()) {
			if(!this.isMute) {//Not Mute by default

				
				DataInputStream in = null;
				int nbrToRead = 0;
				byte[] byteArray = null;
				int nbrRd = 0;
				ByteArrayInputStream byteArrayI = null;
				
				try {
					in = new DataInputStream(socket.getInputStream());

				} catch (IOException e) {

					e.printStackTrace();
				}
				
				try {
					while (true) {

						nbrToRead = in.readInt();

						byteArray = new byte[nbrToRead];

						nbrRd = 0;
						int nbrLeftToRead = nbrToRead;
						while (nbrLeftToRead > 0) {
							int rd = in.read(byteArray, nbrRd, nbrLeftToRead);
							if (rd < 0)
								break;
							nbrRd += rd; // accumulate bytes read
							nbrLeftToRead -= rd;
						}

						// Converting the image
						byteArrayI = new ByteArrayInputStream(byteArray);

						BufferedImage image = ImageIO.read(byteArrayI);

						this.displayImage.reload(image);


						image.flush();
					}

				} catch (Exception e) {
					e.printStackTrace();

				}
				
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
