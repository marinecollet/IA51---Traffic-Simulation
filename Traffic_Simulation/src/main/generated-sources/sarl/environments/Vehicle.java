package environments;

import environments.EnvironmentObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Vehicle extends EnvironmentObject {
  public Vehicle(final Vector2f vector) {
    super(vector, true, true);
  }
}
