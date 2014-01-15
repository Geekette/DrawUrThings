package fr.drawurthings.figures;

import java.awt.Color;
import java.io.Serializable;

public abstract class Drawable implements Comparable<Drawable>, Serializable {
	
	static final long serialVersionUID = -2727166371836007588L;
	/**
	 * Entier caractérisant la Ligne
	 */
	public final static int LINE = 0;
	/**
	 * Entier caractérisant le Rectangle.
	 */
	public final static int RECTANGLE = 1;
	/**
	 * Entier caractérisant le Carré.
	 */
	public final static int SQUARE = 2;
	/**
	 * Entier caractérisant l'Oval.
	 */
	public final static int OVAL = 3;
	/**
	 * Entier caractérisant le Cercle.
	 */
	public final static int CIRCLE = 4;
	/**
	 * Entier caractérisant le Triangle Isocéle.
	 */
	public final static int TRI_ISO = 5;
	
	/**
	 * Entier désignant le coin en haut à gauche de la figure.
	 */
	public final static int TOP_LEFT_HAND_CORNER = 1000;
	/**
	 * Entier désignant le coin en haut à droite de la figure.
	 */
	public final static int TOP_RIGHT_HAND_CORNER = 1001;
	/**
	 * Entier désignant le coin en bas à gauche de la figure.
	 */
	public final static int BOTTOM_LEFT_HAND_CORNER = 1002;
	/**
	 * Entier désignant le coin en bas à droite de la figure.
	 */
	public final static int BOTTOM_RIGHT_HAND_CORNER = 1003;
	
	protected int SHAPE_TYPE;
	protected int originX, originY, height, width, layer, rotation;
	protected double zoom = 1;
	protected Color border = Color.BLACK,  fill = Color.WHITE;
	
	/**
	 * Méthode permettant d'attribuer un nouveau couple hauteur/largeur à la figure.
	 * @param width La nouvelle largeur.
	 * @param height La nouvelle hauteur.
	 */
	public abstract void resize(int width, int height);
	public abstract boolean isVisibleAt(int posX, int posY);
	public abstract String toString();
	
	/**
	 * Permets d'obtenir l'entier caractérisant la figure courante
	 * @return int (voir les attributs caractérisant une figure)
	 */
	public int getShapeType(){
		return this.SHAPE_TYPE;
	}
	
