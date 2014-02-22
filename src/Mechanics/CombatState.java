package Mechanics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/** The state in which all combat happens. */
public class CombatState extends BasicGameState{

    public static final int SELECT_NONE = -1;
    public static final Color RANGE_CIRCLE_COLOR = new Color(178, 34, 34, 100);
    public static final Color HEAL_RANGE_CIRCLE_COLOR = new Color(30, 144, 255, 100);
    public static final Color PAUSE_OVERLAY_COLOR = new Color(0, 0, 0, 100);
    public static final Color SELECT_AND_PATH_COLOR = new Color(255, 230, 0, 255);

    public static boolean isPaused = false;
    public static boolean pauseText = true;
    public static int selected = SELECT_NONE;
    public static int timer = 0;

    private final int id = 3;
    private CampfireKnights game;
    public static Hero[] heroes = new Hero[4];
    int drawRangeHover = -1;
    int heroHover = -1;

    private boolean holdingMouse = false;
    private boolean WSPressed = false;
    private boolean ADPressed = false;
    private int mouseClickedX = 0;
    private int mouseClickedY = 0;
    private int mouseCurrentX = 0;
    private int mouseCurrentY = 0;

    boolean imagesInitialized = false;

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    TextBox textbox;
    StatBox statbox;
    private Animation movePointer;
    private int movePointerX, movePointerY;

    private int mobCount = 0;
    private boolean showStarHealDescription = false;
    private boolean castStarHeal = false;
    private boolean victory = false;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = (CampfireKnights) game;
        heroes = this.game.getHeroes();
        textbox = new TextBox(this.game);
        statbox = new StatBox(heroes);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        createImages();
        TiledMap grass = new TiledMap("TiledMaps/grass.tmx");
        grass.render(0, 0);

        if (selected == SELECT_NONE && drawRangeHover != -1) {
            Hero hero = heroes[drawRangeHover];
            if (hero.isFighter()) {
                g.setColor(RANGE_CIRCLE_COLOR);
            } else {
                g.setColor(HEAL_RANGE_CIRCLE_COLOR);
            }
            g.fillOval(hero.xPos() + hero.xSize() / 2 - hero.getRange(),
                    hero.yPos() + hero.ySize() - hero.getRange() / 2,
                    2 * hero.getRange(),
                    (hero.getRange()));
        }
        if (selected != SELECT_NONE) {
            Hero hero = heroes[selected];
            if (hero.isFighter()) {
                g.setColor(RANGE_CIRCLE_COLOR);
            } else {
                g.setColor(HEAL_RANGE_CIRCLE_COLOR);
            }
            g.fillOval(hero.xPos() + hero.xSize() / 2 - hero.getRange(),
                    hero.yPos() + hero.ySize() - hero.getRange() / 2,
                    2 * hero.getRange(),
                    (hero.getRange()));
            g.setColor(SELECT_AND_PATH_COLOR);
            g.setLineWidth(g.getLineWidth() + 3);
            g.drawOval(hero.xPos() - hero.xSize() / 6,
                    (float) hero.yPos() + hero.ySize() - 10, (float) 4.0 / 3 *hero.xSize(), 20);
            if (holdingMouse) {
                g.drawLine(heroes[selected].xPos() + heroes[selected].xSize() / 2,
                        heroes[selected].yPos() + heroes[selected].ySize(),
                        mouseCurrentX, mouseCurrentY);
            }g.setLineWidth(g.getLineWidth() - 3);
        }

        statbox.draw(g);

        List<Drawable> entities = makeDrawOrder();
        for (Drawable entity : entities) {
            entity.draw(g);
        }
        for (Hero hero : heroes) {
            hero.drawSpark();
        }
        if (!isPaused) {
            if (castStarHeal) {
                castStarHeal();
                castStarHeal = false;
            }
            statbox.drawStarHeal(heroes);
        }

        textbox.draw(g);

