package environments;

import com.google.common.base.Objects;
import environments.RoadConnection;
import environments.RoadSegmentData;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.TreeMapElementLayer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.primitive.RoadNetworkException;
import org.arakhne.afc.io.dbase.DBaseFileFilter;
import org.arakhne.afc.io.shape.ESRIBounds;
import org.arakhne.afc.io.shape.ShapeElementType;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.vmutil.FileSystem;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem, Thomas Gredin
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private ArrayList<RoadSegmentData> segments = new ArrayList<RoadSegmentData>();
  
  private ArrayList<RoadConnection> connections = new ArrayList<RoadConnection>();
  
  /**
   * Contains all elements loaded from the Shape file
   */
  private MapElementLayer<?> mapElements;
  
  @Pure
  public ArrayList<RoadSegmentData> getSegments() {
    return this.segments;
  }
  
  @Pure
  public ArrayList<RoadConnection> getConnections() {
    return this.connections;
  }
  
  /**
   * Get all elements loaded in the given Shape file, Shape file
   * must be loaded with the loadFromShapeFile method
   */
  @Pure
  public MapElementLayer<?> getMapElement() {
    return this.mapElements;
  }
  
  @Pure
  public void initMap() {
  }
  
  public MapElementLayer<?> loadShapeFile(final String filepath) {
    File file = new File(filepath);
    try {
      StandardRoadNetwork network = null;
      MapElementLayer<MapElement> layer = null;
      File dbfFile = FileSystem.replaceExtension(file, DBaseFileFilter.EXTENSION_DBASE_FILE);
      URL dbfUrl = null;
      boolean _canRead = dbfFile.canRead();
      if (_canRead) {
        dbfUrl = dbfFile.toURI().toURL();
      } else {
        dbfUrl = null;
      }
      try {
        FileInputStream is = new FileInputStream(file);
        class $AssertEvaluator$ {
          final boolean $$result;
          $AssertEvaluator$(final FileInputStream is) {
            this.$$result = (!Objects.equal(is, null));
          }
        }
        assert new $AssertEvaluator$(is).$$result;
        try {
          GISShapeFileReader reader = new GISShapeFileReader(is, null, dbfUrl);
          Rectangle2d worldRect = new Rectangle2d();
          ESRIBounds esriBounds = reader.getBoundsFromHeader();
          worldRect.setFromCorners(esriBounds.getMinX(), esriBounds.getMinY(), esriBounds.getMaxX(), esriBounds.getMaxY());
          ShapeElementType _shapeElementType = reader.getShapeElementType();
          boolean _equals = Objects.equal(_shapeElementType, ShapeElementType.POLYLINE);
          if (_equals) {
            reader.setMapElementType(RoadPolyline.class);
          }
          MapElement element = null;
          while ((!Objects.equal((element = reader.read()), null))) {
            if ((element instanceof RoadPolyline)) {
              boolean _equals_1 = Objects.equal(network, null);
              if (_equals_1) {
                StandardRoadNetwork _standardRoadNetwork = new StandardRoadNetwork(worldRect);
                network = _standardRoadNetwork;
              }
              RoadPolyline sgmt = ((RoadPolyline) element);
              try {
                network.addRoadSegment(sgmt);
              } catch (final Throwable _t) {
                if (_t instanceof RoadNetworkException) {
                  final RoadNetworkException e = (RoadNetworkException)_t;
                  throw new RuntimeException(e);
                } else {
                  throw Exceptions.sneakyThrow(_t);
                }
              }
            } else {
              boolean _equals_2 = Objects.equal(layer, null);
              if (_equals_2) {
                TreeMapElementLayer<MapElement> _treeMapElementLayer = new TreeMapElementLayer<MapElement>(worldRect);
                layer = _treeMapElementLayer;
              }
              try {
                layer.addMapElement(element);
              } catch (final Throwable _t_1) {
                if (_t_1 instanceof RoadNetworkException) {
                  final RoadNetworkException e_1 = (RoadNetworkException)_t_1;
                  throw new RuntimeException(e_1);
                } else {
                  throw Exceptions.sneakyThrow(_t_1);
                }
              }
            }
          }
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception exc = (Exception)_t;
            InputOutput.<String>println(exc.toString());
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      } catch (final Throwable _t_1) {
        if (_t_1 instanceof Exception) {
          final Exception ex = (Exception)_t_1;
          InputOutput.<String>println(ex.toString());
        } else {
          throw Exceptions.sneakyThrow(_t_1);
        }
      }
      boolean _notEquals = (!Objects.equal(network, null));
      if (_notEquals) {
        RoadNetworkLayer networkLayer = new RoadNetworkLayer(network);
        this.mapElements = networkLayer;
        return networkLayer;
      }
      this.mapElements = layer;
      return layer;
    } catch (final Throwable _t_2) {
      if (_t_2 instanceof IOException) {
        final IOException exception = (IOException)_t_2;
        throw new IOError(exception);
      } else {
        throw Exceptions.sneakyThrow(_t_2);
      }
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe return type is incompatible with equals(Object). Current method has the return type: void. The super method has the return type: boolean."
      + "\nThe return type is incompatible with equals(Object). Current method has the return type: void. The super method has the return type: boolean.");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe return type is incompatible with equals(Object). Current method has the return type: void. The super method has the return type: boolean.");
  }
  
  @SyntheticMember
  public RoadNetwork() {
    super();
  }
}
