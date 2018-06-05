import java.awt.Color;
import java.awt.Graphics;

public abstract class Player implements Moveable {
	public final int LEFT_SIDE = 0;
	public final int RIGHT_SIDE = 1;
	
	public final int RADIUS=10;
	public final double SPEED = 1.3; // basic speed
	protected double cx,cy;
	protected double alpha;
	protected double playerSpeed;
	protected Color teamColor;
	protected int side;
	
	public Player(){
		this(0,0,0,Color.WHITE,0);
	}
	public Player(int x, int y, double a, Color c, int s){
		setCenter(x,y);
		setAlpha(a);
		teamColor = c;
		side = s;
	}
	public Player(Color c, int s){ // gets color and side
		this(0,0,0,c,s);
		if(s==1) // if this is a right side player
			setAlpha(Math.PI);
	}
	public void setCenter(double x, double y){
		cx = x ;
		cy = y;
	}
	
	public void setAlpha(double a){
		alpha = a;
	}
	public double getX(){
		return cx;
	}
	public double getY(){
		return cy;
	}
	
	
	public abstract void decideWhatToDo(Ball b, boolean control);
	// must be implemented in concrete classes
	public void drawMe(Graphics g){
		g.setColor(teamColor);
		g.fillOval((int)cx-RADIUS, (int)cy-RADIUS, 2*RADIUS, 2*RADIUS);
		g.setColor(Color.BLACK);
		g.drawOval((int)cx-RADIUS, (int)cy-RADIUS, 2*RADIUS, 2*RADIUS);
		g.drawLine((int)cx, (int)cy, (int)(cx+RADIUS*Math.cos(alpha)),
												(int)(cy+RADIUS*Math.sin(alpha)));
	}
	public double getSpeed() {
		return playerSpeed;
	}
	public double getAlpha() {
		return alpha;
	}
	

	
	
}
