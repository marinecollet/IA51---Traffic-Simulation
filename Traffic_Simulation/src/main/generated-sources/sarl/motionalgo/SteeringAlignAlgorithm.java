/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package motionalgo;

import framework.agent.MotionAlgorithmOutput;
import framework.environment.DynamicType;
import framework.math.MathUtil;
import framework.math.Vector2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import motionalgo.AlignAlgorithm;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class SteeringAlignAlgorithm implements AlignAlgorithm {
  private final static float TIME_TO_REACH_DIRECTION = 2f;
  
  private final float stopRadius;
  
  private final float decelerateRadius;
  
  /**
   * @param stopRadius is the angle between the current direction and the target direction
   * under which the rotation for alignment is ignored.
   * @param decelerateRadius is the angle between the current direction and the target direction
   * under which the rotation is going slower.
   */
  public SteeringAlignAlgorithm(final float stopRadius, final float decelerateRadius) {
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$() {
        this.$$result = (stopRadius < decelerateRadius);
      }
    }
    assert new $AssertEvaluator$().$$result;
    this.stopRadius = stopRadius;
    this.decelerateRadius = decelerateRadius;
  }
  
  @Override
  public MotionAlgorithmOutput run(final Vector2f orientation, final float angularSpeed, final float maxAngular, final Vector2f target) {
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$() {
        this.$$result = (maxAngular >= 0.0f);
      }
    }
    assert new $AssertEvaluator$().$$result;
    final float rotation = orientation.operator_upTo(target);
    final float rotationSize = Math.abs(rotation);
    float acceleration = 0f;
    if ((rotationSize >= this.stopRadius)) {
      final float speed = Math.abs(angularSpeed);
      final float directionToTarget = Math.signum(rotation);
      if (((rotationSize > this.decelerateRadius) || (speed == 0f))) {
        acceleration = (maxAngular * directionToTarget);
      } else {
        acceleration = (rotation / (SteeringAlignAlgorithm.TIME_TO_REACH_DIRECTION * SteeringAlignAlgorithm.TIME_TO_REACH_DIRECTION));
      }
    } else {
      acceleration = ((-angularSpeed) / (SteeringAlignAlgorithm.TIME_TO_REACH_DIRECTION * SteeringAlignAlgorithm.TIME_TO_REACH_DIRECTION));
    }
    MotionAlgorithmOutput output = new MotionAlgorithmOutput(DynamicType.STEERING);
    output.setAngular(MathUtil.clamp(acceleration, (-maxAngular), maxAngular));
    return output;
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
    SteeringAlignAlgorithm other = (SteeringAlignAlgorithm) obj;
    if (Float.floatToIntBits(other.stopRadius) != Float.floatToIntBits(this.stopRadius))
      return false;
    if (Float.floatToIntBits(other.decelerateRadius) != Float.floatToIntBits(this.decelerateRadius))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.stopRadius);
    result = prime * result + Float.floatToIntBits(this.decelerateRadius);
    return result;
  }
}
