package org.angrybee.meet.ui.client;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLayeredPane;

/**
 * 
 * @author vissol
 *
 */
public class UIMainContainer extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<UILayer> layers;

	public ArrayList<UILayer> getLayers() {
		return this.layers;
	}
	
	public void addLayer(UILayer layer, int layerPosition) {
		//TODO
		
		this.layers.add(layer);
	}
	
	
	public UIMainContainer() {
		//TODO
		this.layers = new ArrayList<UILayer>();
		
	}
	
	
	/**
	 * Configure the display of internal graphical components with one configuration
	 */
	public void displayComponents() {
		
		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				
				ArrayList<UILayer> layers = getLayers();
				
				for (Iterator<UILayer> iterator = layers.iterator(); iterator.hasNext();) {
					
					UILayer uiLayer = iterator.next();

					int width = (int) Math.round(getRootPane().getWidth() * uiLayer.getPercentOfParentSize());

					int height = (int) Math.round(getRootPane().getHeight() * uiLayer.getPercentOfParentSize());
					
					
					uiLayer.getLayer().setBounds(uiLayer.getPosX(), uiLayer.getPosY(), width, height);
				}
				
			}
		});		
		
		
	}
	
	
	
	
	
	
	
	
	
}
