package environments;

import environments.Panel;
import environments.TrafficLightColor;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Traffic light
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class TrafficLight extends Panel {
  /**
   * State of the traffic light (green, orange or red)
   */
  private TrafficLightColor state;
  
  public TrafficLight(final UUID id, final String name, final Point2f position) {
    super(id, position, name);
    this.setType("LIGHT");
    this.changeColor(TrafficLightColor.RED);
  }
  
  /**
   * Change the state of the traffic light
   */
  public void changeColor(final TrafficLightColor toState) {
    this.state = toState;
  }
  
  /**
   * Get the state of the traffic light
   */
  @Pure
  public TrafficLightColor getState() {
    return this.state;
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
  public TrafficLight clone() {
    try {
      return (TrafficLight) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -3141971771L;
}
