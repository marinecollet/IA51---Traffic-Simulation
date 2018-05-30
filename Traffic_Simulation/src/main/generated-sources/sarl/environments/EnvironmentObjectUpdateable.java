package environments;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@FunctionalInterface
@SarlSpecification("0.7")
@SarlElementType(11)
@SuppressWarnings("all")
public interface EnvironmentObjectUpdateable {
  public abstract void update(final GameContainer gc, final StateBasedGame sbg, final int delta);
}
