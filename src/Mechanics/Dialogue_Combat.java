package Mechanics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Dialogue_Combat {

    private static Random random = new Random();
    private static Color optimist = new Color(224, 255, 255);
    private static Color rational = new Color(255, 170, 170);
    private static Color apathetic = new Color(173, 255, 47);
    private static Color cynic = new Color(255, 255, 200);

    public static void optimist_Select_Basic() {
        if (Optimist_Select_Basic == null || Optimist_Select_Basic.isEmpty()) {
            Optimist_Select_Basic = new ArrayList<String>();
            Optimist_Select_Basic.add("Let's roll!");
            Optimist_Select_Basic.add("'m ready when you are!");
            Optimist_Select_Basic.add("Ready to go!");
            Optimist_Select_Basic.add("Howdy.");
            Optimist_Select_Basic.add("Let's get rockin'!");
            Optimist_Select_Basic.add("Let's get started!");
            Optimist_Select_Basic.add("We got this!");
            Optimist_Select_Basic.add("Where we goin'?");
            Optimist_Select_Basic.add("What's up?");
            Optimist_Select_Basic.add("What're we doin'?");
            Collections.shuffle(Optimist_Select_Basic);
        } try {
            Image portrait = new Image("assets/ui/portraits/Kirthas_basic_right.png");
            TextBox.writeText(Optimist_Select_Basic.remove(0), optimist, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void rational_Select_Basic() {
        if (Rational_Select_Basic == null || Rational_Select_Basic.isEmpty()) {
            Rational_Select_Basic = new ArrayList<String>();
            Rational_Select_Basic.add("Yeah?");
            Rational_Select_Basic.add("Let's get going.");
            Rational_Select_Basic.add("We should get a move on.");
            Rational_Select_Basic.add("We should get moving.");
            Rational_Select_Basic.add("Let's hurry!");
            Rational_Select_Basic.add("I'm ready.");
            Rational_Select_Basic.add("Got a plan?");
            Rational_Select_Basic.add("What's the plan?");
            Rational_Select_Basic.add("Good to go.");
            Rational_Select_Basic.add("It's time to move.");
            Collections.shuffle(Rational_Select_Basic);
        } try {
            Image portrait = new Image("assets/ui/portraits/Isabelle_basic_right.png");
            TextBox.writeText(Rational_Select_Basic.remove(0), rational, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void apathetic_Select_Basic() {
        if (Apathetic_Select_Basic == null || Apathetic_Select_Basic.isEmpty()) {
            Apathetic_Select_Basic = new ArrayList<String>();
            Apathetic_Select_Basic.add("Yes?");
            Apathetic_Select_Basic.add("Let's continue.");
            Apathetic_Select_Basic.add("Go on...");
            Apathetic_Select_Basic.add("Our objective?");
            Apathetic_Select_Basic.add("Let's move forward.");
            Apathetic_Select_Basic.add("Hmmm...");
            Apathetic_Select_Basic.add("Light our path.");
            Apathetic_Select_Basic.add("I am ready.");
            Apathetic_Select_Basic.add("I'm listening.");
            Apathetic_Select_Basic.add("Greetings.");
            Collections.shuffle(Apathetic_Select_Basic);
        } try {
            Image portrait = new Image("assets/ui/portraits/Damian_basic_right.png");
            TextBox.writeText(Apathetic_Select_Basic.remove(0), apathetic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void cynic_Select_Basic() {
        if (Cynic_Select_Basic == null || Cynic_Select_Basic.isEmpty()) {
            Cynic_Select_Basic = new ArrayList<String>();
            Cynic_Select_Basic.add("What now?");
            Cynic_Select_Basic.add("Yeah?");
            Cynic_Select_Basic.add("Let's go already.");
            Cynic_Select_Basic.add("Whatever it takes.");
            Cynic_Select_Basic.add("This better be good.");
            Cynic_Select_Basic.add("Uh huh.");
            Cynic_Select_Basic.add("Get on with it.");
            Cynic_Select_Basic.add("C'mon already.");
            Cynic_Select_Basic.add("Do this, do that.");
            Cynic_Select_Basic.add("Yeah, yeah, I hear you.");
            Collections.shuffle(Cynic_Select_Basic);
        } try {
            Image portrait = new Image("assets/ui/portraits/Sky_basic_right.png");
            TextBox.writeText(Cynic_Select_Basic.remove(0), cynic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void randomize_Warning_Splash() {
        while (true) {
            int hero = random.nextInt(4);
            if (!CombatState.getHeroes()[hero].isDown()) {
                switch(hero) {
                case 0: optimist_Warning_Splash(); break;
                case 1: rational_Warning_Splash(); break;
                case 2: apathetic_Warning_Splash(); break;
                case 3: cynic_Warning_Splash(); break;
                }
                return;
            }
        }
    }

    public static void optimist_Warning_Splash() {
        if (Optimist_Warning_Splash == null || Optimist_Warning_Splash.isEmpty()) {
            Optimist_Warning_Splash = new ArrayList<String>();
            Optimist_Warning_Splash.add("Watch out y'all! He can hit a bunch of us at once!");
            Optimist_Warning_Splash.add("Woah, that guy can hit everyone near you, too!");
            Optimist_Warning_Splash.add("Normally I'd say stick together, but that guy can hit more than one of us!");
            Optimist_Warning_Splash.add("Don't stick too close, y'all, or he'll hit all of us!");
            Collections.shuffle(Optimist_Warning_Splash);
        } try {
            Image portrait = new Image("assets/ui/portraits/Kirthas_stern_right.png");
            TextBox.writeTextPriority(Optimist_Warning_Splash.remove(0), optimist, portrait, 4000);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void rational_Warning_Splash() {
        if (Rational_Warning_Splash == null || Rational_Warning_Splash.isEmpty()) {
            Rational_Warning_Splash = new ArrayList<String>();
            Rational_Warning_Splash.add("Spread out! His attack can hit more than one of us!");
            Rational_Warning_Splash.add("Don't stick together or we might all get hit by his attack!");
            Rational_Warning_Splash.add("Move it! He'll hit more than one of us if we're too close together!");
            Rational_Warning_Splash.add("Spread out! Don't let his attacks hit all of us at once!");
            Collections.shuffle(Rational_Warning_Splash);
        } try {
            Image portrait = new Image("assets/ui/portraits/Isabelle_basic_right.png");
            TextBox.writeTextPriority(Rational_Warning_Splash.remove(0), rational, portrait, 4000);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void apathetic_Warning_Splash() {
        if (Apathetic_Warning_Splash == null || Apathetic_Warning_Splash.isEmpty()) {
            Apathetic_Warning_Splash = new ArrayList<String>();
            Apathetic_Warning_Splash.add("Careful! His attacks will also hit everyone around you!");
            Apathetic_Warning_Splash.add("Watch yourselves, this foe's attacks will hit everything around you!");
            Apathetic_Warning_Splash.add("We should spread out! This foe can hit more than one of us at a time!");
            Apathetic_Warning_Splash.add("If we're too close together, his attacks will hit all of us!");
            Collections.shuffle(Apathetic_Warning_Splash);
        } try {
            Image portrait = new Image("assets/ui/portraits/Damian_stern_right.png");
            TextBox.writeTextPriority(Apathetic_Warning_Splash.remove(0), apathetic, portrait, 4000);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void cynic_Warning_Splash() {
        if (Cynic_Warning_Splash == null || Cynic_Warning_Splash.isEmpty()) {
            Cynic_Warning_Splash = new ArrayList<String>();
            Cynic_Warning_Splash.add("Get away from each other! His attacks will hit everyone around you!");
            Cynic_Warning_Splash.add("Don't stick together! He can hit more than one of us at once!");
            Cynic_Warning_Splash.add("Shit, move aside! If we're too close together, he can hit more than one of us!");
            Cynic_Warning_Splash.add("Out of the way! Don't huddle together or we'll all get hit!");
            Collections.shuffle(Cynic_Warning_Splash);
        } try {
            Image portrait = new Image("assets/ui/portraits/Sky_stern_right.png");
            TextBox.writeTextPriority(Cynic_Warning_Splash.remove(0), cynic, portrait, 4000);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    private static ArrayList<String> Optimist_Select_Basic;
    private static ArrayList<String> Rational_Select_Basic;
    private static ArrayList<String> Apathetic_Select_Basic;
    private static ArrayList<String> Cynic_Select_Basic;
    private static ArrayList<String> Optimist_Select_Injured;
    private static ArrayList<String> Rational_Select_Injured;
    private static ArrayList<String> Apathetic_Select_Injured;
    private static ArrayList<String> Cynic_Select_Injured;

    private static ArrayList<String> Optimist_Move_Basic;
    private static ArrayList<String> Rational_Move_Basic;
    private static ArrayList<String> Apathetic_Move_Basic;
    private static ArrayList<String> Cynic_Move_Basic;
    private static ArrayList<String> Optimist_Move_Injured;
    private static ArrayList<String> Rational_Move_Injured;
    private static ArrayList<String> Apathetic_Move_Injured;
    private static ArrayList<String> Cynic_Move_Injured;

    private static ArrayList<String> Optimist_Attack_Basic;
    private static ArrayList<String> Rational_Attack_Basic;
    private static ArrayList<String> Apathetic_Attack_Basic;
    private static ArrayList<String> Cynic_Attack_Basic;
    private static ArrayList<String> Optimist_Attack_Injured;
    private static ArrayList<String> Rational_Attack_Injured;
    private static ArrayList<String> Apathetic_Attack_Injured;
    private static ArrayList<String> Cynic_Attack_Injured;

    private static ArrayList<String> Optimist_Warning_Splash;
    private static ArrayList<String> Rational_Warning_Splash;
    private static ArrayList<String> Apathetic_Warning_Splash;
    private static ArrayList<String> Cynic_Warning_Splash;

    private static ArrayList<String> Optimist_StarHeal_Desc;
    private static ArrayList<String> Optimist_StarHeal_Basic;
    private static ArrayList<String> Optimist_StarHeal_Injured;
    private static ArrayList<String> Optimist_StarHeal_Insufficient;
}
