package fr.drawurthings.graphics.listener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;

public class DrawPanelListener implements MouseListener, MouseMotionListener{
	
	private Paint p;
	private DrawPanel d;
	private int working_layer = -1, oringin_x, origin_y, delta_x, delta_y;
	
	public DrawPanelListener(Paint p, DrawPanel d){
		this.p = p;
		this.d = d;
		
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
		}else{
			if(working_layer != -1){
				d.paint(d.getGraphics());
				Graphics graphics = d.getGraphics();
				Drawable figure = p.getDrawables().get(working_layer);
				graphics.setColor(Color.BLUE);
				if(figure.getShapeType() != Drawable.LINE){ 
					graphics.drawRect(figure.getOriginX(), figure.getOriginY(), figure.getWidth(), figure.getHeight());
					graphics.drawRect(figure.getOriginX()-5, figure.getOriginY()-5, 10, 10);
					graphics.drawRect(figure.getOriginX()-5, figure.getOriginY() + figure.getHeight()-5, 10, 10);
					graphics.drawRect(figure.getOriginX()+figure.getWidth()-5, figure.getOriginY()+ figure.getHeight()-5, 10, 10);
					graphics.drawRect(figure.getOriginX() + figure.getWidth()-5, figure.getOriginY()-5, 10, 10);
				}else{
					graphics.drawRect(figure.getOriginX()-5, figure.getOriginY()-5, 10, 10);
					graphics.drawRect(figure.getOriginX()+figure.getWidth()-5, figure.getOriginY()+ figure.getHeight()-5, 10, 10);
				}
			}else{
				d.repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
			oringin_x = e.getX();
			origin_y = e.getY();
			if(p.getActiveLayerAt(e.getX(), e.getY()) != -1){
				this.working_layer = p.getActiveLayerAt(e.getX(), e.getY());
				delta_x = e.getX() - p.getDrawables().get(working_layer).getOriginX();
				delta_y = e.getY() - p.getDrawables().get(working_layer).getOriginY();
			}else{
				this.working_layer = -1;
			}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(p.getCurrentTool() != -1){
			p.addFigures(p.getCurrentTool(), oringin_x, origin_y, delta_x, delta_y);
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
	public void mouseDragged(MouseEvent e) {
		Graphics graphics = d.getGraphics();
		if(this.p.getCurrentTool() == -1){
			if(working_layer != -1){
				Drawable figure = p.getDrawables().get(working_layer);
				p.moveFiguresOnLayer(this.working_layer, e.getX()-delta_x, e.getY()-delta_y);
			}
		}else{
			try{
				Thread.sleep(20);
				//d.paint(d.getGraphics());
				d.repaint();
			}catch(Exception ex){
				
			}
			delta_x = e.getX() - oringin_x ;
			delta_y = e.getY() - origin_y ;
			if(p.getCurrentTool() == Drawable.LINE){
				graphics.drawLine(oringin_x, origin_y, e.getX(), e.getY());
			}else if(p.getCurrentTool() == Drawable.RECTANGLE){
				graphics.drawRect(oringin_x, origin_y, delta_x, delta_y);
			}else if(p.getCurrentTool() == Drawable.SQUARE){
				graphics.drawRect(oringin_x, origin_y, (delta_x/delta_x) * delta_y, (delta_x/delta_x) * delta_y);
			}else if(p.getCurrentTool() == Drawable.OVAL){
				graphics.drawOval(oringin_x,origin_y, delta_x, delta_y);
			}else if(p.getCurrentTool() == Drawable.CIRCLE){
				graphics.drawOval(oringin_x, origin_y, (delta_x/delta_x) * delta_y, (delta_x/delta_x) * delta_y);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
