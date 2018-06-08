package environments;

import environments.RoadNetwork;
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
import java.util.Collection;
import java.util.List;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.Application;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment extends AbstractEnvironment {
  private RoadNetwork roadNetwork;
  
  public Environment(final float width, final float height) {
    super(width, height, new StepTimeManager(500));
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Method to initialize the environment.
   * Here we load the given Shape file and give it to the Application
   * that will create the GISContainer to display road segments in
   * the window.
   */
  protected boolean initialize(final String filepath) {
    RoadNetwork _roadNetwork = new RoadNetwork();
    this.roadNetwork = _roadNetwork;
    MapElementLayer mapElementLayer = this.roadNetwork.loadShapeFile(filepath);
    if ((mapElementLayer == null)) {
      return false;
    }
    Application.getInstance().setupRoadNetworkContainer(mapElementLayer);
    return true;
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
}
