package environments;

import environments.Point;
import framework.environment.AbstractSituatedObject;
import framework.math.Point2f;
import framework.math.Rectangle2f;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvironmentObject extends AbstractSituatedObject {
  protected MapPolygon element;
  
  private String name;
  
  private Serializable type;
  
  private ArrayList<Point> points = new ArrayList<Point>();
  
  @DefaultValueSource
  public EnvironmentObject(final UUID id, @DefaultValue("environments.EnvironmentObject#NEW_0") final Point2f position, final String name) {
    super(id, new Rectangle2f(new Point2f(), new Point2f()));
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$() {
        this.$$result = (id != null);
      }
    }
    assert new $AssertEvaluator$().$$result;
    this.name = name;
    if ((position != null)) {
      this.getPosition().set(position);
    }
  }
  
  /**
   * Default value for the parameter position
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Point2f $DEFAULT_VALUE$NEW_0 = null;
  
  @DefaultValueUse("java.util.UUID,framework.math.Point2f,java.lang.String")
  @SyntheticMember
  public EnvironmentObject(final UUID id, final String name) {
    this(id, $DEFAULT_VALUE$NEW_0, name);
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
    EnvironmentObject other = (EnvironmentObject) obj;
    if (!Objects.equals(this.name, other.name)) {
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
    result = prime * result + Objects.hashCode(this.name);
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public EnvironmentObject clone() {
    try {
      return (EnvironmentObject) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3381123413L;
}
