package environments;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Entity {
  /**
   * La position de l'entitï¿½
   */
  private final Vector2f position;
  
  private final Vector2f size;
  
  /**
   * Permet de savoir si l'entité est updatable
   */
  private final boolean updatable;
  
  /**
   * Permet de savoir si l'entité est dessinable
   */
  private final boolean drawable;
  
  /**
   * Construit une entitï¿½ qui possï¿½de la position et la taille donnï¿½e
   * 
   * @param _position
   *            La position de l'entitï¿½
   * @param _size
   *            La taille de l'entitï¿½
   */
  public Entity(final Vector2f _position, final Vector2f _size) {
    Vector2f _vector2f = new Vector2f(_position.x, _position.y);
    this.position = _vector2f;
    Vector2f _vector2f_1 = new Vector2f(_size.x, _size.y);
    this.size = _vector2f_1;
    this.updatable = false;
    this.drawable = false;
  }
  
  /**
   * Construit une entitï¿½ avec la position donnï¿½e mais une taille qui est
   * de 0 pour la largeur ainsi que la hauteur de l'entitï¿½
   * 
   * @param _position
   *            La position de l'entitï¿½
   */
  public Entity(final Vector2f _position) {
    Vector2f _vector2f = new Vector2f(_position.x, _position.y);
    this.position = _vector2f;
    Vector2f _vector2f_1 = new Vector2f();
    this.size = _vector2f_1;
    this.updatable = false;
    this.drawable = false;
  }
  
  /**
   * Rï¿½cupï¿½rer la position actuelle de l'entitï¿½
   * 
   * @return la position
   */
  @Pure
  public Vector2f getPosition() {
    return this.position;
  }
  
  /**
   * Donner une position qui sera la nouvelle position de l'entitï¿½
   * 
   * @param _position
   * la nouvelle position
   */
  public float setPosition(final Vector2f _position) {
    float _xblockexpression = (float) 0;
    {
      this.position.x = _position.x;
      _xblockexpression = this.position.y = _position.y;
    }
    return _xblockexpression;
  }
  
  /**
   * Permet de dï¿½placer l'entitï¿½ par la valeur de l'offset donnï¿½ sur les
   * axes x et y
   * 
   * @param _offset
   * les offset de dï¿½placement sur x et y
   */
  public float move(final Vector2f _offset) {
    float _xblockexpression = (float) 0;
    {
      float _x = this.position.x;
      this.position.x = (_x + _offset.x);
      float _y = this.position.y;
      _xblockexpression = this.position.y = (_y + _offset.y);
    }
    return _xblockexpression;
  }
  
  /**
   * Permet de rï¿½cupï¿½rer un vecteur qui donne la taille de l'entitï¿½ (sa
   * largeur dans x et sa hauteur dans y)
   * 
   * @return le vecteur contenant la taille
   */
  @Pure
  public Vector2f getSize() {
    return this.size;
  }
  
  /**
   * Permet de dï¿½finir la taille de l'entitï¿½
   * 
   * @param _size
   * la composante x reprï¿½sente la largeur et la composante y
   * reprï¿½sente la hauteur de l'entitï¿½
   */
  public float setSize(final Vector2f _size) {
    float _xblockexpression = (float) 0;
    {
      this.size.x = _size.x;
      _xblockexpression = this.size.y = _size.y;
    }
    return _xblockexpression;
  }
  
  /**
   * Permet de rï¿½cupï¿½rer un rectangle dont les composantes reprï¿½sentent
   * la position ainsi que la taille de l'entitï¿½ :<br>
   * - x : position en x<br>
   * - y : position en y<br>
   * - width : largeur de l'entitï¿½<br>
   * - height : hauteur de l'entitï¿½<br>
   * Ce rectangle peut servir dans le cadre de la dï¿½tection d'un clique
   * souris par exemple
   * 
   * @return
   */
  @Pure
  public Rectangle getRect() {
    final Rectangle rect = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    return rect;
  }
  
  /**
   * Permet de savoir si l'entité peut être updaté
   * 
   * @return updatable
   */
  @Pure
  public boolean isUpdateble() {
    return this.updatable;
  }
  
  /**
   * Permet de savoir si l'entité peut être dessiné
   * 
   * @return drawable
   */
  @Pure
  public boolean isDrawable() {
    return this.drawable;
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
    Entity other = (Entity) obj;
    if (other.updatable != this.updatable)
      return false;
    if (other.drawable != this.drawable)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.updatable ? 1231 : 1237);
    result = prime * result + (this.drawable ? 1231 : 1237);
    return result;
  }
}
