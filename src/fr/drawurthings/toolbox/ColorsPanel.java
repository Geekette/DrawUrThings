package fr.drawurthings.toolbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorsPanel extends JPanel {
	
	JPanel panelColorInterieur, panelColorBordure;
	
	public ColorsPanel(ToolboxModel m){
		
		setLayout(new GridLayout(2, 2, 10, -10));
		
		JLabel interieur = new JLabel("Intérieur");
		JLabel bordure   = new JLabel("Bordure");
		panelColorInterieur = new JPanel();
		panelColorInterieur.setPreferredSize(new Dimension(50,50));
		panelColorInterieur.setBackground(m.getInterieur());
		panelColorInterieur.addMouseListener(new ColorListener("interieur", m));
		panelColorBordure = new JPanel();
		panelColorBordure.setPreferredSize(new Dimension(50,50));
		panelColorBordure.setBackground(m.getBordure());
		panelColorBordure.addMouseListener(new ColorListener("bordure", m));
		add(interieur);
		add(bordure);
		add(panelColorInterieur);
		add(panelColorBordure);
		panelColorBordure.setSize(50, 50);
		panelColorInterieur.setSize(50, 50);
	}
	
	public void setBordure(Color c){
		panelColorBordure.setBackground(c);
	}
	
	public void setInterieur(Color c){
		panelColorInterieur.setBackground(c);
	}

}

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