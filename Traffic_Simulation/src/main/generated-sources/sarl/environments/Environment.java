package environments;

import agents.CarAgent;
import agents.pathAStar;
import agents.requestAStar;
import com.google.common.base.Objects;
import environments.Car;
import environments.EnvironmentObject;
import environments.RoadSegmentData;
import environments.RoadSegmentDataCollection;
import environments.StopSign;
import environments.TrafficLight;
import environments.TrafficLightColor;
import framework.environment.AgentBody;
import framework.environment.Percept;
import framework.environment.SimulationAgentReady;
import framework.environment.StartSimulation;
import framework.environment.StopSimulation;
import framework.math.Point2f;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.arakhne.afc.gis.road.path.astar.RoadAStar;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.Application;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(18)
@SuppressWarnings("all")
public class Environment extends Agent {
  /**
   * Contains all bodies
   */
  private HashSet<AgentBody> bodies;
  
  private HashSet<EnvironmentObject> environmentObjects;
  
  /**
   * Contains all data to draw Road Segments using JavaFX
   */
  private RoadNetwork roadNetwork;
  
  private RoadNetworkLayer network;
  
  /**
   * Contains all data about road segments
   */
  private RoadSegmentDataCollection roadSegmentDataCollection;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    while ((!Application.getInstance().getIsReady())) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.debug("attend");
    }
    MapElementLayer<?> _roadNetworkLayer = Application.getInstance().getRoadNetworkLayer();
    this.network = ((RoadNetworkLayer) _roadNetworkLayer);
    HashSet<AgentBody> _hashSet = new HashSet<AgentBody>();
    this.bodies = _hashSet;
    HashSet<EnvironmentObject> _hashSet_1 = new HashSet<EnvironmentObject>();
    this.environmentObjects = _hashSet_1;
    RoadSegmentDataCollection _roadSegmentDataCollection = new RoadSegmentDataCollection();
    this.roadSegmentDataCollection = _roadSegmentDataCollection;
    HashMap<Point2d, Integer> stops = new HashMap<Point2d, Integer>();
    Collection<? extends RoadSegment> _roadSegments = this.network.getRoadNetwork().getRoadSegments();
    for (final RoadSegment seg : _roadSegments) {
      {
        RoadSegmentData roadSegmentData = new RoadSegmentData(seg);
        Iterable<Point2d> pts = seg.points();
        for (final Point2d pt : pts) {
          boolean _containsKey = stops.containsKey(pt);
          if (_containsKey) {
            Integer _get = stops.get(pt);
            int _plus = ((_get).intValue() + 1);
            stops.replace(pt, ((Integer) Integer.valueOf(_plus)));
          } else {
            Integer _integer = new Integer(1);
            stops.put(pt, _integer);
          }
        }
        this.roadSegmentDataCollection.add(roadSegmentData);
      }
    }
    StopSign stop = null;
    TrafficLight trafficLight = null;
    Set<Point2d> _keySet = stops.keySet();
    for (final Point2d key : _keySet) {
      {
        Integer cpt = stops.get(key);
        if (((cpt).intValue() == 3)) {
          double _x = key.getX();
          double _y = key.getY();
          Point2f _point2f = new Point2f(_x, _y);
          StopSign _stopSign = new StopSign(_point2f);
          stop = _stopSign;
          this.environmentObjects.add(stop);
          HashSet<RoadSegmentData> segments = this.roadSegmentDataCollection.findRoadSegmentsForConnection(key);
          for (final RoadSegmentData segment : segments) {
            Point2d _beginPoint = segment.getBeginPoint();
            boolean _tripleEquals = (_beginPoint == key);
            if (_tripleEquals) {
              segment.setObjectAtStart(stop);
            } else {
              Point2d _endPoint = segment.getEndPoint();
              boolean _tripleEquals_1 = (_endPoint == key);
              if (_tripleEquals_1) {
                segment.setObjectAtEnd(stop);
              }
            }
          }
        } else {
          if (((cpt).intValue() > 3)) {
            double _x_1 = key.getX();
            double _y_1 = key.getY();
            Point2f _point2f_1 = new Point2f(_x_1, _y_1);
            Point2f _point2f_2 = new Point2f(_point2f_1);
            TrafficLight _trafficLight = new TrafficLight(_point2f_2);
            trafficLight = _trafficLight;
            trafficLight.changeColor(TrafficLightColor.GREEN);
            this.environmentObjects.add(trafficLight);
            HashSet<RoadSegmentData> segments_1 = this.roadSegmentDataCollection.findRoadSegmentsForConnection(key);
            for (final RoadSegmentData segment_1 : segments_1) {
              Point2d _beginPoint_1 = segment_1.getBeginPoint();
              boolean _tripleEquals_2 = (_beginPoint_1 == key);
              if (_tripleEquals_2) {
                segment_1.setObjectAtStart(trafficLight);
              } else {
                Point2d _endPoint_1 = segment_1.getEndPoint();
                boolean _tripleEquals_3 = (_endPoint_1 == key);
                if (_tripleEquals_3) {
                  segment_1.setObjectAtEnd(trafficLight);
                }
              }
            }
          }
        }
      }
    }
    double _maxX = this.network.getMapElementAt(0).getGeoLocation().toBounds2D().getMaxX();
    double _maxY = this.network.getMapElementAt(0).getGeoLocation().toBounds2D().getMaxY();
    Point2f _point2f = new Point2f(_maxX, _maxY);
    Car car = new Car(_point2f, 0, 0, 0, 0);
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(CarAgent.class, car.getID(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext());
    this.bodies.add(car);
    double _maxX_1 = this.network.getMapElementAt(3).getGeoLocation().toBounds2D().getMaxX();
    double _maxY_1 = this.network.getMapElementAt(3).getGeoLocation().toBounds2D().getMaxY();
    Point2f _point2f_1 = new Point2f(_maxX_1, _maxY_1);
    Car car2 = new Car(_point2f_1, 0, 0, 0, 0);
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER_1 = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER_1.spawnInContextWithID(CarAgent.class, car2.getID(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.getDefaultContext());
    this.bodies.add(car2);
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
  
  protected void runEnvironmentBehavior() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("runEnvironmentBehavior");
  }
  
  @SyntheticMember
  private void $behaviorUnit$requestAStar$3(final requestAStar occurrence) {
    double _maxX = this.network.getMapElementAt(0).getGeoLocation().toBounds2D().getMaxX();
    double _maxY = this.network.getMapElementAt(0).getGeoLocation().toBounds2D().getMaxY();
    Point2d startPoint = new Point2d(_maxX, _maxY);
    double _maxX_1 = this.network.getMapElementAt(3).getGeoLocation().toBounds2D().getMaxX();
    double _maxY_1 = this.network.getMapElementAt(3).getGeoLocation().toBounds2D().getMaxY();
    Point2d endPoint = new Point2d(_maxX_1, _maxY_1);
    RoadAStar AStar = new RoadAStar();
    RoadPath path = AStar.solve(startPoint, endPoint, this.network.getRoadNetwork());
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    pathAStar _pathAStar = new pathAStar(path);
    final Scope<Address> _function = (Address it) -> {
      Address _source = occurrence.getSource();
      return Objects.equal(it, _source);
    };
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_pathAStar, _function);
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Compute perceptions for each bodies in the environment.
   */
  @Pure
  protected void computePerceptions() {
    for (final AgentBody body : this.bodies) {
      List<Percept> _perceivedObjects = body.getPerceivedObjects();
      for (final Percept o : _perceivedObjects) {
      }
    }
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * Update the environment...
   */
  @Pure
  protected void update() {
    for (final AgentBody body : this.bodies) {
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
  public Environment(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Environment(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Environment(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
