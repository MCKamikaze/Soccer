import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball implements Moveable{
	public final int RADIUS = 10;
	private double bx,by;
	private double alpha;
	private double ball_speed;
	
	public Ball(){
		ball_speed = 3;
		alpha = 2*Math.PI*Math.random();
	}
	
	@Override
	public void goToInitialPosition(int w, int h) {
		bx = w/2;
		by = h/2;
		
	}

	@Override
	public void move() {
		double dx,dy;
		dx = Math.cos(alpha);
		dy = Math.sin(alpha);
		
		bx =( bx+ball_speed*dx);
		by = ( by+ball_speed*dy);
	}
	
	public void drawMe(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(new File("ball.jpg"));
		}
		catch(IOException ex){
			System.out.println("File ball.jpg was not found\n");
		}
		TexturePaint tp = new TexturePaint(bi,
										new Rectangle((int)bx+10,(int)by,50,50));
		g2d.setPaint(tp);
		
//		g.setColor(Color.GRAY);
		g2d.fillOval((int)bx-RADIUS,(int) by-RADIUS, 2*RADIUS, 2*RADIUS);
		g2d.setColor(Color.BLACK);
		g2d.drawOval((int)bx-RADIUS, (int)by-RADIUS, 2*RADIUS, 2*RADIUS);
		g2d.dispose();
	}
	public double getX(){
		return bx;
	}
	public double getY(){
		return by;
	}
	public void setCenter(int x, int y){
		bx = x;
		by = y;
	}
	// return the x-component of ball direction
	public double dirX(){
		return Math.cos(alpha);
	}
	
	
	// change angle when ball meets borders 
	public void checkAngle(SoccerField sf){
		double dx,dy;
		Rectangle r = sf.getBorders();
		
		dx = Math.cos(alpha);
		dy = Math.sin(alpha);
		
		if(dx>0){ // check right border
			if(bx+RADIUS+dx*ball_speed>r.getMaxX())
				alpha = Math.PI-alpha;
		}
		else{ // check left border
			if(bx-RADIUS+dx*ball_speed<r.getMinX())
				alpha = Math.PI-alpha;
	}
		if(dy>0){  // check bottom border
			if(by+RADIUS+dy*ball_speed>r.getMaxY())
				alpha = -alpha;
		}
		else{ // check top border
			if(by-RADIUS+dy*ball_speed<r.getMinY())
				alpha = -alpha;
			
		}
		
	}

	public void setAlpha(double angle) {
		alpha = angle;
		
	}

}
