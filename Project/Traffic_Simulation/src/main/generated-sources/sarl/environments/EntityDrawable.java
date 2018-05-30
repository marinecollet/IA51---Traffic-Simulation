package environments;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.newdawn.slick.Graphics;

/**
 * @author jerem
 */
@FunctionalInterface
@SarlSpecification("0.7")
@SarlElementType(11)
@SuppressWarnings("all")
public interface EntityDrawable {
  public abstract void render(final Graphics arg2);
}
