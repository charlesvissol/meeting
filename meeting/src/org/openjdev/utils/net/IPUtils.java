package org.openjdev.utils.net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

public class IPUtils {

	/**
	 * Get the IP address of a PC
	 * @return String representation of the IP address
	 */
	public static String getInetAddresses(){
		
		InetAddress thisIp = null;
		
		try {
			thisIp = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return thisIp.getHostAddress().toString();
		
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

		InetAddress geek = null;
		try {
			geek = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			if (geek.isReachable(5000)) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;// By default returns false
	}

	public static void main(String args[]) {
		System.out.println(IPUtils.getInetAddresses());
		
	}

}
