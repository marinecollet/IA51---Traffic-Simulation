package environments;

import com.google.common.base.Objects;
import environments.EnvironmentObject;
import framework.environment.AgentBody;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import java.util.UUID;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
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
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Return the collection of all agent bodies that are on the Road Segment.
   */
  @Pure
  public HashSet<AgentBody> getAgentBodies() {
    return this.bodies;
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Add an agent body to the Road Segment but before it check if this agent is not already
   * on it with its UUID.
   */
  public boolean appendAgentBody(final AgentBody body) {
    final Function1<AgentBody, Boolean> _function = (AgentBody el) -> {
      UUID _iD = el.getID();
      UUID _iD_1 = body.getID();
      return Boolean.valueOf(Objects.equal(_iD, _iD_1));
    };
    AgentBody _findFirst = IterableExtensions.<AgentBody>findFirst(this.bodies, _function);
    boolean _notEquals = (!Objects.equal(_findFirst, null));
    if (_notEquals) {
      return false;
    }
    this.bodies.add(body);
    return true;
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
