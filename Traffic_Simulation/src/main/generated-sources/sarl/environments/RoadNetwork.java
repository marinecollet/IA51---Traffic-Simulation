package environments;

import environments.RoadConnection;
import environments.RoadSegment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem, Thomas Gredin
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private ArrayList<RoadSegment> segments = new ArrayList<RoadSegment>();
  
  private ArrayList<RoadConnection> connections = new ArrayList<RoadConnection>();
  
  /**
   * Contains all elements loaded from the Shape file
   */
  private MapElementLayer<?> mapElements;
  
  public abstract ArrayList<RoadSegment> getSegments();
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    throw new Error("Unresolved compilation problems:"
      + "\nmismatched input \'(\' expecting \'}\'"
      + "\nmismatched input \'(\' expecting \'}\'");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    throw new Error("Unresolved compilation problems:"
      + "\nmismatched input \'(\' expecting \'}\'");
  }
  
  @SyntheticMember
  public RoadNetwork() {
    super();
  }
}
