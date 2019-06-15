import java.awt.event.KeyEvent;
import java.awt.*;

public class Paddle extends Sprite implements Commonparams {

    private int dx;

    public Paddle() {

        super(INIT_PADDLE_X, INIT_PADDLE_Y, 50, 3);
        initPaddle();

    }

    private void initPaddle() {
        resetState();
    }


    public void move() {

        this.setX(this.getX() + dx);
        this.spriteBoundary = new Rectangle(this.getX(), y, 50, 3);

        if (this.getX() <= 0) {
            this.setX(0);
        }

        if (this.getX() >= WIDTH - 50) {
            this.setX(WIDTH - 50);
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {

        this.setX(INIT_PADDLE_X);
        y = INIT_PADDLE_Y;
    }

    public void setdx_test(int dx){
        this.dx = dx;
    }
}