

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Projectile {

	double x,y,dx,dy;
	int angle;
	double vitesse;
	Image imgProjectile;
	Rectangle hitBox;
	boolean visible=false;
	
	
	static float G=9.8f;
	
	
	public Projectile(double x, double y, int angle, double vitesse) {
		super();
		
		this.x = x;
		this.y = y;
		
		this.angle= angle;
		this.vitesse=vitesse;
		
		
		this.dx = this.vitesse*Math.cos(Math.toRadians(this.angle-90));
		this.dy = this.vitesse*Math.sin(Math.toRadians(this.angle-90));
		
		try {
			this.imgProjectile=Toolkit.getDefaultToolkit().getImage("images/projectile.png");
		}
		catch (Exception ex) {
			System.out.println("ERROR:"+ex);
		}
		
		hitBox= new Rectangle((int)x,(int)y-30,48,39);
	}
	
	public void aug(int val) {
		this.vitesse+=val;
	}
	
	
	public void bouge() {
		
		dy+=0.15;
		
		this.x=this.x+dx;
		this.y=this.y+dy;
		hitBox.x=(int) x;
		hitBox.y=(int) y-30;
		
	}
	
	
	public void dessiner(Graphics g) {
		
		if(visible) {
			g.drawImage(imgProjectile, (int)x, (int)y-30, 48, 39, null);
		}

		

		
	}
	
}


