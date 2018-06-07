package environments;

import environments.EnvironmentObject;
import environments.RoadConnection;
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
public class RoadSegment extends EnvironmentObject {
  private final RoadConnection start;
  
  private final RoadConnection end;
  
  private Rectangle rectangle;
  
  private final ArrayList<EnvironmentObject> objects;
  
  public RoadSegment(final RoadConnection start, final RoadConnection end) {
    super(start.getPosition(), new Point2f(1, 1), false, true);
    this.start = start;
    this.end = end;
    this.start.addConnectionOnSegment(this);
    this.end.addConnectionOnSegment(this);
    ArrayList<EnvironmentObject> _arrayList = new ArrayList<EnvironmentObject>();
    this.objects = _arrayList;
    Rectangle _rectangle = new Rectangle();
    this.rectangle = _rectangle;
  }
  
  @Pure
  public RoadConnection getStart() {
    return this.start;
  }
  
  @Pure
  public RoadConnection getEnd() {
    return this.end;
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
