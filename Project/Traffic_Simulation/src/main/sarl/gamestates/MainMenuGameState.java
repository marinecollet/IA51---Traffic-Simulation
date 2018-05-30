package gamestates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import entities.Button;

import org.newdawn.slick.Graphics;

import logic.Map;

public class MainMenuGameState extends BasicGameState
{
	private Button play_button;
	private Button option_button;
	private Button quit_button;

	private Image logo;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		arg0.getGraphics().setBackground(Color.white);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		arg2.drawString("Howdy!", 100, 10);
	}

	@Override
	public int getID() {
		return SimulationWindow.GS_MAIN_MENU;
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer container = new AppGameContainer(new SimulationWindow("Mini Navette (UTBM)"), Map.WIDTH,
					Map.HEIGHT, false);
			container.setTargetFrameRate(60);
			container.start();
		}
		catch (SlickException ex)
		{
			//Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
