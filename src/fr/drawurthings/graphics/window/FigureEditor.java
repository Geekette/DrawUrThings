package fr.drawurthings.graphics.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.drawurthings.figures.Drawable;
import fr.drawurthings.model.Paint;

@SuppressWarnings("serial")
public class FigureEditor extends JFrame {
	
	private Paint p;
	private int working_layer;
	private Drawable currentDrawable;
	
	private JTabbedPane mainPanel;
	private JButton okBut, cancelBut,appliquerBut;
	private JTextField tailleX, tailleY, originX, originY;
	private JPanel param, borderColor, bgColor;
	private JColorChooser bgColorCC, borderColorCC;

	
	private Ecouteur ec;
	
	public FigureEditor(Paint p, int working_layer){
		setTitle("Editer une figure");
		this.p = p;
		this.working_layer = working_layer;
		this.currentDrawable = this.p.getDrawables().get(this.working_layer);
		this.ec = new Ecouteur();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildContentPane();
		setVisible(true);
		pack();
		Dimension resol = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(resol.width/2-getWidth()/2, resol.height/2-getHeight()/2);
	}
	
	public void buildContentPane(){
		mainPanel = new JTabbedPane();
		param = new JPanel();
		borderColor = new JPanel();
		bgColor = new JPanel();
		
		JPanel paramArea = new JPanel();
		paramArea.setLayout(new BoxLayout(paramArea, BoxLayout.Y_AXIS));
		for(int i = 0; i < 4; i++)
			paramArea.add(createParamLine(i));
		
				
		param.setLayout(new BoxLayout(param, BoxLayout.Y_AXIS));
        param.add(paramArea);
        param.add(createBottomPanel());
        param.setBorder(BorderFactory.createTitledBorder("Editez les paramètres"));
        mainPanel.addTab("Paramètres figure", null, param, "Permet de changer les paramtres de la figure");
        
        borderColorCC = new JColorChooser();
        borderColor.setLayout(new BoxLayout(borderColor, BoxLayout.Y_AXIS));
        borderColor.add(borderColorCC);
        borderColor.add(createBottomPanel());
        borderColor.setBorder(BorderFactory.createTitledBorder("Choisissez la couleur de bordure"));
        mainPanel.addTab("Couleur bordure", null, borderColor, "Permet de changer la couleur de bordure de la figure");
        
        bgColorCC = new JColorChooser();
        bgColor.setLayout(new BoxLayout(bgColor, BoxLayout.Y_AXIS));
        bgColor.add(bgColorCC);
        bgColor.add(createBottomPanel());
        bgColor.setBorder(BorderFactory.createTitledBorder("Choisissez la couleur de remplissage"));
        mainPanel.addTab("Couleur remplissage", null, bgColor, "Permet de changer la couleur de remplissage de la figure");
        
        this.getContentPane().add(mainPanel);
	}

	private JPanel createParamLine(int i) {
		JPanel paramLine = new JPanel();
		paramLine.setLayout(new GridLayout(1, 2));
		
		if(i == 0){
			JTextArea originXTA = new JTextArea("Origine en X :");
			originXTA.setEditable(false);
			originX = new JTextField("" + currentDrawable.getOriginX());
			originXTA.setPreferredSize(originXTA.getPreferredSize());
			originX.setPreferredSize(originX.getPreferredSize());
			paramLine.add(originXTA);
			paramLine.add(originX);
			originX.addKeyListener(ec);
		}else if(i == 1){
			JTextArea originYTA = new JTextArea("Origine en Y :");
			originYTA.setEditable(false);
			originY = new JTextField("" + currentDrawable.getOriginY());
			originYTA.setPreferredSize(originYTA.getPreferredSize());
			originY.setPreferredSize(originY.getPreferredSize());
			paramLine.add(originYTA);
			paramLine.add(originY);
			originY.addKeyListener(ec);
		}else if(i == 2){
			JTextArea tailleXTA = new JTextArea("Largeur :");
			tailleXTA.setEditable(false);
			tailleX = new JTextField("" + currentDrawable.getWidth());
			tailleXTA.setPreferredSize(tailleXTA.getPreferredSize());
			tailleX.setPreferredSize(tailleX.getPreferredSize());
			paramLine.add(tailleXTA);
			paramLine.add(tailleX);
			tailleX.addKeyListener(ec);
		}else if(i == 3){
			JTextArea tailleYTA = new JTextArea("Hauteur :");
			tailleYTA.setEditable(false);
			tailleY = new JTextField("" + currentDrawable.getHeight());
			tailleYTA.setPreferredSize(tailleYTA.getPreferredSize());
			tailleY.setPreferredSize(tailleY.getPreferredSize());
			paramLine.add(tailleYTA);
			paramLine.add(tailleY);
			tailleY.addKeyListener(ec);
		}
		
		return paramLine;
	}

	private JPanel createBottomPanel() {
			JPanel bottomPanel = new JPanel();
			okBut = new JButton("OK");
			appliquerBut = new JButton("Appliquer");
			cancelBut = new JButton("Annuler");
			okBut.addActionListener(ec);
			cancelBut.addActionListener(ec);
			appliquerBut.addActionListener(ec);
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
			bottomPanel.add(okBut);
			bottomPanel.add(cancelBut);
			bottomPanel.add(appliquerBut);
		return bottomPanel;
	}
	
	public class Ecouteur implements ActionListener, KeyListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.getText().equals("Annuler")){
				dispose();
			}else{
				if(mainPanel.getSelectedComponent().equals(param)){
					p.resizeFiguresOnLayer(working_layer, Integer.parseInt(tailleX.getText()), Integer.parseInt(tailleY.getText()));
					p.moveFiguresOnLayer(working_layer, Integer.parseInt(originX.getText()), Integer.parseInt(originY.getText()));
				}else if(mainPanel.getSelectedComponent().equals(borderColor)){
					Color newColor = borderColorCC.getColor();
					p.setFigureBorderColor(working_layer, newColor);
				}else if(mainPanel.getSelectedComponent().equals(bgColor)){
					Color newColor = bgColorCC.getColor();
					p.setFigureFillingColor(working_layer, newColor);
				}
				if(source.getText().equals("OK"))
					dispose();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField laSource = (JTextField)e.getSource();
			try{
				Double.parseDouble(laSource.getText());
			}catch(NumberFormatException ex){
				if(laSource.getText().length() != 0)
					laSource.setText(laSource.getText().substring(0, laSource.getText().length() - 1));
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
}
