import java.awt.*;

public class Ball extends Sprite implements Commonparams {

    private int xdir;
    private int ydir;

    public Ball() {
        super(INIT_BALL_X, INIT_BALL_Y, BALL_WIDTH, BALL_HEIGHT);
        initBall();
    }

    private void initBall() {

        xdir = -1;
        ydir = -1;

//        resetState();
    }

    public void move() {

        this.setX(this.getX() + xdir);
        y += ydir;
        this.spriteBoundary = new Rectangle(this.getX(), y, 6, 6);

        if (this.getX() <= 3) {
            setXDir(1);
        }

        if (this.getX() >= (WIDTH - 3)) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }

    public void handleCollisionWithPaddle(Paddle paddle){
        int leftLimit = (int)paddle.getRect().getMinX() + (int)(50/3);
        int rightLimit = (int)paddle.getRect().getMaxX() - (int)(50/3);

        if(this.getX() < leftLimit){
            this.setXDir(-1);
            this.setYDir(-1);
        }
        else if(this.getX() > rightLimit){
            this.setXDir(1);
            this.setYDir(-1);
        }
        else{
            this.setYDir(-1 * this.getYDir());
        }
    }

    public void handleCollisionWithBrick(Brick brick){
        Rectangle ballRect = this.getRect();
        Rectangle brickRect = brick.getRect();

        Rectangle intersection = ballRect.intersection(brickRect);
        if(intersection.width > intersection.height){
            this.setYDir(this.getYDir() * -1);
        }
        else if(intersection.width < intersection.height){
            this.setXDir(this.getXDir() * -1);
        }
        else{
            this.setYDir(this.getYDir() * -1);
            this.setXDir(this.getXDir() * -1);
        }
    }

    public void setXDir(int x) {
        this.xdir = x;
    }

    public void setYDir(int y) {
        this.ydir = y;
    }

    public int getXDir() {
        return xdir;
    }

    public int getYDir() {
        return ydir;
    }
}