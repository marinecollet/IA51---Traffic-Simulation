package environments;

import environments.RoadConnection;
import environments.RoadSegment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadPolyline extends RoadSegment {
  public RoadPolyline(final RoadConnection start, final RoadConnection end) {
    super(start, end);
  }
}
