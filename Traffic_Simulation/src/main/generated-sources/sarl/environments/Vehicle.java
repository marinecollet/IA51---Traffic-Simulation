package environments;

import environments.CarFrustum;
import framework.environment.AgentBody;
import framework.math.Point2f;
import framework.math.Rectangle2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Vehicle extends AgentBody {
  protected float perceptionDistance;
  
  public Vehicle(final Point2f point, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration, final UUID uuid) {
    super(uuid, 
      new Rectangle2f(new Point2f(point.getX(), (point.getY() - 0.5f)), new Point2f(point.getX(), (point.getY() + 0.5f))), maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration, 
      new CarFrustum(uuid));
  }
  
  public abstract void moveVehicle(final Point2f newPos);
  
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
    Vehicle other = (Vehicle) obj;
    if (Float.floatToIntBits(other.perceptionDistance) != Float.floatToIntBits(this.perceptionDistance))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.perceptionDistance);
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public Vehicle clone() {
    try {
      return (Vehicle) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -1723241170L;
}
