package fr.drawurthings.toolbox;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.drawurthings.figures.Drawable;
 
public class ShapesPanel extends JPanel {
	JButton[] shapes;
	public ShapesPanel(ToolboxModel m) {
		setLayout(new GridLayout(4,2,10,10));
		shapes = new JButton[7];
		// TODO
		shapes[0] = new JButton(""+Drawable.CURSOR /*, iconeCurseur */);
		shapes[1] = new JButton(""+Drawable.LINE /*, iconeLigne */);
		shapes[2] = new JButton(""+Drawable.RECTANGLE /*, iconeRect */);
		shapes[3] = new JButton(""+Drawable.OVAL/*, iconeOvale */);
		shapes[4] = new JButton(""+Drawable.SQUARE /*, iconeCarre */);
		shapes[5] = new JButton(""+Drawable.CIRCLE /*, iconeCercle */);
		shapes[6] = new JButton(""+Drawable.POLYGON /*, iconePolygon */);
		for(int i=0; i<7; i++){
			shapes[i].addActionListener(new ShapeListener(m));
			add(shapes[i]);
		}
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