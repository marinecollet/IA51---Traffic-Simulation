package environments;

import environments.Panel;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class StopSign extends Panel {
  @SyntheticMember
  public StopSign() {
    super();
  }
  
  @SyntheticMember
  public StopSign(final Point2f _position, final boolean isUpdatable, final boolean isDrawable) {
    super(_position, isUpdatable, isDrawable);
  }
  
  @SyntheticMember
  public StopSign(final Point2f _position, final Point2f _size, final boolean isUpdatable, final boolean isDrawable) {
    super(_position, _size, isUpdatable, isDrawable);
  }
}
