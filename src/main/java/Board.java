import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel

public class Board extends JPanel implements Commonparams {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];
    private boolean inGame = true;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        bricks = new Brick[N_OF_BRICKS];
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    private void gameInit() {

        ball = new Ball();
        paddle = new Paddle();

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                bricks[k] = new Brick(j * Commonparams.BRICK_WIDTH, i * Commonparams.BRICK_HEIGHT);
                k++;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        Shape balls = new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getHeight(), ball.getWidth());
        g2d.draw(balls);
        Shape paddles = new Rectangle2D.Double(paddle.getX(), paddle.getY(), 50, 5);
        g2d.draw(paddles);

        for (int i = 0; i < N_OF_BRICKS; i++) {
            if (!bricks[i].isDestroyed()) {
                Shape bricksh = new Rectangle2D.Double(bricks[i].getX(), bricks[i].getY(), bricks[i].getWidth(), bricks[i].getHeight());
                g2d.draw(bricksh);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commonparams.WIDTH - metr.stringWidth(message)) / 2,
                Commonparams.WIDTH / 2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {

            ball.move();
            paddle.move();
            checkCollision();
            repaint();
        }
    }

    private void stopGame() {

        inGame = false;
        timer.cancel();
    }

    private void checkCollision() {
        handleEndGame();

        if ((ball.getRect()).intersects(paddle.getRect())) {
            ball.handleCollisionWithPaddle(paddle);
        }

        for (int i = 0; i < N_OF_BRICKS; i++) {

            if (!bricks[i].isDestroyed() && (ball.getRect()).intersects(bricks[i].getRect())) {

                ball.handleCollisionWithBrick(bricks[i]);

                bricks[i].setDestroyed(true);
            }
        }

    }

    private void handleEndGame(){
        if (ball.y+3 > Commonparams.BOTTOM_EDGE) {
            stopGame();
        }

        for (int i = 0, j = 0; i < N_OF_BRICKS; i++) {

            if (bricks[i].isDestroyed()) {
                j++;
            }

            if (j == N_OF_BRICKS) {
                message = "Victory";
                stopGame();
            }
        }
    }
}
