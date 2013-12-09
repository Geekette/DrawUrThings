package fr.drawurthings.figures;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Drawable implements Comparable<Drawable> {
	
	protected int originX, originY, height, width, layer;
	protected Color border = Color.BLACK,  fill = Color.WHITE;
	
	public abstract void drawOnGraphics(Graphics g);
	public abstract void magnifyOnGraphics(Graphics g, double factor);
	public abstract void resize(int width, int heigth);
	public abstract String toString();
	
	public void moveOrigin(int x, int y){
		this.originX = x;
		this.originY = y;
	}
	
	public int getOriginX() {
		return originX;
	}
	public void setOriginX(int originX) {
		this.originX = originX;
	}
	public int getOriginY() {
		return originY;
	}
	public void setOriginY(int originY) {
		this.originY = originY;
	}
	public int getHeight() {
		return height;
	}
	public void setHeigth(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public Color getBorderColor() {
		return border;
	}
	public void setBorderColor(Color border) {
		this.border = border;
	}
	public Color getFillingColor() {
		return fill;
	}
	public void setFillingColor(Color fill) {
		this.fill = fill;
	}
	
	@Override
	public int compareTo(Drawable o) {
		if(this.layer>o.getLayer()){
			return 1;
		}else if(this.layer<o.getLayer()){
			return -1;
		}
		return 0;
	}

}
