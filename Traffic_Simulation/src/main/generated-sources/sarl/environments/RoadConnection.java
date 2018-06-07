package environments;

import environments.RoadSegment;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadConnection {
  private Point2f position;
  
  private ArrayList<RoadSegment> onSegments;
  
  public RoadConnection(final Point2f pos) {
    this.position = pos;
    ArrayList<RoadSegment> _arrayList = new ArrayList<RoadSegment>();
    this.onSegments = _arrayList;
  }
  
  @Pure
  public Point2f getPosition() {
    return this.position;
  }
  
  public boolean addConnectionOnSegment(final RoadSegment seg) {
    return this.onSegments.add(seg);
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
