package environments;

import com.google.common.base.Objects;
import environments.RoadConnection;
import environments.RoadSegment;
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
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private final ArrayList<RoadSegment> segments = new ArrayList<RoadSegment>();
  
  private final ArrayList<RoadConnection> connections = new ArrayList<RoadConnection>();
  
  public MapElementLayer<?> LoadShapeFile() {
    try {
      File shapefile = new File("asset/Belfort.shp");
      StandardRoadNetwork network = null;
      MapElementLayer<MapElement> layer = null;
      final File dbfFile = FileSystem.replaceExtension(shapefile, DBaseFileFilter.EXTENSION_DBASE_FILE);
      URL dbfUrl = null;
      boolean _canRead = dbfFile.canRead();
      if (_canRead) {
        dbfUrl = dbfFile.toURI().toURL();
      } else {
        dbfUrl = null;
      }
      FileInputStream is = null;
      try {
        FileInputStream _fileInputStream = new FileInputStream(shapefile);
        is = _fileInputStream;
        class $AssertEvaluator$ {
          final boolean $$result;
          $AssertEvaluator$(final FileInputStream is) {
            this.$$result = (!Objects.equal(is, null));
          }
        }
        assert new $AssertEvaluator$(is).$$result;
        GISShapeFileReader reader = null;
        try {
          GISShapeFileReader _gISShapeFileReader = new GISShapeFileReader(is, null, dbfUrl);
          reader = _gISShapeFileReader;
          final Rectangle2d worldRect = new Rectangle2d();
          final ESRIBounds esriBounds = reader.getBoundsFromHeader();
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
              final RoadPolyline sgmt = ((RoadPolyline) element);
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
          if (_t instanceof IOException) {
            final IOException ex = (IOException)_t;
            throw new IOException(ex);
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        boolean _notEquals = (!Objects.equal(network, null));
        if (_notEquals) {
          final RoadNetworkLayer networkLayer = new RoadNetworkLayer(network);
          return networkLayer;
        }
        return layer;
      } catch (final Throwable _t_1) {
        if (_t_1 instanceof IOException) {
          final IOException exception = (IOException)_t_1;
          throw new IOError(exception);
        } else {
          throw Exceptions.sneakyThrow(_t_1);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @SyntheticMember
  public RoadNetwork() {
    super();
  }
}
