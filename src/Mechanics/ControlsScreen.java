package Mechanics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ControlsScreen extends BasicGameState{

    private final int id = 1;
    private StateBasedGame game;
    private GameContainer container;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        // TODO Auto-generated method stub
        this.game = game;
        this.container = container;

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setColor(Color.white);
        g.drawString("Campfire Knights", 100, 10);

        String controls = " ----------------------------------------------------------------------\n";
        controls += "| Controls\n";
        controls += "|\n";
        controls += "| Party members automatically attack enemies or heal allies in range.\n";
        controls += "| Left Click to select character. (Or F1, F2, F3, F4)\n";
        controls += "| Right Click to move selected character.\n";
        controls += "|(Alternatively, left click + drag to select and move a character)\n";
        controls += "| Press space for a tactical pause during which you can issue commands.\n";
        controls += "| Select icons or press numbers 1-5 to use skills.\n";
        controls += "| Press ESC to quit anytime.\n";
        controls += "-----------------------------------------------------------------------\n\n";
        controls += "  Press Space to continue.";
        g.drawString(controls, 50, 100);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public void keyReleased(int key, char c) {
        switch(key) {
        case Input.KEY_SPACE:
            game.enterState(new CombatState().getID(),
                    new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            break;
        case Input.KEY_ESCAPE:
            System.exit(0);
            break;
        default:
            break;
        }
    }

}