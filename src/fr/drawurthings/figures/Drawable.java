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
	protected double zoom = 1;
	protected Color border = Color.BLACK,  fill = Color.WHITE;
	
	public abstract void resize(int width, int height);
	public abstract boolean isVisibleAt(int posX, int posY);
	public abstract String toString();
	
	public int getShapeType(){
		return this.SHAPE_TYPE;
	}
	
	public void resizeMovingACorner(int corner, int posX, int posY){
		int newWidth, newHeight;
		if(corner == Drawable.TOP_LEFT_HAND_CORNER){
			/*setHeigth(getHeight()-(getOriginY()-posY));
			moveOrigin(posX, posY);*/			
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
			//this.moveOrigin(posX, getOriginY());
		}else if(corner == Drawable.TOP_RIGHT_HAND_CORNER){
			newWidth = posX - getOriginX();
			newHeight = getHeight() - (posY-getOriginY());
			setWidth(newWidth);
			setHeigth(newHeight);
			setOriginY(posY);
			/*newWidth = posX - getOriginX();
			newHeight = getHeight() - (posY-getOriginY());
			this.setWidth(newWidth);
			this.setHeigth(newHeight);
			this.moveOrigin((int)(getOriginX()*zoom), posY);*/
			/*newWidth = posX - getOriginX();
			newHeight = getHeight() + (getOriginY()-posY);
			setWidth(newWidth);
			setHeigth(newHeight);
			this.setOriginY(posY);*/
			//this.moveOrigin(getOriginX(), posY);
		}else if(corner == Drawable.BOTTOM_RIGHT_HAND_CORNER){
			setWidth(posX-getOriginX());
			setHeigth(posY-getOriginY());
		}
		adaptNegative();
	}
	
	public void moveOrigin(int x, int y){
		setOriginX(x);
		setOriginY(y);
	}
	
	public int getOriginX() {
		return (int) (zoom*originX);
	}
	public void setOriginX(int originX) {
		this.originX = (int) (originX/zoom);
	}
	public int getOriginY() {
		return (int) (zoom*originY);
	}
	public void setOriginY(int originY) {
		this.originY = (int) (originY/zoom);
	}
	public int getHeight() {
		return (int) (zoom*height);
	}
	public void setHeigth(int height) {
		if(zoom == 1){
			this.height = (int) (height/zoom);
		}else{
			this.height = (int) (height/zoom)+1;
		}
	}
	public int getWidth() {
		return (int) (zoom*width);
	}
	public void setWidth(int width) {
		if(zoom == 1){
			this.width = (int) (width/zoom);
		}else{
			this.width = (int) (width/zoom)+1;
		}
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
	
	public void setZoom(double zoom){
		this.zoom = zoom;
	}
	
	public void modifyScale(double scale){
		this.originX = (int)(originX/scale);
		this.originY = (int)(originY/scale);
		this.width = (int)(width/scale)+1;
		this.height = (int)(height/scale)+1;
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
