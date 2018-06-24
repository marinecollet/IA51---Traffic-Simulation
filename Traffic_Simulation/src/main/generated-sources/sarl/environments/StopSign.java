package environments;

import environments.Panel;
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
public class StopSign extends Panel {
  private Point2f position;
  
  public StopSign(final UUID id, final String name, final Point2f position) {
    super(id, name);
    this.position = position;
    this.setType("SIGN");
    MapPolygon _mapPolygon = new MapPolygon();
    this.element = _mapPolygon;
    for (int i = 0; (i < 8); i++) {
      float _x = position.getX();
      double _cos = Math.cos(((((2 * Math.PI) / 8) * i) + (Math.PI / 8)));
      double _multiply = (_cos * 2);
      double _plus = (_x + _multiply);
      float _y = position.getY();
      double _sin = Math.sin(((((2 * Math.PI) / 8) * i) + (Math.PI / 8)));
      double _multiply_1 = (_sin * 2);
      double _plus_1 = (_y + _multiply_1);
      this.element.addPoint(_plus, _plus_1);
    }
    this.element.setColor(0x990000);
    ApplicationMap.getInstance().addStopInLayer(this.element);
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
  public StopSign clone() {
    try {
      return (StopSign) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 124593387L;
}
