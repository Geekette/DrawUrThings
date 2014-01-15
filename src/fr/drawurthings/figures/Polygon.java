package fr.drawurthings.figures;


/**
 * La classe abstraite Polygon définit l'ensemble des méthodes permettant la gestion d'une figure géométrique polygonale
 * en mode vectoriel. Chaque figure du type Drawable sera ainsi inscrite dans un rectangle ayant une origine et une projection
 * de sa longeur et de sa largeur sur les axes verticaux et horizontaux relatifs à cette origine. Des méthodes spécifiques permettent
 * de calculer l'ensemble des points de la figure à l'intérieur de l'espace vectoriel ainsi définis. Une liste de points peut alors être
 * obtenu pour un tracé.
 */
public abstract class Polygon extends Drawable{
	
	
	private static final long serialVersionUID = -7169738631857715835L;
	protected int[] xPoints, yPoints;

	/**
	 * Retourne l'ensemble des coordonnées en X des points constituants le polygone.
	 * @return int[] Coordonnées en X des points constituants le polygone.
	 */
	public abstract int[] getXArray();
	/**
	 * Retourne l'ensemble des coordonnées en Y des points constituants le polygone.
	 * @return int[] Coordonnées en Y des points constituants le polygone.
	 */
	public abstract int[] getYArray();
	/**
	 * Retourne le nombre de sommets du polygone.
	 * @return int Nombre de sommets.
	 */
	public abstract int getNbPoint();
	/**
	 * Cette méthode permets de recalculer les coordonnées de chaque sommets après des changements concernant l'origine
	 * ou le vecteur directeur.
	 */
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
