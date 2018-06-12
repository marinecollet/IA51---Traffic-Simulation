package environments;

import environments.EnvironmentObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.List;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadSegmentData {
  private RoadSegment segment;
  
  private List<EnvironmentObject> objectsOnSegment;
  
  public RoadSegmentData(final RoadSegment segment) {
    this.segment = segment;
    ArrayList<EnvironmentObject> _arrayList = new ArrayList<EnvironmentObject>();
    this.objectsOnSegment = _arrayList;
  }
  
  @Pure
  public RoadSegment getSegment() {
    return this.segment;
  }
  
  public boolean addEnvironmentObject(final EnvironmentObject object) {
    return this.objectsOnSegment.add(object);
  }
  
  public boolean removeEnvironmentObject(final EnvironmentObject object) {
    return this.objectsOnSegment.remove(object);
  }
  
  @Pure
  public List<EnvironmentObject> getObjectsOnSegment() {
    return this.objectsOnSegment;
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
