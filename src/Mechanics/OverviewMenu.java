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

public class OverviewMenu extends BasicGameState{

    private final int id = 2;
    private CampfireKnights game;
    private GameContainer container;
    /** The menu option the mouse is hovering on. Options start at 1. */
    private int selection = 0;
    /** The party member the mouse is hovering on. First party member is at 0. */
    private int partySelection = -1;
    private boolean choosingEquip = false;

    TextBox textbox;
    private boolean initialText = false;

    private boolean imagesInitialized = false;
    private HashMap<String, Image> images = new HashMap<String, Image>();

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = (CampfireKnights) game;
        this.container = container;
        textbox = new TextBox(this.game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        createImages();
        g.drawImage(images.get("background"), 0, 0);
        if (!choosingEquip) {
            g.drawImage(images.get("border"), 20, 10);
            g.drawImage(images.get("border"), 335, 10);
            g.drawImage(images.get("border"), 20, 220);
            g.drawImage(images.get("border"), 335, 220);
        } else {
            g.drawImage(images.get("dark_border"), 20, 10);
            g.drawImage(images.get("dark_border"), 335, 10);
            g.drawImage(images.get("dark_border"), 20, 220);
            g.drawImage(images.get("dark_border"), 335, 220);
            switch (partySelection) {
            case 0:
                g.drawImage(images.get("border"), 20, 10);
                break;
            case 1:
                g.drawImage(images.get("border"), 335, 10);
                break;
            case 2:
                g.drawImage(images.get("border"), 20, 220);
                break;
            case 3:
                g.drawImage(images.get("border"), 335, 220);
                break;
            }
        }
        //Avatars
        g.drawImage(images.get("Optimist"), 30, 20);
        g.drawImage(images.get("Rational"), 345, 20);
        g.drawImage(images.get("Apathetic"), 30, 230);
        g.drawImage(images.get("Cynic"), 345, 230);
        //Names
        g.setColor(Color.white);
        g.drawString("Kirthas", 90, 20);
        g.drawString("Isabelle", 405, 20);
        g.drawString("Damian", 90, 230);
        g.drawString("Sky", 405, 230);
        //Health
        g.setColor(Color.green);
        drawHealth(g, this.game.getHeroes()[0], 90, 40);
        g.drawString(this.game.getHeroes()[0].getHealth() + "/" +
                this.game.getHeroes()[0].getMaxHealth(), 245, 31);
        drawHealth(g, this.game.getHeroes()[1], 405, 40);
        g.drawString(this.game.getHeroes()[1].getHealth() + "/" +
                this.game.getHeroes()[1].getMaxHealth(), 560, 31);
        drawHealth(g, this.game.getHeroes()[2], 90, 250);
        g.drawString(this.game.getHeroes()[2].getHealth() + "/" +
                this.game.getHeroes()[2].getMaxHealth(), 245, 241);
        drawHealth(g, this.game.getHeroes()[3], 405, 250);
        g.drawString(this.game.getHeroes()[3].getHealth() + "/" +
                this.game.getHeroes()[3].getMaxHealth(), 560, 241);
        //Energy
        g.setColor(Color.yellow);
        drawEnergy(g, this.game.getHeroes()[0], 90, 48);
        g.drawString(this.game.getHeroes()[0].getEnergy() + "/" +
                this.game.getHeroes()[0].getMaxEnergy(), 252, 45);
        drawEnergy(g, this.game.getHeroes()[1], 405, 48);
        g.drawString(this.game.getHeroes()[1].getEnergy() + "/" +
                this.game.getHeroes()[1].getMaxEnergy(), 567, 45);
        drawEnergy(g, this.game.getHeroes()[2], 90, 258);
        g.drawString(this.game.getHeroes()[2].getEnergy() + "/" +
                this.game.getHeroes()[2].getMaxEnergy(), 252, 255);
        drawEnergy(g, this.game.getHeroes()[3], 405, 258);
        g.drawString(this.game.getHeroes()[3].getEnergy() + "/" +
                this.game.getHeroes()[3].getMaxEnergy(), 567, 255);
        //Equipment
        g.setColor(Color.white);
        g.drawString(EquippedItems.getEquip(0, Equip.WEAPON).getName(), 60, 110);
        g.setColor(Color.gray);
        g.drawString("-Empty-", 60, 130);
        g.drawString("-Empty-", 60, 150);
        g.setColor(Color.white);
        g.drawString(EquippedItems.getEquip(1, Equip.WEAPON).getName(), 375, 110);
        g.setColor(Color.gray);
        g.drawString("-Empty-", 375, 130);
        g.drawString("-Empty-", 375, 150);
        g.setColor(Color.white);
        g.drawString(EquippedItems.getEquip(2, Equip.WEAPON).getName(), 60, 320);
        g.setColor(Color.gray);
        g.drawString("-Empty-", 60, 340);
        g.drawString("-Empty-", 60, 360);
        g.setColor(Color.white);
        g.drawString(EquippedItems.getEquip(3, Equip.WEAPON).getName(), 375, 320);
        g.setColor(Color.gray);
        g.drawString("-Empty-", 375, 340);
        g.drawString("-Empty-", 375, 360);

        if (!choosingEquip && selection == 1) {
            //g.drawImage(images.get("arrows"), 640, 30);
        } else if (!choosingEquip && selection == 2) {
            //g.drawImage(images.get("arrows"), 640, 70);
        } else if (!choosingEquip && selection == 3
                || choosingEquip && selection == 3 && partySelection == -1) {
            g.drawImage(images.get("arrows"), 640, 110);
        } else if (!choosingEquip && selection == 4) {
            g.drawImage(images.get("arrows"), 640, 150);
        }
        g.setColor(Color.lightGray);
        g.drawString("Supplies", 680, 30);
        g.drawString("Party", 680, 70);
        g.setColor(Color.white);
        if (choosingEquip)
            g.drawString("Cancel", 680, 110);
        else
            g.drawString("Equip", 680, 110);
        if (choosingEquip)
            g.setColor(Color.lightGray);
        g.drawString("DONE", 680, 150);

        textbox.draw(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        textbox.update(delta);
        if (!initialText) {
            textbox.writeText("Take a moment to equip your party members.", Color.white);
            initialText = true;
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
                images.put("border", new Image("assets/ui/characterBorder.png"));
                images.put("dark_border", new Image("assets/ui/characterBorderGrayed.png"));
                images.put("Optimist", new Image("assets/heroes/Kirthas_Stand.png"));
                images.put("Rational", new Image("assets/heroes/Isabelle_Stand.png"));
                images.put("Apathetic", new Image("assets/heroes/Damian_Stand.png"));
                images.put("Cynic", new Image("assets/heroes/Sky_Stand.png"));
                images.put("healthbar", new Image("assets/ui/healthbar.png"));
                images.put("energybar", new Image("assets/ui/energybar.png"));
                images.put("healthbar_empty", new Image("assets/ui/healthbarEmpty.png"));
                images.put("energybar_empty", new Image("assets/ui/energybarEmpty.png"));
                images.put("healthbar_black", new Image("assets/ui/healthbarBlack.png"));
                images.put("energybar_black", new Image("assets/ui/energybarBlack.png"));
                imagesInitialized = true;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch(key) {
        case Input.KEY_UP:
            if (!choosingEquip) {
                if (selection > 3) {
                    selection--;
                } else if (selection == 0) {
                    selection = 4;
                }
            }
            break;
        case Input.KEY_DOWN:
            if (!choosingEquip) {
                if (selection == 0) {
                    selection = 3;
                } else if (selection < 4) {
                    selection++;
                }
            }
            break;
        case Input.KEY_ESCAPE:
            if (choosingEquip) {
                choosingEquip = false;
            } else {
                System.exit(0);
            }
            break;
        default:
            break;
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        selection = 0;
        partySelection = -1;
        if (Utility.inside(640, 20, 150, 40, newx, newy)) {
            //selection = 1;
        } else if (Utility.inside(640, 60, 150, 40, newx, newy)) {
            //selection = 2;
        } else if (Utility.inside(640, 100, 150, 40, newx, newy)) {
            selection = 3;
        } else if (Utility.inside(640, 140, 150, 40, newx, newy)) {
            selection = 4;
        }

        if (Utility.inside(20, 10, 300, 200, newx, newy))
            partySelection = 0;
        else if (Utility.inside(335, 10, 300, 200, newx, newy))
            partySelection = 1;
        else if (Utility.inside(20, 220, 300, 200, newx, newy))
            partySelection = 2;
        else if (Utility.inside(335, 220, 300, 200, newx, newy))
            partySelection = 3;
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        switch (button) {
        case Input.MOUSE_LEFT_BUTTON:
            if (Utility.inside(640, 20, 150, 40, x, y)
                    && selection == 1) {
            } else if (Utility.inside(640, 60, 150, 40, x, y)
                    && selection == 2) {
            } else if (Utility.inside(640, 100, 150, 40, x, y)
                    && selection == 3) {
                choosingEquip = !choosingEquip;
                partySelection = -1;
            } else if (Utility.inside(640, 140, 150, 40, x, y)
                    && selection == 4) {
                if (!choosingEquip) {
                    TextBox.clear();
                    partySelection = -1;
                    game.enterState(new ControlsScreen().getID(),
                            new FadeOutTransition(Color.black),
                            new FadeInTransition(Color.black));
                }
            }
            if (choosingEquip) {
                if (Utility.inside(20, 10, 300, 200, x, y)) {
                    ((EquipMenu) game.getState(new EquipMenu().getID())).setHero(0);
                    CombatState.pauseText = false;
                    partySelection = -1;
                    game.enterState(new EquipMenu().getID(),
                            new FadeOutTransition(Color.black),
                            new FadeInTransition(Color.black));
                } else if (Utility.inside(335, 10, 300, 200, x, y)) {
                    ((EquipMenu) game.getState(new EquipMenu().getID())).setHero(1);
                    CombatState.pauseText = false;
                    partySelection = -1;
                    game.enterState(new EquipMenu().getID(),
                            new FadeOutTransition(Color.black),
                            new FadeInTransition(Color.black));
                } else if (Utility.inside(20, 220, 300, 200, x, y)) {
                    ((EquipMenu) game.getState(new EquipMenu().getID())).setHero(2);
                    CombatState.pauseText = false;
                    partySelection = -1;
                    game.enterState(new EquipMenu().getID(),
                            new FadeOutTransition(Color.black),
                            new FadeInTransition(Color.black));
                } else if (Utility.inside(335, 220, 300, 200, x, y)) {
                    ((EquipMenu) game.getState(new EquipMenu().getID())).setHero(3);
                    CombatState.pauseText = false;
                    partySelection = -1;
                    game.enterState(new EquipMenu().getID(),
                            new FadeOutTransition(Color.black),
                            new FadeInTransition(Color.black));
                }
            }
            break;
        case Input.MOUSE_RIGHT_BUTTON:
            if (choosingEquip) {
                choosingEquip = false;
            }
            break;
        }
    }

    void drawHealth(Graphics g, Hero hero, int x, int y) {
        int health = hero.getHealth();
        int maxHealth = hero.getMaxHealth();
        double percentage = (double) health / maxHealth;
        /* 18 Health bars*/
        int bars = 18 - (int) (18 - percentage * 18);
        g.drawImage(images.get("healthbar_black"), x, y);
        for (int i = 8; i < 152; i += 8) {
            if (bars > 0) {
                g.drawImage(images.get("healthbar"), x + i, y);
            } else {
                g.drawImage(images.get("healthbar_empty"), x + i, y);
            }
            bars--;
        }
    }

    void drawEnergy(Graphics g, Hero hero, int x, int y) {
        int energy = hero.getEnergy();
        int maxEnergy = hero.getMaxEnergy();
        double percentage = (double) energy / maxEnergy;
        /* 18 Energy bars*/
        int bars = 18 - (int) (18 - percentage * 18);
        g.drawImage(images.get("energybar_black"), x + 8, y);
        for (int i = 16; i < 160; i += 8) {
            if (bars > 0) {
                g.drawImage(images.get("energybar"), x + i, y);
            } else {
                g.drawImage(images.get("energybar_empty"), x + i, y);
            }
            bars--;
        }
    }

}