package environments;

import environments.RoadSegment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadConnection {
  private Vector2f position;
  
  private ArrayList<RoadSegment> onSegments;
  
  public RoadConnection(final Vector2f pos) {
    this.position = pos;
    ArrayList<RoadSegment> _arrayList = new ArrayList<RoadSegment>();
    this.onSegments = _arrayList;
  }
  
  @Pure
  public Vector2f getPosition() {
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
