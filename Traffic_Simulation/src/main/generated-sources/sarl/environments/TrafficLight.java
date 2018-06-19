package environments;

import environments.Panel;
import environments.TrafficLightColor;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.ApplicationMap;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class TrafficLight extends Panel {
  private Point2f position;
  
  private MapPolygon element;
  
  private TrafficLightColor state;
  
  public TrafficLight(final Point2f pos) {
    this.position = pos;
    MapPolygon _mapPolygon = new MapPolygon();
    this.element = _mapPolygon;
    for (int i = 0; (i < 16); i++) {
      float _x = this.position.getX();
      double _cos = Math.cos(((((2 * Math.PI) / 16) * i) + (Math.PI / 16)));
      double _multiply = (_cos * 8);
      double _plus = (_x + _multiply);
      float _y = this.position.getY();
      double _sin = Math.sin(((((2 * Math.PI) / 16) * i) + (Math.PI / 16)));
      double _multiply_1 = (_sin * 8);
      double _plus_1 = (_y + _multiply_1);
      this.element.addPoint(_plus, _plus_1);
    }
    this.changeColor(TrafficLightColor.RED);
    ApplicationMap.getInstance().addFlashlightInLayer(this.element);
  }
  
  public void changeColor(final TrafficLightColor toState) {
    this.state = toState;
    final TrafficLightColor state = this.state;
    if (state != null) {
      switch (state) {
        case RED:
          this.element.setColor(0x990000);
          break;
        case ORANGE:
          this.element.setColor(0xffa500);
          break;
        default:
          this.element.setColor(0x006600);
          break;
      }
    } else {
      this.element.setColor(0x006600);
    }
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
