package environments;

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

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment extends AbstractEnvironment {
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
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected void applyInfluences(final Collection<MotionInfluence> motionInfluences, final Collection<Influence> otherInfluences, final TimeManager timeManager) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Iterable<? extends SituatedObject> getAllObjects() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
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
