package Equipment;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Mechanics.Hero_Names;

public class Wooden_Bow extends Mechanics.Weapon {

    public Wooden_Bow() {
        super("Wooden Bow", W_Group.BOW, false, 4, 2450, 300, false);
    }

    @Override
    public Animation getAnimation(int hero) {
        try {
            Image[] attackAnim = new Image[10];
            int[] attackAnimTime = new int[10];
            switch (hero) {
            case Hero_Names.OPTIMIST_ID:
                attackAnim[0] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 1.png");
                attackAnimTime[0] = 700;
                attackAnim[1] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 2.png");
                attackAnimTime[1] = 150;
                attackAnim[2] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 3.png");
                attackAnimTime[2] = 150;
                attackAnim[3] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 4.png");
                attackAnimTime[3] = 150;
                attackAnim[4] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 5.png");
                attackAnimTime[4] = 150;
                attackAnim[5] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 6.png");
                attackAnimTime[5] = 150;
                attackAnim[6] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 7.png");
                attackAnimTime[6] = 700;
                attackAnim[7] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 8.png");
                attackAnimTime[7] = 100;
                attackAnim[8] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 9.png");
                attackAnimTime[8] = 100;
                attackAnim[9] = new Image("assets/heroes/Kirthas Attacks/Bow/Kirthas Attack - Bow 10.png");
                attackAnimTime[9] = 100;
                return new Animation(attackAnim, attackAnimTime);
            case Hero_Names.CYNIC_ID:
                attackAnim[0] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 1.png");
                attackAnimTime[0] = 700;
                attackAnim[1] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 2.png");
                attackAnimTime[1] = 150;
                attackAnim[2] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 3.png");
                attackAnimTime[2] = 150;
                attackAnim[3] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 4.png");
                attackAnimTime[3] = 150;
                attackAnim[4] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 5.png");
                attackAnimTime[4] = 150;
                attackAnim[5] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 6.png");
                attackAnimTime[5] = 150;
                attackAnim[6] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 7.png");
                attackAnimTime[6] = 700;
                attackAnim[7] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 8.png");
                attackAnimTime[7] = 100;
                attackAnim[8] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 9.png");
                attackAnimTime[8] = 100;
                attackAnim[9] = new Image("assets/heroes/Sky Attacks/Bow/Sky Attack - Bow 10.png");
                attackAnimTime[9] = 100;
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
