package logic;

public class Map {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int SIMULATIONSPEED_MAX = 5; // valeur maximale du gamespeed
	private static Map INSTANCE;

	public int simulationSpeed;
	/**
	 * Point d'accès pour l'instance unique du singleton
	 * 
	 * @return l'instance unique du singleton
	 */
	
	private Map() {
		simulationSpeed = 1;
	}
	public static Map getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}
}
