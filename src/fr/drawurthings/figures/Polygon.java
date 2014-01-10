package fr.drawurthings.figures;

public abstract class Polygon extends Drawable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7169738631857715835L;
	public int[] xPoints, yPoints;

	public abstract int[] getXArray();
	public abstract int[] getYArray();
	public abstract int getNbPoint();
	public abstract void recalculate();

	@Override
	public void moveOrigin(int x, int y){
		setOriginX(x);
		setOriginY(y);
		recalculate();
	}
	
	@Override
	public void setZoom(double zoom){
		this.zoom = zoom;
		recalculate();
	}
	
	@Override
	public void modifyScale(double scale){
		this.originX = (int)(originX/scale);
		this.originY = (int)(originY/scale);
		this.width = (int)(width/scale)+1;
		this.height = (int)(height/scale)+1;
		recalculate();
	}
	
	
	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if(posX > originX && posX < (originX + height))
			if(posY > originY && posY < (originY + width))
				return true;
		return false;
	}

}
