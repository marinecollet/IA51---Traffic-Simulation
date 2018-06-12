package environments;

import environments.Vehicle;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.Application;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends Vehicle {
  /**
   * Position in the layer
   */
  private Point2f position;
  
  /**
   * The representation of the car (here a circle)
   */
  private MapPolygon element;
  
  public Car(final Point2f point, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration) {
    super(point, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration);
    this.position = point;
    MapPolygon _mapPolygon = new MapPolygon();
    this.element = _mapPolygon;
    float _x = this.position.getX();
    float _minus = (_x - 2);
    float _y = this.position.getY();
    float _plus = (_y + 4);
    this.element.addPoint(_minus, _plus);
    float _x_1 = this.position.getX();
    float _minus_1 = (_x_1 - 2);
    float _y_1 = this.position.getY();
    float _minus_2 = (_y_1 - 4);
    this.element.addPoint(_minus_1, _minus_2);
    float _x_2 = this.position.getX();
    float _plus_1 = (_x_2 + 2);
    float _y_2 = this.position.getY();
    float _minus_3 = (_y_2 - 4);
    this.element.addPoint(_plus_1, _minus_3);
    float _x_3 = this.position.getX();
    float _plus_2 = (_x_3 + 2);
    float _y_3 = this.position.getY();
    float _plus_3 = (_y_3 + 4);
    this.element.addPoint(_plus_2, _plus_3);
    this.element.setColor(0x60c36e);
    Application.getInstance().addAgentBodyInLayer(this.element);
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Do cleaning stuff, must be called before destroying
   */
  public boolean cleanUp() {
    return Application.getInstance().removeAgentBodyInLayer(this.element);
  }
  
  @Override
  public void moveVehicle(final Point2f newPos) {
    this.position = newPos;
  }
  
  public Point2f getPosition() {
    return this.position;
  }
  
  @Pure
  public MapPolygon getElement() {
    return this.element;
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
  private final static long serialVersionUID = 4996291056L;
}
