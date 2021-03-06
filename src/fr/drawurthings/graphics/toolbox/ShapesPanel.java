package fr.drawurthings.graphics.toolbox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.drawurthings.model.ToolboxModel;



/**
 * Panel d'outils selectionnables.
 * 
 * @author Théo Plockyn, Valentin Ramecourt, Alexandre Canny
 *
 */
@SuppressWarnings("serial")
public class ShapesPanel extends JPanel {
	JButton[] shapes;

	final static int nbShapes = 7;

	/**
	 * Constructeur du panel contenant les outils
	 * @param m - Le modèle de la toolbox
	 */
	public ShapesPanel(ToolboxModel m) {
		setLayout(new GridLayout(4,2,10,2));
		shapes = new JButton[7];
		Icon iconeCurseur = new ImageIcon("ressources/icon/cursor.png");
		Icon iconeLigne   = new ImageIcon("ressources/icon/line.png");
		Icon iconeRect    = new ImageIcon("ressources/icon/rectangle.png");
		Icon iconeSquare  = new ImageIcon("ressources/icon/square.png");
		Icon iconeOval    = new ImageIcon("ressources/icon/oval.png");
		Icon iconeCircle  = new ImageIcon("ressources/icon/circle.png");
		Icon iconePolygon = new ImageIcon("ressources/icon/polygon.png");
		shapes[0] = new JButton(iconeCurseur);
		shapes[1] = new JButton(iconeLigne);
		shapes[2] = new JButton(iconeRect);
		shapes[3] = new JButton(iconeSquare);
		shapes[4] = new JButton(iconeOval);
		shapes[5] = new JButton(iconeCircle);
		shapes[6] = new JButton(iconePolygon);
		for(int i=0; i<nbShapes; i++){
			shapes[i].addActionListener(new ShapeListener(m, this));
			add(shapes[i]);
		}
	}

}

/**
 * ActionListener du panel d'outils permettant de choisir l'outil ou la forme que l'on souhaite.
 * 
 * @author Théo Plockyn, Valentin Ramecourt, Alexandre Canny
 *
 */
class ShapeListener implements ActionListener{
	ToolboxModel m;
	ShapesPanel s;
	public ShapeListener(ToolboxModel m, ShapesPanel s){
		this.m = m;
		this.s = s;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i;
		for(i=0; i<ShapesPanel.nbShapes; i++){
			if(s.shapes[i].equals((arg0.getSource()))){
				break;
			}
		}
		m.setShape(i-1);
	}
}