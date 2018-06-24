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
 * Ask the environment for traffic lights in a crossroads
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class AskForLinkedFlashLights extends Event {
  /**
   * UUID of the traffic light controler sender
   */
  public final UUID ID;
  
  public AskForLinkedFlashLights(final UUID id) {
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
    AskForLinkedFlashLights other = (AskForLinkedFlashLights) obj;
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
   * Returns a String representation of the AskForLinkedFlashLights event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("ID", this.ID);
  }
}
