package Equipment;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Mechanics.Hero_Names;

public class Iron_Daggers extends Mechanics.Weapon {

    public Iron_Daggers() {
        super("Iron Daggers", W_Group.DAGGER, false, 2, 1300, 100, false);
    }

    @Override
    public Animation getAnimation(int hero) {
        try {
            Image[] attackAnim = new Image[8];
            int[] attackAnimTime = new int[8];
            switch (hero) {
            case Hero_Names.OPTIMIST_ID:
                attackAnim[0] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 1.png");
                attackAnimTime[0] = 600;
                attackAnim[1] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 2.png");
                attackAnimTime[1] = 75;
                attackAnim[2] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 3.png");
                attackAnimTime[2] = 200;
                attackAnim[3] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 4.png");
                attackAnimTime[3] = 100;
                attackAnim[4] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 5.png");
                attackAnimTime[4] = 75;
                attackAnim[5] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 6.png");
                attackAnimTime[5] = 75;
                attackAnim[6] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 7.png");
                attackAnimTime[6] = 75;
                attackAnim[7] = new Image("assets/heroes/Kirthas Attacks/Daggers/Kirthas Attack - Daggers 8.png");
                attackAnimTime[7] = 100;
                return new Animation(attackAnim, attackAnimTime);
            case Hero_Names.APATHETIC_ID:
                attackAnim[0] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 1.png");
                attackAnimTime[0] = 600;
                attackAnim[1] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 2.png");
                attackAnimTime[1] = 150;
                attackAnim[2] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 3.png");
                attackAnimTime[2] = 150;
                attackAnim[3] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 4.png");
                attackAnimTime[3] = 75;
                attackAnim[4] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 5.png");
                attackAnimTime[4] = 75;
                attackAnim[5] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 6.png");
                attackAnimTime[5] = 75;
                attackAnim[6] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 7.png");
                attackAnimTime[6] = 75;
                attackAnim[7] = new Image("assets/heroes/Damian Attacks/Daggers/Damian Attack - Daggers 8.png");
                attackAnimTime[7] = 100;
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
