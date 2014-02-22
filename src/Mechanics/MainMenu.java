package Mechanics;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Equipment.Iron_Daggers;
import Equipment.Iron_Sword_Shield;
import Equipment.Wooden_Bow;
import Equipment.Wooden_Restoration_Staff;

public class MainMenu extends BasicGameState{

    private final int id = 0;
    private StateBasedGame game;
    private GameContainer container;
    private int selection = 0;

    private boolean imagesInitialized = false;
    private HashMap<String, Image> images = new HashMap<String, Image>();

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;
        this.container = container;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        createImages();
        g.drawImage(images.get("background"), 0, 0);
        g.setColor(Color.white);
        g.drawString("Campfire Knights", 60, 10);

        if (selection == 1) {
            g.drawImage(images.get("arrows"), 325, 200);
        } else if (selection == 2) {
            g.drawImage(images.get("arrows"), 325, 270);
        } else if (selection == 3) {
            g.drawImage(images.get("arrows"), 325, 340);
        }
        g.drawString("Play Game", 360, 200);
        g.drawString("  Toggle\nFullscreen", 355, 260);
        g.drawString("Quit", 380, 340);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (EquippedItems.getOptimistEquip(Equip.WEAPON) == null) {
            ((CampfireKnights) game).getHeroes()[Hero_Names.OPTIMIST_ID].changeWeapon(new Wooden_Restoration_Staff());
            ((CampfireKnights) game).getHeroes()[Hero_Names.RATIONAL_ID].changeWeapon(new Iron_Sword_Shield());
            ((CampfireKnights) game).getHeroes()[Hero_Names.APATHETIC_ID].changeWeapon(new Iron_Daggers());
            ((CampfireKnights) game).getHeroes()[Hero_Names.CYNIC_ID].changeWeapon(new Wooden_Bow());
        }
    }

    @Override
    public int getID() {
        return id;
    }

    private void createImages() {
        if (!imagesInitialized) {
            try {
                images.put("background", new Image("assets/ui/background.png"));
                images.put("arrows", new Image("assets/ui/selectArrows.png"));
                imagesInitialized = true;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch(key) {
        /*case Input.KEY_1:
            game.enterState(new ControlsScreen().getID(),
                    new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            break;
        case Input.KEY_2:
            try {
                container.setFullscreen(!container.isFullscreen());
            } catch (SlickException e) {
                e.printStackTrace();
            }
            break;
        case Input.KEY_3:
            System.exit(0);
            break;
        case Input.KEY_ENTER:
        case Input.KEY_SPACE:
            if (selection == 1) {
                game.enterState(new ControlsScreen().getID(),
                        new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            } else if (selection == 2) {
                try {
                    container.setFullscreen(!container.isFullscreen());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            } else if (selection == 3) {
                System.exit(0);
            }
            break;*/
        case Input.KEY_UP:
            if (selection > 1) {
                selection--;
            } else if (selection == 0) {
                selection = 3;
            }
            break;
        case Input.KEY_DOWN:
            if (selection < 3) {
                selection++;
            }
            break;
        case Input.KEY_ESCAPE:
            System.exit(0);
            break;
        default:
            break;
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (Utility.inside(325, 190, 150, 40, newx, newy)) {
            selection = 1;
        } else if (Utility.inside(325, 260, 150, 40, newx, newy)) {
            selection = 2;
        } else if (Utility.inside(325, 330, 150, 40, newx, newy)) {
            selection = 3;
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        switch (button) {
        case Input.MOUSE_LEFT_BUTTON:
            if (Utility.inside(325, 190, 150, 40, x, y)
                    && selection == 1) {
                CombatState.pauseText = false;
                game.enterState(new OverviewMenu().getID(),
                        new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            } else if (Utility.inside(325, 260, 150, 40, x, y)
                    && selection == 2) {
                try {
                    container.setFullscreen(!container.isFullscreen());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            } else if (Utility.inside(325, 330, 150, 40, x, y)
                    && selection == 3) {
                System.exit(0);
            }
        }
    }

}