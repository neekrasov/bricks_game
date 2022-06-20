import java.awt.Image;
import java.awt.Rectangle;

class PowerBrick extends Brick {

    public PowerBrick(PlayField pf, BrickPile bp, Rectangle p, Image img) {
        super(pf, bp, img, p);
    }

    public void hitBy(Puck p) {
        _isDead = true;
        if (_bp.unbrokenCount() == 0) {
            _pf.getMatch().win();
        }
        p.getVelocity().reverseY();
        p.setDirection(p.getDirection()+100);
    }
}
