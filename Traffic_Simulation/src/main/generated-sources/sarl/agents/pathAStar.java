package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Send the A* path
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class pathAStar extends Event {
  public final RoadPath pathReturn;
  
  public pathAStar(final RoadPath p) {
    this.pathReturn = p;
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
  
  /**
   * Returns a String representation of the pathAStar event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("pathReturn", this.pathReturn);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -1801272198L;
}
