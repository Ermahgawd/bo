
public class Brick extends Sprite implements Commonparams{

    private boolean destroyed;

    public Brick(int x, int y){
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    private void initBrick(int x, int y) {

        this.x = x;
        this.y = y;

        destroyed = false;

    }

    public boolean isDestroyed() {

        return destroyed;
    }

    public void setDestroyed(boolean val) {

        destroyed = val;
    }
}