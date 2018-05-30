package gamestates;

import environments.Button;
import environments.EventEnvironmentObjectMouseClicked;
import gamestates.SimulationWindow;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import logic.Map;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class MainMenuGameState extends BasicGameState {
  private Button play_button;
  
  private Button option_button;
  
  private Button quit_button;
  
  private Image logo;
  
  @Override
  public void init(final GameContainer arg0, final StateBasedGame arg1) throws SlickException {
    arg0.getGraphics().setBackground(Color.white);
    Vector2f _vector2f = new Vector2f(((Map.WIDTH / 2) - (600 / 2)), 375);
    Button _button = new Button(_vector2f, "asset/b_play_idle.png", 
      "asset/b_play_hover.png", "asset/b_play_pressed.png");
    this.play_button = _button;
    this.play_button.setEventCallback(new EventEnvironmentObjectMouseClicked() {
      @Override
      public void mouseClicked() {
        try {
          arg1.getState(SimulationWindow.GS_SIMULATION()).init(arg0, arg1);
          arg1.enterState(SimulationWindow.GS_SIMULATION());
        } catch (final Throwable _t) {
          if (_t instanceof SlickException) {
            final SlickException e = (SlickException)_t;
            e.printStackTrace();
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      }
    });
  }
  
  @Override
  public void render(final GameContainer arg0, final StateBasedGame arg1, final Graphics arg2) throws SlickException {
    this.play_button.render(arg2);
  }
  
  @Override
  public void update(final GameContainer arg0, final StateBasedGame arg1, final int arg2) throws SlickException {
    this.play_button.update(arg0, arg1, arg2);
  }
  
  @Override
  public int getID() {
    return SimulationWindow.GS_MAIN_MENU();
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
  
  @SyntheticMember
  public MainMenuGameState() {
    super();
  }
}
