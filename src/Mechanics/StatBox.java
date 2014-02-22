package Mechanics;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class StatBox {

    public static final int statBoxYPos = 70;

    private int xPos, yPos;
    Hero[] heroes;
    private boolean imagesInitialized = false;
    private HashMap<String, Image> images =
            new HashMap<String, Image>();

    private Animation[] starHealAnimations;

    public StatBox(Hero[] heroes) {
        xPos = 0;
        yPos = 0;
        this.heroes = heroes;
    }

    void draw(Graphics g) {
        if (!imagesInitialized) {
            try {
                images.put("statbox", new Image("assets/ui/statBox.png"));
                images.put("healthbar", new Image("assets/ui/healthbar.png"));
                images.put("energybar", new Image("assets/ui/energybar.png"));
                images.put("healthbar_empty", new Image("assets/ui/healthbarEmpty.png"));
                images.put("energybar_empty", new Image("assets/ui/energybarEmpty.png"));
                images.put("healthbar_black", new Image("assets/ui/healthbarBlack.png"));
                images.put("energybar_black", new Image("assets/ui/energybarBlack.png"));
                images.put("optimist_icon", new Image("assets/heroes/Kirthas_Icon.png"));
                images.put("rational_icon", new Image("assets/heroes/Isabelle_Icon.png"));
                images.put("apathetic_icon", new Image("assets/heroes/Damian_Icon.png"));
                images.put("cynic_icon", new Image("assets/heroes/Sky_Icon.png"));
                images.put("starheal_icon", new Image("assets/ui/Star Heal.png"));
                createAbilityAnimations();
                imagesInitialized = true;
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(images.get("statbox"), xPos, yPos);
        g.drawImage(images.get("optimist_icon"), 370, 0);
        g.drawImage(images.get("rational_icon"), 580, 2);
        g.drawImage(images.get("apathetic_icon"), 370, 30);
        g.drawImage(images.get("cynic_icon"), 580, 33);

        if(CombatState.selected != CombatState.SELECT_NONE
                && heroes[CombatState.selected].abilityEquippedIndex(Ability_Names.STAR_HEAL) == 0) {
            g.drawImage(images.get("starheal_icon"), 40, 7);
        }

        drawHealth(g, heroes[0], 410, 10);
        drawHealth(g, heroes[1], 620, 10);
        drawHealth(g, heroes[2], 410, 40);
        drawHealth(g, heroes[3], 620, 40);
        drawEnergy(g, heroes[0], 410, 18);
        drawEnergy(g, heroes[1], 620, 18);
        drawEnergy(g, heroes[2], 410, 48);
        drawEnergy(g, heroes[3], 620, 48);

    }

    public void drawStarHeal(Hero[] heroes) {
        if (starHealAnimations != null) {
            for (int i = 1; i < 5; i++) {
                int heroX = heroes[i - 1].xPos();
                int heroY = heroes[i - 1].yPos();
                int heroSizeX = heroes[i - 1].xSize();
                int heroSizeY = heroes[i - 1].ySize();
                if (!starHealAnimations[i].isStopped()) {
                    starHealAnimations[i].draw(heroX - 8,
                            heroY + heroSizeY + 8 - 448);
                }
            }
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

    void update(int delta) {

    }

    void castStarHeal() {
        if (starHealAnimations != null) {
            for (int i = 1; i < 5; i++) {
                starHealAnimations[i].restart();
            }
        }
    }

    void createAbilityAnimations() throws SlickException {
        starHealAnimations = new Animation[5];
        Image[] optimistAttackAnimSpark = new Image[7];
        int[] optimistAttackAnimSparkTime = new int[7];
        optimistAttackAnimSpark[0] = new Image("assets/effects/Star Heal/Star Heal 1 7.png");
        optimistAttackAnimSparkTime[0] = 25;
        optimistAttackAnimSpark[1] = new Image("assets/effects/Star Heal/Star Heal 2 6.png");
        optimistAttackAnimSparkTime[1] = 25;
        optimistAttackAnimSpark[2] = new Image("assets/effects/Star Heal/Star Heal 3 5.png");
        optimistAttackAnimSparkTime[2] = 25;
        optimistAttackAnimSpark[3] = new Image("assets/effects/Star Heal/Star Heal 4.png");
        optimistAttackAnimSparkTime[3] = 150;
        optimistAttackAnimSpark[4] = new Image("assets/effects/Star Heal/Star Heal 3 5.png");
        optimistAttackAnimSparkTime[4] = 100;
        optimistAttackAnimSpark[5] = new Image("assets/effects/Star Heal/Star Heal 2 6.png");
        optimistAttackAnimSparkTime[5] = 50;
        optimistAttackAnimSpark[6] = new Image("assets/effects/Star Heal/Star Heal 1 7.png");
        optimistAttackAnimSparkTime[6] = 25;
        for (int i = 1; i < 5; i++) {
            starHealAnimations[i] = new Animation(optimistAttackAnimSpark, optimistAttackAnimSparkTime);
            starHealAnimations[i].stop();
            starHealAnimations[i].setLooping(false);
        }
    }

}
