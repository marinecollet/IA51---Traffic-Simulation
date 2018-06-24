package agents;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.ArrayList;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Send new traffic lights states
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class ChangeTrafficLight extends Event {
  public ArrayList<UUID> greens;
  
  public ArrayList<UUID> oranges;
  
  public ArrayList<UUID> reds;
  
  public ChangeTrafficLight(final ArrayList<UUID> greens, final ArrayList<UUID> oranges, final ArrayList<UUID> reds) {
    this.greens = greens;
    this.oranges = oranges;
    this.reds = reds;
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
  
  /**
   * Returns a String representation of the ChangeTrafficLight event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("greens", this.greens);
    builder.add("oranges", this.oranges);
    builder.add("reds", this.reds);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3228547192L;
}
