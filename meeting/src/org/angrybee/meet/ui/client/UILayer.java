package org.angrybee.meet.ui.client;

import java.awt.Component;

import javax.swing.JLayeredPane;

/**
 * Internal component to display it in an JLayeredPane
 * Each component brings its own dimension configuration: X, Y, Width, Height and % of parent component dimension (Width, Height)
 * 
 * @author Charles Vissol
 *
 */
public class UILayer {

	private int layerPosition;
	
	/** Convenience object defining the Default layer. Equivalent to Integer.valueOf(0).*/
	public void setDefaultLayerPosition() {
		this.layerPosition = JLayeredPane.DEFAULT_LAYER;
	}
	
	/** Convenience object defining the Palette layer. Equivalent to Integer.valueOf(100).*/
	public void setPaletteLayerPosition() {
		this.layerPosition = JLayeredPane.PALETTE_LAYER;
	}
	
	/** Convenience object defining the Modal layer. Equivalent to Integer.valueOf(200).*/
	public void setModalLayerPosition() {
		this.layerPosition = JLayeredPane.MODAL_LAYER;
	}
	
	/** Convenience object defining the Popup layer. Equivalent to Integer.valueOf(300).*/
	public void setPopupLayerPosition() {
		this.layerPosition = JLayeredPane.POPUP_LAYER;
	}
	
	/** Convenience object defining the Drag layer. Equivalent to Integer.valueOf(400).*/
	public void setDragLayerPosition() {
		this.layerPosition = JLayeredPane.DRAG_LAYER;
	}

	public int getLayerPosition() {
		return this.layerPosition;
	}
	
	/**
	 * internal component
	 */
	private Component layer;
	
	/**
	 * X position for the component
	 */
	private int posX;
	
	/**
	 * Y position for the component
	 */
	private int posY;
	
	/**
	 * Width of the internal component
	 */
	private int width;
	
	/**
	 * Height of the internal component
	 */
	private int height;
	
	/**
	 * % of parent dimension
	 */
	private double percentOfParentSize;

	
	public double getPercentOfParentSize() {
		return percentOfParentSize;
	}

	public void setPercentOfParentSize(double percentOfParentSize) {
		this.percentOfParentSize = percentOfParentSize;
	}
	
	public Component getLayer() {
		return layer;
	}

	public void setLayer(Component layer) {
		this.layer = layer;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
