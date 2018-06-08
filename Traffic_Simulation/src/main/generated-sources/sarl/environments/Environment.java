package environments;

import environments.RoadNetwork;
import framework.environment.AbstractEnvironment;
import framework.environment.AgentBody;
import framework.environment.Influence;
import framework.environment.MotionInfluence;
import framework.environment.Percept;
import framework.environment.SituatedObject;
import framework.time.TimeManager;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import java.util.List;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment extends AbstractEnvironment {
  private RoadNetwork roadNetwork;
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Method to initialize the environment.
   * Here we load the given Shape file and give it to the Application
   * that will create the GISContainer to display road segments in
   * the window.
   */
  protected MapElementLayer<?> initialize(final String filepath) {
    MapElementLayer<?> _xblockexpression = null;
    {
      RoadNetwork _roadNetwork = new RoadNetwork();
      this.roadNetwork = _roadNetwork;
      _xblockexpression = this.roadNetwork.loadShapeFile(filepath);
    }
    return _xblockexpression;
  }
  
  protected void onAgentBodyCreated(final AgentBody body) {
  }
  
  protected void onAgentBodyDestroyed(final AgentBody body) {
  }
  
  protected List<Influence> computeEndogenousBehaviorInfluences() {
    return null;
  }
  
  protected List<Percept> computePerceptionsFor(final AgentBody agent) {
    return null;
  }
  
  protected void applyInfluences(final Collection<MotionInfluence> motionInfluences, final Collection<Influence> otherInfluences, final TimeManager timeManager) {
  }
  
  public Iterable<? extends SituatedObject> getAllObjects() {
    return null;
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
  
  /**
   * @param width is the width of the environment.
   * @param height is the height of the environment.
   * @param timeManager is the time manager to use.
   */
  @SyntheticMember
  public Environment(final float width, final float height, final TimeManager timeManager) {
    super(width, height, timeManager);
  }
}
