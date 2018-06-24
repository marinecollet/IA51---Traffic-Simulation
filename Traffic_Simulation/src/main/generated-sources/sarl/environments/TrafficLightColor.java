package environments;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * It corresponds to the different state of a traffic light
 */
@SarlSpecification("0.7")
@SarlElementType(12)
@SuppressWarnings("all")
public enum TrafficLightColor {
  RED,
  
  ORANGE,
  
  GREEN;
}
