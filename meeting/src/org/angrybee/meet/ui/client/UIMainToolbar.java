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

	public UIMainToolbar() {
		initComponents();
	}
	
	
	
	public void initComponents() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		
		/**
		 * Meeting
		 */
		URL meetingImageURL = UIMainToolbar.class.getResource("calendar.png");

		Image meetingImage = Toolkit.getDefaultToolkit().getImage(meetingImageURL);
		ImageIcon meetingIcon = new ImageIcon(meetingImage);
		
		this.meeting = new JLabel();
		this.meeting.setIcon(meetingIcon);

		/**
		 * User
		 */
		URL userImageURL = UIMainToolbar.class.getResource("user.png");

		Image userImage = Toolkit.getDefaultToolkit().getImage(userImageURL);
		ImageIcon userIcon = new ImageIcon(userImage);
		
		this.user = new JLabel("Charles Vissol");
		this.user.setIcon(userIcon);
		
		
		/**
		 * Add components in the toolbar
		 */
		this.add(this.meeting);
		this.add(this.user);
		
		
	}

}
