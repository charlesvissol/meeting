package org.angrybee.meet.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JLayerFrame extends JFrame {

	private JPanel toolPane;
	private JPanel internalPane;
	private JLayeredPane layeredPane;

	public JPanel getToolBar() {
		return this.toolPane;
	}

	public JPanel getInternalPane() {
		return this.internalPane;
	}

	public JLayeredPane getLayeredPane() {
		return this.layeredPane;
	}

	public JLayerFrame() {

		this.internalPane = new JPanel();
		this.toolPane = new JPanel();

		this.getContentPane().setLayout(new BorderLayout());

		this.add(this.toolPane, BorderLayout.NORTH);
		this.add(this.internalPane, BorderLayout.CENTER);

		this.layeredPane = new JLayeredPane();
		this.internalPane.add(this.layeredPane);

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();

		this.internalPane.setBorder(raisedbevel);
		this.toolPane.setBorder(raisedbevel);

// pane.setBackground(Color.DARK_GRAY);

		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
// This is only called when the user releases the mouse button.
				System.out.println("Width=" + getRootPane().getWidth() + " Height=" + getRootPane().getHeight());

				int width = (int) Math.round(getRootPane().getWidth() * 0.95);

				int height = (int) Math.round(getRootPane().getHeight() * 0.95);

				int x = 5;
				int y = 5;

				internalPane.setBounds(x, y, width, height);
			}
		});
	}

	public static void main(String arv[]) {

		JLayerFrame frame = new JLayerFrame();
		frame.setSize(600, 600);

		frame.pack();

		frame.setVisible(true);

	}

}