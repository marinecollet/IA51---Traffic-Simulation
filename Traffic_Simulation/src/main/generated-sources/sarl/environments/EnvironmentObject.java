package environments;

import framework.environment.AbstractSituatedObject;
import framework.math.Circle2f;
import framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapPolygon;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Define an environment object
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvironmentObject extends AbstractSituatedObject {
  /**
   * Represent an object
   */
  protected MapPolygon element;
  
  /**
   * Name of the object
   */
  private String name;
  
  /**
   * Points of the object
   */
  private ArrayList<Point2d> points = new ArrayList<Point2d>();
  
  public EnvironmentObject(final UUID id, final Point2f position, final String name) {
    super(id, new Circle2f(position, 2), position);
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$() {
        this.$$result = (id != null);
      }
    }
    assert new $AssertEvaluator$().$$result;
    this.name = name;
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
  private final static long serialVersionUID = 1545366784L;
}
