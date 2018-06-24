package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.mapelement.GISElementContainer;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;
import org.arakhne.afc.gis.maplayer.GISLayerContainer;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.MapLayer;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.maplayer.TreeMapElementLayer;
import org.arakhne.afc.gis.primitive.FlagContainer;
import org.arakhne.afc.gis.primitive.GISContainer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.primitive.RoadNetworkException;
import org.arakhne.afc.gis.ui.GisPane;
import org.arakhne.afc.io.dbase.DBaseFileFilter;
import org.arakhne.afc.io.shape.ESRIBounds;
import org.arakhne.afc.io.shape.ShapeElementType;
import org.arakhne.afc.io.shape.ShapeFileFilter;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.text.TextUtil;
import org.arakhne.afc.vmutil.FileSystem;
import org.arakhne.afc.vmutil.json.JsonBuffer;
import org.arakhne.afc.vmutil.locale.Locale;

/*
 * JavaFX Applicationof our map
 */
public class ApplicationMap extends Application {

	private volatile boolean dragging;

	private volatile MapElement selectedRoad;
	
	//Contains car's body
	public CarLayer agentBodyLayer = new CarLayer();
	
	//Contains traffic light's body
	public TrafficLightLayer flashlightLayer = new TrafficLightLayer();

	//Contains stop's body
	public ArrayMapElementLayer<MapPolygon> stopLayer = new ArrayMapElementLayer<MapPolygon>();
	
	//Contains the road network
	public MapElementLayer<?>  roadNetworkLayer; 
	
	//Singleton pattern
	static ApplicationMap instance;
	
	//Used has a zoomable pane
	public GisPane scrollPane;
	
	//Is the ApplicationMap ready
	static boolean isReady = false;

	public ApplicationMap() {
		instance = this;
	}
	
	/**
	 * Initialize the ApplicationMap by loading a shape file 
	 */
	public void init() {
		roadNetworkLayer = ApplicationMap.loadShapeFile(new File("asset/Ville.shp"));
	}
	
	/**
	 * Update the application 
	 */
	public void update() {
		agentBodyLayer.update();
		flashlightLayer.update();
		if(scrollPane != null) {
			scrollPane.drawContent();
		}
	}
	
