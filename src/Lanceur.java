import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Lanceur {

	int x, y;
	int angle; //chiffre à virgule
	int limiteDeplacement=120;
	Image imgLanceur;
	
	int Xcanon,Ycanon;
	
	
	public Lanceur(int x, int y, int angle) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		
		
		this.Xcanon=(int) (x+29+198*Math.cos(Math.toRadians(this.angle-90)));
		this.Ycanon=(int) (y+197+198*Math.sin(Math.toRadians(this.angle-90)));
		
		//x2 =x+W+this.canon*Math.cos(Math.toRadians(this.angle));
		//y2 =y-this.canon*Math.sin(Math.toRadians(this.angle));
		
		try {
			this.imgLanceur=Toolkit.getDefaultToolkit().getImage("images/lanceur.png");
		}
		catch (Exception ex) {
			System.out.println("ERROR:"+ex);
		}
	}
	
	
	public void deplacer(int val){
		//this.x+=valeur;
		
		int tmp=x+val;
		
		if(tmp>10 && tmp<limiteDeplacement) {
			this.x=tmp;
		}
	}
	
	public void vise(int val) {
		//if (this.angle>0 && this.angle);
		
		this.angle+=val;
		
		if(this.angle<0)
			this.angle=0;
		
		if(this.angle>80)
			this.angle=79;
	}
	
	
	//void= ne renvoie rien 
	public void dessiner(Graphics g){
		
		this.Xcanon=(int) (x+29+198*Math.cos(Math.toRadians(this.angle-90)));
		this.Ycanon=(int) (y+197+198*Math.sin(Math.toRadians(this.angle-90)));
		
		
		BufferedImage buffImg= toBufferedImage(imgLanceur);
		BufferedImage tmp=rotate2(buffImg, angle);
		g.drawImage(tmp, x, y, tmp.getWidth(), tmp.getHeight(), null);
		
//		g.setColor(Color.blue);
//		g.fillOval(this.Xcanon-2, this.Ycanon-2, 4,4);
//		g.drawLine(this.x+68, this.y+197, this.Xcanon, this.Ycanon);
		
	}
	
	//Fonction qui permet de transformer un type Image en type BufferedImage
	BufferedImage toBufferedImage(Image image)
	{
		if( image instanceof BufferedImage ) {
			return( (BufferedImage)image );
			}
		else {
			image = new ImageIcon(image).getImage();
			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
			
			Graphics g = bufferedImage.createGraphics();
			g.drawImage(image,0,0,null);
			g.dispose();
			return(bufferedImage);
		}
	}
	
	//Fonction qui prend un image et la tourne selon un angle en degré
	public BufferedImage rotate2(BufferedImage img, int angle)
	{
		int w = img.getWidth();
		int h = img.getHeight();
		
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle),w,h);
		
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		img = op.filter(img, null);
		
		return img;
	}
}
