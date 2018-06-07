package agents;

import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

/**
 * @author jerem
 */
@FunctionalInterface
@SarlSpecification("0.7")
@SarlElementType(19)
@SuppressWarnings("all")
public interface MovingVehicle extends Capacity {
  public abstract Point2f moveVehicle(final Point2f from, final Point2f to, final double speed);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MovingVehicle> extends Capacity.ContextAwareCapacityWrapper<C> implements MovingVehicle {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public Point2f moveVehicle(final Point2f from, final Point2f to, final double speed) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.moveVehicle(from, to, speed);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
