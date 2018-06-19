package environments;

import agents.pathAStar;
import agents.requestAStar;
import com.google.common.base.Objects;
import environments.CityEnvironment;
import environments.RoadSegmentData;
import environments.RoadSegmentDataCollection;
import environments.StopSign;
import environments.TrafficLight;
import environments.TrafficLightColor;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import logic.PathUtils;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
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
public class AgentEnvironment extends Agent {
  /**
   * City environment
   */
  private CityEnvironment environment;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    while ((!Application.getInstance().getIsReady())) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.debug("attend");
    }
    CityEnvironment _cityEnvironment = new CityEnvironment();
    this.environment = _cityEnvironment;
    MapElementLayer<?> _roadNetworkLayer = Application.getInstance().getRoadNetworkLayer();
    this.environment.setNetwork(((RoadNetworkLayer) _roadNetworkLayer));
    RoadSegmentDataCollection _roadSegmentDataCollection = new RoadSegmentDataCollection();
    this.environment.setRoadSegmentDataCollection(_roadSegmentDataCollection);
    HashMap<Point2d, Integer> connectionsOccurence = new HashMap<Point2d, Integer>();
    Collection<? extends RoadSegment> _roadSegments = this.environment.getNetwork().getRoadNetwork().getRoadSegments();
    for (final RoadSegment seg : _roadSegments) {
      {
        RoadSegmentData roadSegmentData = new RoadSegmentData(seg);
        Iterable<Point2d> pts = seg.points();
        for (final Point2d pt : pts) {
          boolean _containsKey = connectionsOccurence.containsKey(pt);
          if (_containsKey) {
            Integer _get = connectionsOccurence.get(pt);
            int _plus = ((_get).intValue() + 1);
            connectionsOccurence.replace(pt, ((Integer) Integer.valueOf(_plus)));
          } else {
            Integer _integer = new Integer(1);
            connectionsOccurence.put(pt, _integer);
          }
        }
        this.environment.getRoadSegmentDataCollection().add(roadSegmentData);
      }
    }
    StopSign stop = null;
    TrafficLight trafficLight = null;
    Set<Point2d> _keySet = connectionsOccurence.keySet();
    for (final Point2d key : _keySet) {
      {
        Integer cpt = connectionsOccurence.get(key);
        if (((cpt).intValue() == 3)) {
          double _x = key.getX();
          double _y = key.getY();
          Point2f _point2f = new Point2f(_x, _y);
          StopSign _stopSign = new StopSign(_point2f);
          stop = _stopSign;
          this.environment.addEnvironmentObject(stop);
          HashSet<RoadSegmentData> segments = this.environment.getRoadSegmentDataCollection().findRoadSegmentsForConnection(key);
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
            this.environment.addEnvironmentObject(trafficLight);
            HashSet<RoadSegmentData> segments_1 = this.environment.getRoadSegmentDataCollection().findRoadSegmentsForConnection(key);
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
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_simulationAgentReady);
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
    double _maxX = this.environment.getNetwork().getMapElementAt(0).getGeoLocation().toBounds2D().getMaxX();
    double _maxY = this.environment.getNetwork().getMapElementAt(0).getGeoLocation().toBounds2D().getMaxY();
    Point2d startPoint = new Point2d(_maxX, _maxY);
    double _maxX_1 = this.environment.getNetwork().getMapElementAt(3).getGeoLocation().toBounds2D().getMaxX();
    double _maxY_1 = this.environment.getNetwork().getMapElementAt(3).getGeoLocation().toBounds2D().getMaxY();
    Point2d endPoint = new Point2d(_maxX_1, _maxY_1);
    ArrayList<RoadSegment> path = PathUtils.GPS(startPoint, endPoint, this.environment.getRoadNetwork());
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    pathAStar _pathAStar = new pathAStar(path);
    final Scope<Address> _function = (Address it) -> {
      Address _source = occurrence.getSource();
      return Objects.equal(it, _source);
    };
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_pathAStar, _function);
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
  public AgentEnvironment(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public AgentEnvironment(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public AgentEnvironment(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
