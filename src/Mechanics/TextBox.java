package Mechanics;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** TextBox at the bottom of the screen that controls dialogue and observations. */
public class TextBox{

    public static final Color IMPORTANT_TEXT = new Color(255, 90, 60);

    public static final int textBoxYPos = 430;

    private static final double TEXT_SPEED_FACTOR = 0.10;
    /** Number of miliseconds a completed String stays on the TextBox. */
    private static final int TEXT_DURATION = 5000;
    private static String text;

    CampfireKnights game;

    /** How much of the String written so far. */
    private static double charsWritten;
    /** How long the String has been completely written. */
    private static double durationDone;
    private static double durationRequired;
    private static boolean isImportant = false;

    private Image image;
    private int xPos, yPos;
    private static Color textColor = new Color(0, 0, 0);
    public static Image portrait;

    public TextBox(CampfireKnights game) {
        xPos = 0;
        yPos = textBoxYPos;
        durationDone = -1;
        this.game = game;
    }


    public static void writeText(String text) {
        if (durationDone != 0) {
            TextBox.text = text;
            charsWritten = 0;
            durationDone = 0;
            durationRequired = 0;
            isImportant = false;
            textColor = Color.white;
            portrait = null;
        }
    }

    public static void writeText(String text, Color color) {
        if (durationDone != 0 & durationRequired <= 0) {
            TextBox.text = text;
            charsWritten = 0;
            durationDone = 0;
            durationRequired = 0;
            isImportant = false;
            textColor = color;
            portrait = null;
        }
    }

    public static void writeText(String text, Color color, Image icon) {
        if (durationDone != 0 & durationRequired <= 0) {
            TextBox.text = text;
            charsWritten = 0;
            durationDone = 0;
            durationRequired = 0;
            isImportant = false;
            textColor = color;
            portrait = icon;
        }
    }

    public static void writeText(String text, Color color, Image icon, int durationRequired2) {
        if (durationDone != 0 & durationRequired <= 0) {
            TextBox.text = text;
            charsWritten = 0;
            durationDone = 0;
            durationRequired = durationRequired2;
            isImportant = false;
            textColor = color;
            portrait = icon;
        }
    }

    public static void writeTextPriority(String text, Color color, Image icon, int durationRequired2) {
        if (durationRequired <= 0) {
            TextBox.text = text;
            charsWritten = 0;
            durationDone = 0;
            durationRequired = durationRequired2;
            isImportant = true;
            textColor = color;
            portrait = icon;
        }
    }

    public static void clear() {
        TextBox.text = null;
        durationDone = 1;
        durationRequired = 0;
        charsWritten = 0;
        isImportant = false;
        portrait = null;
    }

    void draw(Graphics g) {
        if (image == null) {
            try {
                image = new Image("assets/ui/textBox.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(image, xPos, yPos);
        if (durationDone != -1 && durationDone < TEXT_DURATION) {
            int extraX = 0;
            if (portrait != null) {
                g.drawImage(portrait, xPos + 12, yPos + 12);
                extraX = 147;
            }
            String s = "";
            for (int i = 0; i < charsWritten; i++) {
                s += TextBox.text.charAt(i);
            }
            if (isImportant)
                g.setColor(IMPORTANT_TEXT);
            else
                g.setColor(TextBox.textColor);
            String tempS = s;
            int yOffset = 0;
            while (tempS.length() > 60) {
                if (tempS.charAt(59) != ' ' && tempS.charAt(60) != ' ') {
                    int lastSpace = tempS.substring(0, 60).lastIndexOf(' ');
                    g.drawString(tempS.substring(0, lastSpace), xPos + 30 + extraX, yPos + 20 + yOffset);
                    tempS = tempS.substring(lastSpace + 1);
                } else {
                    g.drawString(tempS.substring(0, 60), xPos + 30 + extraX, yPos + 20 + yOffset);
                    tempS = tempS.substring(60);
                }
                yOffset += 20;
            }
            g.drawString(tempS, xPos + 30 + extraX, yPos + 20 + yOffset);
        }
    }

    void update(int delta) {
        if (TextBox.text != null
                && (game.getCurrentState().getID() != new CombatState().getID()
                || (!CombatState.isPaused ||
                        (CombatState.isPaused && !CombatState.pauseText)))) {
            charsWritten += delta * TEXT_SPEED_FACTOR;
            if (charsWritten >= text.length()) {
                charsWritten = text.length();
                durationDone += delta;
                durationRequired -= delta;
            }
        }
    }

}
