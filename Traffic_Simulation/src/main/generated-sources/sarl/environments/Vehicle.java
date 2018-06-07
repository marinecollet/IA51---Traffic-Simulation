package environments;

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
  public Vehicle(final Point2f point, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration) {
    super(
      UUID.randomUUID(), 
      new Rectangle2f(new Point2f(point.getX(), (point.getY() - 0.5f)), new Point2f(point.getX(), (point.getY() + 0.5f))), maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration, 
      null);
  }
  
  public abstract void moveVehicle(final Point2f newPos);
  
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
  private final static long serialVersionUID = -617805095L;
}
