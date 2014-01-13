package fr.drawurthings.graphics.listener;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JOptionPane;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;
import fr.drawurthings.graphics.panel.DrawPopup;
import fr.drawurthings.graphics.window.FigureEditor;

public class DrawPanelListener implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private Paint p;
	private DrawPanel d;
	private int working_layer = -1, active_corner, oringin_x, origin_y, delta_x, delta_y;
	private boolean now_resizing = false, dragged = false;
	
	public DrawPanelListener(Paint p, DrawPanel d){
		this.p = p;
		this.d = d;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
			
			if(working_layer == -1){
				JOptionPane.showMessageDialog(null, "Fonctionnalité non implementée.");
			}else{
				new FigureEditor(this.p, this.working_layer);
			}
		}else if(e.getButton() == MouseEvent.BUTTON3){
			if(working_layer == -1){
				new DrawPopup(p).show(d, e.getX(), e.getY());
			}else{
				new DrawPopup(p, p.getDrawables().get(working_layer)).show(d, e.getX(), e.getY());
				working_layer = -1;
				p.setWorkingLayer(working_layer);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
			oringin_x = e.getX();
			origin_y = e.getY();
			working_layer = p.getWorkingLayer();
			if(working_layer != -1){
				Drawable figure = p.getDrawables().get(working_layer);
				if(e.getX() >= figure.getOriginX() - 5 && e.getX() <= figure.getOriginX() + 5 && e.getY() >= figure.getOriginY() - 5 && e.getY() <= figure.getOriginY() + 5){
					active_corner = Drawable.TOP_LEFT_HAND_CORNER;
					d.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
					now_resizing=true;
				}else if(e.getX() >= figure.getOriginX() - 5 && e.getX() <= figure.getOriginX() + 5 && e.getY() >= figure.getOriginY()+figure.getHeight() - 5 && e.getY() <= figure.getOriginY()+figure.getHeight() + 5){
					active_corner = Drawable.BOTTOM_LEFT_HAND_CORNER;
					d.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
					now_resizing=true;
				}else if(e.getX() >= figure.getOriginX()+figure.getWidth() - 5 && e.getX() <= figure.getOriginX()+figure.getWidth() + 5 && e.getY() >= figure.getOriginY()+figure.getHeight() - 5 && e.getY() <= figure.getOriginY()+figure.getHeight() + 5){
					active_corner = Drawable.BOTTOM_RIGHT_HAND_CORNER;
					d.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
					now_resizing=true;
				}else if(e.getX() >= figure.getOriginX()+figure.getWidth() - 5 && e.getX() <= figure.getOriginX()+figure.getWidth() + 5 && e.getY() >= figure.getOriginY() - 5 && e.getY() <= figure.getOriginY() + 5){
					active_corner = Drawable.TOP_RIGHT_HAND_CORNER;
					d.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
					now_resizing=true;
				}else if (p.getActiveLayerAt(e.getX(), e.getY()) != figure.getLayer()){
					working_layer = p.getActiveLayerAt(e.getX(), e.getY());
					p.setWorkingLayer(working_layer);
				}else{
					d.setCursor(new Cursor(Cursor.HAND_CURSOR));
					delta_x = e.getX() - figure.getOriginX() ;
					delta_y = e.getY() - figure.getOriginY() ;
				}
			}else{
				if(p.getActiveLayerAt(e.getX(), e.getY()) != -1){
					this.working_layer = p.getActiveLayerAt(e.getX(), e.getY());
					p.setWorkingLayer(working_layer);
					delta_x = e.getX() - p.getDrawables().get(working_layer).getOriginX();
					delta_y = e.getY() - p.getDrawables().get(working_layer).getOriginY();
				}else{
					this.working_layer = -1;
					p.setWorkingLayer(working_layer);
				}
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		now_resizing = false;
		d.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if(p.getCurrentTool() != -1 && e.getButton() == MouseEvent.BUTTON1 && dragged){
			p.addFigures(p.getCurrentTool(), oringin_x, origin_y, delta_x, delta_y);
		}
		dragged = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Graphics graphics = d.getGraphics();
		if(this.p.getCurrentTool() == -1){
			if(working_layer != -1){
				if(now_resizing){
					p.resizeFigureUsingCorner(working_layer, active_corner, e.getX(), e.getY());
				}else{
					p.moveFiguresOnLayer(this.working_layer, e.getX()-delta_x, e.getY()-delta_y);
				}
			}
		}else{
			try{
				Thread.sleep(15);
				d.paint(d.getGraphics());
				delta_x = e.getX() - oringin_x ;
				delta_y = e.getY() - origin_y ;
				d.paintDrawable(p.buildDrawable(p.getCurrentTool(), oringin_x, origin_y, delta_x, delta_y), graphics);
			}catch(Exception ex){
				
			}
		}
		dragged = true;
	}

	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.isControlDown()){	
			if(e.getWheelRotation()<0){
				p.demagnify();
			}else{
				p.magnify();
			}
		}
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
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
