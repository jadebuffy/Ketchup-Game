import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ballistick extends JFrame implements KeyListener, Runnable {

	AireDeJeu a1;
	
	int W=1054;
	int H=679;
	private Image doubleBuffer;
	private Image win;
	private Image perdu;
	private Graphics gBuffer;
	boolean go;
	int compteur=3; //on initialise le compteur à 3 (compte à rebours);
	int Lvl=1; //on initialise le level à 1 afin que l'aire de jeu 1 se lance;
	
	Thread processusJeu; //lancement du thread, évite les clignotants

	//importation des fonts
	Font font = new Font("TimesRoman", Font.BOLD, 30);
	Font font1 = new Font("TimesRoman", Font.BOLD, 20);
	
	
	
	public Ballistick() {
		
		
		super("Ballistick"); //on crée un objet fenêtre
		this.setSize(1054,679);//on lui donne une taille
		this.setTitle("Hello");
		this.add(new JLabel("yop"));//on ajoute dessus un nouvel objet Label : c'est une manière d'afficher du texte dans une fenêtre
		this.setLocation(10,10);//on lui donne une position
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//on affecte au bouton fermeture la fermeture du programme java complet
		
		
		this.addKeyListener(this);

		a1=new AireDeJeu(10,10,W,H);
		
		processusJeu= new Thread(this);
		processusJeu.start();
		
		this.setVisible(true);//la fenêtre est prête on l'affiche
		
		//outils pour le double buffer
		doubleBuffer = createImage(getSize().width,getSize().height);
		gBuffer =doubleBuffer.getGraphics();
		
		//importation des images 
		try {
			this.win=Toolkit.getDefaultToolkit().getImage("images/win.png");
			this.perdu=Toolkit.getDefaultToolkit().getImage("images/perdu.png");
		}
		catch (Exception ex) {
			System.out.println("ERROR:"+ex);
		}
		
		
		
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		//nécessaire au système des levels
		if (gBuffer != null && Lvl==1) {
			a1.dessiner(gBuffer);
			gBuffer.setColor(new Color(241,147,30));
			gBuffer.setFont(font1);
			gBuffer.drawString("Angle "+a1.monLanceur.angle, 30, 70);
			gBuffer.drawString("Puissance "+a1.monProjectile.vitesse, 30, 95);
			gBuffer.drawString("Vies "+compteur, 30, 120);
			gBuffer.drawString("Level "+Lvl, 930, 100);
			
		}else if (Lvl>1) {
			a1.dessiner2(gBuffer);
			gBuffer.setColor(new Color(241,147,30));
			gBuffer.setFont(font1);
			gBuffer.drawString("Angle "+a1.monLanceur.angle, 30, 70);
			gBuffer.drawString("Puissance "+a1.monProjectile.vitesse, 30, 95);
			gBuffer.drawString("Vies "+compteur, 30, 120);
			gBuffer.drawString("Level "+Lvl, 930, 100);
		}
		
		
		//initialisation des hitboxs
		Rectangle rect1 = a1.monProjectile.hitBox;
		Rectangle rect2 = a1.maCible.hitBox;
		
		//Lors de l'intersection on affiche l'image et le texte
		if(rect1.intersects(rect2))
		{
			go=false;
			gBuffer.drawImage(win,0,0, 1054, 679, null);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(font);
			gBuffer.drawString("Votre score est de "+Lvl, 360,450);
			gBuffer.drawString("[R] Restart        [S] Suivant", 350,500);
			gBuffer.drawString("[Q] Quitter", 450, 550);	
			a1.monProjectile.visible=false;
		}
		
		
		
		//Si le compteur est à 0 alors le joueur a perdu
		if(compteur==0) {
			go=false;
			gBuffer.drawImage(perdu,0,0, 1054, 679, null);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(font);
			gBuffer.drawString("[R] Restart", 350,500);
			gBuffer.drawString("[Q] Quitter", 550, 500);
		}
		
		//affichage du jeu
		g.drawImage(doubleBuffer, 0,0, this);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ballistick monjeu=new Ballistick();
		
		
	}
	
	
	
	//KeyListener

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()== e.VK_UP) {
			
			System.out.println("haut");
			
			a1.monLanceur.vise(-5);
		}
		
		if(e.getKeyCode()== e.VK_DOWN) {
			
			System.out.println("bas");
			
			a1.monLanceur.vise(5);
		}
		
		if(e.getKeyCode()== e.VK_RIGHT) {
			
			System.out.println("droite");
			
			a1.monLanceur.deplacer(5); //+= veut dire soi-meme plus la valeur
		}
		
		if(e.getKeyCode()== e.VK_LEFT) {
			
			System.out.println("gauche");
			
			a1.monLanceur.deplacer(-5);
		}
		
		if(e.getKeyCode()== e.VK_SPACE) {
			this.shoot();
			go=true;
			a1.monProjectile.visible=true;
		}
		
		if(e.getKeyCode()== e.VK_R) {
			a1=new AireDeJeu(10,10,W,H);
			this.compteur=3;	
		}
		
		//Si la touche S est touchée alors le level gagne 1 et on relance le shoot
		if(e.getKeyCode()== e.VK_S) {
			this.Lvl++;
			this.shoot();
			a1.monLanceur.angle=10;
			a1.monProjectile.vitesse=4;

		}
		
		if(e.getKeyCode()== e.VK_Q) {
			this.dispose();//fermeture de Jframe
			
		}
		
		//Début du système d'augmentation de vitesse, ne marche que quand le projectile bouge car quand on reshoot 
		if(e.getKeyCode()== e.VK_P) {
			a1.monProjectile.aug(1);
		}
		
		if(e.getKeyCode()== e.VK_M) {
			a1.monProjectile.aug(-1);
		}
		
		
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			
			try {
				
				//vérification s'il y a une collion 
				this.collisions();
				//this.perdu();
				Rectangle rect1 = a1.monProjectile.hitBox;
				Rectangle rect2 = a1.maCible.hitBox;
				//bouge le projectile s'il est vivant
				if (go==true){ 
					a1.monProjectile.bouge();
				}else if(rect1.intersects(rect2)){
					
				}else {
					this.shoot();
				}
				
				this.repaint();
				processusJeu.sleep(20);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void collisions() {
		if(a1.monProjectile.y>a1.hauteur-145 || a1.monProjectile.y<0 || a1.monProjectile.x>a1.largeur|| a1.monProjectile.x<0 || a1.monProjectile.hitBox.intersects(a1.monObstacle.hitBox)||a1.monProjectile.hitBox.intersects(a1.monObstacle.hitBox2)||a1.monProjectile.hitBox.intersects(a1.monObstacle.hitBox3)) {
			
			//a1.monProjectile.dy=a1.monProjectile.dy;
			go=false;
			this.shoot();
			compteur--;
		}

	}
	


	
	public void shoot() {
		
		a1.monProjectile=new Projectile((int)a1.monLanceur.Xcanon,(int)a1.monLanceur.Ycanon,(int)a1.monLanceur.angle,(int)a1.monProjectile.vitesse);
		
	}
	
	

}
