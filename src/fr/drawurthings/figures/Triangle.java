package fr.drawurthings.figures;

import java.awt.Color;

public class Triangle extends Polygon{

	
	public Triangle(int type, int originX, int originY, int width, int height, int layer){
		this.SHAPE_TYPE = type;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
		if(width<0 || height<0){
			adaptNegative();
		}
		xPoints = new int[3];
		yPoints = new int[3];
		recalculate();
	}
	
	public Triangle(int type, int originX, int originY, int width, int height, int layer, Color border){
		this(type, originX, originY, width, height, layer);
		this.border = border;
	}
	
	public Triangle(int type, int originX, int originY, int width, int height, int layer, Color border,Color fill){
		this(type, originX, originY, width, height, layer,border);
		this.fill = fill;
	}
	
	@Override
	public int[] getXArray() {
		return xPoints;
	}

	@Override
	public int[] getYArray() {
		return yPoints;
	}

	@Override
	public int getNbPoint() {
		return 3;
	}
	
	@Override
	public void recalculate(){
		if(this.SHAPE_TYPE == Drawable.TRI_ISO){
			xPoints[0] = (int) (this.getOriginX()+(this.getWidth()/2));
			yPoints[0] = (int) (this.getOriginY());
			xPoints[1] = (int) (this.getOriginX());
			yPoints[1] = (int) (this.getOriginY()+ this.getHeight());
			xPoints[2] = (int) (this.getOriginX()+getWidth());
			yPoints[2] = (int) (this.getOriginY()+getHeight());
		}
	}

	
	public void resizeMovingACorner(int corner, int posX, int posY){
		int newWidth, newHeight;
		if(corner == Drawable.TOP_LEFT_HAND_CORNER){			
			newWidth = getWidth() + (getOriginX()-posX);
			newHeight = getHeight() + (getOriginY()-posY);
			setWidth(newWidth);
			setHeigth(newHeight);
			this.moveOrigin(posX, posY);
		}else if(corner == Drawable.BOTTOM_LEFT_HAND_CORNER){
			newWidth = getWidth() + (getOriginX()-posX);
			newHeight = posY - getOriginY();
			setHeigth(newHeight);
			setWidth(newWidth);
			this.setOriginX(posX);
		}else if(corner == Drawable.TOP_RIGHT_HAND_CORNER){
			newWidth = posX - getOriginX();
			newHeight = getHeight() - (posY-getOriginY());
			setWidth(newWidth);
			setHeigth(newHeight);
			setOriginY(posY);
		}else if(corner == Drawable.BOTTOM_RIGHT_HAND_CORNER){
			setWidth(posX-getOriginX());
			setHeigth(posY-getOriginY());
		}
		adaptNegative();
		recalculate();
	}
	
	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		if(width<0 || height<0){
			adaptNegative();
		}
		recalculate();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
