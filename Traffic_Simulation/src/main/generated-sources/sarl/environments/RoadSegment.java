package environments;

import environments.EnvironmentObject;
import environments.RoadConnection;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadSegment extends EnvironmentObject {
  private final RoadConnection start;
  
  private final RoadConnection end;
  
  private Polygon polygonRender;
  
  private final ArrayList<EnvironmentObject> objects;
  
  public RoadSegment(final RoadConnection start, final RoadConnection end) {
    super(start.getPosition(), new Vector2f(1, 1), false, true);
    this.start = start;
    this.end = end;
    this.start.addConnectionOnSegment(this);
    this.end.addConnectionOnSegment(this);
    this.setPolygon();
    ArrayList<EnvironmentObject> _arrayList = new ArrayList<EnvironmentObject>();
    this.objects = _arrayList;
  }
  
  @Pure
  public RoadConnection getStart() {
    return this.start;
  }
  
  @Pure
  public RoadConnection getEnd() {
    return this.end;
  }
  
  private void setPolygon() {
    Polygon _polygon = new Polygon();
    this.polygonRender = _polygon;
    this.polygonRender.setClosed(true);
    this.polygonRender.addPoint(this.start.getPosition().x, this.start.getPosition().y);
    this.polygonRender.addPoint(this.end.getPosition().x, this.end.getPosition().y);
  }
  
  public void render(final Graphics arg2) {
    arg2.setLineWidth(5);
    arg2.setColor(Color.red);
    arg2.draw(this.polygonRender);
  }
  
  public void update(final GameContainer gc, final StateBasedGame sbg, final int delta) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
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
