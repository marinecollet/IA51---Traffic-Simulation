package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class AskChangementFlashLight extends Event {
  public double deltaTime;
  
  public AskChangementFlashLight(final double deltaTime) {
    this.deltaTime = deltaTime;
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
    AskChangementFlashLight other = (AskChangementFlashLight) obj;
    if (Double.doubleToLongBits(other.deltaTime) != Double.doubleToLongBits(this.deltaTime))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.deltaTime) ^ (Double.doubleToLongBits(this.deltaTime) >>> 32));
    return result;
  }
  
  /**
   * Returns a String representation of the AskChangementFlashLight event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("deltaTime", this.deltaTime);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1160066922L;
}
