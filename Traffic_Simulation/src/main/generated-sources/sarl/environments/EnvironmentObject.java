package environments;

import environments.Entity;
import environments.EntityDrawable;
import environments.EntityUpdateable;
import environments.Point;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvironmentObject extends Entity implements EntityDrawable, EntityUpdateable, Runnable {
  private ArrayList<Point> points = new ArrayList<Point>();
  
  public EnvironmentObject(final Vector2f vector, final boolean isUpdatable, final boolean isDrawable) {
    super(vector, isUpdatable, isDrawable);
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
}
