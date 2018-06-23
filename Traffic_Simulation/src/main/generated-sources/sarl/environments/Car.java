package environments;

import environments.Vehicle;
import framework.math.Point2f;
import framework.math.Vector2f;
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
  private final int longueur = 8;
  
  private final int largeur = 4;
  
  public Car(final Point2f point, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration) {
    super(point, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration, UUID.randomUUID());
    this.setPosition(point);
    this.perceptionDistance = 0;
    MapPolygon _mapPolygon = new MapPolygon();
    this.element = _mapPolygon;
    float _x = this.getPosition().getX();
    float _minus = (_x - this.largeur);
    float _y = this.getPosition().getY();
    float _plus = (_y + this.longueur);
    this.element.addPoint(_minus, _plus);
    float _x_1 = this.getPosition().getX();
    float _minus_1 = (_x_1 - this.largeur);
    float _y_1 = this.getPosition().getY();
    float _minus_2 = (_y_1 - this.longueur);
    this.element.addPoint(_minus_1, _minus_2);
    float _x_2 = this.getPosition().getX();
    float _plus_1 = (_x_2 + this.largeur);
    float _y_2 = this.getPosition().getY();
    float _minus_3 = (_y_2 - this.longueur);
    this.element.addPoint(_plus_1, _minus_3);
    float _x_3 = this.getPosition().getX();
    float _plus_2 = (_x_3 + this.largeur);
    float _y_3 = this.getPosition().getY();
    float _plus_3 = (_y_3 + this.longueur);
    this.element.addPoint(_plus_2, _plus_3);
    this.element.setColor(0x60c36e);
    Vector2f _vector2f = new Vector2f();
    this.rectangle = this.getFrustum().toShape(this, _vector2f);
    ApplicationMap.getInstance().addAgentBodyInLayer(this);
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
    this.setPosition(newPos);
  }
  
  /**
   * def getPosition(): Point2f
   * {
   * return ;
   * }
   */
  public void setPolygon() {
    MapPolygon _mapPolygon = new MapPolygon();
    this.element = _mapPolygon;
    float _x = this.getPosition().getX();
    float _minus = (_x - this.largeur);
    float _y = this.getPosition().getY();
    float _plus = (_y + this.longueur);
    this.element.addPoint(_minus, _plus);
    float _x_1 = this.getPosition().getX();
    float _minus_1 = (_x_1 - this.largeur);
    float _y_1 = this.getPosition().getY();
    float _minus_2 = (_y_1 - this.longueur);
    this.element.addPoint(_minus_1, _minus_2);
    float _x_2 = this.getPosition().getX();
    float _plus_1 = (_x_2 + this.largeur);
    float _y_2 = this.getPosition().getY();
    float _minus_3 = (_y_2 - this.longueur);
    this.element.addPoint(_plus_1, _minus_3);
    float _x_3 = this.getPosition().getX();
    float _plus_2 = (_x_3 + this.largeur);
    float _y_3 = this.getPosition().getY();
    float _plus_3 = (_y_3 + this.longueur);
    this.element.addPoint(_plus_2, _plus_3);
    this.element.setColor(0x60c36e);
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
    Car other = (Car) obj;
    if (other.longueur != this.longueur)
      return false;
    if (other.largeur != this.largeur)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.longueur;
    result = prime * result + this.largeur;
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
  private final static long serialVersionUID = -1588912496L;
}
