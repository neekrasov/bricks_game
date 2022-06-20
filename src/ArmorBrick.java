import java.awt.Image;
import java.awt.Rectangle;

class ArmorBrick extends Brick {
    /* @_hitCount- количество допустимых ударов шайбой кирпич разрушается,
    как только атрибут будет равен нулю @_woundImg- изображение повреждённого кирпича заменяет исходное
    изображение при первом ударе шайбы */
    private int _hitCount = 1;

    public ArmorBrick(PlayField pf, BrickPile bp, Rectangle p, Image img) {
        super(pf, bp, img, p);
    }
    /* * Oбработка соударения с шайбой.
    Как только * значение _hitCount становится равным нулю, * кирпич будет удален с игрового поля */

    public void hitBy(Puck p) {
        if (
                (p._pos.y + p._pos.height >= this._pos.y) &&
                (p._pos.x  < this._pos.x) &&
                (p._pos.x + p._pos.width > this._pos.x + this._pos.width)
        ) {

            _isDead = true;
        } else {
            if (_bp.unbrokenCount() == 0) {
                _pf.getMatch().win();
            }
        }
        p.getVelocity().reverseY();
    }
}
