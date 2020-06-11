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

	public UIMainToolbar() {
		initComponents();
	}
	
	
	
	public void initComponents() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		URL meetingImageURL = UIMainToolbar.class.getResource("calendar.png");

		Image meetingImage = Toolkit.getDefaultToolkit().getImage(meetingImageURL);
		ImageIcon meetingIcon = new ImageIcon(meetingImage);
		
		this.meeting = new JLabel();
		this.meeting.setIcon(meetingIcon);
		
		this.add(this.meeting);

	}

}
