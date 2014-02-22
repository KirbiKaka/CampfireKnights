package Mechanics;

import java.util.HashMap;
import java.util.Iterator;

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

import Equipment.W_Group;

public class EquipMenu extends BasicGameState{

    private final int id = 5;
    private CampfireKnights game;
    private GameContainer container;

    private int hero = 0;
    /** The menu option the mouse is hovering on. Starts at 1. */
    private int selection = 0;
    private int equipSelection = 0;
    private boolean choosingEquip = false;
    private int equipHovering = -1;

    TextBox textbox;

    private boolean imagesInitialized = false;
    private HashMap<String, Image> images = new HashMap<String, Image>();

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = (CampfireKnights) game;
        this.container = container;
        textbox = new TextBox(this.game);
    }

    public void setHero(int h) { hero = h; }
    public int getHero() { return hero; }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        createImages();
        g.drawImage(images.get("background"), 0, 0);
        g.drawImage(images.get(Hero_Names.getTitle(hero)), 40, 20);
        g.setColor(Color.white);
        g.drawString(Hero_Names.getName(hero), 100, 20);
        g.setColor(Color.green);
        drawHealth(g, this.game.getHeroes()[hero], 100, 40);
        g.drawString(this.game.getHeroes()[hero].getHealth() + "/" +
                this.game.getHeroes()[hero].getMaxHealth(), 255, 31);
        g.setColor(Color.yellow);
        drawEnergy(g, this.game.getHeroes()[hero], 100, 48);
        g.drawString(this.game.getHeroes()[hero].getEnergy() + "/" +
                this.game.getHeroes()[hero].getMaxEnergy(), 262, 45);
        if (equipSelection == 1)
            g.drawImage(images.get("equip_border"), 30, 105);
        else
            g.drawImage(images.get("dark_equip_border"), 30, 105);
        g.setColor(Color.white);
        g.drawString(EquippedItems.getEquip(hero, Equip.WEAPON).getName(), 50, 110);
        g.drawImage(images.get("dark_equip_border"), 30, 145);
        g.drawImage(images.get("dark_equip_border"), 30, 185);
        g.setColor(Color.gray);
        g.drawString("-Empty-", 50, 150);
        g.drawString("-Empty-", 50, 190);

        if (choosingEquip) {
            g.drawImage(images.get("inventory_border"), 360, 10);
            g.setColor(Color.white);
            Iterator<Equip> iter = Inventory.getEquipmentList().iterator();
            int offset = 0;
            while (iter.hasNext()) {
                offset += 40;
                Equip next = iter.next();
                if (!Inventory.isEquippable(next)
                        || EquippedItems.getEquip(hero, next.getType()).getName().equals(next.getName())) {
                    g.setColor(Color.gray);
                }
                g.drawString(next.getName(), 380, -10 + offset);
                g.drawString("" + Inventory.getEquipmentAmount(next),
                        630, -10 + offset);
                g.setColor(Color.white);
            }

            if (equipHovering != -1) {
                Weapon hoveredEquip = (Weapon) (Inventory.getEquipmentList().get(equipHovering));
                if (hoveredEquip.getIsHeal())
                    g.drawString("Heal: " + hoveredEquip.getAttack(), 50, 300);
                else
                    g.drawString("Attack: " + hoveredEquip.getAttack(), 50, 300);
                g.drawString("Attack Speed: " + (3000 - hoveredEquip.getAttackSpeed()), 50, 330);
                g.drawString("Range: " + hoveredEquip.getRange(), 50, 360);
            }
        }

        if (selection == 1) {
            //g.drawImage(images.get("arrows"), 640, 30);
        } else if (selection == 2) {
            //g.drawImage(images.get("arrows"), 640, 70);
        } else if (selection == 3) {
            //g.drawImage(images.get("arrows"), 640, 110);
        } else if (selection == 4) {
            g.drawImage(images.get("arrows"), 640, 150);
        }
        g.setColor(Color.lightGray);
        g.drawString("Supplies", 680, 30);
        g.drawString("Party", 680, 70);
        g.drawString("Equip", 680, 110);
        g.setColor(Color.white);
        g.drawString("Back", 680, 150);

        textbox.draw(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        textbox.update(delta);
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
                images.put("equip_border", new Image("assets/ui/equipmentBorder.png"));
                images.put("dark_equip_border", new Image("assets/ui/equipmentBorderGrayed.png"));
                images.put("inventory_border", new Image("assets/ui/inventoryBorder.png"));
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
                selection = 4;
            }
            break;
        case Input.KEY_DOWN:
            if (!choosingEquip) {
                selection = 4;
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
        if (!choosingEquip) {
            equipSelection = 0;
        }
        if (Utility.inside(640, 20, 150, 40, newx, newy))
            selection = 1;
        else if (Utility.inside(640, 60, 150, 40, newx, newy))
            selection = 2;
        else if (Utility.inside(640, 100, 150, 40, newx, newy))
            selection = 3;
        else if (Utility.inside(640, 140, 150, 40, newx, newy))
            selection = 4;

        /* Hovering over equipped weapon. */
        if (Utility.inside(30, 105, 300, 30, newx, newy)) {
            if (!choosingEquip) {
                equipSelection = 1;
            }
        }

        /* Hovering over equipment selection. */
        if (choosingEquip) {
            if (Utility.inside(370, 25, 270, 380, newx, newy)) {
                int y = (newy - 25) / 40;
                if (Inventory.getEquipmentList().get(y) != null) {
                    equipHovering = y;
                } else
                    equipHovering = -1;
            } else
                equipHovering = -1;
        }

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
            } else if (Utility.inside(640, 140, 150, 40, x, y)
                    && selection == 4) {
                TextBox.clear();
                choosingEquip = false;
                game.enterState(new OverviewMenu().getID(),
                        new FadeOutTransition(Color.black),
                        new FadeInTransition(Color.black));
            }
            if (Utility.inside(30, 105, 300, 30, x, y)) {
                if (choosingEquip && equipSelection == 1) {
                    choosingEquip = false;
                } else if (choosingEquip) {
                    equipSelection = 1;
                } else {
                    choosingEquip = true;
                }
            }
            if (choosingEquip) {
                if (Utility.inside(360, 10, 300, 400, x, y)) {
                    int size = Inventory.getEquipmentList().size();
                    for (int i = 0; i < size; i++) {
                        if (Utility.inside(350, 20 + (i * 40), 290, 40, x, y)) {
                            Equip nextEquip = Inventory.getEquipmentList().get(i);
                            if (Inventory.isEquippable(nextEquip)
                                    && !EquippedItems.getEquip(hero, nextEquip.getType()).getName().equals(nextEquip.getName())) {
                                game.getHeroes()[hero].changeWeapon(
                                        (Weapon) Inventory.getEquipmentList().get(i));
                                switch(getHero()) {
                                case Hero_Names.OPTIMIST_ID:
                                    if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.DAGGER) {
                                        Dialogue_Inventory.optimist_Select_Daggers();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.SWORD_SHIELD) {
                                        Dialogue_Inventory.optimist_Select_Sword_Shield();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.BOW) {
                                        Dialogue_Inventory.optimist_Select_Bow();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.RESTORATION_STAFF) {
                                        Dialogue_Inventory.optimist_Select_Resto_Staff();
                                    }
                                    break;
                                case Hero_Names.RATIONAL_ID:
                                    if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.DAGGER) {
                                        Dialogue_Inventory.rational_Select_Daggers();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.SWORD_SHIELD) {
                                        Dialogue_Inventory.rational_Select_Sword_Shield();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.BOW) {
                                        Dialogue_Inventory.rational_Select_Bow();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.RESTORATION_STAFF) {
                                        Dialogue_Inventory.rational_Select_Resto_Staff();
                                    }
                                    break;
                                case Hero_Names.APATHETIC_ID:
                                    if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.DAGGER) {
                                        Dialogue_Inventory.apathetic_Select_Daggers();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.SWORD_SHIELD) {
                                        Dialogue_Inventory.apathetic_Select_Sword_Shield();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.BOW) {
                                        Dialogue_Inventory.apathetic_Select_Bow();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.RESTORATION_STAFF) {
                                        Dialogue_Inventory.apathetic_Select_Resto_Staff();
                                    }
                                    break;
                                case Hero_Names.CYNIC_ID:
                                    if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.DAGGER) {
                                        Dialogue_Inventory.cynic_Select_Daggers();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.SWORD_SHIELD) {
                                        Dialogue_Inventory.cynic_Select_Sword_Shield();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.BOW) {
                                        Dialogue_Inventory.cynic_Select_Bow();
                                    } else if (((Weapon) Inventory.getEquipmentList().get(i))
                                            .getGroup() == W_Group.RESTORATION_STAFF) {
                                        Dialogue_Inventory.cynic_Select_Resto_Staff();
                                    }
                                    break;
                                }
                                choosingEquip = false;
                                equipHovering = -1;
                                equipSelection = 0;
                                break;
                            }
                        }
                    }
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