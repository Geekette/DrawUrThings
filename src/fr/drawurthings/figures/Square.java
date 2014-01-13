package fr.drawurthings.figures;

import java.awt.Color;

@SuppressWarnings("serial")
public class Square extends Drawable{
	
	
	public Square(int originX, int originY,int width, int height, int layer){
		this.SHAPE_TYPE = Drawable.SQUARE;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		if(height>0 && width<0 || height<0 && width>0){
			this.width = -height;
		}else{
			this.width = height;
		}
		this.layer = layer;
		if(width<0 || height<0){
			adaptNegative();
		}
	}
	
	public Square(int originX, int originY,int width, int height, int layer, Color border){
		this(originX, originY,width, height, layer);
		this.border = border;
	}
	
	public Square(int originX, int originY,int width, int height, int layer, Color border,Color fill){
		this(originX, originY,width, height, layer,border);
		this.fill = fill;
	}


	
	public void resizeMovingACorner(int corner, int posX, int posY){
		int newWidth, newHeight;
		if(corner == Drawable.TOP_LEFT_HAND_CORNER){			
			newWidth = getWidth() + (getOriginX()-posX);
			newHeight = getHeight() + (getOriginY()-posY);
			if(newHeight>0 && newWidth<0 || newHeight<0 && newWidth>0){
				newWidth = -newHeight;
			}else{
				newWidth = newHeight;
			}
			setWidth(newWidth);
			setHeigth(newHeight);
			this.moveOrigin(posX, posY);
		}else if(corner == Drawable.BOTTOM_LEFT_HAND_CORNER){
			newWidth = getWidth() + (getOriginX()-posX);
			newHeight = posY - getOriginY();
			if(newHeight>0 && newWidth<0 || newHeight<0 && newWidth>0){
				newWidth = -newHeight;
			}else{
				newWidth = newHeight;
			}
			setHeigth(newHeight);
			setWidth(newWidth);
			this.setOriginX(posX);
		}else if(corner == Drawable.TOP_RIGHT_HAND_CORNER){
			newWidth = posX - getOriginX();
			newHeight = getHeight() - (posY-getOriginY());
			if(newHeight>0 && newWidth<0 || newHeight<0 && newWidth>0){
				newWidth = -newHeight;
			}else{
				newWidth = newHeight;
			}
			setWidth(newWidth);
			setHeigth(newHeight);
			setOriginY(posY);
		}else if(corner == Drawable.BOTTOM_RIGHT_HAND_CORNER){
			newWidth = posX-getOriginX();
			newHeight = posY - getOriginY();
			if(newHeight>0 && newWidth<0 || newHeight<0 && newWidth>0){
				newWidth = -newHeight;
			}else{
				newWidth = newHeight;
			}
			setWidth(newWidth);
			setHeigth(newHeight);
		}
		adaptNegative();
	}
	
	@Override
	public void resize(int width, int height) {
		this.width = height;
		this.height = height;
		if(width<0 || height<0){
			adaptNegative();
		}
	}

	@Override
	public String toString() {
		return "Rectangle, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if((posX/zoom) > originX && (posX/zoom) < (originX + width))
			if((posY/zoom) > originY && (posY/zoom) < (originY + height))
				return true;
		return false;
	}

}
