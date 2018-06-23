package environments;

import environments.Car;
import environments.EnvironmentObject;
import environments.RoadSegmentData;
import environments.RoadSegmentDataCollection;
import environments.StopSign;
import environments.TrafficLight;
import environments.TrafficLightColor;
import environments.Vehicle;
import framework.environment.AbstractEnvironment;
import framework.environment.AgentBody;
import framework.environment.DynamicType;
import framework.environment.Influence;
import framework.environment.MotionInfluence;
import framework.environment.Percept;
import framework.environment.SituatedObject;
import framework.math.Point2f;
import framework.math.Vector2f;
import framework.time.StepTimeManager;
import framework.time.TimeManager;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import logic.Map;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Pure;
import ui.ApplicationMap;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class CityEnvironment extends AbstractEnvironment {
  @SarlSpecification("0.7")
  @SarlElementType(10)
  private static class AnimatAction {
    private final AgentBody body;
    
    private final Vector2f move;
    
    private final float rotation;
    
    /**
     * @param object is the animat body.
     * @param move is the translation.
     * @param rotation is the rotation.
     */
    public AnimatAction(final AgentBody object, final Vector2f move, final float rotation) {
      this.body = object;
      this.move = move;
      this.rotation = rotation;
    }
    
    /**
     * Replies the moved object.
     * 
     * @return the moved object.
     */
    @Pure
    public AgentBody getObjectToMove() {
      return this.body;
    }
    
    /**
     * Replies the translation.
     * 
     * @return the translation.
     */
    @Pure
    public Vector2f getTranslation() {
      return this.move;
    }
    
    /**
     * Replies the rotation.
     * 
     * @return the rotation.
     */
    @Pure
    public float getRotation() {
      return this.rotation;
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
      AnimatAction other = (AnimatAction) obj;
      if (Float.floatToIntBits(other.rotation) != Float.floatToIntBits(this.rotation))
        return false;
      return super.equals(obj);
    }
    
    @Override
    @Pure
    @SyntheticMember
    public int hashCode() {
      int result = super.hashCode();
      final int prime = 31;
      result = prime * result + Float.floatToIntBits(this.rotation);
      return result;
    }
  }
  
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
  
  private HashMap<RoadConnection, Integer> connectionsOccurence = new HashMap<RoadConnection, Integer>();
  
  private ArrayList<RoadConnection> entryExitConnections = new ArrayList<RoadConnection>();
  
  public CityEnvironment() {
    super(Map.WIDTH, Map.HEIGHT, new StepTimeManager(500));
    RoadSegmentDataCollection _roadSegmentDataCollection = new RoadSegmentDataCollection();
    this.roadSegmentDataCollection = _roadSegmentDataCollection;
    ArrayList<EnvironmentObject> _arrayList = new ArrayList<EnvironmentObject>();
    this.environmentObjects = _arrayList;
    this.network = ((RoadNetworkLayer) ApplicationMap.getInstance().roadNetworkLayer);
    this.roadNetwork = this.network.getRoadNetwork();
    Collection<? extends RoadSegment> _roadSegments = this.network.getRoadNetwork().getRoadSegments();
    for (final RoadSegment seg : _roadSegments) {
      {
        RoadSegmentData roadSegmentData = new RoadSegmentData(seg);
        boolean _containsKey = this.connectionsOccurence.containsKey(seg.getBeginPoint());
        if (_containsKey) {
          Integer _get = this.connectionsOccurence.get(seg.getBeginPoint());
          int _plus = ((_get).intValue() + 1);
          this.connectionsOccurence.replace(seg.getBeginPoint(), ((Integer) Integer.valueOf(_plus)));
        } else {
          Integer _integer = new Integer(1);
          this.connectionsOccurence.put(seg.getBeginPoint(), _integer);
        }
        boolean _containsKey_1 = this.connectionsOccurence.containsKey(seg.getEndPoint());
        if (_containsKey_1) {
          Integer _get_1 = this.connectionsOccurence.get(seg.getEndPoint());
          int _plus_1 = ((_get_1).intValue() + 1);
          this.connectionsOccurence.replace(seg.getEndPoint(), ((Integer) Integer.valueOf(_plus_1)));
        } else {
          Integer _integer_1 = new Integer(1);
          this.connectionsOccurence.put(seg.getEndPoint(), _integer_1);
        }
        this.roadSegmentDataCollection.add(roadSegmentData);
      }
    }
    StopSign stop = null;
    TrafficLight trafficLight = null;
    Set<RoadConnection> _keySet = this.connectionsOccurence.keySet();
    for (final RoadConnection key : _keySet) {
      {
        Integer cpt = this.connectionsOccurence.get(key);
        if (((cpt).intValue() == 1)) {
          this.entryExitConnections.add(key);
        } else {
          if (((cpt).intValue() == 3)) {
            UUID _randomUUID = UUID.randomUUID();
            double _x = key.getPoint().getX();
            double _y = key.getPoint().getY();
            Point2f _point2f = new Point2f(_x, _y);
            StopSign _stopSign = new StopSign(_randomUUID, "", _point2f);
            stop = _stopSign;
            this.addEnvironmentObject(stop);
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
              UUID _randomUUID_1 = UUID.randomUUID();
              double _x_1 = key.getPoint().getX();
              double _y_1 = key.getPoint().getY();
              Point2f _point2f_1 = new Point2f(_x_1, _y_1);
              Point2f _point2f_2 = new Point2f(_point2f_1);
              TrafficLight _trafficLight = new TrafficLight(_randomUUID_1, "", _point2f_2);
              trafficLight = _trafficLight;
              trafficLight.changeColor(TrafficLightColor.GREEN);
              this.addEnvironmentObject(trafficLight);
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
    }
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
  public ArrayList<RoadConnection> getEntryExitConnections() {
    return this.entryExitConnections;
  }
  
  public void createAgentBody() {
    double _random = Math.random();
    int _size = this.entryExitConnections.size();
    double _multiply = (_random * _size);
    int random = ((int) _multiply);
    double _x = this.entryExitConnections.get(random).getPoint().getX();
    double _y = this.entryExitConnections.get(random).getPoint().getY();
    Point2f _point2f = new Point2f(_x, _y);
    Car car = new Car(_point2f, 10, 10, 10, 10);
    this.addAgentBody(car, car.getPosition(), car.getAngle());
  }
  
  protected void onAgentBodyCreated(final AgentBody body) {
  }
  
  protected void onAgentBodyDestroyed(final AgentBody body) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected List<Influence> computeEndogenousBehaviorInfluences() {
    return CollectionLiterals.<Influence>emptyList();
  }
  
  protected List<Percept> computePerceptionsFor(final AgentBody agent) {
    ArrayList<Percept> u = new ArrayList<Percept>();
    for (final EnvironmentObject o : this.environmentObjects) {
      boolean _intersects = o.element.intersects(((Shape2d<?>) ((Vehicle) agent).rectangle));
      if (_intersects) {
      }
    }
    return u;
  }
  
  protected void applyInfluences(final Collection<MotionInfluence> motionInfluences, final Collection<Influence> otherInfluences, final TimeManager timeManager) {
    int _size = motionInfluences.size();
    ArrayList<CityEnvironment.AnimatAction> actions = new ArrayList<CityEnvironment.AnimatAction>(_size);
    int _size_1 = motionInfluences.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size_1, true);
    for (final Integer index1 : _doubleDotLessThan) {
      {
        MotionInfluence inf1 = ((MotionInfluence[])Conversions.unwrapArray(motionInfluences, MotionInfluence.class))[(index1).intValue()];
        AgentBody body1 = this.getAgentBodyFor(inf1.getEmitter());
        Vector2f move = null;
        float rotation = 0;
        DynamicType _type = inf1.getType();
        boolean _tripleEquals = (_type == DynamicType.STEERING);
        if (_tripleEquals) {
          move = this.computeSteeringTranslation(body1, inf1.getLinearInfluence(), timeManager);
          rotation = this.computeSteeringRotation(body1, inf1.getAngularInfluence(), timeManager);
        } else {
          move = this.computeKinematicTranslation(body1, inf1.getLinearInfluence(), timeManager);
          rotation = this.computeKinematicRotation(body1, inf1.getAngularInfluence(), timeManager);
        }
        CityEnvironment.AnimatAction _animatAction = new CityEnvironment.AnimatAction(body1, move, rotation);
        actions.add(_animatAction);
      }
    }
    for (final CityEnvironment.AnimatAction action : actions) {
      {
        AgentBody body = action.getObjectToMove();
        if ((body != null)) {
          this.move(body, action.getTranslation(), action.rotation);
        }
      }
    }
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
