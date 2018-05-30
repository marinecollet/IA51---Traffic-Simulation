package gamestates;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SimulationWindow extends StateBasedGame {
	
	// Les ids de tous les GameState du jeu
	public static final int GS_MAIN_MENU = 0;
	public static final int GS_SIMULATION = 1;
	public static final int GS_PAUSE_MENU = 2;
	public static final int GS_OPTION_MENU = 3;
	public static final int GS_GAME_OVER = 4;

	public SimulationWindow(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainMenuGameState());
	}
}