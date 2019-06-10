import java.awt.Rectangle;

public class Sprite {

    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Rectangle spriteBoundary;

    public Sprite(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.spriteBoundary = new Rectangle(x, y, width, height);
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected int getY() {
        return y;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected int getHeight() {
        return height;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected int getWidth() {
        return width;
    }

    protected Rectangle getRect() {
        return spriteBoundary;
    }

}