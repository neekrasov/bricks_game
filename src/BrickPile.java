//package bricks;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Vector;

/* Контейнер кирпичей, которые еще не введены в игру. */
/* Игрок выиграл, если все кирпичи разбиты. */

public class BrickPile {
	/*
	 @_pf    - игровое поле
	 @_briks - множество кирпичей
	 @_rows  - количество линий кирпичей
	 @cols   - количество кирпичей в каждой линии
	 */

    private PlayField _pf;
    private Vector _bricks;
    private final int _rows = 1;
    private final int _cols = 3;

/*
	public BrickPile(PlayField pf, Image img) {
		_pf = pf;
		_bricks = new Vector();
		int startx = 80; 
		int x = startx, y = 10;
	
		for (int r = 0; r < _rows; r++) {
			for (int c = 0; c < _cols; c++) {
				Rectangle pos = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
				
				Brick b = new Brick(_pf, this, img, pos);
				pf.addSprite(b);
				_bricks.addElement(b);
        
				x += img.getWidth(null);
			}
			
			y += img.getHeight(null) + 2;
			x = startx;
		}
	}
	*/

    public BrickPile(PlayField pf, Image img1, Image img2, Image img3, Image img4, Image img5) {
        _pf = pf;
        _bricks = new Vector(); //!!!
        int startx = 80;
        int x = startx, y = 10;

        for (int r = 0; r < _rows; r++) {
            for (int c = 0; c < _cols; c++) {
                Rectangle pos = new Rectangle(x, y, img1.getWidth(null), img1.getHeight(null));

                // В зависимости от номера кирпича добавим на игровое поле
                // либо простой кирпич, либо крепкий.
                double k = Math.random();

                if (k <= 0.15 && k >= 0.1) {
                    PowerBrick pb = new PowerBrick(_pf, this, pos, img3);
                    pf.addSprite(pb);
                    _bricks.addElement(pb);
                } else if (k <= 0.3 && k >= 0.15) {
                    HardBrick hb = new HardBrick(_pf, this, pos, img2, img2);
                    pf.addSprite(hb);
                    _bricks.addElement(hb);
                } else if (k <= 0.35 && k >= 0.3) {
                    WallBrick b = new WallBrick(_pf, this, pos, img5);
                    pf.addSprite(b);
                    _bricks.addElement(b);
                } else if (k <= 0.4 && k >= 0.35) {
                    ArmorBrick b = new ArmorBrick(_pf, this, pos, img4);
                    pf.addSprite(b);
                    _bricks.addElement(b);
                } else {
                    Brick b = new Brick(_pf, this, img1, pos);
                    pf.addSprite(b);
                    _bricks.addElement(b);
                }

                x += img1.getWidth(null);
            }

            y += img1.getHeight(null) + 2;
            x = startx;
        }
    }

    public int unbrokenCount() {
        int result = 0;

        for (int i = 0; i < _bricks.size(); i++) {
            if (_bricks.elementAt(i) instanceof WallBrick) {
                continue;
            } else if (((Brick) _bricks.elementAt(i)).isDead()) result++;
        }

        return result;
    }

    public int brokenCount() {
        return _bricks.size() - unbrokenCount();
    }
}
