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

        x += dx;
        this.spriteBoundary = new Rectangle(x, y, 50, 3);

        if (x <= 0) {
            x = 0;
        }

        if (x >= WIDTH - 50) {
            x = WIDTH - 50;
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

        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
    }

    public void setdx_test(int dx){
        this.dx = dx;
    }
}