	/**
	 * Load a shape file
	 */
	public static MapElementLayer<?> loadShapeFile(File file) {
		try {
			StandardRoadNetwork network = null;
			MapElementLayer<MapElement> layer = null;
			
			final File dbfFile = FileSystem.replaceExtension(file, DBaseFileFilter.EXTENSION_DBASE_FILE);
			final URL dbfUrl;
			if (dbfFile.canRead()) {
				dbfUrl = dbfFile.toURI().toURL();
			} else {
				dbfUrl = null;
			}

			try (InputStream is = new FileInputStream(file)) {
				assert is != null;
				try (GISShapeFileReader reader = new GISShapeFileReader(is, null, dbfUrl)) {
					final Rectangle2d worldRect = new Rectangle2d();
					final ESRIBounds esriBounds = reader.getBoundsFromHeader();
					worldRect.setFromCorners(
							esriBounds.getMinX(),
							esriBounds.getMinY(),
							esriBounds.getMaxX(),
							esriBounds.getMaxY());

					if (reader.getShapeElementType() == ShapeElementType.POLYLINE) {
						reader.setMapElementType(RoadPolyline.class);
					}

					MapElement element;
					
					while ((element = reader.read()) != null) {
						if (element instanceof RoadPolyline) {
							if (network == null) {
								network = new StandardRoadNetwork(worldRect);
							}
							final RoadPolyline sgmt = (RoadPolyline) element;
							try {
								network.addRoadSegment(sgmt);
							} catch (RoadNetworkException e) {
								throw new RuntimeException(e);
							}
						} else {
							if (layer == null) {
								layer = new TreeMapElementLayer<>(worldRect);
							}
							try {
								layer.addMapElement(element);
							} catch (RoadNetworkException e) {
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
			if (network != null) {
				final RoadNetworkLayer networkLayer = new RoadNetworkLayer(network);
				return networkLayer;
			}
			return layer;
		} catch (IOException exception) {
			throw new IOError(exception);
		}
	}

	/**
	 * Start and display the window
	 */
	@Override
	@SuppressWarnings({"checkstyle:magicnumber", "checkstyle:regexp", "checkstyle:npathcomplexity",
		"checkstyle:nestedifdepth", "rawtypes", "unchecked"})
	public void start(Stage primaryStage) 
	{	
		List<File> shapeFiles = new ArrayList<File>();
		shapeFiles.add(new File("asset/Ville.shp"));

		if (shapeFiles != null && !shapeFiles.isEmpty()) 
		{
			final List<MapElementLayer> containers = new ArrayList<>();
			final StringBuilder filename = new StringBuilder();
			
			for (final File shapeFile : shapeFiles) 
			{
				// Load the shape file
				final MapElementLayer loadedResource = loadShapeFile(shapeFile);
				if (loadedResource != null) 
				{
					containers.add(loadedResource);
					
					if (filename.length() > 0) 
					{
						filename.append(", "); //$NON-NLS-1$
					}
					filename.append(shapeFile.getName());
				}
			}

			if (containers.isEmpty()) 
			{
				System.exit(0);
			}
			
			//Add the different others layers
			containers.add(agentBodyLayer);
			containers.add(stopLayer);
			containers.add(flashlightLayer);
			
			
			final GISContainer container;
			if (containers.size() == 1) 
			{
				container = containers.get(0);
			} 
			else 
			{
				final MultiMapLayer layer = new MultiMapLayer<>();
				for (final MapLayer child : containers) 
				{
					layer.addMapLayer(child);
				}
				container = layer;
			}

			
			final BorderPane root = new BorderPane();
			scrollPane = new GisPane(container);
			root.setCenter(scrollPane);

			final Scene scene = new Scene(root, 1024, 768);
			primaryStage.setTitle(Locale.getString(ApplicationMap.class, "IA51 - Road simulation", filename.toString())); //$NON-NLS-1$
			primaryStage.setScene(scene);
			isReady = true;

			primaryStage.show();
		} else {
			primaryStage.close();
			System.exit(0);
		}
	}

	/** Replies the element at the given mouse position.
	 *
	 * @param pane the element pane.
	 * @param x the x position of the mouse.
	 * @param y the y position of the mouse.
	 * @return the element.
	 * @since 15.0
	 */
	@SuppressWarnings({ "rawtypes" })
	public MapElement getElementUnderMouse(GisPane<?> pane, double x, double y) {
		final GISContainer model = pane.getDocumentModel();
		final Point2d mousePosition = pane.toDocumentPosition(x, y);
		final Rectangle2d selectionArea = pane.toDocumentRect(x - 2, y - 2, 5, 5);
		return getElementUnderMouse(model, mousePosition, selectionArea);
	}

	@SuppressWarnings({ "rawtypes" })
	private MapElement getElementUnderMouse(Object model, Point2d mousePosition, Rectangle2d selectionArea) {
		if (model instanceof GISElementContainer<?>) {
			return getElementUnderMouse((GISElementContainer) model, mousePosition, selectionArea);
		}
		if (model instanceof GISLayerContainer<?>) {
			return getElementUnderMouse((GISLayerContainer) model, mousePosition, selectionArea);
		}
		return null;
	}

	@SuppressWarnings({ "static-method" })
	private MapElement getElementUnderMouse(GISElementContainer<?> model, Point2d mousePosition, Rectangle2d selectionArea) {
		final Iterator<? extends MapElement> iterator = model.iterator(selectionArea);
		double dist = Double.MAX_VALUE;
		MapElement select = null;
		while (iterator.hasNext()) {
			final MapElement road = iterator.next();
			final double distance = Math.abs(road.getDistance(mousePosition));
			if (distance < dist) {
				dist = distance;
				select = road;
			}
		}
		return select;
	}

	private MapElement getElementUnderMouse(GISLayerContainer<?> model, Point2d mousePosition, Rectangle2d selectionArea) {
		final Iterator<? extends MapLayer> iterator = model.iterator();
		while (iterator.hasNext()) {
			final MapLayer layer = iterator.next();
			if (layer.isVisible() && layer.isClickable()) {
				final MapElement selected = getElementUnderMouse(layer, mousePosition, selectionArea);
				if (selected != null) {
					return selected;
				}
			}
		}
		return null;
	}

	/**
	 * Add a stop in his corresponding layer
	 */
	public void addStopInLayer(MapPolygon element) 
	{
		stopLayer.addMapElement(element);
	}

	/**
	 * Remove a stop in his corresponding layer
	 */
	public void removeStopInLayer(MapPolygon element) 
	{
		stopLayer.removeMapElement(element);
	}

	/**
	 * Is the application ready?
	 */
	public boolean getIsReady()
	{
		return isReady;
	}
	
	/**
	 * Get the road network layer
	 */
	public MapElementLayer<?> getRoadNetworkLayer(){
		return roadNetworkLayer;
	}
	
	/**
	 * @author Thomas Gredin
	 * 
	 * @description
	 * Get the singleton instance of the application
	 */
	public static ApplicationMap getInstance()
	{
		if(instance == null)
			instance = new ApplicationMap();
		return instance;
	}
	

}