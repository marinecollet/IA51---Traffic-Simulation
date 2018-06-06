package environments;

import environments.EnvironmentObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Vehicle extends EnvironmentObject {
  private final UUID agentId;
  
  public Vehicle(final Vector2f vector, final UUID id) {
    super(vector, true, true);
    this.agentId = id;
  }
  
  @Pure
  public final UUID getAgentId() {
    return this.agentId;
  }
  
  public abstract void moveVehicle(final Vector2f newPos);
  
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
    Vehicle other = (Vehicle) obj;
    if (!Objects.equals(this.agentId, other.agentId)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.agentId);
    return result;
  }
}
