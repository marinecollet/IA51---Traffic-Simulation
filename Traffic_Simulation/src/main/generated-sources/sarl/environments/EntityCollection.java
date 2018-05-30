package environments;

import com.google.common.base.Objects;
import environments.Entity;
import environments.EntityDrawable;
import environments.EntityUpdateable;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import logic.Map;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class EntityCollection implements EntityUpdateable, EntityDrawable {
  private ArrayList<Entity> entities;
  
  public EntityCollection() {
    ArrayList<Entity> _arrayList = new ArrayList<Entity>();
    this.entities = _arrayList;
  }
  
  @Pure
  public ArrayList<Entity> getEntities() {
    return this.entities;
  }
  
  public boolean add(final Entity _entity) {
    return this.entities.add(_entity);
  }
  
  public void addAt(final Entity _entity, final int index) {
    this.entities.add(index, _entity);
  }
  
  public boolean delete(final Entity _entity) {
    return this.entities.remove(_entity);
  }
  
  /**
   * Permet de supprimer avec une �galit� profonde, contrairement a delete
   * (qui utilise la methode equals pour comparer les objets)
   * 
   * @param _entity
   * @return
   */
  public boolean deleteObject(final Entity _entity) {
    for (int i = 0; (i < this.entities.size()); i++) {
      Entity _get = this.entities.get(i);
      boolean _equals = Objects.equal(_get, _entity);
      if (_equals) {
        this.entities.remove(i);
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void render(final Graphics arg2) {
    for (final Entity entitie : this.entities) {
      boolean _isDrawable = entitie.isDrawable();
      if (_isDrawable) {
        ((EntityDrawable) entitie).render(arg2);
      }
    }
  }
  
  @Override
  public void update(final GameContainer gc, final StateBasedGame sbg, final int delta) {
    for (final Entity entitie : this.entities) {
      boolean _isUpdateble = entitie.isUpdateble();
      if (_isUpdateble) {
        ((EntityUpdateable) entitie).update(gc, sbg, (delta * Map.getInstance().simulationSpeed));
      }
    }
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
