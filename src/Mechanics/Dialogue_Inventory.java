package Mechanics;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dialogue_Inventory {

    private static Random random = new Random();
    private static Color optimist = new Color(224, 255, 255);
    private static Color rational = new Color(255, 170, 170);
    private static Color apathetic = new Color(173, 255, 47);
    private static Color cynic = new Color(255, 255, 200);

    public static void optimist_Select_Resto_Staff() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Savin' lives is the best kind of magic."; break;
        case 1: text = "MEDIC!! Oh wait, that's me."; break;
        case 2: text = "Man, I wonder if I could bring back my ol' pet mouse."; break;
        case 3: text = "Sticks and stones may break our bones, but this'll patch us right up!"; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Kirthas_basic_right.png");
            TextBox.writeText(text, optimist, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void rational_Select_Resto_Staff() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Field medic!"; break;
        case 1: text = "Better than any first-aid kit."; break;
        case 2: text = "Someone once saved me with one of these... I'll always remember her."; break;
        case 3: text = "Besides just mending wounds, healers tend to boost group morale."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Isabelle_basic_right.png");
            TextBox.writeText(text, rational, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void apathetic_Select_Resto_Staff() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "I'm all too happy to return to others' service."; break;
        case 1: text = "Ah, the familiar feel of a staff and its restorative aura."; break;
        case 2: text = "\"Listen closely, for they are health to those who find them.\""; break;
        case 3: text = "It does not matter how many we kill if we do not live to see our victory."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Damian_basic_right.png");
            TextBox.writeText(text, apathetic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void cynic_Select_Resto_Staff() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Don't worry, I won't completely forget about healing you guys."; break;
        case 1: text = "Let's play a game called \"Don't let the guy with the healing stick die,\" okay?"; break;
        case 2: text = "The only downside is you can't really clobber someone with this."; break;
        case 3: text = "One of these would have been really handy a long time ago."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Sky_basic_right.png");
            TextBox.writeText(text, cynic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }




    public static void optimist_Select_Bow() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "When the curtains draw, it's time to take a bow. Heh."; break;
        case 1: text = "Really makes me miss my ol' shooter."; break;
        case 2: text = "90% of the time, hunters use a trap. The other 10%? Arrow in the face."; break;
        case 3: text = "How 'bout I catch us some rabbits?"; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Kirthas_basic_right.png");
            TextBox.writeText(text, optimist, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void rational_Select_Bow() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Range is always an advantage."; break;
        case 1: text = "Time to provide cover fire!"; break;
        case 2: text = "It's crucial to keep a calm, steady aim in the midst of battle."; break;
        case 3: text = "Every soldier is trained to use long range weaponry."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Isabelle_basic_right.png");
            TextBox.writeText(text, rational, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void apathetic_Select_Bow() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Now I don't have to get my robes dirty charging into battle."; break;
        case 1: text = "Sometimes it's okay to let a little faith guide your arrows."; break;
        case 2: text = "In the stories of war, hell always breaks loose after the first volley of arrows."; break;
        case 3: text = "Using a bow requires a keen eye."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Damian_basic_right.png");
            TextBox.writeText(text, apathetic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void cynic_Select_Bow() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Long range rampage!"; break;
        case 1: text = "No formal training, but I'll be damned if I'm not a good shot."; break;
        case 2: text = "You know that thing you hear in stories where people deflect arrows with their swords? Yeah, no."; break;
        case 3: text = "The further away, the cleaner your hands are afterwards."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Sky_basic_right.png");
            TextBox.writeText(text, cynic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }




    public static void optimist_Select_Sword_Shield() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Nothin' like a shield to protect your friends!"; break;
        case 1: text = "I got your backs, guys, and this shield's got mine."; break;
        case 2: text = "The shield's not a bad umbrella when it's raining, too."; break;
        case 3: text = "Lemme know if we find a shield with a badass dragon on it!"; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Kirthas_basic_right.png");
            TextBox.writeText(text, optimist, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void rational_Select_Sword_Shield() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Provides plenty of defensive options."; break;
        case 1: text = "A perfect balance between offense and defence."; break;
        case 2: text = "Block first, then counter."; break;
        case 3: text = "Brings me back to my training days..."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Isabelle_basic_right.png");
            TextBox.writeText(text, rational, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void apathetic_Select_Sword_Shield() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "The crusaders' weapon of choice in times long past."; break;
        case 1: text = "A coat of arms tells a family's history."; break;
        case 2: text = "Ah, this reminds me of the story of the holy knight Sir Davion..."; break;
        case 3: text = "When faith does not protect you, a sturdy shield will suffice."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Damian_basic_right.png");
            TextBox.writeText(text, apathetic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void cynic_Select_Sword_Shield() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Hey, someone's gotta watch my hide. Why not do it myself?"; break;
        case 1: text = "In a pinch, smack someone with the shield. It still hurts."; break;
        case 2: text = "Hey is this where the family crest- uh, actually nevermind."; break;
        case 3: text = "A little bit of \"they die,\" a little bit of \"I don't die.\""; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Sky_basic_right.png");
            TextBox.writeText(text, cynic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }



    public static void optimist_Select_Daggers() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "If you lose one stabby thing, you still have another!"; break;
        case 1: text = "I'd never backstab my friends. Everyone else on the other hand..."; break;
        case 2: text = "Met you once, cut you twice!"; break;
        case 3: text = "Slice to meet you!"; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Kirthas_basic_right.png");
            TextBox.writeText(text, optimist, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void rational_Select_Daggers() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Speed is the heart of battle."; break;
        case 1: text = "Perfect for finding the chinks in their armor."; break;
        case 2: text = "Hit 'em faster than they can hit you."; break;
        case 3: text = "The best part is how quietly these kill."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Isabelle_basic_right.png");
            TextBox.writeText(text, rational, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void apathetic_Select_Daggers() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "To end a life swiftly and painlessly."; break;
        case 1: text = "In these times, two blades makes good insurance."; break;
        case 2: text = "Sometimes it's better to appear unarmed and keep your weapons hidden."; break;
        case 3: text = "Never judge a weapon by its size."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Damian_basic_right.png");
            TextBox.writeText(text, apathetic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    public static void cynic_Select_Daggers() {
        int select = random.nextInt(4);
        String text = "";
        switch(select) {
        case 0: text = "Sharpened and ready."; break;
        case 1: text = "Just the size to keep out of sight."; break;
        case 2: text = "Two blades, twice the fun, eh?"; break;
        case 3: text = "Not unlike my old pocket knife."; break;
        }
        try {
            Image portrait = new Image("assets/ui/portraits/Sky_basic_right.png");
            TextBox.writeText(text, cynic, portrait);
        } catch (SlickException e) { e.printStackTrace(); }
    }

    /*public static void randomize_Warning_Splash() {
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

    private static ArrayList<String> Optimist_Select_Basic;
    private static ArrayList<String> Rational_Select_Basic;
    private static ArrayList<String> Apathetic_Select_Basic;*/

}
