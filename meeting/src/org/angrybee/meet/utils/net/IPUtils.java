package org.angrybee.meet.utils.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class IPUtils {
	

	/**
	 * Get hostname of the PC
	 * @return String representation of the hostname
	 */
	public static String getHostname() {
		InetAddress addr = null;
		
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return addr.getHostName();
	}	
	
	/**
	 * Get the list of network interfaces that provide unique network identifier to the local PC
	 * @return List of network interfaces values
	 */
	public static ArrayList<String> getNetworkInterfaces() {
		
		ArrayList<String> netInterfaces = new ArrayList<String>();
		Enumeration<NetworkInterface> interfaces = null;
		
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		while (interfaces.hasMoreElements()) {
			NetworkInterface ni;
			Enumeration<InetAddress> adresses;
			
			ni = (NetworkInterface) interfaces.nextElement();
			
			adresses = ni.getInetAddresses();
			
			while (adresses.hasMoreElements()) {
			
				InetAddress ia = (InetAddress) adresses.nextElement();
				netInterfaces.add(ia.toString().substring(1));
			
			}
		}
		return netInterfaces;
	}

    
    
	/**
	 * Check if the ip address is reachable
	 * 
	 * @param ipAddress String representation of an IP address
	 * @return true if ping ok and false if not reachable
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean isPingable(String ipAddress) {

		if(ipAddress.startsWith("/"))
			ipAddress = ipAddress.substring(1);
		
		
		if(ipAddress.contains(":"))//Not MAC like address
			return false;
		if(ipAddress.contains("127.0.0.1"))//Not localhost address
			return false;
		
		InetAddress geek = null;
		try {
			geek = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			return false;
		}

		try {
			if (geek.isReachable(5000)) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}

	}

	
	/**
	 * Get the local IP address of the PC
	 * @return Representative IP address in String format
	 */
	public static String getIp() {
		
		Enumeration<NetworkInterface> interfaces = null;
		
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		while (interfaces.hasMoreElements()) {
			NetworkInterface ni;
			Enumeration<InetAddress> adresses;
			
			ni = (NetworkInterface) interfaces.nextElement();
						
			adresses = ni.getInetAddresses();
			
			while (adresses.hasMoreElements()) {
			
				InetAddress ia = (InetAddress) adresses.nextElement();
				
				if(IPUtils.isPingable(ia.toString().substring(1)))
					return ia.toString().substring(1);
			}
		}
		return "";//In case of no address available
	}	
	
	
	public static void main(String args[]) {


		System.out.println(IPUtils.getIp());
		
		ArrayList<String> list = IPUtils.getNetworkInterfaces();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

}
