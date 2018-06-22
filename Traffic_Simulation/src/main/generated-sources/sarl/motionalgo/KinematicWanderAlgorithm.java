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
import motionalgo.WanderAlgorithm;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Kinematic Wander Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class KinematicWanderAlgorithm implements WanderAlgorithm {
  private final Random random = new Random();
  
  @Override
  public MotionAlgorithmOutput run(final Point2f position, final Vector2f orientation, final float linearSpeed, final float maxLinear, final float angularSpeed, final float maxAngular) {
    MotionAlgorithmOutput output = new MotionAlgorithmOutput(DynamicType.KINEMATIC);
    Vector2f v = orientation.clone();
    v.setLength(maxLinear);
    output.setLinear(v);
    double _nextGaussian = this.random.nextGaussian();
    double _multiply = (_nextGaussian * maxAngular);
    float rotation = ((float) _multiply);
    output.setAngular(rotation);
    return output;
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
  public KinematicWanderAlgorithm() {
    super();
  }
}
