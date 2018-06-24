package logic;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.arakhne.afc.gis.road.path.astar.RoadAStar;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Contains functions about path finding
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class PathUtils {
  /**
   * Get the A* path to a destination
   */
  @Pure
  public static ArrayList<RoadSegment> GPS(final Point2d startPoint, final Point2d endPoint, final RoadNetwork road) {
    RoadAStar AStar = new RoadAStar();
    RoadPath path = AStar.solve(startPoint, endPoint, road);
    ArrayList<RoadSegment> newPath = null;
    Object[] _array = path.toArray();
    ArrayList _arrayList = new ArrayList((Collection<?>)Conversions.doWrapArray(_array));
    newPath = _arrayList;
    return newPath;
  }
  
  @SyntheticMember
  public PathUtils() {
    super();
  }
}
