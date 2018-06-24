package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Inform the environment that the agent has reached his final point
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class DestinationReached extends Event {
  public final UUID ID;
  
  public DestinationReached(final UUID id) {
    this.ID = id;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DestinationReached other = (DestinationReached) obj;
    if (!Objects.equals(this.ID, other.ID)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.ID);
    return result;
  }
  
  /**
   * Returns a String representation of the DestinationReached event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("ID", this.ID);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2702576416L;
}
