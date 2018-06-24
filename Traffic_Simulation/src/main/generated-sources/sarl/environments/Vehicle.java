package environments;

import environments.CarFrustum;
import framework.environment.AgentBody;
import framework.math.Circle2f;
import framework.math.Point2f;
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
  public Vehicle(final Point2f point, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration, final UUID uuid, final float perceptionDistance) {
    super(uuid, 
      new Circle2f(point, perceptionDistance), maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration, 
      new CarFrustum(uuid), perceptionDistance);
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
  private final static long serialVersionUID = 480096728L;
}
