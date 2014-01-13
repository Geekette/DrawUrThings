package fr.drawurthings.graphics.draw;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.listener.FigureListListener;
import fr.drawurthings.model.Paint;

@SuppressWarnings("serial")
public class FiguresList extends JPanel implements Observer{
	
	private Paint paint;
	@SuppressWarnings("rawtypes")
	private JList liste;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FiguresList(Paint p) {
		this.paint = p;
		this.liste = new JList(paint.getDrawables().toArray());
		liste.setCellRenderer(new DrawableRenderer());
		liste.addMouseListener(new FigureListListener(paint));
		JScrollPane listeScrool = new JScrollPane(liste);
		listeScrool.setPreferredSize(new Dimension(200, 300));
		this.add(listeScrool);
		p.addObserver(this);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		liste.setListData(paint.getDrawables().toArray());
	}
	
	public class DrawableRenderer extends DefaultListCellRenderer{
		
		Icon iconeLigne   = new ImageIcon("ressources/icon/line.png");
		Icon iconeRect    = new ImageIcon("ressources/icon/rectangle.png");
		Icon iconeSquare  = new ImageIcon("ressources/icon/square.png");
		Icon iconeOval    = new ImageIcon("ressources/icon/oval.png");
		Icon iconeCircle  = new ImageIcon("ressources/icon/circle.png");
		Icon iconePoly    = new ImageIcon("ressources/icon/polygon.png");
		
		@SuppressWarnings("rawtypes")
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean hasFocus){
			Drawable figure = (Drawable) value;
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
			String design = "Figure " + figure.getLayer() + "\nOrigine : (" +figure.getOriginX()+","+figure.getOriginY()+")\nDimension : ("+figure.getWidth()+","+figure.getHeight()+")";
			label.setText("<html>" + design.replaceAll("\n", "<br>"));
			if(figure.getShapeType() == Drawable.LINE){
				label.setIcon(iconeLigne);
			}else if(figure.getShapeType() == Drawable.RECTANGLE){
				label.setIcon(iconeRect);
			}else if(figure.getShapeType() == Drawable.SQUARE){
				label.setIcon(iconeSquare);
			}else if(figure.getShapeType() == Drawable.OVAL){
				label.setIcon(iconeOval);
			}else if(figure.getShapeType() == Drawable.CIRCLE){
				label.setIcon(iconeCircle);
			}else{
				label.setIcon(iconePoly);
			}
			return label;
		}
	}

}
