import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SoccerField {
	private int width, height;
	private Gate leftGate, rightGate;
	
	public SoccerField(){
		leftGate = new Gate();
		rightGate = new Gate();
	}
	public void setSize(int w, int h){
		width = w;
		height = h;
	}
	
	public Rectangle getBorders(){
		// return left-top corner and width-height
		return new Rectangle(20,10,width-40,height-20);
	}
	
	public void drawMe(Graphics g){
		int delta = width/15;
		// background
		for(int i=0;i<15;i++){
			if(i%2==0)
				g.setColor(new Color(100,200,100));
			else 
				g.setColor(new Color(120,230,120));
			g.fillRect(i*delta, 0, delta, height);
		}
		// foreground
		g.setColor(Color.WHITE);
		g.drawRect(20, 10, width-40, height-20);
		g.drawRect(21, 11, width-42, height-22);
		g.drawRect(22, 12, width-44, height-24);
		
		g.drawLine(width/2, 10, width/2, height-10);
		g.drawOval(width/2-100, height/2-100, 200, 200);
		g.fillOval(width/2-10, height/2-10, 20, 20);
		
		// goalkeeper area
		
		g.drawRect(20, height/2-100, 100, 200);
		g.drawRect(width - 120, height/2-100, 100, 200);
		
		leftGate.setAll(20, height/2-70, 20, 140);
		rightGate.setAll(width - 40, height/2-70, 20, 140);
		leftGate.drawMe(g);
		rightGate.drawMe(g);
	}
}
