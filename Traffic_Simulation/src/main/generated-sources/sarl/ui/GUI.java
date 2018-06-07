package ui;

import framework.gui.AbstractFrameworkGUI;
import framework.math.Point2f;
import framework.math.Vector2f;
import framework.time.TimeManager;
import framework.util.Resources;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class GUI extends AbstractFrameworkGUI {
  /**
   * @param worldWidth
   * @param worldHeight
   * @param formations
   */
  public GUI(final float worldWidth, final float worldHeight, final TimeManager timeManager) {
    super(
      "Traffic Simulation", worldWidth, worldHeight, 
      Resources.getResource(GUI.class, "icon.png"), timeManager);
  }
  
  protected boolean isMouseCursorHidden() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void paintAgentBody(final Graphics2D g2d, final Point2f positionOnScreen, final Vector2f orientationOnScreen, final Shape shape, final Serializable type, final String name, final Point2f positionInMas, final Shape frustum) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void paintSituatedObject(final Graphics2D g2d, final Point2f positionOnScreen, final Vector2f orientationOnScreen, final Shape shape, final Serializable type, final String name, final Point2f positionInMas) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3034679230L;
}
