package ui;

import java.util.ArrayList;

import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;

import environments.TrafficLight;
import framework.environment.AgentBody;
import javafx.scene.paint.Color;

public class TrafficLightLayer extends ArrayMapElementLayer<MapElement> {

	private Iterable<TrafficLight> trafficLightList;

	
	
	/** Constructor for CarLayer. carList is the collection which contains the cars which need to be rendered.
	 * 
	 * @param carList
	 */
	public TrafficLightLayer() {
		super();
		this.trafficLightList = new ArrayList<>();
	}
	
	public void update() {
		this.removeAllMapElements();
		for(TrafficLight tl : trafficLightList) {
			
			MapPolygon element = new MapPolygon();
			
			for (int i = 0; i < 16; i++) {
				element.addPoint(tl.getPosition().getX() + Math.cos(2 * Math.PI / 16 * i + Math.PI / 16) * 2,
					tl.getPosition().getY() + Math.sin(2 * Math.PI / 16 * i + Math.PI / 16) * 2);
			}
			
			Color trafficColor;
			switch(tl.getState()) {
			case GREEN:
				trafficColor = Color.GREENYELLOW;
				break;
			case ORANGE:
				trafficColor = Color.ORANGE;
				break;
			default:
				trafficColor = Color.INDIANRED;
				break;
			}
			element.setColor(getIntFromColor(trafficColor.getRed(), trafficColor.getGreen(), trafficColor.getBlue()));
			
			this.addMapElement(element);	
		}
	}
	
	public void setList(Iterable<TrafficLight> list) {
		trafficLightList = list;
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
