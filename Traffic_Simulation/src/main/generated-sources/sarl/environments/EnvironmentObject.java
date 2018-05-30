package environments;

import environments.EnvironmentObjectDrawable;
import environments.EnvironmentObjectUpdateable;
import environments.Point;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvironmentObject implements EnvironmentObjectDrawable, EnvironmentObjectUpdateable {
  private boolean updatable;
  
  private boolean drawable;
  
  private Vector2f position;
  
  private Vector2f size;
  
  private ArrayList<Point> points = new ArrayList<Point>();
  
  public EnvironmentObject(final Vector2f _position, final Vector2f _size, final boolean isUpdatable, final boolean isDrawable) {
    Vector2f _vector2f = new Vector2f(_position.x, _position.y);
    this.position = _vector2f;
    Vector2f _vector2f_1 = new Vector2f(_size.x, _size.y);
    this.size = _vector2f_1;
    this.updatable = isUpdatable;
    this.drawable = isDrawable;
  }
  
  public EnvironmentObject(final Vector2f _position, final boolean isUpdatable, final boolean isDrawable) {
    Vector2f _vector2f = new Vector2f(_position.x, _position.y);
    this.position = _vector2f;
    Vector2f _vector2f_1 = new Vector2f();
    this.size = _vector2f_1;
    this.updatable = isUpdatable;
    this.drawable = isDrawable;
  }
  
  @Pure
  public Vector2f getPosition() {
    return this.position;
  }
  
  public float setPosition(final Vector2f _position) {
    float _xblockexpression = (float) 0;
    {
      this.position.x = _position.x;
      _xblockexpression = this.position.y = _position.y;
    }
    return _xblockexpression;
  }
  
  public float move(final Vector2f _offset) {
    float _xblockexpression = (float) 0;
    {
      float _x = this.position.x;
      this.position.x = (_x + _offset.x);
      float _y = this.position.y;
      _xblockexpression = this.position.y = (_y + _offset.y);
    }
    return _xblockexpression;
  }
  
  @Pure
  public Vector2f getSize() {
    return this.size;
  }
  
  public float setSize(final Vector2f _size) {
    float _xblockexpression = (float) 0;
    {
      this.size.x = _size.x;
      _xblockexpression = this.size.y = _size.y;
    }
    return _xblockexpression;
  }
  
  @Pure
  public Rectangle getRect() {
    Rectangle rect = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    return rect;
  }
  
  @Pure
  public boolean isUpdateble() {
    return this.updatable;
  }
  
  @Pure
  public boolean isDrawable() {
    return this.drawable;
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
    EnvironmentObject other = (EnvironmentObject) obj;
    if (other.updatable != this.updatable)
      return false;
    if (other.drawable != this.drawable)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.updatable ? 1231 : 1237);
    result = prime * result + (this.drawable ? 1231 : 1237);
    return result;
  }
}
