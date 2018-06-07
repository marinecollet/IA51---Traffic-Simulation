package environments;

import com.google.common.base.Objects;
import environments.EnvironmentObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class EnvironmentObjectCollection {
  private ArrayList<EnvironmentObject> entities;
  
  public EnvironmentObjectCollection() {
    ArrayList<EnvironmentObject> _arrayList = new ArrayList<EnvironmentObject>();
    this.entities = _arrayList;
  }
  
  @Pure
  public ArrayList<EnvironmentObject> getEntities() {
    return this.entities;
  }
  
  public boolean add(final EnvironmentObject _entity) {
    return this.entities.add(_entity);
  }
  
  public void addAt(final EnvironmentObject _entity, final int index) {
    this.entities.add(index, _entity);
  }
  
  public boolean delete(final EnvironmentObject _entity) {
    return this.entities.remove(_entity);
  }
  
  /**
   * Permet de supprimer avec une �galit� profonde, contrairement a delete
   * (qui utilise la methode equals pour comparer les objets)
   * 
   * @param _entity
   * @return
   */
  public boolean deleteObject(final EnvironmentObject _entity) {
    for (int i = 0; (i < this.entities.size()); i++) {
      EnvironmentObject _get = this.entities.get(i);
      boolean _equals = Objects.equal(_get, _entity);
      if (_equals) {
        this.entities.remove(i);
        return true;
      }
    }
    return false;
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