        if (isPaused) {
            g.setColor(PAUSE_OVERLAY_COLOR);
            g.fillRect(0, 0, 800, 600);
            g.setColor(new Color(255, 255, 255, 200));
            g.drawString("TACTICAL PAUSE", 334, 395);
            g.drawString("press space to unpause", 300, 410);
        }
        if (showStarHealDescription) {
            g.setColor(new Color(255, 255, 255, 200));
            g.drawString("Heal all party members for 20 hp. (20 energy)", 20, 71);
        } else {
            if (selected == SELECT_NONE) {
                if (heroHover != -1) {
                    for (Hero hero : heroes) {
                        if (heroHover == hero.getID()) {
                            if (isPaused) {
                                g.setColor(new Color(255, 255, 255, 200));
                                String attackMessage = "Basic Attack: ";
                                if (hero.isFighter())
                                    attackMessage += "Damage enemy for ";
                                else
                                    attackMessage += "Heal ally for ";
                                attackMessage += "" + hero.getDamage() + " hp. (" + hero.getRange() + " Range)";
                                g.drawString(attackMessage, 20, 71);
                            }
                        }
                    }
                }
            } else {
                Hero hero = heroes[selected];
                if (isPaused) {
                    g.setColor(new Color(255, 255, 255, 200));
                    String attackMessage = "Basic Attack: ";
                    if (hero.isFighter())
                        attackMessage += "Damage enemy for ";
                    else
                        attackMessage += "Heal ally for ";
                    attackMessage += "" + hero.getDamage() + " hp. (" + hero.getRange() + " Range)";
                    g.drawString(attackMessage, 20, 71);
                }
            }
        }
        if (victory) {
            isPaused = true;
            g.setColor(new Color(255, 255, 255, 255));
            g.drawString("VICTORY", 350, 250);
        }

