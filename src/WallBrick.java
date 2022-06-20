import java.awt.Image;
import java.awt.Rectangle;

class WallBrick extends Brick {

    public WallBrick(PlayField pf, BrickPile bp, Rectangle p, Image img) {
        super(pf, bp, img, p);
    }
    public void hitBy(Puck p) {
        p.getVelocity().reverseY();
    }
}
