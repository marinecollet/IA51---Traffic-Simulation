package environments;

import agents.AskChangementFlashLight;
import agents.AskForLinkedFlashLights;
import agents.CarAgent;
import agents.ChangeTrafficLight;
import agents.DestinationReached;
import agents.GiveLinkedFlashLights;
import agents.pathAStar;
import agents.requestAStar;
import com.google.common.base.Objects;
import environments.CityEnvironment;
import environments.TrafficLight;
import environments.TrafficLightColor;
import framework.environment.AgentBody;
import framework.environment.Influence;
import framework.environment.InfluenceEvent;
import framework.environment.Percept;
import framework.environment.PerceptionEvent;
import framework.environment.SimulationAgentReady;
import framework.environment.StartSimulation;
import framework.environment.StopSimulation;
import framework.math.Point2f;
import framework.time.TimePercept;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import io.sarl.util.OpenEventSpace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import logic.PathUtils;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.ApplicationMap;

/**
 * Agent environment
 */
@SarlSpecification("0.7")
@SarlElementType(18)
@SuppressWarnings("all")
public class AgentEnvironment extends Agent {
  /**
   * City environment
   */
  private CityEnvironment environment;
  
  /**
   * Influences
   */
  private final AtomicInteger influences = new AtomicInteger(0);
  
  /**
   * Current space
   */
  private OpenEventSpace space;
  
  /**
   * Current address
   */
  private Address myAdr;
  
  /**
   * Is freezing
   */
  private final AtomicBoolean freeze = new AtomicBoolean(false);
  
  /**
   * SpaceID
   */
  private UUID spaceId;
  
  /**
   * Delay to spawn a new agent
   */
  private double spawnAgentDelay;
  
  /**
   * Cars arrived to their destinations
   */
  private ArrayList<UUID> carArrived = new ArrayList<UUID>();
  
