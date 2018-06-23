package ui;

import java.util.ArrayList;
import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;

import environments.Car;
import framework.environment.AgentBody;
import javafx.scene.paint.Color;

public class CarLayer extends ArrayMapElementLayer<MapElement>{

	private Iterable<AgentBody> carList;

	
	
	/** Constructor for CarLayer. carList is the collection which contains the cars which need to be rendered.
	 * 
	 * @param carList
	 */
	public CarLayer() {
		super();
		this.carList = new ArrayList<>();
	}
	
	
	/**
	 * Update method for the CarLayer. Intended to be used at each frame. 
	 * 
	 * For each Car in CarList, it will add a circle on the layer.
	 */
	public void update() {
		this.removeAllMapElements();
		for(AgentBody c : carList) {
			
			
			MapPolygon poly = new MapPolygon();
			poly.addPoint(c.getPosition().getX(),c.getPosition().getY());
			poly.addPoint(c.getPosition().getX(),c.getPosition().getY()-10);
			poly.addPoint(c.getPosition().getX()-10,c.getPosition().getY());
			Color carColor = Color.BLUEVIOLET;
			poly.setColor(getIntFromColor(carColor.getRed(), carColor.getGreen(), carColor.getBlue()));
			// TODO : check filters
			MapCircle mapCircle = new MapCircle(c.getPosition().getX(),c.getPosition().getY(), 3);
			//Color carColor = Color.YELLOW;
			mapCircle.setColor(getIntFromColor(carColor.getRed(), carColor.getGreen(), carColor.getBlue()));
			this.addMapElement(poly);	
		}
	}
	
	public void setList(Iterable<AgentBody> list) {
		carList = list;
	}
	
	private int getIntFromColor(double Red, double Green, double Blue){
	    int R = (int) Math.round(255 * Red);
	    int G = (int) Math.round(255 * Green);
	    int B = (int) Math.round(255 * Blue);

	    R = (R << 16) & 0x00FF0000;
	    G = (G << 8) & 0x0000FF00;
	    B = B & 0x000000FF;

	    return 0xFF000000 | R | G | B;
	}
}
