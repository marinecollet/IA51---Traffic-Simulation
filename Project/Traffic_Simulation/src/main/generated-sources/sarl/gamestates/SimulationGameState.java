package gamestates;

import environments.Car;
import environments.EntityCollection;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
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
  private final EntityCollection entities = new EntityCollection();
  
  public int getID() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void init(final GameContainer arg0, final StateBasedGame arg1) throws SlickException {
    Vector2f _vector2f = new Vector2f(0.0f, 0.0f);
    Car car = new Car(_vector2f);
    this.entities.add(car);
  }
  
  public void render(final GameContainer arg0, final StateBasedGame arg1, final Graphics arg2) throws SlickException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void update(final GameContainer arg0, final StateBasedGame arg1, final int arg2) throws SlickException {
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
  
  @SyntheticMember
  public SimulationGameState() {
    super();
  }
}
