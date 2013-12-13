package fr.drawurthings.figures;

import java.awt.Color;

public class Square extends Drawable{
	
	
	public Square(int originX, int originY, int height, int layer){
		this.SHAPE_TYPE = Drawable.SQUARE;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = height;
		this.layer = layer;
	}
	
	public Square(int originX, int originY, int height, int layer, Color border){
		this(originX, originY, height, layer);
		this.border = border;
	}
	
	public Square(int originX, int originY, int height, int layer, Color border,Color fill){
		this(originX, originY, height, layer,border);
		this.fill = fill;
	}


	@Override
	public void resize(int width, int height) {
		this.width = height;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if(posX > originX && posX < (originX + height))
			if(posY > originY && posY < (originY + width))
				return true;
		return false;
	}

}
