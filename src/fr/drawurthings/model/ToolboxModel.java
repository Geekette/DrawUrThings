package fr.drawurthings.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;

/**
 * Modèle du panel d'outils et de couleurs selectionnables.
 * 
 * @author Théo Plockyn, Valentin Ramecourt, Alexandre Canny
 *
 */
public class ToolboxModel extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9057354513410611102L;
	Color colorInterieur, colorBordure;
	int shapeSelected;

	/**
	 *  Constructeur initialisant par défaut les couleurs séléctionnées à blanc pour l'intérieur et noir pour l'extérieur, ainsi que l'outil de selection en focus.
	 */
	public ToolboxModel(){
		colorInterieur = Color.WHITE;
		colorBordure = Color.BLACK;
		shapeSelected = -1;
	}

	/**
	 * Setter de la couleur de l'int�rieur
	 * @param c - La couleur souhaitée de l'interieur
	 */
	public void setInterieur(Color c){
		colorInterieur = c;
		setChanged();notifyObservers();
	}

	/**
	 * Setter de la couleur de la bordure
	 * @param c - La couleur souhaitée de la bordure
	 */
	public void setBordure(Color c){
		colorBordure = c;
		setChanged();notifyObservers();
	}

	/**
	 * Setter de l'outil
	 * @param s - La forme à dessiner souhaitée ( ou l'outil de selection )
	 */
	public void setShape(int s){
		shapeSelected = s;
		setChanged();notifyObservers();
	}

	/**
	 * Getter de la couleur intérieure
	 * @return La couleur actuelle de l'interieur
	 */
	public Color getInterieur(){
		return colorInterieur;
	}

	/**
	 * Getter de la couleur extérieure
	 * @return La couleur actuelle de la bordure
	 */
	public Color getBordure(){
		return colorBordure;
	}

	/**
	 * Getter de l'outil
	 * @return L'outil actuel
	 */
	public int getShape(){
		return shapeSelected;
	}
}
