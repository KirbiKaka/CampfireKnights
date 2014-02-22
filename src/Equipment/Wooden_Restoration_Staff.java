package Equipment;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Mechanics.Ability_Names;
import Mechanics.Hero_Names;

public class Wooden_Restoration_Staff extends Mechanics.Weapon{

    public Wooden_Restoration_Staff() {
        super("Wooden Restoration Staff", W_Group.RESTORATION_STAFF, true, 3, 2150, 250, true, Ability_Names.STAR_HEAL);
    }

    @Override
    public Animation getAnimation(int hero) {
        try {
            Image[] attackAnim = new Image[7];
            int[] attackAnimTime = new int[7];
            switch (hero) {
            case Hero_Names.OPTIMIST_ID:
                attackAnim[0] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 1.png");
                attackAnimTime[0] = 700;
                attackAnim[1] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 2 7.png");
                attackAnimTime[1] = 150;
                attackAnim[2] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 3.png");
                attackAnimTime[2] = 150;
                attackAnim[3] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 4.png");
                attackAnimTime[3] = 150;
                attackAnim[4] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 5.png");
                attackAnimTime[4] = 150;
                attackAnim[5] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 6.png");
                attackAnimTime[5] = 750;
                attackAnim[6] = new Image("assets/heroes/Kirthas Attacks/Staff/Kirthas Attack - Staff 2 7.png");
                attackAnimTime[6] = 100;
                return new Animation(attackAnim, attackAnimTime);
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Animation getSparkAnimation() {
        try {
            Image[] attackAnimSpark = new Image[8];
            int[] attackAnimSparkTime = new int[8];
            attackAnimSpark[0] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 1.png");
            attackAnimSparkTime[0] = 50;
            attackAnimSpark[1] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 2.png");
            attackAnimSparkTime[1] = 50;
            attackAnimSpark[2] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 3.png");
            attackAnimSparkTime[2] = 50;
            attackAnimSpark[3] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 4.png");
            attackAnimSparkTime[3] = 50;
            attackAnimSpark[4] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 5.png");
            attackAnimSparkTime[4] = 50;
            attackAnimSpark[5] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 6.png");
            attackAnimSparkTime[5] = 50;
            attackAnimSpark[6] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 7.png");
            attackAnimSparkTime[6] = 50;
            attackAnimSpark[7] = new Image("assets/effects/Attack Sparks/Staff/Attack Spark - Staff 8.png");
            attackAnimSparkTime[7] = 50;
            return new Animation(attackAnimSpark, attackAnimSparkTime);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }

}
