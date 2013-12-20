package fr.drawurthings.toolbox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
 


/* Sources des icones : 
 * 
 * https://www.iconfinder.com/search/?q=iconset%3Afugue+layer
 * https://www.iconfinder.com/search/?q=iconset%3Afugue
 * 
 */
public class ShapesPanel extends JPanel {
	JButton[] shapes;

	final static int nbShapes = 6;
	public ShapesPanel(ToolboxModel m) {
		setLayout(new GridLayout(4,2,10,2));
		shapes = new JButton[7];
		// TODO
		Icon iconeCurseur = new ImageIcon("cursor.png");
		Icon iconeLigne   = new ImageIcon("line.png");
		Icon iconeRect    = new ImageIcon("rectangle.png");
		Icon iconeSquare  = new ImageIcon("square.png");
		Icon iconeOval    = new ImageIcon("oval.png");
		Icon iconeCircle  = new ImageIcon("circle.png");
		//Icon iconePolygon = new ImageIcon("polygon.png");
		shapes[0] = new JButton(iconeCurseur);
		shapes[1] = new JButton(iconeLigne);
		shapes[2] = new JButton(iconeRect);
		shapes[3] = new JButton(iconeSquare);
		shapes[4] = new JButton(iconeOval);
		shapes[5] = new JButton(iconeCircle);
		//shapes[6] = new JButton(iconePolygon);
		for(int i=0; i<nbShapes; i++){
			shapes[i].addActionListener(new ShapeListener(m, this));
			add(shapes[i]);
		}
	}

}

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
			if(s.shapes[i].equals(((JButton)arg0.getSource()))){
				break;
			}
		}
		m.setShape(i-1);
	}
}