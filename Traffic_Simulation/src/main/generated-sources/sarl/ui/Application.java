package ui;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.arakhne.afc.gis.primitive.GISContainer;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas Gredin
 * 
 * @description Class that handle all stuff about window that display the feedback of the
 * animation of the simulation.
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Application extends javafx.application.Application {
  /**
   * Variable to handle dragging events
   */
  private boolean dragging;
  
  public void init() {
  }
  
  public void start(final Stage primaryStage) {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 1024, 768);
    this.setupRoadNetworkContainer();
    primaryStage.setTitle("Traffic simulation !");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * @author Thomas Gredin
   * 
   * @description
   * setupRoadNetwork
   * 
   * Function that create GISContainer.
   * This container handle the display of the map which is extracted from
   * the loaded Shape file.
   * 
   * @return GISContainer ready to be used
   */
  public GISContainer setupRoadNetworkContainer() {
    return null;
  }
  
  @Pure
  public static void launch() {
    Application.launch(Application.class);
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
    Application other = (Application) obj;
    if (other.dragging != this.dragging)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.dragging ? 1231 : 1237);
    return result;
  }
  
  @SyntheticMember
  public Application() {
    super();
  }
}
