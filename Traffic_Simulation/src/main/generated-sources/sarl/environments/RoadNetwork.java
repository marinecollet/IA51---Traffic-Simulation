package environments;

import environments.RoadConnection;
import environments.RoadSegment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.util.ArrayList;
import org.arakhne.afc.io.dbase.DBaseFileReader;
import org.arakhne.afc.io.shape.ShapeFileReader;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadNetwork {
  private final ArrayList<RoadSegment> segments;
  
  private final ArrayList<RoadConnection> connections;
  
  public RoadNetwork() {
    ArrayList<RoadSegment> _arrayList = new ArrayList<RoadSegment>();
    this.segments = _arrayList;
    ArrayList<RoadConnection> _arrayList_1 = new ArrayList<RoadConnection>();
    this.connections = _arrayList_1;
  }
  
  public Integer LoadShapeFile() {
    try {
      Integer _xblockexpression = null;
      {
        File shapefile = new File("asset/Belfort.shp");
        File dbfile = new File("asset/Belfort.dbf");
        DBaseFileReader dbreader = new DBaseFileReader(dbfile);
        ShapeFileReader<Object> reader = new ShapeFileReader<Object>(shapefile, dbreader, null);
        _xblockexpression = InputOutput.<Integer>println(Integer.valueOf(reader.getFileReadingPosition()));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
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
