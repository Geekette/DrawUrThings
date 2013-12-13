package fr.drawurthings.graphics.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import fr.drawurthings.bin.Paint;

public class DrawPanelListener implements MouseListener, MouseMotionListener{
	
	private Paint p;
	private int working_layer = -1;
	
	public DrawPanelListener(Paint p){
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			JOptionPane.showMessageDialog(null, "Vous avez double-clique.");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(p.getActiveLayerAt(e.getX(), e.getY()) != -1){
			this.working_layer = p.getActiveLayerAt(e.getX(), e.getY());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		working_layer = -1;
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
			p.moveFiguresOnLayer(this.working_layer, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
