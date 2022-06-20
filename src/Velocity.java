//package bricks;

/* Скорость. */

class Velocity {
	/*
	 * @_dx - состовляющая скорости по оси Х
	 * @_dy - состовляющая скорости по оси Y
	 * @_speed - скорость спрайта
	 */

	private double _dx, _dy;
	private int _speed;

	public Velocity(int D, int S) {
		//D - напрвление движения (угол в градусах):
		//0 - движение на восток, 90 - движение на юг.
		//S - скорость (неотрицательна, в условных единицах).
		_speed = S;
		setDirection(D);
	}

	public int getSpeed() {
		return _speed;
	}

	public int getSpeedX() {
		return (int) _dx;
	}

	public int getSpeedY() {
		return (int) _dy;
	}

	public void setDirection(int d) {
		_dx = Math.cos(Math.toRadians(d)) * (double) _speed;
		_dy = Math.sin(Math.toRadians(d)) * (double) _speed;
	}

	public int getDirection() {
		return ((int) Math.toDegrees(Math.atan2(_dy, _dx)))%360; //без "%360"?
	}

	/* инвертировать направление движения */
	public void reverse() {
		_dx = -_dx; //reverseX()
		_dy = -_dy; //reverseY()
	}
	
	/* инвертировать направление по оси Х*/
	public void reverseX() {
		_dx = -_dx;
	}
	
	/* инвертировать направление по оси Y*/
	public void reverseY() {
		_dy = -_dy;
	}
}
