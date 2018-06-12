package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * Send the A* path
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class pathAStar extends Event {
  @SyntheticMember
  public pathAStar() {
    super();
  }
  
  @SyntheticMember
  public pathAStar(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
