package environments;

import environments.Clickable;
import environments.Drawable;
import environments.EventEnvironmentObjectMouseClicked;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Button extends Clickable implements Drawable {
  private Image img_actual;
  
  private Image img_idle;
  
  private Image img_hover;
  
  private Image img_pressed;
  
  /**
   * Constructeur.
   * @param _position
   * @param _img_idle
   * @param _img_hover
   * @param _img_pressed
   */
  public Button(final Vector2f _position, final String _img_idle, final String _img_hover, final String _img_pressed) {
    super(_position);
    try {
      Image _image = new Image(_img_idle);
      this.img_idle = _image;
      Image _image_1 = new Image(_img_hover);
      this.img_hover = _image_1;
      Image _image_2 = new Image(_img_pressed);
      this.img_pressed = _image_2;
    } catch (final Throwable _t) {
      if (_t instanceof SlickException) {
        final SlickException e = (SlickException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    this.img_actual = this.img_idle;
    Vector2f _size = this.getSize();
    _size.x = this.img_actual.getWidth();
    Vector2f _size_1 = this.getSize();
    _size_1.y = this.img_actual.getHeight();
  }
  
  /**
   * Constructeur.
   * @param _position
   * @param height
   * @param width
   * @param _img_idle
   * @param _img_hover
   * @param _img_pressed
   */
  public Button(final Vector2f _position, final float height, final float width, final String _img_idle, final String _img_hover, final String _img_pressed) {
    super(_position);
    try {
      Image _image = new Image(_img_idle);
      this.img_idle = _image;
      Image _image_1 = new Image(_img_hover);
      this.img_hover = _image_1;
      Image _image_2 = new Image(_img_pressed);
      this.img_pressed = _image_2;
    } catch (final Throwable _t) {
      if (_t instanceof SlickException) {
        final SlickException e = (SlickException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    this.img_actual = this.img_idle;
    Vector2f _size = this.getSize();
    _size.x = width;
    Vector2f _size_1 = this.getSize();
    _size_1.y = height;
  }
  
  @Override
  public void render(final Graphics arg2) {
    this.img_actual.draw(this.getPosition().x, this.getPosition().y, this.getSize().x, this.getSize().y);
  }
  
  @Override
  public void update(final GameContainer gc, final StateBasedGame sbg, final int delta) {
    super.update(gc, sbg, delta);
    boolean _isMouseHover = this.isMouseHover();
    if (_isMouseHover) {
      boolean _isMouseHoverAndPressed = this.isMouseHoverAndPressed();
      if (_isMouseHoverAndPressed) {
        this.img_actual = this.img_pressed;
      } else {
        this.img_actual = this.img_hover;
      }
    } else {
      this.img_actual = this.img_idle;
    }
  }
  
  /**
   * Permet de mettre en place l'action qui sera menï¿½ quand le bouton de la
   * souris sera cliquï¿½
   * 
   * @param _mouseClicked
   * L'interface surchargï¿½e
   */
  public void setEventCallback(final EventEnvironmentObjectMouseClicked _mouseClicked) {
    this.setClickedEvent(_mouseClicked);
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
