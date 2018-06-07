package environments;

import environments.Vehicle;
import framework.math.Point2f;
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
public class Car extends Vehicle {
  private Point2f position;
  
  public Car(final Point2f point, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration) {
    super(point, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration);
    this.position = point;
  }
  
  @Override
  public void moveVehicle(final Point2f newPos) {
    this.position = newPos;
  }
  
  public Point2f getPosition() {
    return this.position;
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
  
  @Override
  @Pure
  @SyntheticMember
  public Car clone() {
    try {
      return (Car) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2920673959L;
}
