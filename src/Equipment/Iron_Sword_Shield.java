package Equipment;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Mechanics.Hero_Names;

public class Iron_Sword_Shield extends Mechanics.Weapon {

    public Iron_Sword_Shield() {
        super("Iron Sword and Shield", W_Group.SWORD_SHIELD, false, 2, 1800, 100, false);
    }

    @Override
    public Animation getAnimation(int hero) {
        try {
            Image[] attackAnim = new Image[7];
            int[] attackAnimTime = new int[7];
            switch (hero) {
            case Hero_Names.OPTIMIST_ID:
                attackAnim[0] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 1.png");
                attackAnimTime[0] = 800;
                attackAnim[1] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 2.png");
                attackAnimTime[1] = 150;
                attackAnim[2] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 3.png");
                attackAnimTime[2] = 150;
                attackAnim[3] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 4.png");
                attackAnimTime[3] = 400;
                attackAnim[4] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 5.png");
                attackAnimTime[4] = 100;
                attackAnim[5] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 6.png");
                attackAnimTime[5] = 100;
                attackAnim[6] = new Image("assets/heroes/Kirthas Attacks/Sword and Shield/Kirthas Attack - Sword and Shield 7.png");
                attackAnimTime[6] = 100;
                return new Animation(attackAnim, attackAnimTime);
            case Hero_Names.RATIONAL_ID:
                attackAnim[0] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 1.png");
                attackAnimTime[0] = 800;
                attackAnim[1] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 2.png");
                attackAnimTime[1] = 150;
                attackAnim[2] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 3.png");
                attackAnimTime[2] = 150;
                attackAnim[3] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 4.png");
                attackAnimTime[3] = 400;
                attackAnim[4] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 5.png");
                attackAnimTime[4] = 100;
                attackAnim[5] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 6.png");
                attackAnimTime[5] = 100;
                attackAnim[6] = new Image("assets/heroes/Isabelle Attacks/Sword and Shield/Isabelle Attack - Sword and Shield 7.png");
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
        return null;
    }

}
