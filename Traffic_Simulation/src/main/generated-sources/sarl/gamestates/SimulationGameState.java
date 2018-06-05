package gamestates;

import environments.Car;
import environments.EnvironmentObject;
import environments.EnvironmentObjectCollection;
import environments.RoadNetwork;
import gamestates.SimulationWindow;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import logic.Map;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class SimulationGameState extends BasicGameState {
  private RoadNetwork rd = new RoadNetwork();
  
  private final EnvironmentObjectCollection entities = new EnvironmentObjectCollection();
  
  public int getID() {
    return SimulationWindow.GS_SIMULATION();
  }
  
  public void init(final GameContainer arg0, final StateBasedGame arg1) throws SlickException {
    ArrayList<EnvironmentObject> list = this.rd.createTestMap();
    for (final EnvironmentObject el : list) {
      this.entities.add(el);
    }
    Vector2f _vector2f = new Vector2f((Map.WIDTH / 2), (Map.HEIGHT / 2));
    Car car = new Car(_vector2f);
    this.entities.add(car);
  }
  
  public void render(final GameContainer arg0, final StateBasedGame arg1, final Graphics arg2) throws SlickException {
    this.entities.render(arg2);
  }
  
  public void update(final GameContainer arg0, final StateBasedGame arg1, final int arg2) throws SlickException {
    this.entities.update(arg0, arg1, arg2);
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
  public SimulationGameState() {
    super();
  }
}
