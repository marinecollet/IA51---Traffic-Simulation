package environments;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * @author jerem
 */
@FunctionalInterface
@SarlSpecification("0.7")
@SarlElementType(11)
@SuppressWarnings("all")
public interface EventEnvironmentObjectMouseClicked {
  public abstract void mouseClicked();
}
