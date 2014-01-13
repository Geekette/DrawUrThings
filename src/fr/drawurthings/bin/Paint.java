package fr.drawurthings.bin;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

import fr.drawurthings.figures.*;
import fr.drawurthings.toolbox.ToolboxModel;

public class Paint extends Observable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7422059367299580777L;
	public static int BACKGROUD_LAYER = -10;
	public static int UPPER_LAYER = -20;
	public static int DOWN_LAYER = -30;
	public static int FOREGROUND_LAYER = -40;
	
	private ArrayList<Drawable> figures;
	private Color bgcolor;
	private ToolboxModel toolbox;
	private Export export;
	private int active_layer = -1;
	private double magnifyingLevel = 1;
	
	public Paint(ToolboxModel t){
		this.figures = new ArrayList<Drawable>();
		this.bgcolor = Color.WHITE;
		this.toolbox = t;
		this.export  = new Export(this);
	}

	public Paint(Color bgColor){
		this.figures = new ArrayList<Drawable>();
		this.bgcolor = bgColor;
	}
	
	public Color getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Ajoute au dessin une figure de type et dimension passées en paramètres. Le contexte courant couleur/zoom lui est appliqué.
	 * @param type Type de figure (voir fr.drawurthings.drawable)
	 * @param originX Position de l'origine du vecteur directeur sur l'axe horizontal
	 * @param originY Position de l'origine du vecteur directeur sur l'axe vertical
	 * @param width Position horizontale de la pointe du vecteur directeur
	 * @param height Position verticale de la pointe du vecteur directeur
	 * @return Une instance fille de la classe abstraite fr.drawurthings.figures.Drawable
	 */
	public void addFigures(int type,int originX,int originY, int width, int height){
		if(type == Drawable.LINE){
			figures.add(new Line(originX, originY, width, height, figures.size(),toolbox.getBordure()));
		}else if(type == Drawable.RECTANGLE){
			figures.add(new Rectangle(originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type == Drawable.SQUARE){
			figures.add(new Square(originX, originY,width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type == Drawable.OVAL){
			figures.add(new Oval(originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type == Drawable.CIRCLE){
			figures.add(new Circle(originX, originY,width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type>=Drawable.TRI_ISO && type<=Drawable.TRI_ISO){
			figures.add(new Triangle(type, originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}
		if(this.magnifyingLevel !=1){
			figures.get(figures.size()-1).modifyScale(magnifyingLevel);
			figures.get(figures.size()-1).setZoom(magnifyingLevel);
		}
		setChanged();
		notifyObservers();
	}
	
	
	/**
	 * Retourne un Drawable adapté au contexte (couleurs et niveau de zoom actif) de l'objet Paint courant.
	 * @param type Type de figure (voir fr.drawurthings.drawable)
	 * @param originX Position de l'origine du vecteur directeur sur l'axe horizontal
	 * @param originY Position de l'origine du vecteur directeur sur l'axe vertical
	 * @param width Position horizontale de la pointe du vecteur directeur
	 * @param height Position verticale de la pointe du vecteur directeur
	 * @return Une instance fille de la classe abstraite fr.drawurthings.figures.Drawable
	 */
	public Drawable buildDrawable(int type,int originX,int originY, int width, int height){
		Drawable toReturn = null;
		if(type == Drawable.LINE){
			toReturn = new Line(originX, originY, width, height, figures.size(),toolbox.getBordure());
		}else if(type == Drawable.RECTANGLE){
			toReturn = new Rectangle(originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur());
		}else if(type == Drawable.SQUARE){
			toReturn = new Square(originX, originY,width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur());
		}else if(type == Drawable.OVAL){
			toReturn = new Oval(originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur());
		}else if(type == Drawable.CIRCLE){
			toReturn = new Circle(originX, originY,width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur());
		}else if(type>=Drawable.TRI_ISO && type<=Drawable.TRI_ISO){
			toReturn = new Triangle(type, originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur());
		}
		if(this.magnifyingLevel !=1){
			toReturn.modifyScale(magnifyingLevel);
			toReturn.setZoom(magnifyingLevel);
		}
		return toReturn;
	}
	
	/**
	 * Ajoute au dessin une copie de la figure dont le numéro de calque est passé en paramétre avec un décalage horizontal et vertical de 15px.
	 * @param layer Numéro du calque contenant la figure à dupliquer.
	 */
	public void duplicateFigure(int layer){
		Drawable tmp = figures.get(layer);
		this.addFigures(tmp.getShapeType(), tmp.getOriginX()+15, tmp.getOriginY()+15, tmp.getWidth(), tmp.getHeight());
		figures.get(figures.size()-1).setBorderColor(tmp.getBorderColor());
		figures.get(figures.size()-1).setFillingColor(tmp.getFillingColor());
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Supprime la figure passée en paramètre.
	 * @param layer Numéro du calque contenant la figure à supprimer.
	 */
	public void removeFigure(int layer){
		figures.remove(layer);
		Collections.sort(figures);
		for(int i = 0;i<figures.size();i++){
			figures.get(i).setLayer(i);
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Supprime toutes les figures et restaure la couleur d'arrière plan à blanc.
	 */
	public void removeAll(){
		this.bgcolor = Color.WHITE;
		figures.clear();
		setChanged();
		notifyObservers();
	}
	
	
	public void setFigureFillingColor(int layer,Color c){
		this.figures.get(layer).setFillingColor(c);
		setChanged();
		notifyObservers();
	}
	
	public void setFigureBorderColor(int layer,Color c){
		this.figures.get(layer).setBorderColor(c);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Drawable> getDrawables(){
		Collections.sort(this.figures);
		return this.figures;
	}
		
	public void resizeFiguresOnLayer(int layer, int width, int heigth){
		figures.get(layer).resize(width, heigth);
		setChanged();
		notifyObservers();
	}
	
	public void moveFiguresOnLayer(int layer, int coordX, int coordY){
		figures.get(layer).moveOrigin(coordX, coordY);
		setChanged();
		notifyObservers();
	}
	
	public void resizeFigureUsingCorner(int layer, int corner, int posX, int posY){
		this.figures.get(layer).resizeMovingACorner(corner, posX, posY);
		setChanged();
		notifyObservers();
	}
	
	public void rotateFigureOnLayer(int layer, int angle){
		this.figures.get(layer).setRotation(angle);;
		this.setChanged();
		this.notifyObservers();
	}
	
	public double getMagnifyingLevel() {
		return magnifyingLevel;
	}

	public void setMagnifyingLevel(double magnifyingLevel) {
		this.magnifyingLevel = magnifyingLevel;
		for(Iterator<Drawable> it = figures.iterator();it.hasNext();){
			it.next().setZoom(this.magnifyingLevel);
		}
		setChanged();
		notifyObservers();
	}
	
	public void magnify(){
		if(magnifyingLevel == 0.25){
			setMagnifyingLevel(0.5);
		}else if(magnifyingLevel == 0.5){
			setMagnifyingLevel(1);
		}else if(magnifyingLevel == 1){
			setMagnifyingLevel(1.5);
		}else if(magnifyingLevel == 1.5){
			setMagnifyingLevel(2.5);
		}
	}
	
	public void demagnify(){
		if(magnifyingLevel == 2.5){
			setMagnifyingLevel(1.5);
		}else if(magnifyingLevel == 0.5){
			setMagnifyingLevel(0.25);
		}else if(magnifyingLevel == 1){
			setMagnifyingLevel(0.5);
		}else if(magnifyingLevel == 1.5){
			magnifyingLevel = 1;
		}
	}

	public void arrangeLayout(int source_layer, int action) throws IllegalArgumentException{
		Drawable tmp = figures.get(source_layer);
		if(action == UPPER_LAYER){
			if(source_layer == figures.size()-1) throw new IllegalArgumentException("La figure est au premier plan et ne peut être avancée.");
			figures.get(source_layer).setLayer(source_layer+1);
			figures.get(source_layer+1).setLayer(source_layer);
		}else if(action == DOWN_LAYER){
			if(source_layer == 0) throw new IllegalArgumentException("La figure est en arrière plan et ne peut être reculée.");
			figures.get(source_layer).setLayer(source_layer-1);
			figures.get(source_layer-1).setLayer(source_layer);
		}else if(action == BACKGROUD_LAYER){
			if(source_layer == 0) throw new IllegalArgumentException("La figure est en arrière plan et ne peut être reculée.");
			figures.remove(tmp);
			for(int i = 0; i<source_layer;i++){
				figures.get(i).setLayer(i+1);
			}
			tmp.setLayer(0);
			figures.add(tmp);
		}else if(action == FOREGROUND_LAYER){
			if(source_layer == figures.size()-1) throw new IllegalArgumentException("La figure est au premier plan et ne peut être avancée.");
			figures.remove(tmp);
			figures.add(tmp);
			for(int i = 0; i<figures.size();i++){
				figures.get(i).setLayer(i);
			}
		}
		setChanged();
		notifyObservers();
	}

	public int getActiveLayerAt(int posX, int posY){
		for(int i = figures.size()-1;i>=0;i--){
			if(figures.get(i).isVisibleAt(posX,posY)){
				return i;
			}
		}
		return -1;
	}
	
	public int getWorkingLayer(){
		return active_layer;
	}
	
	public void setWorkingLayer(int layer){
		this.active_layer = layer;
		setChanged();
		notifyObservers();
	}
	
	public int getCurrentTool(){
		return this.toolbox.getShape();
	}
	
	public ToolboxModel getToolbox(){
		return this.toolbox;
	}
	
	public void saveAs(String file){
		try{
			File fichier = new File(file);
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fichier));
			os.writeObject(this);
			os.close();
		}catch(Exception ex){

		}
	}
	
	public void open(String file){
		ObjectInputStream is = null;
		try{
			File fichier = new File(file);
			is = new ObjectInputStream(new FileInputStream(fichier));
			Paint p = (Paint)is.readObject();
			is.close();
			this.figures = p.getDrawables();
			this.bgcolor = p.getBgcolor();
			setChanged();
			notifyObservers();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void exportAs(String file){
		export.exportAs(file);
	}
	
}
