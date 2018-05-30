package environments;

import environments.EnvironmentObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Panel extends EnvironmentObject {
  @SyntheticMember
  public Panel(final Vector2f _position, final boolean isUpdatable, final boolean isDrawable) {
    super(_position, isUpdatable, isDrawable);
  }
  
  @SyntheticMember
  public Panel(final Vector2f _position, final Vector2f _size, final boolean isUpdatable, final boolean isDrawable) {
    super(_position, _size, isUpdatable, isDrawable);
  }
}
