import static junit.framework.TestCase.assertEquals;
import java.awt.Rectangle;

public class BallTest {

    private Ball ball;
    private Paddle paddle = new Paddle();
    private Brick brick = new Brick(200, 200);

    //Hits top
    private void testMoveData1(){
        ball = new Ball();
        ball.setX(15);
        ball.setY(1);
        ball.setXDir(-1);
        ball.setYDir(-1);
    }

    //Hits left
    private void testMoveData2(){
        ball = new Ball();
        ball.setX(3);
        ball.setY(56);
        ball.setXDir(-1);
        ball.setYDir(1);
    }

    //Hits right
    private void testMoveData3(){
        ball = new Ball();
        ball.setX(398);
        ball.setY(56);
        ball.setXDir(1);
        ball.setYDir(-1);
    }

    //Regular move
    private void testMoveData4(){
        ball = new Ball();
        ball.setX(200);
        ball.setY(200);
        ball.setXDir(-1);
        ball.setYDir(-1);
    }

    // Left paddle hit
    private void testPaddleCollData1(){
        ball = new Ball();
        ball.setX(205);
        ball.setY(360);
        ball.setXDir(1);
        ball.setYDir(1);
    }
    private void testPaddleCollData2(){
        ball = new Ball();
        ball.setX(205);
        ball.setY(360);
        ball.setXDir(-1);
        ball.setYDir(1);
    }

    // Middle paddle hit
    private void testPaddleCollData3() {
        ball = new Ball();
        ball.setX(220);
        ball.setY(360);
        ball.setXDir(-1);
        ball.setYDir(1);
    }

    // Right paddle hit
    private void testPaddleCollData4(){
        ball = new Ball();
        ball.setX(245);
        ball.setY(360);
        ball.setXDir(1);
        ball.setYDir(1);
    }
    private void testPaddleCollData5(){
        ball = new Ball();
        ball.setX(245);
        ball.setY(360);
        ball.setXDir(-1);
        ball.setYDir(1);
    }

    // Hit brick
    private void testBlockCollData1(){
        ball = new Ball();
        ball.setX(205);
        ball.setY(200);
        ball.setXDir(1);
        ball.setYDir(1);
        ball.spriteBoundary = new Rectangle(205, 200-6, 6, 6);
    }
    private void testBlockCollData2(){
        ball = new Ball();
        ball.setX(205);
        ball.setY(192);
        ball.setXDir(1);
        ball.setYDir(-1);
        ball.spriteBoundary = new Rectangle(205, 192+6, 6, 6);
    }
    private void testBlockCollData3(){
        ball = new Ball();
        ball.setX(200);
        ball.setY(197);
        ball.setXDir(1);
        ball.setYDir(-1);
        ball.spriteBoundary = new Rectangle(200-6, 197, 6, 6);
    }
    private void testBlockCollData4(){
        ball = new Ball();
        ball.setX(250);
        ball.setY(197);
        ball.setXDir(-1);
        ball.setYDir(-1);
        ball.spriteBoundary = new Rectangle(250+6, 197, 6, 6);
    }

    @org.junit.Test
    public void testMove() {
        testMoveData1();
        ball.move();
        assertEquals(-1, ball.getXDir());
        assertEquals(1, ball.getYDir());

        testMoveData2();
        ball.move();
        assertEquals(1, ball.getXDir());
        assertEquals(1, ball.getYDir());

        testMoveData3();
        ball.move();
        assertEquals(-1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testMoveData4();
        ball.move();
        assertEquals(199, ball.getX());
        assertEquals(199, ball.getY());
    }

    @org.junit.Test
    public void testHandleCollisionWithPaddle() {
        testPaddleCollData1();
        ball.handleCollisionWithPaddle(paddle);
        assertEquals(-1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testPaddleCollData2();
        ball.handleCollisionWithPaddle(paddle);
        assertEquals(-1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testPaddleCollData3();
        ball.handleCollisionWithPaddle(paddle);
        assertEquals(-1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testPaddleCollData4();
        ball.handleCollisionWithPaddle(paddle);
        assertEquals(1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testPaddleCollData5();
        ball.handleCollisionWithPaddle(paddle);
        assertEquals(1, ball.getXDir());
        assertEquals(-1, ball.getYDir());
    }

    @org.junit.Test
    public void testHandleCollisionWithBrick(){
        testBlockCollData1();
        ball.handleCollisionWithBrick(brick);
        assertEquals(1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testBlockCollData2();
        ball.handleCollisionWithBrick(brick);
        assertEquals(1, ball.getXDir());
        assertEquals(1, ball.getYDir());

        testBlockCollData3();
        ball.handleCollisionWithBrick(brick);
        assertEquals(-1, ball.getXDir());
        assertEquals(-1, ball.getYDir());

        testBlockCollData4();
        ball.handleCollisionWithBrick(brick);
        assertEquals(1, ball.getXDir());
        assertEquals(-1, ball.getYDir());
    }
}