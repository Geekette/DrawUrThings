package fr.drawurthings.figures;

import java.awt.Color;

public abstract class Drawable implements Comparable<Drawable> {
	
	public final static int CURSOR = -1;
	public final static int LINE = 0;
	public final static int RECTANGLE = 1;
	public final static int SQUARE = 2;
	public final static int OVAL = 3;
	public final static int CIRCLE = 4;
	public final static int POLYGON = 5;
	
	public final static int TOP_LEFT_HAND_CORNER = 1000;
	public final static int TOP_RIGHT_HAND_CORNER = 1001;
	public final static int BOTTOM_LEFT_HAND_CORNER = 1002;
	public final static int BOTTOM_RIGHT_HAND_CORNER = 1003;
	
	protected int SHAPE_TYPE;
	protected int originX, originY, height, width, layer;
	protected Color border = Color.BLACK,  fill = Color.WHITE;
	
	public abstract void resize(int width, int height);
	public abstract boolean isVisibleAt(int posX, int posY);
	public abstract String toString();
	
	public int getShapeType(){
		return this.SHAPE_TYPE;
	}
	
	public void resizeMovingACorner(int corner, int posX, int posY){
		int newWidth, newHeight, newOriginX, newOriginY;
		if(corner == Drawable.TOP_LEFT_HAND_CORNER){
			newWidth = this.width + (this.originX-posX);
			newHeight = this.height + (this.originY-posY);
			setWidth(newWidth);
			setHeigth(newHeight);
			this.moveOrigin(posX, posY);
		}else if(corner == Drawable.BOTTOM_LEFT_HAND_CORNER){
			newWidth = this.width + (this.originX-posX);
			newHeight = posY - getOriginY();
			setHeigth(newHeight);
			setWidth(newWidth);
			this.moveOrigin(posX, getOriginY());
		}else if(corner == Drawable.TOP_RIGHT_HAND_CORNER){
			newWidth = posX - getOriginX();
			newHeight = this.height + (this.originY-posY);
			setWidth(newWidth);
			setHeigth(newHeight);
			this.moveOrigin(getOriginX(), posY);
		}else if(corner == Drawable.BOTTOM_RIGHT_HAND_CORNER){
			setWidth(posX-getOriginX());
			setHeigth(posY-getOriginY());
		}
		adaptNegative();
	}
	
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
	
	protected void adaptNegative(){
		int newOriginX = originX, newOriginY=originY, newWidth=width, newHeight=height;
		if(width<0 && height<0){
			newOriginX = originX+width;
			newOriginY = originY+height;
			newWidth = width*-1;
			newHeight = height*-1;
		}else if(width<0){
			newOriginX = originX+width;
			newWidth = width*-1;
		}else if(height<0){
			newOriginY = originY+height;
			newHeight = height*-1;
		}
		originX = newOriginX;
		originY = newOriginY;
		width = newWidth;
		height = newHeight;
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
