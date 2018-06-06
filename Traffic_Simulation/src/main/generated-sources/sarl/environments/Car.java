package environments;

import environments.Vehicle;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends Vehicle {
  private Polygon polygon;
  
  private Vector2f position;
  
  public Car(final Vector2f vector, final UUID id) {
    super(vector, id);
    this.position = vector;
    Polygon _polygon = new Polygon();
    this.polygon = _polygon;
    this.polygon.addPoint((this.position.x - 1f), (this.position.y - 5f));
    this.polygon.addPoint((this.position.x - 1f), (this.position.y + 5f));
    this.polygon.addPoint((this.position.x + 1f), (this.position.y - 5f));
    this.polygon.addPoint((this.position.x + 1f), (this.position.y + 5f));
    this.polygon.setClosed(true);
  }
  
  @Override
  public void moveVehicle(final Vector2f newPos) {
    this.position = newPos;
    Polygon _polygon = new Polygon();
    this.polygon = _polygon;
    this.polygon.addPoint((this.position.x - 1f), (this.position.y - 5f));
    this.polygon.addPoint((this.position.x - 1f), (this.position.y + 5f));
    this.polygon.addPoint((this.position.x + 1f), (this.position.y - 5f));
    this.polygon.addPoint((this.position.x + 1f), (this.position.y + 5f));
    this.polygon.setClosed(true);
    super.move(newPos);
  }
  
  @Pure
  public Vector2f getPosition() {
    return this.position;
  }
  
  public void render(final Graphics arg2) {
    arg2.setColor(Color.red);
    arg2.draw(this.polygon);
  }
  
  public void update(final GameContainer gc, final StateBasedGame sbg, final int delta) {
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
