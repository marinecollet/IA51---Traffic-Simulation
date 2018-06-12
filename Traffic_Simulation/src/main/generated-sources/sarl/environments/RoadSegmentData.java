package environments;

import environments.EnvironmentObject;
import framework.environment.AgentBody;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadSegmentData {
  /**
   * Targeted segment
   */
  private RoadSegment segment;
  
  /**
   * EnvironmentObject located at the start of the segment
   */
  private EnvironmentObject objectAtStart;
  
  /**
   * EnvironmentObject located at the end of the segment
   */
  private EnvironmentObject objectAtEnd;
  
  /**
   * Agent bodies that are present on the segment
   */
  private HashSet<AgentBody> bodies;
  
  public RoadSegmentData(final RoadSegment segment) {
    this.segment = segment;
  }
  
  @Pure
  public EnvironmentObject getObjectAtStart() {
    return this.objectAtStart;
  }
  
  @Pure
  public EnvironmentObject getObjectAtEnd() {
    return this.objectAtEnd;
  }
  
  @Pure
  public RoadSegment getSegment() {
    return this.segment;
  }
  
  public void setObjectAtStart(final EnvironmentObject object) {
    this.objectAtStart = object;
  }
  
  public void setObjectAtEnd(final EnvironmentObject object) {
    this.objectAtEnd = object;
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
