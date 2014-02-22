package Mechanics;

import org.newdawn.slick.Animation;

import Equipment.W_Group;

public abstract class Weapon extends Equip {

    private int attack;
    private int attackspeed;
    private boolean isHeal;
    private int range;
    private boolean hasSpark;
    private W_Group group;

    public Weapon(String name, W_Group group, boolean isHeal, int atk, int aspd,
            int range, boolean hasSpark) {
        super(name, Equip.WEAPON);
        attack = atk;
        attackspeed = aspd;
        this.group = group;
        this.isHeal = isHeal;
        this.range = range;
        this.hasSpark = hasSpark;
    }

    public Weapon(String name, W_Group group, boolean isHeal, int atk, int aspd,
            int range, boolean hasSpark, String ability) {
        super(name, Equip.WEAPON, ability);
        attack = atk;
        attackspeed = aspd;
        this.group = group;
        this.isHeal = isHeal;
        this.range = range;
        this.hasSpark = hasSpark;
    }

    /** @return the group. */
    public W_Group getGroup() { return group; }

    /** @return the attack damage. */
    public int getAttack() { return attack; }

    /** Set attack damage. */
    public void setAttack(int attack) { this.attack = attack; }

    /** @return the attack speed. */
    public int getAttackSpeed() { return attackspeed; }

    /** Set attack speed. */
    public void setAttackSpeed(int attackspeed) { this.attackspeed = attackspeed; }

    /** @return the attack range. */
    public int getRange() { return range; }

    /** Set the attack range. */
    public void setRange(int range) { this.range = range; }

    /** @return whether this weapon heals. */
    public boolean getIsHeal() { return isHeal; }

    /** Set whether this weapon heals. */
    public void setIsHeal(boolean isHeal) { this.isHeal = isHeal; }

    /** @return whether this weapon has a spark. */
    public boolean getHasSpark() { return hasSpark; }

    /** Set whether this weapon heals. */
    public void setHasSpark(boolean hasSpark) { this.hasSpark = hasSpark; }

    /** Returns this weapon's animation. */
    public abstract Animation getAnimation(int hero);

    /** Returns this weapon's spark animation, if it has one. */
    public abstract Animation getSparkAnimation();

}
