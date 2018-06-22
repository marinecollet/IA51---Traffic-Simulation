package environments;

import environments.RoadSegmentData;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.math.geometry.d2.Point2D;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas Gredin
 * 
 * @description
 * This class store a collection of RoadSegmentData and offer multiple
 * way to sort and get RoadSegmentData using different criteria.
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadSegmentDataCollection {
  private HashSet<RoadSegmentData> collection;
  
  public RoadSegmentDataCollection() {
    HashSet<RoadSegmentData> _hashSet = new HashSet<RoadSegmentData>();
    this.collection = _hashSet;
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Add a RoadSegmentData instance to the collection.
   */
  public boolean add(final RoadSegmentData roadSegmentData) {
    return this.collection.add(roadSegmentData);
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * remove a RoadSegmentData instance from the collection.
   */
  public boolean remove(final RoadSegmentData roadSegmentData) {
    boolean _remove = this.collection.remove(roadSegmentData);
    if (_remove) {
      return true;
    }
    return false;
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Return all RoadSegmentData that are a segment that is connected to the
   * given road connection. Just need the Point2D instance of the connection.
   */
  @Pure
  public HashSet<RoadSegmentData> findRoadSegmentsForConnection(final Point2D connection) {
    HashSet<RoadSegmentData> segments = new HashSet<RoadSegmentData>();
    for (final RoadSegmentData sgmt : this.collection) {
      Iterable<Point2d> _points = sgmt.getSegment().points();
      for (final Point2d point : _points) {
        if ((point == connection)) {
          segments.add(sgmt);
          continue;
        }
      }
    }
    return segments;
  }
  
  @Pure
  public HashSet<RoadSegmentData> findRoadSegmentsForConnection(final RoadConnection connection) {
    HashSet<RoadSegmentData> segments = new HashSet<RoadSegmentData>();
    for (final RoadSegmentData sgmt : this.collection) {
      Iterable<Point2d> _points = sgmt.getSegment().points();
      for (final Point2d point : _points) {
        Point2d _point = connection.getPoint();
        boolean _tripleEquals = (point == _point);
        if (_tripleEquals) {
          segments.add(sgmt);
          continue;
        }
      }
    }
    return segments;
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
}
