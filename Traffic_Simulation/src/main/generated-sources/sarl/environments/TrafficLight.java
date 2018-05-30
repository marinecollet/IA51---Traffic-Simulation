package environments;

import environments.Panel;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class TrafficLight extends Panel {
  public void render(final Graphics arg2) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void update(final GameContainer gc, final StateBasedGame sbg, final int delta) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public void run() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @SyntheticMember
  public TrafficLight(final Vector2f _position, final boolean isUpdatable, final boolean isDrawable) {
    super(_position, isUpdatable, isDrawable);
  }
  
  @SyntheticMember
  public TrafficLight(final Vector2f _position, final Vector2f _size, final boolean isUpdatable, final boolean isDrawable) {
    super(_position, _size, isUpdatable, isDrawable);
  }
}
