package environments;

import com.google.common.base.Objects;
import environments.RoadConnection;
import environments.RoadSegment;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import logic.Map;
import org.arakhne.afc.gis.coordinate.GeodesicPosition;
import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.location.GeoLocationPointList;
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
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.vmutil.FileSystem;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private ArrayList<RoadSegment> segments = new ArrayList<RoadSegment>();
  
  private ArrayList<RoadConnection> connections = new ArrayList<RoadConnection>();
  
  @Pure
  public ArrayList<RoadSegment> getSegments() {
    return this.segments;
  }
  
  @Pure
  public ArrayList<RoadConnection> getConnections() {
    return this.connections;
  }
  
  public MapElementLayer<?> LoadShapeFile(final String name) {
    try {
      File shapefile = new File(name);
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
              InputOutput.<Double>println(Double.valueOf(((RoadPolyline)element).getGeoLocation().toGeodesicPosition().getLatitude()));
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
  
  public String initMap() {
    String _xblockexpression = null;
    {
      MapElementLayer<?> objs = this.LoadShapeFile("asset/Ville.shp");
      for (final Object mapEl : objs) {
        {
          RoadPolyline tmp = ((RoadPolyline) mapEl);
          boolean _equals = Objects.equal(tmp, null);
          if (_equals) {
            continue;
          }
          ArrayList<RoadConnection> listCon = new ArrayList<RoadConnection>();
          Iterable<Point2d> _points = tmp.points();
          for (final Point2d pt : _points) {
            {
              double _x = pt.getX();
              double _y = pt.getY();
              GeodesicPosition geoP = new GeoLocationPointList(_x, _y).toGeodesicPosition();
              Point2f newPoint = new Point2f(((float) geoP.phi), ((float) geoP.lambda));
              RoadConnection con = new RoadConnection(newPoint);
              final Function1<RoadConnection, Boolean> _function = (RoadConnection el) -> {
                return Boolean.valueOf(((el.getPosition().getX() == newPoint.getX()) && (el.getPosition().getY() == newPoint.getY())));
              };
              boolean _exists = IterableExtensions.<RoadConnection>exists(this.connections, _function);
              boolean _not = (!_exists);
              if (_not) {
                this.connections.add(con);
              }
              listCon.add(con);
            }
          }
          RoadConnection _get = listCon.get(0);
          final ArrayList<RoadConnection> _converted_listCon = (ArrayList<RoadConnection>)listCon;
          int _length = ((Object[])Conversions.unwrapArray(_converted_listCon, Object.class)).length;
          int _minus = (_length - 1);
          RoadConnection _get_1 = listCon.get(_minus);
          RoadSegment rs = new RoadSegment(_get, _get_1);
          final Function1<RoadSegment, Boolean> _function = (RoadSegment el) -> {
            return Boolean.valueOf(((Objects.equal(el.getStart().getPosition(), rs.getStart().getPosition()) && Objects.equal(el.getEnd().getPosition(), rs.getEnd().getPosition())) || (Objects.equal(el.getStart().getPosition(), rs.getEnd().getPosition()) && Objects.equal(el.getEnd().getPosition(), rs.getStart().getPosition()))));
          };
          boolean _exists = IterableExtensions.<RoadSegment>exists(this.segments, _function);
          boolean _not = (!_exists);
          if (_not) {
            this.segments.add(rs);
          }
        }
      }
      int _length = ((Object[])Conversions.unwrapArray(this.segments, Object.class)).length;
      String _plus = (Integer.valueOf(_length) + " segments ont été ajoutés depuis le shapefile");
      InputOutput.<String>println(_plus);
      int _length_1 = ((Object[])Conversions.unwrapArray(this.connections, Object.class)).length;
      String _plus_1 = (Integer.valueOf(_length_1) + " connections ont été ajoutés depuis le shapefile");
      _xblockexpression = InputOutput.<String>println(_plus_1);
    }
    return _xblockexpression;
  }
  
  /**
   * XY from lat lng
   */
  @Pure
  public Point2f getXY(final double lat, final double lng) {
    float screenX = ((float) ((lng + 180) * (Map.WIDTH / 360)));
    float screenY = ((float) (((lat * (-1)) + 90) * (Map.HEIGHT / 180)));
    return new Point2f(screenX, screenY);
  }
  
  public boolean createTestMap() {
    boolean _xblockexpression = false;
    {
      Point2f _point2f = new Point2f((Map.WIDTH / 2), (Map.HEIGHT / 2));
      RoadConnection milieu = new RoadConnection(_point2f);
      Point2f _point2f_1 = new Point2f((Map.WIDTH / 2), ((Map.HEIGHT / 2) + 100));
      RoadConnection haut = new RoadConnection(_point2f_1);
      Point2f _point2f_2 = new Point2f((Map.WIDTH / 2), ((Map.HEIGHT / 2) - 100));
      RoadConnection bas = new RoadConnection(_point2f_2);
      Point2f _point2f_3 = new Point2f(((Map.WIDTH / 2) - 100), (Map.HEIGHT / 2));
      RoadConnection gauche = new RoadConnection(_point2f_3);
      Point2f _point2f_4 = new Point2f(((Map.WIDTH / 2) + 100), (Map.HEIGHT / 2));
      RoadConnection droite = new RoadConnection(_point2f_4);
      this.connections.add(milieu);
      this.connections.add(haut);
      this.connections.add(bas);
      this.connections.add(gauche);
      this.connections.add(droite);
      RoadSegment segH = new RoadSegment(milieu, haut);
      RoadSegment segB = new RoadSegment(milieu, bas);
      RoadSegment segG = new RoadSegment(milieu, gauche);
      RoadSegment segD = new RoadSegment(milieu, droite);
      this.segments.add(segH);
      this.segments.add(segB);
      this.segments.add(segG);
      _xblockexpression = this.segments.add(segD);
    }
    return _xblockexpression;
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
