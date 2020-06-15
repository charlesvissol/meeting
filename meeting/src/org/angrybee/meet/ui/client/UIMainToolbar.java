package org.angrybee.meet.ui.client;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

//TODO
public class UIMainToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;

	JLabel meeting;
	JLabel user;
	JLabel microphone;
	JLabel spacer = new JLabel ("\u0020");
	
	public UIMainToolbar() {
		initComponents();
	}
	
	
	
	public void initComponents() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));

		
		/**
		 * User
		 */
		URL userImageURL = UIMainToolbar.class.getResource("cog.png");

		Image userImage = Toolkit.getDefaultToolkit().getImage(userImageURL);
		ImageIcon userIcon = new ImageIcon(userImage);
		
		this.user = new JLabel("\u0020");
		this.user.setIcon(userIcon);
		
		
		
		/**
		 * Meeting
		 */
		URL meetingImageURL = UIMainToolbar.class.getResource("calendar.png");

		Image meetingImage = Toolkit.getDefaultToolkit().getImage(meetingImageURL);
		ImageIcon meetingIcon = new ImageIcon(meetingImage);
		
		this.meeting = new JLabel("\u0020");
		this.meeting.setIcon(meetingIcon);

		/**
		 * Microphone
		 */
		URL microphoneImageURL = UIMainToolbar.class.getResource("microphone.png");

		Image microphoneImage = Toolkit.getDefaultToolkit().getImage(microphoneImageURL);
		ImageIcon microphoneIcon = new ImageIcon(microphoneImage);
		
		this.microphone = new JLabel("\u0020");
		this.microphone.setIcon(microphoneIcon);

		
		
		
		/**
		 * Add components in the toolbar
		 */
		this.add(this.microphone);
		
		this.add(this.meeting);
		
		this.add(this.user);
	}

}
