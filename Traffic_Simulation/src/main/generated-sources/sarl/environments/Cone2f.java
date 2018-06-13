package environments;

import framework.math.Point2f;
import framework.math.Rectangle2f;
import framework.math.Shape2f;
import framework.math.Tuple2f;
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
public class Cone2f extends Shape2f<Cone2f> {
  private Point2f start;
  
  private float perceptionDistance;
  
  public Cone2f(final Point2f position, final float perceptionDistance) {
    this.start = position;
    this.perceptionDistance = perceptionDistance;
  }
  
  public Cone2f(final float x, final float y, final float perceptionDistance) {
    Point2f _point2f = new Point2f(x, y);
    this.start = _point2f;
    this.perceptionDistance = perceptionDistance;
  }
  
  @Pure
  public boolean intersects(final Shape2f<?> r) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Cone2f translate(final Tuple2f<?> vector) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Rectangle2f getBounds() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public float getMaxDemiSize() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
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
    Cone2f other = (Cone2f) obj;
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
  public Cone2f clone() {
    try {
      return (Cone2f) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3103730651L;
}
