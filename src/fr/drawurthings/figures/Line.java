package fr.drawurthings.figures;

import java.awt.Color;

/**
 * La classe Line définit l'ensemble des méthodes permettant la gestion d'une ligne dans un espace vectoriel.
 */
public class Line extends Drawable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7503821072188472800L;

	public Line(int originX, int originY,int width,int height,int layer){
		this.SHAPE_TYPE = Drawable.LINE;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
		if(width<0 || height<0){
			adaptNegative();
		}
	}
	

	public Line(int originX, int originY,int width,int height,int layer, Color c){
		this(originX, originY,width, height, layer);
		this.border = c;
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Line, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

	protected void adaptNegative(){
		int newOriginX = originX, newOriginY=originY, newWidth=width, newHeight=height;
		if(width<0 && height<0){
			newOriginX = originX+width;
			newOriginY = originY+height;
			newWidth = width*-1;
			newHeight = height*-1;
		}
		originX = newOriginX;
		originY = newOriginY;
		width = newWidth;
		height = newHeight;
	}

	@Override
	public boolean isVisibleAt(int posX, int posY) {
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
		if((posX/zoom) > newOriginX && (posX/zoom) < (newOriginX + newWidth))
			if((posY/zoom) > newOriginY && (posY/zoom) < (newOriginY + newHeight))
				return true;
		return false;
	}

}
