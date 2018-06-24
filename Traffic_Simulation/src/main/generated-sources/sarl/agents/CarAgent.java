package agents;

import agents.pathAStar;
import agents.requestAStar;
import com.google.common.base.Objects;
import framework.agent.PhysicEnvironment;
import framework.environment.DynamicType;
import framework.environment.Influence;
import framework.environment.InfluenceEvent;
import framework.environment.MotionInfluence;
import framework.environment.PerceptionEvent;
import framework.math.Vector2f;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.ContextJoined;
import io.sarl.core.ContextLeft;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
import io.sarl.core.MemberJoined;
import io.sarl.core.MemberLeft;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(18)
@SuppressWarnings("all")
public class CarAgent extends Agent {
  private DynamicType behaviorType;
  
  private ArrayList<RoadSegment> path;
  
  private boolean fromBeginToEnd = true;
  
  private double length;
  
  private boolean isBegging = true;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("VehicleAgent was started.");
    this.behaviorType = DynamicType.STEERING;
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    UUID _iD = this.getID();
    requestAStar _requestAStar = new requestAStar(_iD);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_requestAStar);
    this.length = 0;
  }
  
  @SyntheticMember
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("VehicleAgent agent was stopped.");
  }
  
  @SyntheticMember
  private void $behaviorUnit$AgentSpawned$2(final AgentSpawned occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$AgentKilled$3(final AgentKilled occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$pathAStar$4(final pathAStar occurrence) {
    this.path = occurrence.pathReturn;
  }
  
  @SyntheticMember
  private void $behaviorUnit$ContextJoined$5(final ContextJoined occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$ContextLeft$6(final ContextLeft occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$MemberJoined$7(final MemberJoined occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$MemberLeft$8(final MemberLeft occurrence) {
  }
  
  @SyntheticMember
  private void $behaviorUnit$PerceptionEvent$9(final PerceptionEvent occurrence) {
    float _x = occurrence.body.getPosition().getX();
    float _y = occurrence.body.getPosition().getY();
    Point2d currentPos = new Point2d(_x, _y);
    if ((Objects.equal(this.path, null) || this.path.isEmpty())) {
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      InfluenceEvent _influenceEvent = new InfluenceEvent();
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influenceEvent);
      return;
    }
    RoadSegment segment = this.path.get(0);
    if (this.isBegging) {
      this.changeDirection(currentPos, segment);
      this.isBegging = false;
    }
    if (this.fromBeginToEnd) {
      float _maxLinearAcceleration = occurrence.body.getMaxLinearAcceleration();
      float _multiply = (_maxLinearAcceleration * 0.01f);
      double _plus = (this.length + _multiply);
      this.length = _plus;
    } else {
      float _maxLinearAcceleration_1 = occurrence.body.getMaxLinearAcceleration();
      double _multiply_1 = (_maxLinearAcceleration_1 * 0.01);
      double _minus = (this.length - _multiply_1);
      this.length = _minus;
    }
    if ((this.fromBeginToEnd && (this.length >= segment.getLength()))) {
      this.length = segment.getLength();
      this.path.remove(0);
      boolean _isEmpty = this.path.isEmpty();
      if (_isEmpty) {
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        InfluenceEvent _influenceEvent_1 = new InfluenceEvent();
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_influenceEvent_1);
        return;
      }
      segment = this.path.get(0);
      this.changeDirection(currentPos, segment);
    } else {
      if (((!this.fromBeginToEnd) && (this.length <= 0))) {
        this.length = 0;
        this.path.remove(0);
        boolean _isEmpty_1 = this.path.isEmpty();
        if (_isEmpty_1) {
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
          InfluenceEvent _influenceEvent_2 = new InfluenceEvent();
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(_influenceEvent_2);
          return;
        }
        segment = this.path.get(0);
        this.changeDirection(currentPos, segment);
      }
    }
    double _x_1 = segment.getGeoLocationForDistance(this.length).getX();
    double _y_1 = segment.getGeoLocationForDistance(this.length).getY();
    Vector2f newPos = new Vector2f(_x_1, _y_1);
    float _x_2 = newPos.getX();
    double _x_3 = currentPos.getX();
    double _minus_1 = (_x_2 - _x_3);
    double _multiply_2 = (_minus_1 * 0.1f);
    float _y_2 = newPos.getY();
    double _y_3 = currentPos.getY();
    double _minus_2 = (_y_2 - _y_3);
    double _multiply_3 = (_minus_2 * 0.1f);
    Vector2f direction = new Vector2f(_multiply_2, _multiply_3);
    if ((this.fromBeginToEnd && (this.length >= segment.getLength()))) {
      this.length = 0;
    }
    Object _newInstance = Array.newInstance(Influence.class, 1);
    Influence[] influences = ((Influence[]) _newInstance);
    UUID _iD = this.getID();
    MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, _iD, direction, 0);
    influences[0] = _motionInfluence;
    InfluenceEvent infEnv = new InfluenceEvent(influences);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3.emit(infEnv);
  }
  
  private Object changeDirection(final Point2d currentPos, final RoadSegment segment) {
    Object _xblockexpression = null;
    {
      double _x = segment.getBeginPoint().getPoint().getX();
      double _x_1 = currentPos.getX();
      double _minus = (_x - _x_1);
      double _y = segment.getBeginPoint().getPoint().getY();
      double _y_1 = currentPos.getY();
      double _minus_1 = (_y - _y_1);
      Vector2f distanceB = new Vector2f(_minus, _minus_1);
      double _x_2 = segment.getEndPoint().getPoint().getX();
      double _x_3 = currentPos.getX();
      double _minus_2 = (_x_2 - _x_3);
      double _y_2 = segment.getEndPoint().getPoint().getY();
      double _y_3 = currentPos.getY();
      double _minus_3 = (_y_2 - _y_3);
      Vector2f distanceE = new Vector2f(_minus_2, _minus_3);
      Object _xifexpression = null;
      boolean _lessThan = (distanceB.compareTo(distanceE) < 0);
      if (_lessThan) {
        _xifexpression = Boolean.valueOf(this.fromBeginToEnd = true);
      } else {
        double _xblockexpression_1 = (double) 0;
        {
          this.fromBeginToEnd = false;
          _xblockexpression_1 = this.length = segment.getLength();
        }
        _xifexpression = Double.valueOf(_xblockexpression_1);
      }
      _xblockexpression = ((Object)_xifexpression);
    }
    return _xblockexpression;
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(PhysicEnvironment.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(PhysicEnvironment.class, ($0$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT == null || $0$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT.get() == null) ? ($0$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT = $0$getSkill(PhysicEnvironment.class)) : $0$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT)", imported = PhysicEnvironment.class)
  private PhysicEnvironment $CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER() {
    if (this.$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT == null || this.$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT.get() == null) {
      this.$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT = $getSkill(PhysicEnvironment.class);
    }
    return $castSkill(PhysicEnvironment.class, this.$CAPACITY_USE$FRAMEWORK_AGENT_PHYSICENVIRONMENT);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextLeft(final ContextLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextLeft$6(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextJoined(final ContextJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextJoined$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberLeft(final MemberLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberLeft$8(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentSpawned(final AgentSpawned occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$PerceptionEvent(final PerceptionEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$PerceptionEvent$9(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentKilled(final AgentKilled occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentKilled$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$pathAStar(final pathAStar occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$pathAStar$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberJoined(final MemberJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberJoined$7(occurrence));
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CarAgent other = (CarAgent) obj;
    if (other.fromBeginToEnd != this.fromBeginToEnd)
      return false;
    if (Double.doubleToLongBits(other.length) != Double.doubleToLongBits(this.length))
      return false;
    if (other.isBegging != this.isBegging)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.fromBeginToEnd ? 1231 : 1237);
    result = prime * result + (int) (Double.doubleToLongBits(this.length) ^ (Double.doubleToLongBits(this.length) >>> 32));
    result = prime * result + (this.isBegging ? 1231 : 1237);
    return result;
  }
  
  @SyntheticMember
  public CarAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public CarAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public CarAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
