import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Cible {

	int X,Y,R;//X, Y centre du cercle, Rayon
	Rectangle hitBox;
	
	int pas=20;
	
	Image imgCible;
	
	
//constructeur
	public Cible(int x, int y, int r) {
		super();
		X= x;
		Y= y;
		R =r;

		
		try {
			this.imgCible=Toolkit.getDefaultToolkit().getImage("images/cible.png");
		}
		catch (Exception ex) {
			System.out.println("ERROR:"+ex);
		}
		
		hitBox= new Rectangle(x+47,y+60,150,35);
	}
	
	public void dessiner(Graphics g) {
	
		g.drawImage(imgCible, X, Y, 284, 113, null);
//		g.setColor(Color.cyan);
//		g.drawRect(hitBox.x,hitBox.y,150,35);

	}


	
	
	
}
