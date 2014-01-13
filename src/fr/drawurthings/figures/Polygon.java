package fr.drawurthings.figures;

public abstract class Polygon extends Drawable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7169738631857715835L;
	protected int[] xPoints, yPoints;

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
	public boolean isVisibleAt(int posX, int posY) {
		if((posX/zoom) > originX && (posX/zoom) < (originX + getWidth()))
			if((posY/zoom) > originY && (posY/zoom) < (originY + getHeight()))
				return true;
		return false;
	}

}
