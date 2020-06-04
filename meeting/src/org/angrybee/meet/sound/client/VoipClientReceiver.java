package org.angrybee.meet.sound.client;

import java.io.IOException;
import java.net.Socket;

import org.angrybee.meet.utils.sound.Speaker;

/**
 * Client receiving audio data from server.<br>
 * This code is inspired by VoipClient.java from <i>Suraj Kumar</i> <a href="mailto:k975@live.co.uk">k975@live.co.uk</a>.<br>
 * To invoke this class, simply call constructor, initialize parameters and start the thread.<br>
 * <u>Example 1:</u>
 * <pre>
 * 	VoipClientReceiver client = new VoipClientReceiver();
 *	client.setIpServer("192.168.1.23");
 *	client.setPort(8079);
 *	client.init();
 *	client.start();
 * </pre>
 * 
 * Like a Thread, the class could be stopped (stop() method), put in pause during an interval time (ms) with sleep() method.
 * 
 * @author Charles Vissol
 *
 */
public class VoipClientReceiver implements Runnable {

	/**
	 * Thread to run client in order to receive data
	 */
	private Thread runner;

	/**
	 * Status of the client: deaf or not
	 * If deaf, client does not receive data
	 * By default false
	 */
	private boolean isDeaf = false;
	
	/**
	 * Client Socket to receive data
	 */
	private Socket socket;
	
	/**
	 * Represents the microphone where to speak
	 */
	private Speaker speaker;
	
	/**
	 * Represents the public IP address of the server
	 */
	private String ipServer;
	
	/**
	 * Represents the listening port of the server
	 */
	private int portServer;
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
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


	public boolean isDeaf() {
		return isDeaf;
	}

	public void setDeaf(boolean isDeaf) {
		this.isDeaf = isDeaf;
	}
	
	/**
	 * Simple constructor without initialization of IP address/Port of the server
	 */
	public VoipClientReceiver() {
		this.speaker = new Speaker();
		this.setDeaf(true);//By default accept receiving audio data
	}

	/**
	 * Constructor with server parameters
	 * @param ipServer Public IP address of the server
	 * @param portServer Port of the server
	 */
	public VoipClientReceiver(String ipServer, int portServer) {
		
		this.speaker = new Speaker();
		this.setDeaf(true);//By default accept receiving audio data
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
	
	/**
	 * Running thread where a socket is created and the speaker accept audio data (inputstream)
	 */
	@Override
	public void run() {
		
		try {
			
			this.socket = new Socket(this.getIpServer(), this.getPortServer());
			this.socket.setKeepAlive(true);
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return;
		
		}

		
		if (speaker.open()) {
			speaker.start();

			while (socket.isConnected()) {
				try {
					
					if(!this.isDeaf) {//Not Deaf by default

						byte[] buffer = new byte[speaker.getBufferSize() / 5];
						int read = socket.getInputStream().read(buffer, 0, buffer.length);
						
						speaker.write(buffer, 0, read);
						System.out.println("Speaker write: read = " + read);
					}
					
				} catch (IOException e) {
					System.err.println("Could not read data from server:" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * Sample method to show how to use the class
	 * @param argv no arguments
	 */
	public static void main(String argv[]) {
		
		VoipClientReceiver client = new VoipClientReceiver("192.168.1.37", 5060);
		client.init();
		client.start();
		
	}
	
	
	
	
	
	
	
	
}
