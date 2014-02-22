package Mechanics;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class Hero extends Drawable {

    private String name;
    private int id;
    private String[] abilities = {"", "", "", ""};

    private int xPos, yPos;
    private int xSize, ySize;
    private double saveX, saveY;
    private Image image;
    private String imageDir;
    private Animation attackAnim;
    private Animation attackAnimSpark;
    private int xRand, yRand;

    private int state = Default_States.STANDING;
    private int moveCommandX = -1;
    private int moveCommandY = -1;

    private int maxHealth = 50;
    private int health = 50;
    private int maxEnergy = 50;
    private int energy = 50;
    private int energyRegen = 3000;
    private int energyRegenCooldown = energyRegen;
    private double speed = 0.1;
    private int range = 100;
    private int damage = 2;
    private int attackSpeed = 2000;
    private int attackCooldown = attackSpeed;
    private boolean isDown = false;

    private boolean isFighter = true;
    private Enemy target;
    private Enemy priorityTarget;
    private Hero allyTarget;
    private Hero allyPriorityTarget;

    public Hero(String name, int id, String imageDir) {
        this.name = name;
        xPos = yPos = 0;
        this.imageDir = imageDir;
        xSize = 48;
        ySize = 64;
        this.id = id;
    }

    public Hero(String name, int id, String imageDir, int xPos, int yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.imageDir = imageDir;
        xSize = 48;
        ySize = 64;
        this.id = id;
    }

    public void update(int delta) {
        if (isDown()) {
            state = Default_States.DOWN;
        }
        if (CombatState.isPaused) {
            return;
        }
        if (health <= 0) {
            isDown = true;
        }
        if (!isDown && energy < maxEnergy) {
            energyRegenCooldown -= delta;
            if (energyRegenCooldown <= 0) {
                energy += 1;
                energyRegenCooldown += energyRegen;
            }
        }
        switch(state) {
        case Default_States.MOVING:
            attackCooldown = attackSpeed;
            attackAnim.restart();
            attackAnim.stop();
            Vector2f move = null;
            if (moveCommandX == -1) {
                if (moveCommandY == -1)
                    move = new Vector2f(0, 0);
                else
                    move = new Vector2f(0, moveCommandY - yPos);
            } else if (moveCommandY == -1) {
                move = new Vector2f(moveCommandX - xPos, 0);
            } else
                move = new Vector2f(moveCommandX - xPos, moveCommandY - yPos);
            move.normalise();
            saveX += move.getX() * speed * delta;
            saveY += move.getY() * speed * delta;
            if (Math.abs(saveX) >= 1.00 || Math.abs(saveY) >= 1.00) {
                moveRelative((int) saveX, (int) saveY);
                saveX -= (int) saveX;
                saveY -= (int) saveY;
            }
            if (Utility.approximatelyEqual(xPos, yPos, moveCommandX, moveCommandY)) {
                state = Default_States.STANDING;
                moveCommandX = -1;
                moveCommandY = -1;
            }
            break;
        case Default_States.ATTACKING:
            if (isFighter) {
                if (target != null) {
                    if (!Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                            target.xPos() + target.xSize() / 2, target.yPos() + target.ySize(),
                            range)) {
                        state = Default_States.STANDING;
                        break;
                    }
                    if (attackCooldown <= 0) {
                        target.takeDamage(damage);
                        attackCooldown = attackSpeed;
                        if (attackAnimSpark != null) {
                            attackAnimSpark.restart();
                            Random r = new Random();
                            xRand = target.xPos() + target.xSize() / 2 - 40 + r.nextInt(16);
                            yRand = target.yPos() + target.ySize() / 2 - 40 + r.nextInt(16);
                        }
                        if (target.isDead()) {
                            state = Default_States.STANDING;
                            target = null;
                            break;
                        }
                    } else {
                        attackCooldown -= delta;
                    }
                }
            } else {
                if (allyTarget != null) {
                    if (!Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                            allyTarget.xPos() + allyTarget.xSize() / 2, allyTarget.yPos() + allyTarget.ySize(),
                            range)) {
                        state = Default_States.STANDING;
                        break;
                    }
                    if (attackCooldown <= 0) {
                        allyTarget.receiveHealing(damage);
                        attackCooldown = attackSpeed;
                        if (attackAnimSpark != null) {
                            attackAnimSpark.restart();
                            Random r = new Random();
                            xRand = allyTarget.xPos() + allyTarget.xSize() / 2 - 40 + r.nextInt(16);
                            yRand = allyTarget.yPos() + allyTarget.ySize() / 2 - 40 + r.nextInt(16);
                        }
                        if (allyTarget.isDown()) {
                            state = Default_States.STANDING;
                            allyTarget = null;
                            break;
                        }
                    } else {
                        attackCooldown -= delta;
                    }
                }
            }
            break;
        default:
            attackCooldown = attackSpeed;
            attackAnim.restart();
            attackAnim.stop();
            break;
        }
    }

    public void createImage(String imageDir) {
        if (image == null) {
            try {
                image = new Image(imageDir);
            } catch (SlickException e) {
                e.printStackTrace();
            }
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

    public void drawSpark() {
        if (attackAnimSpark != null) {
            if (!attackAnimSpark.isStopped()) {
                attackAnimSpark.draw(xRand, yRand);
            }
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " has " + health + " health remaining.");
    }

    public void receiveHealing(int healing) {
        health += healing;
        if (health > maxHealth) {
            health = maxHealth;
        }
        System.out.println(name + " now has " + health + " health!");
    }

    public boolean hasEnergy(int req) {
        return energy >= req;
    }

    public boolean useEnergy(int use) {
        if (energy >= use) {
            energy -= use;
            return true;
        }
        return false;
    }

    public void moveRelative(int x, int y) {
        xPos += x;
        yPos += y;
    }

    public void setPosition(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void commandAttack(Enemy target) {
        state = Default_States.ATTACKING;
        this.target = target;
    }

    public void commandHeal(Hero target) {
        state = Default_States.ATTACKING;
        this.allyTarget = target;
    }

    public void findTarget(ArrayList<Enemy> enemies) {
        if (priorityTarget != null) {
            if (priorityTarget.isDead()) {
                state = Default_States.STANDING;
                priorityTarget = null;
                return;
            }
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    priorityTarget.xPos() + priorityTarget.xSize() / 2, priorityTarget.yPos() + priorityTarget.ySize(), range)) {
                commandAttack(priorityTarget);
                return;
            }
        }

        if (target != null) {
            if (target.isDead()) {
                state = Default_States.STANDING;
                target = null;
                return;
            }
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    target.xPos() + target.xSize() / 2, target.yPos() + target.ySize(), range)) {
                commandAttack(target);
                return;
            }
        }

        ArrayList<Enemy> targets = new ArrayList<Enemy>();
        for (Enemy enemy : enemies) {
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    enemy.xPos() + enemy.xSize() / 2, enemy.yPos() + enemy.ySize(), range)) {
                targets.add(enemy);
            }
        }
        if (!targets.isEmpty()) {
            Random r = new Random();
            commandAttack(targets.get(r.nextInt(targets.size())));
        }
    }

    public void findAllyTarget(Hero[] heroes) {
        if (allyPriorityTarget != null) {
            if (allyPriorityTarget.isDown() || allyPriorityTarget.isFullHealth()) {
                state = Default_States.STANDING;
                allyPriorityTarget = null;
                return;
            }
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    allyPriorityTarget.xPos() + allyPriorityTarget.xSize() / 2, allyPriorityTarget.yPos() + allyPriorityTarget.ySize(), range)) {
                commandHeal(allyPriorityTarget);
                return;
            }
        }

        if (allyTarget != null) {
            if (allyTarget.isDown() || allyTarget.isFullHealth()) {
                state = Default_States.STANDING;
                allyTarget = null;
                return;
            }
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    allyTarget.xPos() + allyTarget.xSize() / 2, allyTarget.yPos() + allyTarget.ySize(), range)) {
                commandHeal(allyTarget);
                return;
            }
        }

        ArrayList<Hero> targets = new ArrayList<Hero>();
        for (Hero ally : heroes) {
            if (Utility.withinRange(xPos + xSize / 2, yPos + ySize,
                    ally.xPos() + ally.xSize() / 2, ally.yPos() + ally.ySize(), range)) {
                if (!ally.isFullHealth()) {
                    targets.add(ally);
                }
            }
        }
        if (!targets.isEmpty()) {
            Random r = new Random();
            commandHeal(targets.get(r.nextInt(targets.size())));
        }
    }

    public void commandMove(int x, int y) {
        state = Default_States.MOVING;
        resetAttack();
        moveCommandX = x - xSize / 2;
        moveCommandY = y - ySize + 5;
    }

    public void commandMoveX(int x) {
        state = Default_States.MOVING;
        resetAttack();
        moveCommandX = x - xSize / 2;
    }

    public void commandMoveY(int y) {
        state = Default_States.MOVING;
        resetAttack();
        moveCommandY = y - ySize + 5;
    }

    public void stop() {
        state = Default_States.STANDING;
        resetAttack();
        moveCommandX = -1;
        moveCommandY = -1;
    }

    public void stopX() {
        moveCommandX = -1;
        if (moveCommandY == -1)
            state = Default_States.STANDING;
    }

    public void stopY() {
        moveCommandY = -1;
        if (moveCommandX == -1)
            state = Default_States.STANDING;
    }

    public boolean inside(int x, int y) {
        if (x >= xPos && x <= xPos + xSize) {
            if (y >= yPos && y <= yPos + ySize) {
                return true;
            }
        }
        return false;
    }

    public void changeWeapon(Weapon weap) {
        if (EquippedItems.getEquip(id, Equip.WEAPON) != null
                && EquippedItems.getEquip(id, Equip.WEAPON).getAbility() != null) {
            removeAbility(EquippedItems.getEquip(id, Equip.WEAPON).getAbility());
        }
        EquippedItems.setEquip(id, weap);
        if (weap.getAbility() != null) {
            addAbility(weap.getAbility());
        }
        damage = weap.getAttack();
        attackSpeed = weap.getAttackSpeed();
        attackCooldown = attackSpeed;
        range = weap.getRange();
        isFighter = !weap.getIsHeal();
        if (weap.getAnimation(id) != null)
            attackAnim = weap.getAnimation(id);
        if (weap.getHasSpark()) {
            attackAnimSpark = weap.getSparkAnimation();
            attackAnimSpark.setLooping(false);
            attackAnimSpark.stop();
        } else {
            attackAnimSpark = null;
        }
    }

    public void setupAttackAnim(Image[] frames, int[] durations) {
        attackAnim = new Animation(frames, durations);
    }

    public void setupAttackAnimSpark(Image[] frames, int[] durations) {
        attackAnimSpark = new Animation(frames, durations);
        attackAnimSpark.setLooping(false);
        attackAnimSpark.stop();
    }

    public void resetAttack() {
        if (attackAnim != null) {
            attackAnim.stop();
            attackAnim.restart();
        }
        attackCooldown = attackSpeed;
    }

    public boolean isFullHealth() {
        return health >= maxHealth;
    }

    public void setFullHealth() { health = maxHealth; }

    public int getState() { return state; }

    public int xPos() { return xPos; }

    @Override
    public int yPos() { return yPos; }

    public int getRange() { return range; }

    public Enemy getTarget() { return target; }

    public Hero getAllyTarget() { return allyTarget; }

    public int xSize() { return xSize; }

    public int ySize() { return ySize; }

    public int getID() { return id; }

    public String getName() { return name; }

    public int getHealth() { return health; }

    public int getMaxHealth() { return maxHealth; }

    public int getEnergy() { return energy; }

    public int getMaxEnergy() { return maxEnergy; }

    public int getEnergyRegen() { return energyRegen; }

    public void setEnergyRegen(int er) { energyRegen = er; }

    public int getDamage() { return damage; }

    public boolean isFighter() { return isFighter; }

    public boolean isDown() { return isDown; }

    public void setRange(int range) { this.range = range; }

    public void setAttackSpeed(int aspd) {
        attackSpeed = aspd;
        attackCooldown = aspd;
    }

    public void setMaxHealth(int hp) {
        maxHealth = hp;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void setMaxEnergy(int mp) {
        maxEnergy = mp;
        if (energy > maxEnergy) {
            energy = maxEnergy;
        }
    }

    public void setDamage(int damage) { this.damage = damage; }

    public void setIsFighter(boolean isFighter) { this.isFighter = isFighter; }

    public void setPriorityTarget(Enemy target) { priorityTarget = target; }

    public void setAllyPriorityTarget(Hero target) { allyPriorityTarget = target; }

    public String[] getAbilities() { return abilities; }

    public int abilityEquippedIndex(String ability) {
        for (int i = 0; i < abilities.length; i++) {
            if (abilities[i].equals(ability)) {
                return i;
            }
        }
        return -1;
    }

    public int addAbility(String ability) {
        for (int i = 0; i < abilities.length; i++) {
            if (abilities[i] == null || abilities[i].equals("")) {
                abilities[i] = ability;
                return i;
            }
        }
        return -1;
    }

    public boolean removeAbility(String ability) {
        for (int i = 0; i < abilities.length; i++) {
            if (abilities[i].equals(ability)) {
                abilities[i] = "";
                return true;
            }
        }
        return false;
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

}
