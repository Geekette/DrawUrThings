package fr.drawurthings.toolbox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class Toolbox extends JFrame implements Observer {
	
	ToolboxModel m;
	JPanel panelColorInterieur, panelColorBordure;
	JButton[] shapes;
	
	public Toolbox(ToolboxModel m){
		super("Test");
		this.m=m;
		this.m.addObserver(this);
		setSize(450, 350);
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container boxes = getContentPane();
		boxes.setLayout(new GridLayout(2, 1));

		JPanel tools = new JPanel();
		tools.setLayout(new GridLayout(3,2));
		shapes = new JButton[5];
		// TODO
		shapes[0] = new JButton("line" /*, iconeLigne */);
		shapes[1] = new JButton("rect" /*, iconeRect */);
		shapes[2] = new JButton("oval" /*, iconeOvale */);
		shapes[3] = new JButton("square" /*, iconeCarre */);
		shapes[4] = new JButton("circle" /*, iconeCercle */);
		for(int i=0; i<5; i++){
			shapes[i].addActionListener(new ShapeListener(m));
			tools.add(shapes[i]);
		}
		tools.setSize(450,500);
		
		
		JPanel colors = new JPanel();
		
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
		colors.setLayout(new GridLayout(2, 2, 30, 30));
		colors.add(interieur);
		colors.add(bordure);
		colors.add(panelColorInterieur);
		colors.add(panelColorBordure);
		panelColorBordure.setSize(50, 50);
		panelColorInterieur.setSize(50, 50);
		
		boxes.add(tools);
		boxes.add(colors);

		pack();
		setVisible(true);
	}
	
	public static void main(String args[]){
		Toolbox t = new Toolbox(new ToolboxModel());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		panelColorBordure.setBackground(m.getBordure());
		panelColorInterieur.setBackground(m.getInterieur());
		repaint();
	}
}

class ShapeListener implements ActionListener{
	ToolboxModel t;
	public ShapeListener(ToolboxModel t){
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		t.setShape(Integer.parseInt(((JButton)arg0.getSource()).getText()));
		
	}
	
}

class ColorListener implements MouseListener{
	String text;
	ToolboxModel t;
	public ColorListener(String text, ToolboxModel t){
		this.text = text;
		this.t = t;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(text.equals("bordure")){
			t.setBordure(JColorChooser.showDialog(null, "Couleur de la bordure", Color.black));
		} else if(text.equals("interieur")){
			t.setInterieur(JColorChooser.showDialog(null, "Couleur de l'interieur", Color.white));
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
