package fr.drawurthings.graphics.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;

public class DrawPopup extends JPopupMenu{
	
	private Paint p;
	private Drawable figure;
	
	private JMenuItem rotate90,rotate180,rotate270,colorBorder,colorFill, colorBW,arrangetop,arrangeup,arrangedown,arrangeback,duplicate,delete, properties;
	private JMenuItem bgcolor,undo,redo,deleteall;
	
	public DrawPopup(Paint p){
		this.p = p;
		this.buildForBackground();
	}
	
	public DrawPopup(Paint p, Drawable d){
		this.p = p;
		this.figure = d;
		this.buildForFigure();
	}
	
	private void buildForFigure(){
		PopupListener pl = new PopupListener();
			
		JMenu color = new JMenu("Couleurs");
		color.setIcon(new ImageIcon("color.png"));
		colorBorder = new JMenuItem("Trait",new ImageIcon("color_arrow.png"));
		colorBorder.addActionListener(pl);
		colorFill = new JMenuItem("Remplissage",new ImageIcon("color_pencil.png"));
		colorFill.addActionListener(pl);
		colorBW = new JMenuItem("Passer en noir et blanc", new ImageIcon("color_minus.png"));
		color.add(colorBorder);
		color.add(colorFill);
		color.add(new Separator());
		color.add(colorBW);
		
		JMenu organise = new JMenu("Organiser");
		organise.setIcon(new ImageIcon("layer_arrow.png"));
		arrangetop = new JMenuItem("Mettre au premier plan", new ImageIcon("layer.png"));
		arrangetop.addActionListener(pl);
		arrangeup = new JMenuItem("Monter", new ImageIcon("new.png"));
		arrangeup.addActionListener(pl);
		arrangedown = new JMenuItem("Descendre", new ImageIcon("minus.png"));
		arrangedown.addActionListener(pl);
		arrangeback = new JMenuItem("Mettre en arrière plan", new ImageIcon("layer_shade.png"));
		arrangeback.addActionListener(pl);
		organise.add(arrangetop);
		organise.add(arrangeup);
		organise.add(arrangedown);
		organise.add(arrangeback);
		
		JMenu rotate = new JMenu("Pivoter");
		rotate.setIcon(new ImageIcon("rotate.png"));
		this.rotate90 = new JMenuItem("90°");
		this.rotate180 = new JMenuItem("180°");
		this.rotate270 = new JMenuItem("270°");
		rotate.add(rotate90);
		rotate.add(rotate180);
		rotate.add(rotate270);
		
		this.duplicate = new JMenuItem("Dupliquer", new ImageIcon("duplicate.png"));
		this.delete = new JMenuItem("Supprimer", new ImageIcon("remove.png"));
		this.properties = new JMenuItem("Propriétés", new ImageIcon("pencil.png"));
		this.delete.addActionListener(pl);
		this.properties.addActionListener(pl);
		//delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		this.add(color);
		this.add(organise);
		this.add(rotate);
		this.add(new Separator());
		this.add(duplicate);
		this.add(delete);
		this.add(new Separator());
		this.add(properties);
	}
	
	private void buildForBackground(){
		PopupListener pl = new PopupListener();
		bgcolor = new JMenuItem("Couleur d'arrière plan",new ImageIcon("color_pencil.png"));
		bgcolor.addActionListener(pl);
		undo = new JMenuItem("Annuler", new ImageIcon("undo.png"));
		undo.addActionListener(pl);
		redo = new JMenuItem("Refaire", new ImageIcon("redo.png"));
		redo.addActionListener(pl);
		deleteall = new JMenuItem("Tout supprimer", new ImageIcon("remove.png"));
		deleteall.addActionListener(pl);
		this.add(bgcolor);
		this.add(new Separator());
		this.add(undo);
		this.add(redo);
		this.add(new Separator());
		this.add(deleteall);
	}
	
	class PopupListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem)e.getSource();
			if(figure != null){
				try{
					if(source.equals(delete)){
						p.removeFigure(figure.getLayer());
					}else if(source.equals(colorBorder)){
						int rgb_code;
						rgb_code = JColorChooser.showDialog(null, "Couleur des bords", figure.getBorderColor()).getRGB();
						p.setFigureBorderColor(figure.getLayer(), new Color(rgb_code));
					}else if(source.equals(colorFill)){
						int rgb_code;
						rgb_code = JColorChooser.showDialog(null, "Couleur de remplissage", figure.getFillingColor()).getRGB();
						p.setFigureFillingColor(figure.getLayer(), new Color(rgb_code));
					}else if(source.equals(arrangetop)){
						p.arrangeLayout(figure.getLayer(), Paint.FOREGROUND_LAYER);
					}else if(source.equals(arrangeback)){
						p.arrangeLayout(figure.getLayer(), Paint.BACKGROUD_LAYER);
					}else if(source.equals(arrangeup)){
						p.arrangeLayout(figure.getLayer(), Paint.UPPER_LAYER);
					}else if(source.equals(arrangedown)){
						p.arrangeLayout(figure.getLayer(), Paint.DOWN_LAYER);
					}else{
						JOptionPane.showMessageDialog(null, "Fonctionnalité non implémentée.");
					}
				}catch(IllegalArgumentException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}else{
				if(source.equals(bgcolor)){
					int rgb_code;
					rgb_code = JColorChooser.showDialog(null, "Couleur d'arrière plan", p.getBgcolor()).getRGB();
					p.setBgcolor(new Color(rgb_code));
				}else{
					JOptionPane.showMessageDialog(null, "Fonctionnalité non implémentée.");
				}
			}
			
		}
		
	}

}
