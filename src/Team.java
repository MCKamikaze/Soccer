import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Team {
	public final int NUM_PLAYERS = 1;
	private Player [] players;
	private Color tColor;
	private int side;
	
	public Team(){
		this(Color.WHITE,0);
	}
	public Team(Color c, int side){
		tColor = c;
		this.side = side;
		players = new Player[NUM_PLAYERS];
		players[0] = new Goalkeeper(tColor,side);
	}
	
	// rearranges players relatively to width and height
	public void reArrange(int w, int h){
	
		for(int i = 0; i<players.length;i++){
			players[i].goToInitialPosition(w,h);
		}
	
	}
	public void drawMe(Graphics g){
		for(int i = 0; i<players.length;i++){
			players[i].drawMe(g);
		}
	}
	// team is not moveable
	public void move(Ball b){
		for(int i = 0; i<players.length;i++){
			players[i].decideWhatToDo(b,false);
			players[i].move();
		}
	}
	
	public void checkBall(Ball b){
		for(int i=0;i<players.length;i++){
			double dist = Math.sqrt(
					(b.getX()-players[i].getX())*(b.getX()-players[i].getX())+
					(b.getY()-players[i].getY())*(b.getY()-players[i].getY()));
			
			if(dist<2*b.RADIUS){ // AI
				players[i].decideWhatToDo(b,true);
			}
		}
	}
	
	public void getOrder(Ball b, KeyEvent e){
		for(int i = 0; i<players.length;i++){
			if(e==null)
				move(b);
			else 
			{
				switch(e.getKeyCode())
				{
				case KeyEvent.VK_UP: // move forward
					players[i].setCenter(players[i].getX()+
							players[i].getSpeed()*Math.cos(players[i].getAlpha()),
							players[i].getY()+
							players[i].getSpeed()*Math.sin(players[i].getAlpha()));
					break;
				case KeyEvent.VK_DOWN: // move backward
					players[i].setCenter(players[i].getX()-
							players[i].getSpeed()*Math.cos(players[i].getAlpha()),
							players[i].getY()-
							players[i].getSpeed()*Math.sin(players[i].getAlpha()));
				break;
				case KeyEvent.VK_LEFT:
					players[i].setAlpha(players[i].getAlpha()-Math.PI/10);
					break;
				case KeyEvent.VK_RIGHT:
					players[i].setAlpha(players[i].getAlpha()+Math.PI/10);
					break;
				}
			}
		}
		
	}
}
