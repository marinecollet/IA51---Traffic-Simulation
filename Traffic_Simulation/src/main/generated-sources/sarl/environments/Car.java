package environments;

import environments.Vehicle;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.ApplicationMap;

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
    super(point, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration, UUID.randomUUID());
    this.position = point;
    this.perceptionDistance = 0;
  }
  
  public void setPerceptionDisatnce(final float perceptionDistance) {
    this.perceptionDistance = perceptionDistance;
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Do cleaning stuff, must be called before destroying
   */
  public void cleanUp() {
    ApplicationMap.getInstance().removeAgentBodyInLayer(this.element);
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
  private final static long serialVersionUID = 7100667318L;
}
