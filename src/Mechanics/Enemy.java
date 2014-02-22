package Mechanics;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class Enemy extends Drawable {

    private String name;

    private int xPos, yPos;
    private int xSize, ySize;
    Image image;
    private String imageDir;
    private Animation attackAnim;

    private int moveCommandX = -1;
    private int moveCommandY = -1;
    private double saveX = 0;
    private double saveY = 0;

    private int maxHealth = 50;
    private int health = 50;
    private double speed = 0.05;
    private int range = 100;
    private int AoE = 0;
    private int damage = 5;
    private int attackSpeed = 2000;
    private int attackCooldown = 0;

    private boolean isDead = false;

    private Hero target;
    private Hero chaseTarget;

    private boolean warning = false;
    private float time = 8000;

    private int state = Default_States.STANDING;

    public Enemy(String name, String imageDir, int xSize, int ySize) {
        this.name = name;
        xPos = yPos = 0;
        this.imageDir = imageDir;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public Enemy(String name, String imageDir, int xSize, int ySize, int xPos, int yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.imageDir = imageDir;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void update(int delta) {
        if (CombatState.isPaused) {
            return;
        }
        if (health <= 0) {
            isDead = true;
            return;
        }
        time += delta;
        if (warning && time > 10000) {
            Dialogue_Combat.randomize_Warning_Splash();
            time = 0;
        }
        switch(state) {
        case Default_States.MOVING:
            if (chaseTarget != null) {
                commandMove(((chaseTarget.xPos() + chaseTarget.xSize() / 2)), chaseTarget.yPos() + chaseTarget.ySize());
            }
            Vector2f move = new Vector2f(moveCommandX - xPos, moveCommandY - yPos);
            move.normalise();
            saveX += move.getX() * speed * delta;
            saveY += move.getY() * speed * delta;
            if (Math.abs(saveX) >= 1.00 || Math.abs(saveY) >= 1.00) {
                moveRelative((int) saveX, (int) saveY);
                saveX -= (int) saveX;
                saveY -= (int) saveY;
            }
            break;
        case Default_States.ATTACKING:
            if (target != null) {
                if (!Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                        target.xPos() + target.xSize() / 2, target.yPos() + target.ySize(),
                        range)) {
                    state = Default_States.MOVING;
                    break;
                }
                if (attackCooldown <= 0) {
                    if (AoE != 0) {
                        for (Hero hero : CombatState.getHeroes()) {
                            if (Utility.withinRange(hero.xPos() + hero.xSize() / 2, hero.yPos() + hero.ySize(),
                                    target.xPos() + target.xSize() / 2, target.yPos() + target.ySize(), AoE))
                                hero.takeDamage(damage);
                        }
                    } else {
                        target.takeDamage(damage);
                    }
                    attackCooldown += attackSpeed;
                } else {
                    attackCooldown -= delta;
                }
            }
        default:
            break;
        }
    }

    @Override
    public void draw(Graphics g) {
        createImage(imageDir);
        if (state == Default_States.ATTACKING && attackAnim != null) {
            if (CombatState.isPaused) {
                attackAnim.stop();
            } else {
                attackAnim.start();
            }
            attackAnim.draw(xPos, yPos);
        } else {
            g.drawImage(image, xPos, yPos);
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " has " + health + " health remaining.");
    }

    public void moveRelative(int x, int y) {
        xPos += x;
        yPos += y;
    }

    public void setPosition(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void commandAttack(Hero target) {
        state = Default_States.ATTACKING;
        this.target = target;
        chaseTarget = target;
    }

    public void commandMove(int x, int y) {
        state = Default_States.MOVING;
        moveCommandX = x - xSize / 2;
        moveCommandY = y - ySize + 5;
    }

    public boolean inside(int x, int y) {
        if (x >= xPos && x <= xPos + xSize) {
            if (y >= yPos && y <= yPos + ySize) {
                return true;
            }
        }
        return false;
    }

    public void findTarget(Hero[] heroes) {
        int extraRange = 20;
        if (target != null && !target.isDown()) {
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    target.xPos() + target.xSize() / 2, target.yPos() + target.ySize(), range - 50)) {
                commandAttack(target);
                return;
            }
            extraRange = 0;
            target = null;
        }

        ArrayList<Hero> targets = new ArrayList<Hero>();
        for (Hero hero : heroes) {
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    hero.xPos() + hero.xSize() / 2, hero.yPos() + hero.ySize(), range - extraRange )) {
                if (!hero.isDown()) {
                    targets.add(hero);
                }
            }
        }
        if (!targets.isEmpty()) {
            Random r = new Random();
            commandAttack(targets.get(r.nextInt(targets.size())));
        } else {
            state = Default_States.MOVING;
            if (chaseTarget == null || chaseTarget.isDown()) {
                findChase(heroes);
            }
        }
    }

    public void findChase(Hero[] heroes) {
        int downCount = 0;
        for (Hero down : heroes) {
            if (down.isDown()) {
                downCount++;
            }
        }
        if (downCount == 4) {
            return;
        }
        Random r = new Random();
        int hero = r.nextInt(4);
        while (heroes[hero].isDown()) {
            hero = r.nextInt(4);
        }
        chaseTarget = heroes[hero];
        Vector2f enemy = new Vector2f((chaseTarget.xPos() + chaseTarget.xSize() / 2) - (xPos + xSize / 2),
                (chaseTarget.yPos() + chaseTarget.ySize()) - (yPos - ySize));
        commandMove((int) (enemy.getX()), (int) (enemy.getY()));
    }

    public int getState() { return state; }

    public int xPos() { return xPos; }

    @Override
    public int yPos() { return yPos; }

    public int getRange() { return range; }

    public int getAoE() { return AoE; }

    public Hero getTarget() { return target; }

    public int xSize() { return xSize; }

    public int ySize() { return ySize; }

    public boolean isDead() { return isDead; }

    public void setDamage(int damage) { this.damage = damage; }

    public void setAoE(int aoe) { AoE = aoe; }

    public void setMaxHealth(int hp) { maxHealth = hp; }

    public void setFullHealth() { health = maxHealth; }

    public void setSpeed(double speed) { this.speed = speed; }

    public void setWarning(boolean warning) { this.warning = warning; }

    public void setAttackSpeed(int aspd) {
        attackSpeed = aspd;
        attackCooldown = aspd;
    }

    @Override
    public int compareTo(Drawable other) {
        if (yPos > other.yPos()) {
            return 1;
        } else if (yPos == other.yPos()) {
            return 0;
        }
        return -1;
    }

    public void createImage(String imageDir) {
        if (image == null) {
            try {
                image = new Image(imageDir);
                if (name.equals("Silhouette")) {
                    attackAnim = new Animation();
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 1.png"),
                            900);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 2.png"),
                            100);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 3.png"),
                            100);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 4.png"),
                            700);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 5.png"),
                            50);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 6.png"),
                            50);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 7.png"),
                            50);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 8.png"),
                            50);
                    attackAnim.addFrame(new Image("assets/enemies/Silhouette Attack/Silhouette Attack 9.png"),
                            50);
                } else if (name.equals("Sillow")) {
                    attackAnim = new Animation();
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 1.png"),
                            1200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 2.png"),
                            150);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 3.png"),
                            150);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 4.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 5.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 6.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 7.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 8.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 9.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 10.png"),
                            200);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 11.png"),
                            500);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 12.png"),
                            150);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 13.png"),
                            150);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 14.png"),
                            150);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 15.png"),
                            150);
                    attackAnim.addFrame(new Image("assets/enemies/Sillow Attack/Sillow Attack 16.png"),
                            150);
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

}
