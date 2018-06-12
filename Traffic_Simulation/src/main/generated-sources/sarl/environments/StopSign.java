package environments;

import environments.Panel;
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
public class StopSign extends Panel {
  private Point2f position;
  
  private MapPolygon element;
  
  public StopSign(final Point2f pos) {
    this.position = pos;
    MapPolygon _mapPolygon = new MapPolygon();
    this.element = _mapPolygon;
    for (int i = 0; (i < 8); i++) {
      float _x = this.position.getX();
      double _cos = Math.cos(((((2 * Math.PI) / 8) * i) + (Math.PI / 8)));
      double _multiply = (_cos * 8);
      double _plus = (_x + _multiply);
      float _y = this.position.getY();
      double _sin = Math.sin(((((2 * Math.PI) / 8) * i) + (Math.PI / 8)));
      double _multiply_1 = (_sin * 8);
      double _plus_1 = (_y + _multiply_1);
      this.element.addPoint(_plus, _plus_1);
    }
    this.element.setColor(0x990000);
    Application.getInstance().addStopInLayer(this.element);
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
