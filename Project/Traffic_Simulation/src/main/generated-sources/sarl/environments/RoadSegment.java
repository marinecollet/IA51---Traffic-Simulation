package environments;

import environments.RoadConnection;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadSegment {
  private final RoadConnection start;
  
  private final RoadConnection end;
  
  public RoadSegment() {
    RoadConnection _roadConnection = new RoadConnection();
    this.start = _roadConnection;
    RoadConnection _roadConnection_1 = new RoadConnection();
    this.end = _roadConnection_1;
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
