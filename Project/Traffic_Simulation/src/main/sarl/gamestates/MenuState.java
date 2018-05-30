package gamestates;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import entities.Button;

import org.newdawn.slick.Graphics;

import logic.Map;

public class MenuState extends BasicGame
{
	private Button play_button;
	private Button option_button;
	private Button quit_button;

	private Image logo;
	
	public MenuState(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Howdy!", 100, 10);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MenuState("Simple Slick Game"));
			appgc.setDisplayMode(Map.WIDTH, Map.HEIGHT, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			//Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
