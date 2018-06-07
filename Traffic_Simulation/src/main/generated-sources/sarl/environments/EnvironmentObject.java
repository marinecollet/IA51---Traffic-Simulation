package environments;

import environments.Point;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvironmentObject {
  private boolean updatable;
  
  private boolean drawable;
  
  private Point2f position;
  
  private Point2f size;
  
  private ArrayList<Point> points = new ArrayList<Point>();
  
  public EnvironmentObject(final Point2f _position, final Point2f _size, final boolean isUpdatable, final boolean isDrawable) {
    float _x = _position.getX();
    float _y = _position.getY();
    Point2f _point2f = new Point2f(_x, _y);
    this.position = _point2f;
    float _x_1 = _size.getX();
    float _y_1 = _size.getY();
    Point2f _point2f_1 = new Point2f(_x_1, _y_1);
    this.size = _point2f_1;
    this.updatable = isUpdatable;
    this.drawable = isDrawable;
  }
  
  public EnvironmentObject(final Point2f _position, final boolean isUpdatable, final boolean isDrawable) {
    float _x = _position.getX();
    float _y = _position.getY();
    Point2f _point2f = new Point2f(_x, _y);
    this.position = _point2f;
    Point2f _point2f_1 = new Point2f();
    this.size = _point2f_1;
    this.updatable = isUpdatable;
    this.drawable = isDrawable;
  }
  
  public EnvironmentObject() {
    Point2f _point2f = new Point2f();
    this.position = _point2f;
    Point2f _point2f_1 = new Point2f();
    this.size = _point2f_1;
    this.updatable = false;
    this.drawable = false;
  }
  
  @Pure
  public Point2f getPosition() {
    return this.position;
  }
  
  public void setPosition(final Point2f _position) {
    this.position.setX(_position.getX());
    this.position.setY(_position.getY());
  }
  
  public void move(final Point2f _offset) {
    float _x = this.position.getX();
    float _x_1 = _offset.getX();
    float _plus = (_x + _x_1);
    this.position.setX(_plus);
    float _y = this.position.getY();
    float _y_1 = _offset.getY();
    float _plus_1 = (_y + _y_1);
    this.position.setY(_plus_1);
  }
  
  @Pure
  public Point2f getSize() {
    return this.size;
  }
  
  public void setSize(final Point2f _size) {
    this.size.setX(_size.getX());
    this.size.setY(_size.getY());
  }
  
  @Pure
  public Rectangle getRect() {
    float _x = this.position.getX();
    float _y = this.position.getY();
    float _x_1 = this.size.getX();
    float _y_1 = this.size.getY();
    Rectangle rect = new Rectangle(_x, _y, _x_1, _y_1);
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
