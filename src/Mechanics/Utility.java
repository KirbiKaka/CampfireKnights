package Mechanics;

import org.newdawn.slick.geom.Vector2f;


public abstract class Utility {

    /** Returns true if x, y and x2, y2 are no further than 5 apart. */
    public static boolean approximatelyEqual(int x, int y, int x2, int y2) {
        if (Math.abs(x - x2) < 5 && Math.abs(y - y2) < 5) {
            return true;
        }
        return false;
    }

    /** Returns true if endX and endY are within range distance of
     * startX and startY. Squishes Y values into pseudo 3-D. */
    public static boolean withinRange(int startX, int startY, int endX, int endY, int range) {
        Vector2f distance = new Vector2f(endX - startX, (int) ((endY - startY) * 1.5));
        if (range >= distance.length()) {
            return true;
        }
        return false;
    }

    /** Returns true if x and y are inside the object with coordinates
     *  objectX, objectY, and size sizeX and sizeY. */
    public static boolean inside(
            int objectX, int objectY, int sizeX, int sizeY,
            int x, int y) {
        return x >= objectX && x <= objectX + sizeX &&
                y >= objectY && y <= objectY + sizeY;
    }
}
