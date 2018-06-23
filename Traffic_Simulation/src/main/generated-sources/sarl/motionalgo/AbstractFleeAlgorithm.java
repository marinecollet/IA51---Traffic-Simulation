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
import framework.math.Point2f;
import framework.math.Vector2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import motionalgo.FleeAlgorithm;

/**
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractFleeAlgorithm implements FleeAlgorithm {
  public abstract DynamicType getDynamicType();
  
  @Override
  public MotionAlgorithmOutput run(final Point2f position, final float linearSpeed, final float maxLinear, final Point2f target) {
    DynamicType _dynamicType = this.getDynamicType();
    MotionAlgorithmOutput output = new MotionAlgorithmOutput(_dynamicType);
    Vector2f direction = position.operator_minus(target);
    direction.setLength(maxLinear);
    output.setLinear(direction);
    return output;
  }
  
  @SyntheticMember
  public AbstractFleeAlgorithm() {
    super();
  }
}