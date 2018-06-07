package environments;

import environments.RoadConnection;
import environments.RoadSegment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.util.ArrayList;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private ArrayList<RoadSegment> segments = new ArrayList<RoadSegment>();
  
  private ArrayList<RoadConnection> connections = new ArrayList<RoadConnection>();
  
  @Pure
  public ArrayList<RoadSegment> getSegments() {
    return this.segments;
  }
  
  @Pure
  public ArrayList<RoadConnection> getConnections() {
    return this.connections;
  }
  
  public MapElementLayer<?> loadShapeFile(final File file) {
    throw new Error("Unresolved compilation problems:"
      + "\n\'if\' is a reserved keyword which is not allowed as identifier. Please choose another word."
      + "\nno viable alternative at input \'}\'"
      + "\n<> cannot be resolved.");
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
  
  @SyntheticMember
  public RoadNetwork() {
    super();
  }
}
