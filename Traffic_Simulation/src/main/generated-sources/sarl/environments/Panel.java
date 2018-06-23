package environments;

import environments.EnvironmentObject;
import framework.math.Point2f;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Panel extends EnvironmentObject {
  @Override
  @Pure
  @SyntheticMember
  public Panel clone() {
    try {
      return (Panel) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  @DefaultValueUse("java.util.UUID,framework.math.Point2f,java.lang.String")
  public Panel(final UUID id, final String name) {
    super(id, name);
  }
  
  @SyntheticMember
  @DefaultValueSource
  public Panel(final UUID id, @DefaultValue("environments.EnvironmentObject#NEW_0") final Point2f position, final String name) {
    super(id, position, name);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1L;
}
