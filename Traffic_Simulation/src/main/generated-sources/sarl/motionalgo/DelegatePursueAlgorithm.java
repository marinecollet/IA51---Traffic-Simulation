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
import framework.math.Point2f;
import framework.math.Vector2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import motionalgo.PursueAlgorithm;
import motionalgo.SeekAlgorithm;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class DelegatePursueAlgorithm implements PursueAlgorithm {
  private final SeekAlgorithm seek;
  
  public DelegatePursueAlgorithm(final SeekAlgorithm seek) {
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$() {
        this.$$result = (seek != null);
      }
    }
    assert new $AssertEvaluator$().$$result;
    this.seek = seek;
  }
  
  @Override
  public MotionAlgorithmOutput run(final Point2f position, final float linearSpeed, final float maxLinear, final Point2f targetPosition, final Vector2f targetLinearMotion) {
    MotionAlgorithmOutput _xblockexpression = null;
    {
      Point2f seekTarget = targetPosition.operator_minus(targetLinearMotion);
      _xblockexpression = this.seek.run(position, linearSpeed, maxLinear, seekTarget);
    }
    return _xblockexpression;
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
}
