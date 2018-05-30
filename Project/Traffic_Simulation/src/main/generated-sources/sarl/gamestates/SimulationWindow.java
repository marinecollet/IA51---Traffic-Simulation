package gamestates;

import gamestates.MainMenuGameState;
import gamestates.SimulationGameState;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jerem
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class SimulationWindow extends StateBasedGame {
  private final static int GS_MAIN_MENU = 0;
  
  private final static int GS_SIMULATION = 1;
  
  public SimulationWindow(final String name) {
    super(name);
  }
  
  @Override
  public void initStatesList(final GameContainer arg0) throws SlickException {
    MainMenuGameState _mainMenuGameState = new MainMenuGameState();
    this.addState(_mainMenuGameState);
    SimulationGameState _simulationGameState = new SimulationGameState();
    this.addState(_simulationGameState);
  }
  
  @Pure
  public static int GS_MAIN_MENU() {
    return SimulationWindow.GS_MAIN_MENU;
  }
  
  @Pure
  public static int GS_SIMULATION() {
    return SimulationWindow.GS_SIMULATION;
  }
}
