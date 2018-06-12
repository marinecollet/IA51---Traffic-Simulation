package environments;

import framework.environment.AbstractFrustum;
import framework.math.Point2f;
import framework.math.Shape2f;
import framework.math.Vector2f;
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
public class CarFrustum extends AbstractFrustum {
  public Shape2f<?> toShape(final Point2f position, final Vector2f orientation) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public CarFrustum clone() {
    try {
      return (CarFrustum) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  /**
   * @param owner the identifier of the owner of this frustum.
   */
  @SyntheticMember
  public CarFrustum(final UUID owner) {
    super(owner);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -46657710L;
}
