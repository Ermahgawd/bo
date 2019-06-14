import org.junit.Test;

import static org.junit.Assert.*;

public class PaddleTest {

    private Paddle paddle;

    //Regular move
    private void testData1(){
        paddle = new Paddle();
        paddle.setdx_test(1);
        paddle.setY(360);
        paddle.setX(125);
    }

    // Left edge
    private void testData2(){
        paddle = new Paddle();
        paddle.setY(360);
        paddle.setX(0);
    }

    // Right edge
    private void testData3(){
        paddle = new Paddle();
        paddle.setY(360);
        paddle.setX(350);
    }

    @Test
    public void testMove() {
        testData1();
        paddle.move();
        assertEquals(361, paddle.getY());
        assertEquals(126, paddle.getX());

        testData2();
        paddle.move();
        assertEquals(0, paddle.getX());

        testData3();
        paddle.move();
        assertEquals(350, paddle.getX());
    }
}