	/**
	 * Redimensione la figure en renseignant le coin séléctionné par la souris de l'utilisateur.
	 * @param corner (fr.drawurthings.figures.Drawable) Int représentant un coin par valeur entre 1000 et 1003.
	 * @param posX Position de l'origine du vecteur directeur sur l'axe horizontal.
	 * @param posY Position de l'origine du vecteur directeur sur l'axe vertical.
	 */
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
	}
	
	/**
	 * Déplace l'origine aux nouveaux points x et y passés en paramètres.
	 * @param x Nouvelle position en X de l'origine.
	 * @param y Nouvelle position en Y de l'origine.
	 */
	public void moveOrigin(int x, int y){
		setOriginX(x);
		setOriginY(y);
	}
	/**
	 * Retourne la position de l'origine sur l'axe des X.
	 * @return int Position en X de l'origine.
	 */
	public int getOriginX() {
		return (int) (zoom*originX);
	}
	/**
	 * Déplace l'origine sur l'axe des X.
	 * @param Nouvelle coordonnée en X de l'origine.
	 */
	public void setOriginX(int originX) {
		this.originX = (int) (originX/zoom);
	}
	/**
	 * Retourne la position de l'origine sur l'axe des Y.
	 * @return int Position en Y de l'origine.
	 */
	public int getOriginY() {
		return (int) (zoom*originY);
	}
	/**
	 * Déplace l'origine sur l'axe des Y.
	 * @param Nouvelle coordonnée en Y de l'origine.
	 */
	public void setOriginY(int originY) {
		this.originY = (int) (originY/zoom);
	}
	/**
	 * Retourne la longueur de la projection sur l'axe vertical du vecteur directeur de la figure.
	 * @return int Longueur de la projection sur l'axe vertical.
	 */
	public int getHeight() {
		if(rotation == 0 || rotation == 180){
			return (int) (zoom*height);
		}else{
			return (int) (zoom*width);
		}
	}
	/**
	 * Change la longueur de la projection sur l'axe vertical du vecteur directeur de la figure
	 * pour la faire correspondre à la valeur passée en paramètre.
	 * @param height Nouvelle longueur de la projection sur l'axe vertical.
	 */
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
	/**
	 * Retourne la longueur de la projection sur l'axe horizontal du vecteur directeur de la figure.
	 * @return int Longueur de la projection sur l'axe horizontal.
	 */
	public int getWidth() {
		if(rotation == 90 || rotation == 270){
			return (int) (zoom*height);
		}else{
			return (int) (zoom*width);
		}
	}
	/**
	 * Change la longueur de la projection sur l'axe horizontal du vecteur directeur de la figure
	 * pour la faire correspondre à la valeur passée en paramètre.
	 * @param width Nouvelle longueur de la projection sur l'axe horizontal.
	 */
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
	/**
	 * Retournes le numéro de calque sur lequel est située la figure.
	 * @return Numéro de calque de la figure.
	 */
	public int getLayer() {
		return layer;
	}
	/**
	 * Affecte un nouveau numéro de calque à la figure .
	 * @param layer Nouveau numéro de calque.
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}
	/**
	 * Retourne l'angle duquel la figure est pivoté par rapport à son état à la création
	 * @return
	 */
	public int getRotation(){
		return this.rotation;
	}
	/**
	 * Pivote la figure en ajoutant l'angle séléctionné modulo 360 (Pour garder une valeur entre 0 et 360).
	 * @param angle Angle multiple de 90.
	 */
	public void setRotation(int r){
		this.rotation = (this.rotation+r)%360;
	}
	/**
	 * Retourne la couleur des traits délimitant la figure.
	 * @return java.awt.Color Couleur des bords de la figure.
	 */
	public Color getBorderColor() {
		return border;
	}
	/**
	 * Défini la couleur des traits délimitant la figure.
	 * @param border java.awt.Color Couleur des bords de la figure.
	 */
	public void setBorderColor(Color border) {
		this.border = border;
	}
	/**
	 * Retourne la couleur de remplissage la figure.
	 * @return java.awt.Color Couleur du remplissage de la figure.
	 */
	public Color getFillingColor() {
		return fill;
	}
	/**
	 * Défini la couleur de remplissage de la figure.
	 * @param border java.awt.Color Couleur du remplissage de la figure.
	 */
	public void setFillingColor(Color fill) {
		this.fill = fill;
	}
	/**
	 * Permet d'indiquer le niveau de zoom souhaitée
	 * @param zoom Nouveau niveau de zoom
	 */
	public void setZoom(double zoom){
		this.zoom = zoom;
	}
	/**
	 * Recalcule la figure pour la mettre à l'échelle passée en paramétre.
	 * @param scale Nouveau niveau de zoom
	 */
	public void modifyScale(double scale){
		this.originX = (int)(originX/scale);
		this.originY = (int)(originY/scale);
		this.width = (int)(width/scale)+1;
		this.height = (int)(height/scale)+1;
	}
	
	/**
	 * Cette méthode permets de recalculer les attributs de la figure afin que,
	 * si des valeurs négatives ont été reçues, elle soit dans un système de coordonnées
	 * où sa hauteur et sa largeur son négatives.
	 */
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
	/**
	 * Indique si la figure est au dessus, en dessous, ou sur le même calque que la figure
	 * passée en paramétre.
	 */
	public int compareTo(Drawable o) {
		if(this.layer>o.getLayer()){
			return 1;
		}else if(this.layer<o.getLayer()){
			return -1;
		}
		return 0;
	}

}
