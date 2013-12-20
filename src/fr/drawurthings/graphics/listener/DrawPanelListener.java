package fr.drawurthings.graphics.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import fr.drawurthings.bin.Paint;

public class DrawPanelListener implements MouseListener, MouseMotionListener{
	
	private Paint p;
	private int working_layer = -1, delta_x, delta_y;
	
	public DrawPanelListener(Paint p){
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			if(working_layer == -1){
				int rgb_code;
				rgb_code = JColorChooser.showDialog(null, "Background color", p.getBgcolor()).getRGB();
				p.setBgcolor(new Color(rgb_code));
			}else{
				int rgb_code;
				rgb_code = JColorChooser.showDialog(null, "Background color", p.getDrawables().get(working_layer).getFillingColor()).getRGB();
				p.setFigureFillingColor(working_layer, new Color(rgb_code));
				//JOptionPane.showMessageDialog(null, "Vous avez double-clique sur une figure.");
			}
			working_layer = -1;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(p.getActiveLayerAt(e.getX(), e.getY()) != -1){
			this.working_layer = p.getActiveLayerAt(e.getX(), e.getY());
			delta_x = e.getX() - p.getDrawables().get(working_layer).getOriginX();
			delta_y = e.getY() - p.getDrawables().get(working_layer).getOriginY();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(working_layer != -1){
			p.moveFiguresOnLayer(this.working_layer, e.getX()-delta_x, e.getY()-delta_y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
