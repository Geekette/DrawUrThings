package fr.drawurthings.graphics.window;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.drawurthings.bin.Paint;

@SuppressWarnings("serial")
public class FigureEditor extends JFrame {
	
	Paint p;
	int working_layer;
	
	JTabbedPane mainPanel;
	JButton okBut, cancelBut;
	JTextField tailleX, tailleY, originX, originY;
	JPanel param, borderColor, bgColor;
	JColorChooser bgColorCC, borderColorCC;
	
	public FigureEditor(Paint p, int working_layer){
		setTitle("Figure Editor");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		buildContentPane();
		setVisible(true);
		pack();
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
        mainPanel.addTab("Paramètres figure", null, param, "Permet de changer les paramètres de la figure");
        
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
			originX = new JTextField();
			originXTA.setPreferredSize(originXTA.getPreferredSize());
			originX.setPreferredSize(originX.getPreferredSize());
			paramLine.add(originXTA);
			paramLine.add(originX);
		}else if(i == 1){
			JTextArea originYTA = new JTextArea("Origine en Y :");
			originY = new JTextField();
			originYTA.setPreferredSize(originYTA.getPreferredSize());
			originY.setPreferredSize(originY.getPreferredSize());
			paramLine.add(originYTA);
			paramLine.add(originY);
		}else if(i == 2){
			JTextArea tailleXTA = new JTextArea("Largeur :");
			tailleX = new JTextField();
			tailleXTA.setPreferredSize(tailleXTA.getPreferredSize());
			tailleX.setPreferredSize(tailleX.getPreferredSize());
			paramLine.add(tailleXTA);
			paramLine.add(tailleX);
		}else if(i == 3){
			JTextArea tailleYTA = new JTextArea("Hauteur :");
			tailleY = new JTextField();
			tailleYTA.setPreferredSize(tailleYTA.getPreferredSize());
			tailleY.setPreferredSize(tailleY.getPreferredSize());
			paramLine.add(tailleYTA);
			paramLine.add(tailleY);
		}
		
		return paramLine;
	}

	private JPanel createBottomPanel() {
		JPanel bottomPanel = new JPanel();
		okBut = new JButton("OK");
		cancelBut = new JButton("Annuler");
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(okBut);
		bottomPanel.add(cancelBut);
		
		return bottomPanel;
	}
	
	public class Ecouteur implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.equals(cancelBut)){
				dispose();
			}else if(mainPanel.getSelectedComponent().equals(param)){
				/**
				 * On empeche l'utilisateur de rentrer de la merde dans les champs.
				 */
				/**
				 * On change les données dont les champs sont détaillés, et on fait gaffe à ce que ça ne soit que des valeurs numériques(+ valeurs cohérentes pour figures spécifiques [Carré par ex]), avant d'arriver ici !
				 */
				
				p.resizeFiguresOnLayer(working_layer, Integer.parseInt(tailleX.getText()), Integer.parseInt(tailleY.getText()));
				p.moveFiguresOnLayer(working_layer, Integer.parseInt(originX.getText()), Integer.parseInt(originY.getText()));
			}else if(mainPanel.getSelectedComponent().equals(borderColor)){
				Color newColor = borderColorCC.getColor();
				p.setFigureBorderColor(working_layer, newColor);
			}else if(mainPanel.getSelectedComponent().equals(bgColor)){
				Color newColor = bgColorCC.getColor();
				p.setFigureFillingColor(working_layer, newColor);
			}
			
		}
		
	}
	
	/*@SuppressWarnings("unused")
	public static void main(String args[]){
		FigureEditor fe = new FigureEditor();
	}*/
	
}
