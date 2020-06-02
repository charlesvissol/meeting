package org.angrybee.meet.sound.client;

import java.net.Socket;

import org.angrybee.meet.utils.net.IPUtils;
import org.angrybee.meet.utils.sound.Microphone;

/**
 * Client capturing audio from microphone to send sound to server
 * @author Charles Vissol
 *
 */
public class VoipClientSender implements Runnable {

	/**
	 * Thread to run client in order to send data
	 */
	private Thread runner;

	/**
	 * Status of the client: mute or not.
	 * If mute, client does not send data to server but stays connected
	 * By defaut false
	 */
	private boolean isMute = false;
	
	
	/**
	 * Client Socket to send data
	 */
	private Socket socket;
	
	/**
	 * Represents the microphone where to speak
	 */
	private Microphone microphone;
	
	/**
	 * Represents the public IP address of the server
	 */
	private String ipServer;
	
	/**
	 * Represents the listening port of the server
	 */
	private int portServer;

	public Microphone getMicrophone() {
		return microphone;
	}


	public void setMicrophone(Microphone microphone) {
		this.microphone = microphone;
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
	public VoipClientSender() {
		this.microphone = new Microphone();
	}

	/**
	 * Constructor with server parameters
	 * @param ipServer Public IP address of the server
	 * @param portServer Port of the server
	 */
	public VoipClientSender(String ipServer, int portServer) {
		
		this.microphone = new Microphone();
		this.ipServer = ipServer;
		this.portServer = portServer;
		
	}
	
	/**
	 * Initiate the Thread to send data to server
	 */
	public void init() {
		this.runner = new Thread(this);
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
	
	
	/**
	 * set Client mute or not depending on the boolean parameter
	 * @param isMute boolean to set if client is mute or not
	 */
	public void mute(boolean isMute) {
		this.isMute = isMute;
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
		
		if (this.microphone.open()) {
			
			this.microphone.start();

			while (socket.isConnected()) {
				
				try {
					
					if(!isMute) {
						
						byte[] buffer = new byte[microphone.getBufferSize() / 5];
						
						int read = microphone.read(buffer, 0, buffer.length);
					
						socket.getOutputStream().write(buffer, 0, read);
						
						//System.out.println("Data sent");
						
					}
					
				
				} catch (Exception e) {
					
					System.err.println("Could not send data to server:"+ e.getMessage());
					e.printStackTrace();
				}
				
			}
		}

		
	}
	
	/**
	 * 
	 * @param argv
	 */
	public static void main(String argv[]) {
		
		VoipClientSender client = new VoipClientSender(IPUtils.getIp(), 5060);
		client.init();
		client.start();
		
	}
	
	
	
}
