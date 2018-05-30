package environments;

import com.google.common.base.Objects;
import environments.EnvironmentObject;
import environments.EnvironmentObjectUpdateable;
import environments.EventEnvironmentObjectMouseClicked;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvironmentObjectClickable extends EnvironmentObject implements EnvironmentObjectUpdateable {
  private EventEnvironmentObjectMouseClicked clickedEvent;
  
  private boolean isMouseHover;
  
  private boolean isMouseHoverAndPressed;
  
  private boolean isMousePressed;
  
  /**
   * Avec ce constructeur on donne directement le EventEntityMouseClicked
   * 
   * @param _clickedEvent
   *            Le EventEntityMouseClicked qui contient directement les
   *            actions
   * @param _position
   *            La position de l'entit�
   * @param _size
   *            La taille de l'entit�
   */
  public EnvironmentObjectClickable(final EventEnvironmentObjectMouseClicked _clickedEvent, final Vector2f _position, final Vector2f _size) {
    super(_position, _size, true, true);
    this.clickedEvent = _clickedEvent;
    this.isMouseHover = false;
    this.isMouseHoverAndPressed = false;
    this.isMousePressed = false;
  }
  
  /**
   * Avec ce constructeur on est oblig� de d�finir le
   * EventEntityMouseClicked dans la classe qui h�rite
   * 
   * @param _position
   * La position de l'entit�
   * @param _size
   * La taille de l'entit�
   */
  public EnvironmentObjectClickable(final Vector2f _position, final Vector2f _size) {
    super(_position, _size, true, true);
    this.clickedEvent = null;
    this.isMouseHover = false;
    this.isMouseHoverAndPressed = false;
    this.isMousePressed = false;
  }
  
  /**
   * Avec ce constructeur vous devez tout initialiser dans la classe qui
   * h�rite sauf la position donn�e en param�tre
   * 
   * @param _position
   * La position de l'entit�
   */
  public EnvironmentObjectClickable(final Vector2f _position) {
    super(_position, true, true);
    this.clickedEvent = null;
    this.isMouseHover = false;
    this.isMouseHoverAndPressed = false;
    this.isMousePressed = false;
  }
  
  @Pure
  public boolean isMouseHover() {
    return this.isMouseHover;
  }
  
  @Pure
  public boolean isMouseHoverAndPressed() {
    return this.isMouseHoverAndPressed;
  }
  
  @Pure
  public boolean isMousePressed() {
    return this.isMousePressed;
  }
  
  @Override
  public void update(final GameContainer gc, final StateBasedGame sbg, final int delta) {
    Input input = gc.getInput();
    boolean _contains = this.getRect().contains(input.getMouseX(), input.getMouseY());
    if (_contains) {
      this.isMouseHover = true;
      boolean _isMouseButtonDown = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
      if (_isMouseButtonDown) {
        this.isMouseHoverAndPressed = true;
        boolean _notEquals = (!Objects.equal(this.clickedEvent, null));
        if (_notEquals) {
          this.clickedEvent.mouseClicked();
        }
      } else {
        this.isMouseHoverAndPressed = false;
      }
    } else {
      this.isMouseHover = false;
      this.isMouseHoverAndPressed = false;
    }
  }
  
  public void setClickedEvent(final EventEnvironmentObjectMouseClicked ev) {
    this.clickedEvent = ev;
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
    EnvironmentObjectClickable other = (EnvironmentObjectClickable) obj;
    if (other.isMouseHover != this.isMouseHover)
      return false;
    if (other.isMouseHoverAndPressed != this.isMouseHoverAndPressed)
      return false;
    if (other.isMousePressed != this.isMousePressed)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.isMouseHover ? 1231 : 1237);
    result = prime * result + (this.isMouseHoverAndPressed ? 1231 : 1237);
    result = prime * result + (this.isMousePressed ? 1231 : 1237);
    return result;
  }
}
