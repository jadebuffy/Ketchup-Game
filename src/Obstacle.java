import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Obstacle {

	int x,y;
	Rectangle hitBox;
	Rectangle hitBox2;
	Rectangle hitBox3;
	
	Image imgObstacle;
	Image imgObstacle2;

	public Obstacle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	
		try {
			this.imgObstacle=Toolkit.getDefaultToolkit().getImage("images/obstacle1.png");
			this.imgObstacle2=Toolkit.getDefaultToolkit().getImage("images/obstacle2.png");
		}
		catch (Exception ex) {
			System.out.println("ERROR:"+ex);
		}
		
		hitBox= new Rectangle(x+85,y+45,95,250);
		hitBox2=new Rectangle(x+37,y+100,60,200);
		hitBox3=new Rectangle(x,y+200,120,90);
	
	}
	
	public void dessiner(Graphics g) {

		g.drawImage(imgObstacle, x, y, imgObstacle.getWidth(null), imgObstacle.getHeight(null), null);

	}
	
	public void dessiner2(Graphics g) {
		g.drawImage(imgObstacle2, x, y, imgObstacle2.getWidth(null), imgObstacle2.getHeight(null), null);
	}
}