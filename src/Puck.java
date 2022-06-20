//package bricks;

import java.awt.Image;
import java.awt.Rectangle;

/* Шайба */

class Puck extends MovableSprite {
    /*
     * @_ps - хранилище шайб, двунаправленая ассоциация
     */

    private PuckSupply _ps;

    public Puck(PlayField pf, PuckSupply ps, Image img) {
        super(
                pf,
                img,
                (new Rectangle(pf.getWidth() / 2, pf.getHeight() / 2, img.getWidth(pf), img.getHeight(pf))),
                90,
                6
        );

        _isMoving = true;
        _ps = ps;
    }

    public void move() {
        if (!_isMoving)
            return;

        Rectangle b = _pf.getBoundary();

        _prevPos = _pos;
        _pos.translate(_v.getSpeedX(), _v.getSpeedY());

        /* Oбработка соударения sсо стенами, полом и потолком. */
        if (_pos.x <= b.x - _pos.width) {
            _pos.x = b.x + b.width - _pos.width;
        } else if (_pos.x >= b.x + b.width) {
            _pos.x = b.x;
        } else if (_pos.y <= b.y) {
            _pos.y = b.y;
            _v.reverseY();
        } else if (_pos.y + _pos.height > b.y + b.height) {
            /* Шайба упала на пол */
            _isDead = true;
            if (_ps.size() == 0) {
                _pf.getMatch().loose();
            } else {
                _pf.addSprite(_ps.get());
            }
        }

        /* Обработка соударения с другими спрайтами */
        if (collideWith() != null) {
            _pos = _prevPos;
            collideInto(collideWith());
        }
    }

    /* Реакция на возникновение коллизии. */
    public void collideInto(Sprite s) {
        s.hitBy(this);
    }

    public void hitBy(Puck p) {
        ;
    }

}
