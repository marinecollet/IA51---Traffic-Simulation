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

public class ApplicationMap extends Application {

	private volatile boolean dragging;

	private volatile MapElement selectedRoad;

	public CarLayer agentBodyLayer = new CarLayer();
	//public ArrayMapElementLayer<MapPolygon> agentBodyLayer = new ArrayMapElementLayer<MapPolygon>();
	public ArrayMapElementLayer<MapPolygon> stopLayer = new ArrayMapElementLayer<MapPolygon>();
	public ArrayMapElementLayer<MapPolygon> flashlightLayer = new ArrayMapElementLayer<MapPolygon>();
	static ApplicationMap instance;
	public MapElementLayer<?>  roadNetworkLayer; 
	public GisPane scrollPane;
	static boolean isReady = false;

	public ApplicationMap() {
		instance = this;
	}
	
	public void init() {
		roadNetworkLayer = ApplicationMap.loadShapeFile(new File("asset/Ville.shp"));
	}
	
	public void update() {
		agentBodyLayer.update();
		if(scrollPane != null) {
			scrollPane.drawContent();
		}
	}
	
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

			final Label messageBar = new Label("");
			messageBar.setTextAlignment(TextAlignment.CENTER);

			scrollPane = new GisPane(container);

			final String mouseLocationPattern = Locale.getString(ApplicationMap.class, "MOUSE_POSITION"); //$NON-NLS-1$

			/*
			scrollPane.setOnMouseMoved(event -> {
				final Point2d mousePosition = scrollPane.toDocumentPosition(event.getX(), event.getY());
				messageBar.setText(MessageFormat.format(mouseLocationPattern,
						TextUtil.formatDouble(event.getX(), 1),
						TextUtil.formatDouble(event.getY(), 1),
						TextUtil.formatDouble(mousePosition.getX(), 4),
						TextUtil.formatDouble(mousePosition.getY(), 4)));
			});

			scrollPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
				this.dragging = true;
				final Point2d mousePosition = scrollPane.toDocumentPosition(event.getX(), event.getY());
				messageBar.setText(MessageFormat.format(mouseLocationPattern,
						TextUtil.formatDouble(event.getX(), 1),
						TextUtil.formatDouble(event.getY(), 1),
						TextUtil.formatDouble(mousePosition.getX(), 4),
						TextUtil.formatDouble(mousePosition.getY(), 4)));
			});

			scrollPane.setOnMouseReleased(event -> {
				if (!this.dragging) {
					final MapElement select1 = this.selectedRoad;
					this.selectedRoad = null;
					if (select1 != null) {
						select1.unsetFlag(FlagContainer.FLAG_SELECTED);
					}
					final MapElement select2 = getElementUnderMouse(scrollPane, event.getX(), event.getY());
					if (select2 != select1) {
						if (select2 != null) {
							select2.setFlag(FlagContainer.FLAG_SELECTED);
							this.selectedRoad = select2;
							if (event.isControlDown()) {
								// Force the loading of all the attributes.
								select2.getAllAttributeNames();
							}
							System.out.println(JsonBuffer.toString(select2));
						}
						scrollPane.drawContent();
						event.consume();
					}
				}
				this.dragging = false;
			});
			 */
			root.setCenter(scrollPane);
			root.setBottom(messageBar);

			final Scene scene = new Scene(root, 1024, 768);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //$NON-NLS-1$

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
	 * @author Thomas Gredin
	 * 
	 * @description
	 * Add an element to draw into the map elements layer of the
	 * window.
	 */
	public void addAgentBodyInLayer(MapPolygon element)
	{
		agentBodyLayer.addMapElement(element);
	}
	
	/**
	 * @author Thomas Gredin
	 * 
	 * @description
	 * Remove given map element from the map elements layer.
	 */
	public void removeAgentBodyInLayer(MapPolygon element)
	{
		agentBodyLayer.removeMapElement(element);
	}

	public void addStopInLayer(MapPolygon element) 
	{
		stopLayer.addMapElement(element);
	}

	public void removeStopInLayer(MapPolygon element) 
	{
		stopLayer.removeMapElement(element);
	}

	public void addFlashlightInLayer(MapPolygon element) 
	{
		flashlightLayer.addMapElement(element);
	}

	public void removeFlashlightInLayer(MapPolygon element) 
	{
		flashlightLayer.removeMapElement(element);
	}
	
	public boolean getIsReady()
	{
		return isReady;
	}
	
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
	
	
	/** Main program.
	 *
	 * @param args the command line arguments.
	 */
	/*public static void main(String[] args) {
		launch();
	}*/

}