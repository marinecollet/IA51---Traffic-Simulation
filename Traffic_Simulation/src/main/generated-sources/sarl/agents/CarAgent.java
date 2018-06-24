package agents;

import agents.DestinationReached;
import agents.pathAStar;
import agents.requestAStar;
import com.google.common.base.Objects;
import environments.TrafficLight;
import environments.TrafficLightColor;
import framework.agent.PhysicEnvironment;
import framework.environment.DynamicType;
import framework.environment.Influence;
import framework.environment.InfluenceEvent;
import framework.environment.MotionInfluence;
import framework.environment.Percept;
import framework.environment.PerceptionEvent;
import framework.environment.SituatedObject;
import framework.math.Point2f;
import framework.math.Vector2f;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.ContextJoined;
import io.sarl.core.ContextLeft;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
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
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.InputOutput;
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
  
  private boolean isBeginning = true;
  
  private boolean isArrived = false;
  
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
    UUID _iD = this.getID();
    String _plus = ("VehicleAgent agent was stopped. " + _iD);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info(_plus);
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
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
      if ((!this.isBeginning)) {
        this.destionationReached();
      }
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      InfluenceEvent _influenceEvent = new InfluenceEvent();
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influenceEvent);
      return;
    }
    RoadSegment segment = this.path.get(0);
    if (this.isBeginning) {
      this.changeDirection(currentPos, segment);
      this.isBeginning = false;
    }
    float distanceMin = occurrence.perceptionDistance;
    SituatedObject object = null;
    double accelerationCar = occurrence.body.getMaxLinearAcceleration();
    boolean _isEmpty = occurrence.perceptions.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      InputOutput.<Boolean>println(Boolean.valueOf(occurrence.perceptions.isEmpty()));
      for (final Percept o : occurrence.perceptions) {
        {
          Point2f _position = occurrence.body.getPosition();
          Point2f _position_1 = o.getBody().getPosition();
          float distance = Math.abs(_position.operator_minus(_position_1).length());
          if ((distance <= distanceMin)) {
            object = o.getBody();
            distanceMin = distance;
          }
        }
      }
      if ((object != null)) {
        Serializable _type = object.getType();
        boolean _equals = Objects.equal(_type, "LIGHT");
        if (_equals) {
          TrafficLightColor _state = ((TrafficLight) object).getState();
          boolean _equals_1 = Objects.equal(_state, TrafficLightColor.RED);
          if (_equals_1) {
            float a = occurrence.body.getMaxLinearAcceleration();
            float v = occurrence.body.getCurrentLinearSpeed();
            float vf = occurrence.body.getMaxLinearSpeed();
            int adherence = 4;
            float s = (((v / 10) * 6) + 10);
            double _power = Math.pow(v, 2);
            double b = (_power / (2 * distanceMin));
            float T = (distanceMin / vf);
            float deltaV = v;
            double _power_1 = Math.pow((v / vf), adherence);
            double _minus = (1 - _power_1);
            accelerationCar = _minus;
            float part1 = ((s + (v * T)) / distanceMin);
            double _sqrt = Math.sqrt((a * b));
            double _multiply = ((2 * distanceMin) * _sqrt);
            double part2 = ((v * deltaV) / _multiply);
            double _accelerationCar = accelerationCar;
            double _power_2 = Math.pow((part1 + part2), 2);
            accelerationCar = (_accelerationCar - _power_2);
            accelerationCar = (accelerationCar * a);
          } else {
          }
        } else {
          Serializable _type_1 = object.getType();
          boolean _tripleEquals = (_type_1 == "SIGN");
          if (_tripleEquals) {
            accelerationCar = 0;
          } else {
            Serializable _type_2 = object.getType();
            boolean _tripleEquals_1 = (_type_2 == "BODY");
            if (_tripleEquals_1) {
            } else {
              accelerationCar = occurrence.body.getMaxLinearAcceleration();
            }
          }
        }
      }
    }
    if (this.fromBeginToEnd) {
      this.length = (this.length + (accelerationCar * 0.01f));
    } else {
      this.length = (this.length - (accelerationCar * 0.01));
    }
    if ((this.fromBeginToEnd && (this.length >= segment.getLength()))) {
      this.length = segment.getLength();
      this.path.remove(0);
      boolean _isEmpty_1 = this.path.isEmpty();
      if (_isEmpty_1) {
        this.destionationReached();
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
        boolean _isEmpty_2 = this.path.isEmpty();
        if (_isEmpty_2) {
          this.destionationReached();
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
    double _multiply_1 = (_minus_1 * 0.1f);
    float _y_2 = newPos.getY();
    double _y_3 = currentPos.getY();
    double _minus_2 = (_y_2 - _y_3);
    double _multiply_2 = (_minus_2 * 0.1f);
    Vector2f direction = new Vector2f(_multiply_1, _multiply_2);
    Object _newInstance = Array.newInstance(Influence.class, 1);
    Influence[] influences = ((Influence[]) _newInstance);
    UUID _iD = this.getID();
    MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, _iD, direction, 180f);
    influences[0] = _motionInfluence;
    InfluenceEvent infEnv = new InfluenceEvent(influences);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3.emit(infEnv);
  }
  
  private boolean destionationReached() {
    boolean _xifexpression = false;
    if ((!this.isArrived)) {
      boolean _xblockexpression = false;
      {
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        UUID _iD = this.getID();
        DestinationReached _destinationReached = new DestinationReached(_iD);
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_destinationReached);
        _xblockexpression = this.isArrived = true;
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  private double changeDirection(final Point2d currentPos, final RoadSegment segment) {
    double _xblockexpression = (double) 0;
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
      float normeE = distanceE.length();
      float normeB = distanceB.length();
      double _xifexpression = (double) 0;
      if ((normeB < normeE)) {
        double _xblockexpression_1 = (double) 0;
        {
          this.fromBeginToEnd = true;
          _xblockexpression_1 = this.length = 0;
        }
        _xifexpression = _xblockexpression_1;
      } else {
        double _xblockexpression_2 = (double) 0;
        {
          this.fromBeginToEnd = false;
          _xblockexpression_2 = this.length = segment.getLength();
        }
        _xifexpression = _xblockexpression_2;
      }
      _xblockexpression = _xifexpression;
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
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Lifecycle.class, ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $0$getSkill(Lifecycle.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE)", imported = Lifecycle.class)
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
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
    if (other.isBeginning != this.isBeginning)
      return false;
    if (other.isArrived != this.isArrived)
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
    result = prime * result + (this.isBeginning ? 1231 : 1237);
    result = prime * result + (this.isArrived ? 1231 : 1237);
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
