package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@FunctionalInterface
@SarlSpecification("0.7")
@SarlElementType(19)
@SuppressWarnings("all")
public interface MovingVehicle extends Capacity {
  public abstract void moveVehicle(final Vector2f from, final Vector2f to);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MovingVehicle> extends Capacity.ContextAwareCapacityWrapper<C> implements MovingVehicle {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void moveVehicle(final Vector2f from, final Vector2f to) {
      try {
        ensureCallerInLocalThread();
        this.capacity.moveVehicle(from, to);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
