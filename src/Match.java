//package bricks;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Frame --> JFrame
MenuBar --> JMenuBar
Menu --> JMenu
Image --> Imageicon (и отказ от MediaTracker)
Vector --> ArrayList
*/

/* Сеанс игры. */

class Match {
	private Frame _top;
	private PuckSupply _ps; //Хранилище шайб.
	private BrickPile _bp; //Хранилище кирпичей.
	private PlayField _pf;
	private MediaTracker _tracker;
	private Image[] _lib;
	private String _message = "";

	public Match() {
		_top = new Frame("Bricks");
		_pf = new PlayField(this);
		_tracker = new MediaTracker(_pf);
        _lib = new Image[7];//_lib = new Image[3];

		_lib[0] = _pf.getToolkit().getImage("src/images/puck.gif");
		_lib[1] = _pf.getToolkit().getImage("src/images/puddle.gif");
		_lib[2] = _pf.getToolkit().getImage("src/images/brick.gif");
        _lib[3] = _pf.getToolkit().getImage("src/images/hard_brick.gif");
        _lib[4] = _pf.getToolkit().getImage("src/images/power_brick.gif");
        _lib[5] = _pf.getToolkit().getImage("src/images/armor_brick.gif");
        _lib[6] = _pf.getToolkit().getImage("src/images/wall_brick.gif");

		_tracker.addImage(_lib[0], 0);
		_tracker.addImage(_lib[1], 0);
		_tracker.addImage(_lib[2], 0);
        _tracker.addImage(_lib[3], 0);

		MenuBar mbar = new MenuBar();
		_top.setMenuBar(mbar);

		Menu file = new Menu("Game");
		MenuItem i1, i2, i3, i4;
		file.add(i1 = new MenuItem("start"));
		file.add(i2 = new MenuItem("pause"));
		file.add(i3 = new MenuItem("resume"));
		file.add(i4 = new MenuItem("exit"));

		mbar.add(file);
		_top.setSize(600, 400);

		i1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start(); //Начать игру.
			}
		});
    
		i2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_pf.stop(); //Приостановить игру.
			}
		});
    
		i3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_pf.start(); //Возобновить игру.
			}
		});
    
		i4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //Завершить игру.
			}
		});
    
		_top.add("Center", _pf);
		_top.show();
	}

	public void start() {
		try {
			_tracker.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
		
		_message = "";
		_pf.clean();

        _bp = new BrickPile(_pf, _lib[2], _lib[3], _lib[4], _lib[5], _lib[6]); //_bp = new BrickPile(_pf, _lib[2]);
        _ps = new PuckSupply(3, _pf, _lib[0]);
   
		_pf.addSprite(new Puddle(_pf,_lib[1]));
		_pf.addSprite(_ps.get());
    
		_pf.start();
	}

	/* Игрок проиграл. */
	public void loose() {
		_message = "You Loose";
		_pf.repaint();
		_pf.stop();
	}

	/* Игрок выиграл. */
	public void win() {
		_message = "You win";
		_pf.repaint();	
		_pf.stop();
	}

	public static void main(String[] args) {
		Match m = new Match();
	} 
  
	public String getMessage() {
		return _message;
	}
}
