package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class GiveLinkedFlashLights extends Event {
  public UUID top;
  
  public UUID bottom;
  
  public UUID left;
  
  public UUID right;
  
  public GiveLinkedFlashLights(final UUID top, final UUID bottom, final UUID left, final UUID right) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
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
    GiveLinkedFlashLights other = (GiveLinkedFlashLights) obj;
    if (!Objects.equals(this.top, other.top)) {
      return false;
    }
    if (!Objects.equals(this.bottom, other.bottom)) {
      return false;
    }
    if (!Objects.equals(this.left, other.left)) {
      return false;
    }
    if (!Objects.equals(this.right, other.right)) {
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
    result = prime * result + Objects.hashCode(this.top);
    result = prime * result + Objects.hashCode(this.bottom);
    result = prime * result + Objects.hashCode(this.left);
    result = prime * result + Objects.hashCode(this.right);
    return result;
  }
  
  /**
   * Returns a String representation of the GiveLinkedFlashLights event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("top", this.top);
    builder.add("bottom", this.bottom);
    builder.add("left", this.left);
    builder.add("right", this.right);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -764033070L;
}
