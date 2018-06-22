/**
 * $Id$
 * 
 * Copyright (c) 2011-15 Stephane GALLAND <stephane.galland@utbm.fr>.
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
import framework.math.Point2f;
import framework.math.Vector2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Random;
import motionalgo.SteeringFaceAlgorithm;
import motionalgo.WanderAlgorithm;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Steering Wander Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class SteeringWanderAlgorithm implements WanderAlgorithm {
  private final Random random = new Random();
  
  private final SteeringFaceAlgorithm faceBehaviour;
  
  private final float circleDistance;
  
  private final float circleRadius;
  
  private final float maxRotation;
  
  private float rotation;
  
  /**
   * @param circleDistance is the distance between the entity and the circle center.
   * @param circleRadius is the radius of the circle.
   * @param maxRotation is the maximal rotation of the entity.
   * @param stopRadius is the angle between the current direction and the target direction
   * under which the rotation for alignment is ignored.
   * @param decelerateRadius is the angle between the current direction and the target direction
   * under which the rotation is going slower.
   */
  public SteeringWanderAlgorithm(final float circleDistance, final float circleRadius, final float maxRotation, final float stopRadius, final float decelerateRadius) {
    this.circleDistance = circleDistance;
    this.circleRadius = circleRadius;
    this.maxRotation = maxRotation;
    this.rotation = 0f;
    SteeringFaceAlgorithm _steeringFaceAlgorithm = new SteeringFaceAlgorithm(stopRadius, decelerateRadius);
    this.faceBehaviour = _steeringFaceAlgorithm;
  }
  
  @Override
  public MotionAlgorithmOutput run(final Point2f position, final Vector2f orientation, final float linearSpeed, final float maxLinear, final float angularSpeed, final float maxAngular) {
    Vector2f circleCenter = orientation.toColinearVector(this.circleDistance);
    Vector2f displacement = circleCenter.toColinearVector(this.circleRadius);
    displacement.turn(this.rotation);
    float _rotation = this.rotation;
    double _nextGaussian = this.random.nextGaussian();
    double _multiply = (_nextGaussian * this.maxRotation);
    this.rotation = (_rotation + ((float) _multiply));
    Vector2f wanderForce = circleCenter.operator_minus(displacement);
    Point2f target = position.operator_minus(wanderForce);
    MotionAlgorithmOutput output = this.faceBehaviour.run(position, orientation, angularSpeed, maxAngular, target);
    if (((output == null) || (output.getType() != DynamicType.STEERING))) {
      MotionAlgorithmOutput _motionAlgorithmOutput = new MotionAlgorithmOutput(DynamicType.STEERING);
      output = _motionAlgorithmOutput;
    }
    Vector2f linearMotion = orientation.toColinearVector(maxLinear);
    output.setLinear(linearMotion);
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
    SteeringWanderAlgorithm other = (SteeringWanderAlgorithm) obj;
    if (Float.floatToIntBits(other.circleDistance) != Float.floatToIntBits(this.circleDistance))
      return false;
    if (Float.floatToIntBits(other.circleRadius) != Float.floatToIntBits(this.circleRadius))
      return false;
    if (Float.floatToIntBits(other.maxRotation) != Float.floatToIntBits(this.maxRotation))
      return false;
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
    result = prime * result + Float.floatToIntBits(this.circleDistance);
    result = prime * result + Float.floatToIntBits(this.circleRadius);
    result = prime * result + Float.floatToIntBits(this.maxRotation);
    result = prime * result + Float.floatToIntBits(this.rotation);
    return result;
  }
}
