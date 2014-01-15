package fr.drawurthings.figures;

import java.awt.Color;

/**
 * La classe Triangle définit l'ensemble des méthodes permettant la gestion d'un triangle dans un espace vectoriel.
 */
public class Triangle extends Polygon{

	private static final long serialVersionUID = 7574518786775440114l;
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
		recalculate();
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
			if(rotation == 0){
				xPoints[0] = (int) (this.getOriginX()+(this.getWidth()/2));
				yPoints[0] = (int) (this.getOriginY());
				xPoints[1] = (int) (this.getOriginX());
				yPoints[1] = (int) (this.getOriginY()+ this.getHeight());
				xPoints[2] = (int) (this.getOriginX()+getWidth());
				yPoints[2] = (int) (this.getOriginY()+getHeight());
			}else if(rotation == 90){
				xPoints[0] = (int) this.getOriginX()+this.getWidth();
				yPoints[0] = (int) (this.getOriginY()+this.getHeight()/2);
				xPoints[1] = (int) (this.getOriginX());
				yPoints[1] = (int) (this.getOriginY()+ this.getHeight());
				xPoints[2] = (int) (this.getOriginX());
				yPoints[2] = (int) (this.getOriginY());
			}else if(rotation == 180){
				xPoints[0] = (int) (this.getOriginX()+(this.getWidth()/2));
				yPoints[0] = (int) (this.getOriginY()+this.getHeight());
				xPoints[1] = (int) (this.getOriginX());
				yPoints[1] = (int) (this.getOriginY());
				xPoints[2] = (int) (this.getOriginX()+getWidth());
				yPoints[2] = (int) (this.getOriginY());
			}else{
				xPoints[0] = (int) this.getOriginX();
				yPoints[0] = (int) (this.getOriginY()+this.getHeight()/2);
				xPoints[1] = (int) (this.getOriginX()+this.getWidth());
				yPoints[1] = (int) (this.getOriginY()+ this.getHeight());
				xPoints[2] = (int) (this.getOriginX()+this.getWidth());
				yPoints[2] = (int) (this.getOriginY());
			}
		}else{
			//TODO Meme chose pour triangle rectangle 
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
	
	public int getHeight() {
		if(rotation == 0 || rotation == 180){
			return (int) (zoom*height);
		}else{
			return (int) (zoom*width);
		}
	}
	public void setHeigth(int height) {
		if(rotation == 0 || rotation == 180){
			if(zoom == 1){
				this.height = (int) (height/zoom);
			}else{
				this.height = (int) (height/zoom)+1;
			}
		}else{
			if(zoom == 1){
				this.width = (int) (height/zoom);
			}else{
				this.width = (int) (height/zoom)+1;
			}
		}
	}
	public int getWidth() {
		if(rotation == 90 || rotation == 270){
			return (int) (zoom*height);
		}else{
			return (int) (zoom*width);
		}
	}
	public void setWidth(int width) {
		if(rotation == 90 || rotation == 270){
			if(zoom == 1){
				this.height = (int) (width/zoom);
			}else{
				this.height = (int) (width/zoom)+1;
			}
		}else{
			if(zoom == 1){
				this.width = (int) (width/zoom);
			}else{
				this.width = (int) (width/zoom)+1;
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