        if (!movePointer.isStopped())
            movePointer.draw(movePointerX, movePointerY);

    }

    public void createImages() {
        if (!imagesInitialized) {
            setupUIAnims();
            imagesInitialized = true;
        }
    }

    public List<Drawable> makeDrawOrder() {
        List<Drawable> entities = new ArrayList<Drawable>();
        for (Enemy enemy : enemies) {
            entities.add(enemy);
        }
        for (Hero hero : heroes) {
            entities.add(hero);
        }
        Collections.sort(entities);
        return entities;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (!isPaused) {
            timer += delta;
        }
        textbox.update(delta);
        statbox.update(delta);
        for (Hero hero : heroes) {
            if (hero.getState() == Default_States.STANDING || hero.getState() == Default_States.ATTACKING) {
                if (hero.isFighter()) {
                    hero.findTarget(enemies);
                } else {
                    hero.findAllyTarget(heroes);
                }
            }
            hero.update(delta);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).findTarget(heroes);
            enemies.get(i).update(delta);
            if (enemies.get(i).isDead()) {
                enemies.remove(i);
            }
        }
        if (timer > 8000 & mobCount == 0) {
            Enemy silly = new Enemy("Silhouette", "assets/enemies/silhouette.png", 64, 80, 800, 100);
            silly.setDamage(2);
            silly.setMaxHealth(30);
            enemies.add(silly);
            mobCount++;
        } else if (timer > 36000 & mobCount == 1 && enemies.isEmpty()) {
            enemies.add(new Enemy("Silhouette", "assets/enemies/silhouette.png", 64, 80, 800, 250));
            mobCount++;
        } else if (timer > 61000 & mobCount == 2 && enemies.isEmpty()) {
            Enemy silly = new Enemy("Silhouette", "assets/enemies/silhouette.png", 64, 80, 800, 130);
            Enemy silly2 = new Enemy("Silhouette", "assets/enemies/silhouette.png", 64, 80, -150, 200);
            silly.setMaxHealth(30);
            silly.setFullHealth();
            silly2.setMaxHealth(30);
            silly2.setFullHealth();
            enemies.add(silly);
            enemies.add(silly2);
            mobCount++;
        } else if (timer > 91000 & mobCount == 3 && enemies.isEmpty()) {
            Enemy boss = new Enemy("Sillow", "assets/enemies/Sillow.png", 80, 100, 800, 170);
            boss.setAttackSpeed(4150);
            boss.setDamage(10);
            boss.setMaxHealth(100);
            boss.setFullHealth();
            boss.setSpeed(0.02);
            boss.setAoE(50);
            boss.setWarning(true);
            enemies.add(boss);
            mobCount++;
        } else if (mobCount == 4 && enemies.isEmpty()) {
            victory = true;
        }
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void keyPressed(int key, char c) {
        switch(key) {
        case Input.KEY_W:
            if (selected != SELECT_NONE) {
                heroes[selected].commandMoveY(StatBox.statBoxYPos + 40);
                WSPressed = true;
            }
            break;
        case Input.KEY_S:
            if (selected != SELECT_NONE) {
                heroes[selected].commandMoveY(TextBox.textBoxYPos);
                WSPressed = true;
            }
            break;
        case Input.KEY_A:
            if (selected != SELECT_NONE) {
                heroes[selected].commandMoveX(0);
                ADPressed = true;
            }
            break;
        case Input.KEY_D:
            if (selected != SELECT_NONE) {
                heroes[selected].commandMoveX(game.getContainer().getWidth());
                ADPressed = true;
            }
            break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch(key) {
        case Input.KEY_1:
            if (selected != SELECT_NONE
                    && heroes[selected].abilityEquippedIndex(
                            Ability_Names.STAR_HEAL) == 0) {
                if (heroes[selected].hasEnergy(20)) {
                    castStarHeal = true;
                }
            }
            break;
        case Input.KEY_W:
        case Input.KEY_S:
            if (selected != SELECT_NONE && WSPressed) {
                if (ADPressed)
                    heroes[selected].stopY();
                else
                    heroes[selected].stop();
            }
            WSPressed = false;
            break;
        case Input.KEY_A:
        case Input.KEY_D:
            if (selected != SELECT_NONE && ADPressed) {
                if (WSPressed)
                    heroes[selected].stopX();
                else
                    heroes[selected].stop();
            }
            ADPressed = false;
            break;
        case Input.KEY_F1:
            selected = 0;
            Dialogue_Combat.optimist_Select_Basic();
            break;
        case Input.KEY_F2:
            selected = 1;
            Dialogue_Combat.rational_Select_Basic();
            break;
        case Input.KEY_F3:
            selected = 2;
            Dialogue_Combat.apathetic_Select_Basic();
            break;
        case Input.KEY_F4:
            selected = 3;
            Dialogue_Combat.cynic_Select_Basic();
            break;
        case Input.KEY_ESCAPE:
            System.exit(0);
            break;
        case Input.KEY_SPACE:
            if (victory) {
                System.exit(0);
            }
            isPaused = !isPaused;
            break;
        default:
            break;
        }

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        switch(button) {
        case Input.MOUSE_LEFT_BUTTON:
            if (y < TextBox.textBoxYPos && y > StatBox.statBoxYPos) {
                selected = SELECT_NONE;
                for (Hero hero : heroes) {
                    if (hero.inside(x, y) && !hero.isDown()) {
                        holdingMouse = true;
                        mouseClickedX = x;
                        mouseClickedY = y;
                        selected = hero.getID();
                        if (!isPaused) {
                            switch(selected) {
                            case 0: Dialogue_Combat.optimist_Select_Basic(); break;
                            case 1: Dialogue_Combat.rational_Select_Basic(); break;
                            case 2: Dialogue_Combat.apathetic_Select_Basic(); break;
                            case 3: Dialogue_Combat.cynic_Select_Basic(); break;
                            }
                        }
                        break;
                    }
                }
            } else {
                /** Star Heal icon. */
                if (x > 40 && x < 90 && y > 7 && y <= 57) {
                    if (selected != SELECT_NONE
                            && heroes[selected].abilityEquippedIndex(
                                    Ability_Names.STAR_HEAL) == 0) {
                        if (heroes[selected].hasEnergy(20)) {
                            castStarHeal = true;
                        }
                    }
                }
            }
            break;
        case Input.MOUSE_RIGHT_BUTTON:
            for (Hero hero : heroes) {
                if (selected == hero.getID()) {
                    if (y >= TextBox.textBoxYPos) {
                        y = TextBox.textBoxYPos;
                    } else if (y <= StatBox.statBoxYPos + 40) {
                        y = StatBox.statBoxYPos + 40;
                    }
                    hero.commandMove(x, y);
                    if (hero.isFighter()) {
                        for (Enemy enemy : enemies) {
                            if (enemy.inside(x, y)) {
                                hero.setPriorityTarget(enemy);
                            }
                        }
                    } else {
                        for (Hero ally : heroes) {
                            if (ally.inside(x, y)) {
                                hero.setAllyPriorityTarget(ally);
                            }
                        }
                    }

                    WSPressed = false;
                    ADPressed = false;

                    movePointer.restart();
                    movePointerX = x - 10;
                    movePointerY = y - 20;
                }
            }
        default:
            break;
        }
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        if (holdingMouse) {
            mouseCurrentX = newx;
            mouseCurrentY = newy;
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        switch(button) {
        case Input.MOUSE_LEFT_BUTTON:
            if (holdingMouse && !Utility.withinRange(mouseClickedX, mouseClickedY, x, y, 20)) {
                for (Hero hero : heroes) {
                    if (selected == hero.getID()) {
                        if (y >= TextBox.textBoxYPos) {
                            y = TextBox.textBoxYPos;
                        } else if (y <= StatBox.statBoxYPos + 40) {
                            y = StatBox.statBoxYPos + 40;
                        }
                        hero.commandMove(x, y);
                        if (hero.isFighter()) {
                            for (Enemy enemy : enemies) {
                                if (enemy.inside(x, y)) {
                                    hero.setPriorityTarget(enemy);
                                }
                            }
                        } else {
                            for (Hero ally : heroes) {
                                if (ally.inside(x, y)) {
                                    hero.setAllyPriorityTarget(ally);
                                }
                            }
                        }

                        movePointer.restart();
                        movePointerX = x - 10;
                        movePointerY = y - 20;
                    }
                }
            }
            holdingMouse = false;
            break;
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        mouseCurrentX = newx;
        mouseCurrentY = newy;
        showStarHealDescription = false;
        drawRangeHover = -1;
        heroHover = -1;
        for (Hero hero : heroes) {
            if (selected != hero.getID() && hero.inside(newx, newy)) {
                drawRangeHover = hero.getID();
            }
            if (hero.inside(newx, newy)) {
                heroHover = hero.getID();
            }
        }
        if (selected != SELECT_NONE
                && heroes[selected].abilityEquippedIndex(
                        Ability_Names.STAR_HEAL) == 0) {
            if (newx > 40 && newx < 90 && newy > 7 && newy <= 57) {
                showStarHealDescription = true;
            }
        }
    }

    public void castStarHeal() {
        statbox.castStarHeal();
        if (selected != SELECT_NONE
                && heroes[selected].useEnergy(20)) {
            if (!heroes[0].isDown())
                heroes[0].receiveHealing(20);
            if (!heroes[1].isDown())
                heroes[1].receiveHealing(20);
            if (!heroes[2].isDown())
                heroes[2].receiveHealing(20);
            if (!heroes[3].isDown())
                heroes[3].receiveHealing(20);
        }
    }

    public static Hero[] getHeroes() {
        return heroes;
    }

    private void setupUIAnims() {
        try {
            movePointer = new Animation();
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 1.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 2.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 3.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 4.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 5.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 6.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 7.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 8.png"), 100);
            movePointer.addFrame(new Image("assets/ui/Move Pointer/Move Pointer 9.png"), 100);
            movePointer.setLooping(false);
            movePointer.stop();
        } catch (SlickException e) {
            System.err.println("Error creating Images.");
        }
    }

}