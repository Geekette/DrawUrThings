package fr.drawurthings.graphics.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import fr.drawurthings.graphics.draw.DrawPopup;
import fr.drawurthings.model.Paint;

/**
 * Listener de la fenêtre de liste de layers.
 * 
 * @author Alexandre Canny, Valentin Ramecourt, Théo Plockyn
 *
 */
public class FigureListListener implements MouseListener {

	private final Paint model;
	private int indice = -1;

	public FigureListListener(Paint p){
		this.model = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3 && model.getWorkingLayer()>-1){
			new DrawPopup(model, model.getDrawables().get(model.getWorkingLayer())).show(e.getComponent(), e.getX(), e.getY());;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		@SuppressWarnings("rawtypes")
		JList sourceList = (JList)e.getComponent();
		if(e.getButton() == MouseEvent.BUTTON3){
			sourceList.setSelectedIndex(sourceList.locationToIndex(e.getPoint()));
		}
		this.indice = sourceList.getSelectedIndex();
		model.setWorkingLayer(this.indice);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
