package gamestates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import environments.Button
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
	
		
		play_button = new Button(new Vector2f((Map.WIDTH / 2) - (600 / 2), 375), "asset/b_play_idle.png",
				"asset/b_play_hover.png", "asset/b_play_pressed.png");
		play_button.setEventCallback(new EventEntityMouseClicked() {
			@Override
			public void mouseClicked() {
				try {
					arg1.getState(SimulationWindow.GS_SIMULATION).init(arg0, arg1);
					arg1.enterState(SimulationWindow.GS_SIMULATION);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
});
		
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		play_button.render(arg2);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		play_button.update(arg0, arg1, arg2);
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
