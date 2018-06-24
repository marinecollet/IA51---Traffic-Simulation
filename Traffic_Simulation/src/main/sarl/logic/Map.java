package logic;

public class Map {

	/*
	 * Width of the map
	 */
	public static final int WIDTH = 1280;
	
	/*
	 * Height of the map
	 */
	public static final int HEIGHT = 720;
	
	/*
	 * Singletion pattern
	 */
	private static Map INSTANCE;
	
	private Map() {	}
	
	public static Map getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}
}
