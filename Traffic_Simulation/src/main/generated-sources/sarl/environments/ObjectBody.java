package environments;

import framework.environment.AbstractMobileObject;
import framework.math.Shape2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Broutix
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class ObjectBody extends AbstractMobileObject {
  @Override
  @Pure
  @SyntheticMember
  public ObjectBody clone() {
    try {
      return (ObjectBody) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  /**
   * @param id the identifier of the object.
   * @param shape the shape of the body, considering that it is centered at the (0,0) position.
   * @param maxLinearSpeed is the maximal linear speed.
   * @param maxLinearAcceleration is the maximal linear acceleration.
   * @param maxAngularSpeed is the maximal angular speed.
   * @param maxAngularAcceleration is the maximal angular acceleration.
   */
  @SyntheticMember
  public ObjectBody(final UUID id, final Shape2f<?> shape, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration) {
    super(id, shape, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1L;
}