  /**
   * Traffic lights controller in the map
   */
  private ArrayList<UUID> trafficLightsControllers = new ArrayList<UUID>();
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    while ((!ApplicationMap.getInstance().getIsReady())) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.debug("wait");
    }
    Object _get = occurrence.parameters[0];
    UUID spaceId = ((UUID) _get);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    this.space = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext().<OpenEventSpace>getSpace(spaceId);
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    this.space.register(_$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.asEventListener());
    this.myAdr = this.space.getAddress(this.getID());
    Object _get_1 = occurrence.parameters[0];
    spaceId = ((UUID) _get_1);
    this.spawnAgentDelay = 0;
    CityEnvironment _cityEnvironment = new CityEnvironment();
    this.environment = _cityEnvironment;
    ApplicationMap.getInstance().agentBodyLayer.setList(this.environment.getAgentBodies());
    ApplicationMap.getInstance().flashlightLayer.setList(this.environment.getTrafficLights());
    this.environment.createAgentBody();
    List<Object> agentParameters = CollectionLiterals.<Object>newArrayList(spaceId, this.getID());
    Iterable<AgentBody> _agentBodies = this.environment.getAgentBodies();
    for (final AgentBody body : _agentBodies) {
      Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(CarAgent.class, body.getID(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.getDefaultContext(), agentParameters.toArray());
    }
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(_simulationAgentReady);
  }
  
  @SyntheticMember
  private void $behaviorUnit$StopSimulation$1(final StopSimulation occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  @SyntheticMember
  private void $behaviorUnit$StartSimulation$2(final StartSimulation occurrence) {
    this.runEnvironmentBehavior();
  }
  
  /**
   * Define the environment behavior
   */
  protected void runEnvironmentBehavior() {
    try {
      this.freeze.set(true);
      try {
        this.computeCarArrived();
        ArrayList<UUID> _arrayList = new ArrayList<UUID>();
        this.carArrived = _arrayList;
        this.influences.set(0);
        this.environment.runBehaviour();
        float _simulationDelay = this.environment.getTimeManager().getSimulationDelay();
        long delay = ((long) _simulationDelay);
        double _spawnAgentDelay = this.spawnAgentDelay;
        this.spawnAgentDelay = (_spawnAgentDelay + delay);
        if ((delay > 0)) {
          Thread.sleep(delay);
        }
        if ((this.spawnAgentDelay > 5000)) {
          this.initNewAgent();
          this.spawnAgentDelay = 0;
        }
      } finally {
        this.freeze.set(false);
      }
      ApplicationMap.getInstance().update();
      this.notifyAgentsOrDie();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Send event to agents
   */
  protected void notifyAgentsOrDie() {
    float _currentTime = this.environment.getTimeManager().getCurrentTime();
    float _lastStepDuration = this.environment.getTimeManager().getLastStepDuration();
    final TimePercept timePercept = new TimePercept(_currentTime, _lastStepDuration);
    for (final UUID controller : this.trafficLightsControllers) {
      {
        float _lastStepDuration_1 = timePercept.getLastStepDuration();
        AskChangementFlashLight ev = new AskChangementFlashLight(_lastStepDuration_1);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        final Scope<Address> _function = (Address it) -> {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, controller);
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function);
      }
    }
    Iterable<AgentBody> _agentBodies = this.environment.getAgentBodies();
    for (final AgentBody body : _agentBodies) {
      {
        List<Percept> _perceivedObjects = body.getPerceivedObjects();
        Percept _percept = new Percept(body);
        float _perceptionDistance = body.getPerceptionDistance();
        PerceptionEvent ev = new PerceptionEvent(_perceivedObjects, _percept, timePercept, _perceptionDistance);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        final Scope<Address> _function = (Address it) -> {
          UUID _uUID = it.getUUID();
          UUID _iD = body.getID();
          return Objects.equal(_uUID, _iD);
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function);
      }
    }
  }
  
  /**
   * Initialize a car
   */
  protected UUID initNewAgent() {
    UUID _xblockexpression = null;
    {
      UUID agentID = this.environment.createAgentBody();
      List<Object> agentParameters = CollectionLiterals.<Object>newArrayList(this.spaceId, this.getID());
      Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      _xblockexpression = _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(CarAgent.class, agentID, _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext(), agentParameters.toArray());
    }
    return _xblockexpression;
  }
  
  @SyntheticMember
  private void $behaviorUnit$requestAStar$3(final requestAStar occurrence) {
    UUID agentUUID = occurrence.ID;
    Point2f startPosition = null;
    Iterable<AgentBody> _agentBodies = this.environment.getAgentBodies();
    for (final AgentBody body : _agentBodies) {
      UUID _iD = body.getID();
      boolean _equals = Objects.equal(_iD, agentUUID);
      if (_equals) {
        startPosition = body.getPosition();
        break;
      }
    }
    float _x = startPosition.getX();
    float _y = startPosition.getY();
    Point2d startPoint = new Point2d(_x, _y);
    ArrayList<RoadConnection> entryConnections = this.environment.getEntryExitConnections();
    double _random = Math.random();
    int _size = this.environment.getEntryExitConnections().size();
    double _multiply = (_random * _size);
    int random = ((int) _multiply);
    Point2d _point = entryConnections.get(random).getPoint();
    Point2d endPoint = new Point2d(_point);
    while (endPoint.operator_equals(startPoint)) {
      {
        double _random_1 = Math.random();
        int _size_1 = this.environment.getEntryExitConnections().size();
        double _multiply_1 = (_random_1 * _size_1);
        random = ((int) _multiply_1);
        Point2d _point_1 = entryConnections.get(random).getPoint();
        Point2d _point2d = new Point2d(_point_1);
        endPoint = _point2d;
      }
    }
    ArrayList<RoadSegment> path = PathUtils.GPS(startPoint, endPoint, this.environment.getRoadNetwork());
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    pathAStar _pathAStar = new pathAStar(path);
    final Scope<Address> _function = (Address it) -> {
      Address _source = occurrence.getSource();
      return Objects.equal(it, _source);
    };
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_pathAStar, _function);
  }
  
  @SyntheticMember
  private void $behaviorUnit$InfluenceEvent$4(final InfluenceEvent occurrence) {
    for (final Influence influence : occurrence.influences) {
      {
        UUID id = influence.getEmitter();
        if ((id == null)) {
          id = occurrence.getSource().getUUID();
        }
        AgentBody body = this.environment.getAgentBodyFor(id);
        body.influence(influence);
      }
    }
    this.waitAllAgentsInfluences();
  }
  
  @SyntheticMember
  private void $behaviorUnit$AskForLinkedFlashLights$5(final AskForLinkedFlashLights occurrence) {
    ArrayList<TrafficLight[]> trafficLightsGroups = this.environment.getTrafficLightsGroups();
    int _size = trafficLightsGroups.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      TrafficLight[] group = trafficLightsGroups.remove(0);
      UUID _iD = group[0].getID();
      UUID _iD_1 = group[1].getID();
      UUID _iD_2 = group[2].getID();
      UUID _iD_3 = group[3].getID();
      GiveLinkedFlashLights ev = new GiveLinkedFlashLights(_iD, _iD_1, _iD_2, _iD_3);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      final Scope<Address> _function = (Address it) -> {
        UUID _uUID = it.getUUID();
        return Objects.equal(_uUID, occurrence.ID);
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function);
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$ChangeTrafficLight$6(final ChangeTrafficLight occurrence) {
    for (final UUID green : occurrence.greens) {
      this.environment.setStateTrafficLight(TrafficLightColor.GREEN, green);
    }
    for (final UUID orange : occurrence.oranges) {
      this.environment.setStateTrafficLight(TrafficLightColor.ORANGE, orange);
    }
    for (final UUID red : occurrence.reds) {
      this.environment.setStateTrafficLight(TrafficLightColor.RED, red);
    }
    this.waitAllAgentsInfluences();
  }
  
  /**
   * Wait all agents to send an influence
   */
  protected void waitAllAgentsInfluences() {
    int v = this.influences.incrementAndGet();
    int _agentBodyNumber = this.environment.getAgentBodyNumber();
    int _size = this.trafficLightsControllers.size();
    int _plus = (_agentBodyNumber + _size);
    boolean _greaterEqualsThan = (v >= _plus);
    if (_greaterEqualsThan) {
      this.runEnvironmentBehavior();
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$DestinationReached$7(final DestinationReached occurrence) {
    this.carArrived.add(occurrence.ID);
  }
  
  /**
   * Remove car from the simulation
   */
  protected void computeCarArrived() {
    for (final UUID car : this.carArrived) {
      {
        this.environment.removeAgentBodyFromList(car);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        Destroy _destroy = new Destroy();
        final Scope<Address> _function = (Address it) -> {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, car);
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_destroy, _function);
      }
    }
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
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Behaviors.class, ($0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || $0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $0$getSkill(Behaviors.class)) : $0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS)", imported = Behaviors.class)
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class);
    }
    return $castSkill(Behaviors.class, this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  /**
   * @author Marine
   * 
   * @description
   * When the environment receive a perception's request from an agent, he sends back the list of perception
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$requestAStar(final requestAStar occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$requestAStar$3(occurrence));
  }
  
  /**
   * Receive influence from car agent
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$InfluenceEvent(final InfluenceEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$InfluenceEvent$4(occurrence));
  }
  
  /**
   * Traffic light controller asks for traffic lights
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AskForLinkedFlashLights(final AskForLinkedFlashLights occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AskForLinkedFlashLights$5(occurrence));
  }
  
  /**
   * When a car has reached his destination
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$DestinationReached(final DestinationReached occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$DestinationReached$7(occurrence));
  }
  
  /**
   * Receive new states for each traffic light
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ChangeTrafficLight(final ChangeTrafficLight occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ChangeTrafficLight$6(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$StopSimulation(final StopSimulation occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$StopSimulation$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$StartSimulation(final StartSimulation occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$StartSimulation$2(occurrence));
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
    AgentEnvironment other = (AgentEnvironment) obj;
    if (!java.util.Objects.equals(this.spaceId, other.spaceId)) {
      return false;
    }
    if (Double.doubleToLongBits(other.spawnAgentDelay) != Double.doubleToLongBits(this.spawnAgentDelay))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + java.util.Objects.hashCode(this.spaceId);
    result = prime * result + (int) (Double.doubleToLongBits(this.spawnAgentDelay) ^ (Double.doubleToLongBits(this.spawnAgentDelay) >>> 32));
    return result;
  }
  
  @SyntheticMember
  public AgentEnvironment(final UUID arg0, final UUID arg1) {
    super(arg0, arg1);
  }
  
  @SyntheticMember
  @Deprecated
  @Inject
  public AgentEnvironment(final BuiltinCapacitiesProvider arg0, final UUID arg1, final UUID arg2) {
    super(arg0, arg1, arg2);
  }
  
  @SyntheticMember
  @Inject
  public AgentEnvironment(final UUID arg0, final UUID arg1, final DynamicSkillProvider arg2) {
    super(arg0, arg1, arg2);
  }
}
