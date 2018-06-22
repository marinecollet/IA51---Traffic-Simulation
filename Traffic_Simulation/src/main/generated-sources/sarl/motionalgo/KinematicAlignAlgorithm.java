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

@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class KinematicAlignAlgorithm implements AlignAlgorithm {
  @Override
  public MotionAlgorithmOutput run(final Vector2f orientation, final float angularSpeed, final float maxAngular, final Vector2f target) {
    MotionAlgorithmOutput output = new MotionAlgorithmOutput(DynamicType.KINEMATIC);
    float angle = orientation.signedAngle(target);
    output.setAngular(MathUtil.clamp(angle, (-maxAngular), maxAngular));
    return output;
  }
  
  @SyntheticMember
  public KinematicAlignAlgorithm() {
    super();
  }
}
