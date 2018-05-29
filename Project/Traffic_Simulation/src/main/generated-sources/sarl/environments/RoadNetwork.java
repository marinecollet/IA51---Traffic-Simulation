package environments;

import environments.Environment;
import environments.RoadConnection;
import environments.RoadSegment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private final Environment roadNetwork;
  
  private final ArrayList<RoadSegment> segments;
  
  private final ArrayList<RoadConnection> connections;
  
  public RoadNetwork() {
    Environment _environment = new Environment();
    this.roadNetwork = _environment;
    ArrayList<RoadSegment> _arrayList = new ArrayList<RoadSegment>();
    this.segments = _arrayList;
    ArrayList<RoadConnection> _arrayList_1 = new ArrayList<RoadConnection>();
    this.connections = _arrayList_1;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe return type is incompatible with equals(Object). Current method has the return type: void. The super method has the return type: boolean."
      + "\nThe return type is incompatible with equals(Object). Current method has the return type: void. The super method has the return type: boolean.");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe return type is incompatible with equals(Object). Current method has the return type: void. The super method has the return type: boolean.");
  }
}
