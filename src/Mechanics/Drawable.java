package Mechanics;

import org.newdawn.slick.Graphics;


public abstract class Drawable implements Comparable<Drawable> {
    abstract void draw(Graphics g);
    abstract int yPos();
}
