import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class AireDeJeu {
	
	
	int x,y,largeur,hauteur;
	Cible maCible;
	Lanceur monLanceur;
	Projectile monProjectile;
	Obstacle monObstacle;
	Obstacle monObstacle2;

	Image imgFond;
	


	public AireDeJeu(int x, int y, int largeur, int hauteur) {
		super();
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		maCible=new Cible(750,430,50);
		monLanceur=new Lanceur(100,350,10);
		monProjectile=new Projectile(monLanceur.Xcanon,monLanceur.Ycanon,(int)monLanceur.angle,5);
		monObstacle=new Obstacle(400,203);
		monObstacle2=new Obstacle(400,240);
		
		try {
			this.imgFond=Toolkit.getDefaultToolkit().getImage("images/fond.jpg");
		}
		catch (Exception ex) {
			System.out.println("ERROR:"+ex);
		}
	}

	
	public void dessiner(Graphics g) {
		
		g.drawImage(imgFond, x, y, largeur, hauteur, null);
	
		
		maCible.dessiner(g);
		monLanceur.dessiner(g);
		monProjectile.dessiner(g);
		monObstacle.dessiner(g);
		
	}
	public void dessiner2(Graphics g) {
		
		g.drawImage(imgFond, x, y, largeur, hauteur, null);
	
		
		maCible.dessiner(g);
		monLanceur.dessiner(g);
		monProjectile.dessiner(g);
		monObstacle2.dessiner2(g);
		
	}
	
}
