package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * Request the A* path
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class requestAStar extends Event {
  @SyntheticMember
  public requestAStar() {
    super();
  }
  
  @SyntheticMember
  public requestAStar(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
