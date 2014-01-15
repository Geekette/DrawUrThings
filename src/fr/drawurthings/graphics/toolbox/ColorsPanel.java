package fr.drawurthings.graphics.toolbox;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.drawurthings.model.ToolboxModel;

/**
 * Panel de couleurs selectionnables.
 * 
 * @author Théo Plockyn, Valentin Ramecourt, Alexandre Canny
 *
 */
@SuppressWarnings("serial")
public class ColorsPanel extends JPanel {

	private final JPanel panelColorInterieur, panelColorBordure;

	/**
	 * Constructeur du panel contenant les deux couleurs
	 * @param m - Le modèle de la toolbox
	 */
	public ColorsPanel(ToolboxModel m){

		setLayout(new GridLayout(2, 2, 10, -15));
		int colorSize = 10;
		JLabel interieur = new JLabel("Remplissage");
		JLabel bordure   = new JLabel("Trait");
		panelColorInterieur = new JPanel();
		panelColorInterieur.setBackground(m.getInterieur());
		panelColorInterieur.addMouseListener(new ColorListener("interieur", m));
		panelColorBordure = new JPanel();
		panelColorBordure.setBackground(m.getBordure());
		panelColorBordure.addMouseListener(new ColorListener("bordure", m));
		panelColorBordure.setSize(colorSize, colorSize);
		panelColorInterieur.setSize(colorSize, colorSize);
		add(interieur);
		add(bordure);
		add(panelColorInterieur);
		add(panelColorBordure);
	}

	/**
	 * Setter de la couleur de la bordure
	 * @param c - La couleur souhaitée de la bordure
	 */
	public void setBordure(Color c){
		panelColorBordure.setBackground(c);
	}

	/**
	 * Setter de la couleur de l'intérieur
	 * @param c - La couleur souhaitée de l'interieur
	 */
	public void setInterieur(Color c){
		panelColorInterieur.setBackground(c);
	}

}

/**
 * MouseListener du panel de couleur permettant de choisir soit la couleur d'intérieur soit de bordure.
 * 
 * @author Théo Plockyn, Valentin Ramecourt, Alexandre Canny
 *
 */
class ColorListener implements MouseListener{
	String text;
	ToolboxModel m;
	public ColorListener(String text, ToolboxModel m){
		this.text = text;
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(text.equals("bordure")){
			m.setBordure(JColorChooser.showDialog(null, "Couleur de la bordure", Color.black));
		} else if(text.equals("interieur")){
			m.setInterieur(JColorChooser.showDialog(null, "Couleur de l'interieur", Color.white));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}