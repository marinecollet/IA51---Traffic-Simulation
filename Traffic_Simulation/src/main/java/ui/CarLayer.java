package ui;

import java.util.ArrayList;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;

import environments.Car;
import environments.Vehicle;
import javafx.scene.paint.Color;

public class CarLayer extends ArrayMapElementLayer<MapPolygon>{

	
	private ArrayList<Vehicle> vehicles;

	public final int largeur = 4;
	public final int longueur = 8;
	
	public CarLayer() {
		this.vehicles = new ArrayList<Vehicle>();
	}
	
	public void update() {
		this.removeAllMapElements();
		for(Vehicle v : vehicles) {
			this.addMapElement(v.getElement());
		}
	}
	
	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
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
