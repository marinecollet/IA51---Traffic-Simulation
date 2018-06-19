package environments;

import environments.EnvironmentObject;
import environments.RoadSegmentDataCollection;
import framework.environment.AbstractEnvironment;
import framework.environment.AgentBody;
import framework.environment.Influence;
import framework.environment.MotionInfluence;
import framework.environment.Percept;
import framework.environment.SituatedObject;
import framework.time.StepTimeManager;
import framework.time.TimeManager;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import logic.Map;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class CityEnvironment extends AbstractEnvironment {
  private ArrayList<EnvironmentObject> environmentObjects;
  
  /**
   * Contains all data to draw Road Segments using JavaFX
   */
  private RoadNetwork roadNetwork;
  
  private RoadNetworkLayer network;
  
  /**
   * Contains all data about road segments
   */
  private RoadSegmentDataCollection roadSegmentDataCollection;
  
  public CityEnvironment() {
    super(Map.WIDTH, Map.HEIGHT, new StepTimeManager(500));
    RoadSegmentDataCollection _roadSegmentDataCollection = new RoadSegmentDataCollection();
    this.roadSegmentDataCollection = _roadSegmentDataCollection;
    ArrayList<EnvironmentObject> _arrayList = new ArrayList<EnvironmentObject>();
    this.environmentObjects = _arrayList;
  }
  
  @Pure
  public RoadNetworkLayer getNetwork() {
    return this.network;
  }
  
  public void setNetwork(final RoadNetworkLayer network) {
    this.network = network;
  }
  
  @Pure
  public RoadSegmentDataCollection getRoadSegmentDataCollection() {
    return this.roadSegmentDataCollection;
  }
  
  public void setRoadSegmentDataCollection(final RoadSegmentDataCollection roadSegmentDataCollection) {
    this.roadSegmentDataCollection = roadSegmentDataCollection;
  }
  
  @Pure
  public ArrayList<EnvironmentObject> getEnvironmentObjects() {
    return this.environmentObjects;
  }
  
  public boolean addEnvironmentObject(final EnvironmentObject object) {
    return this.environmentObjects.add(object);
  }
  
  @Pure
  public RoadNetwork getRoadNetwork() {
    return this.roadNetwork;
  }
  
  @Pure
  public void createAgentBody() {
  }
  
  protected void onAgentBodyCreated(final AgentBody body) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected void onAgentBodyDestroyed(final AgentBody body) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected List<Influence> computeEndogenousBehaviorInfluences() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected List<Percept> computePerceptionsFor(final AgentBody agent) {
    ArrayList<Percept> u = new ArrayList<Percept>();
    for (final EnvironmentObject o : this.environmentObjects) {
    }
    return u;
  }
  
  protected void applyInfluences(final Collection<MotionInfluence> motionInfluences, final Collection<Influence> otherInfluences, final TimeManager timeManager) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Iterable<? extends SituatedObject> getAllObjects() {
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
