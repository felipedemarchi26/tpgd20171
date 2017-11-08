import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import br.senai.sc.engine.Utils;

public class Bola {

	private int posX;
	private int posY;
	private int velX;
	private int velY;
	private int width;
	private int height;

	public Bola(int velX, int velY) {
		this.velX = velX;
		this.velY = velY;
		this.width = 20;
		this.height = 20;
		reset();
	}
	
	public void reset() {
		this.posX = Utils.getInstance().getWidth() / 2 - this.width / 2;
		this.posY = Utils.getInstance().getHeight() / 2 - this.height / 2;
		Random random = new Random();
		boolean sort = random.nextBoolean();
		if (sort) {
			this.velX *= -1;
		}
		sort = random.nextBoolean();
		if (sort) {
			this.velY *= -1;
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillOval(posX, posY, width, height);
	}
	
	public void update() {
		setPosY(posY + velY);
		setPosX(posX + velX);
	}
	
	//Responsável por realizar a colisão
	//com os limites da tela
	public int colisao() {
		/*if (posX <= 0 || posX + width >= 
			Utils.getInstance().getWidth()) {
			velX *= -1;
		}*/
		if (posY <= 0 || posY + height >= 
				Utils.getInstance().getHeight()) {
			velY *= -1;
		}
		
		if (posX <= 0) {
			reset();
			return 2;
		} else if (posX >= Utils.getInstance().getWidth()) {
			reset();
			return 1;
		}
		return 0;
	}
	
	public void colisao(Barra barra) {
		if (barra.getPosX() < 
			Utils.getInstance().getWidth()/2) {
			//Barra da esquerda
			
			if (velX < 0 && posX <= barra.getPosX() + 
				barra.getWidth() && posY + height >= 
				barra.getPosY() && posY <= 
				barra.getPosY()+barra.getHeight()) {
				
				velX *= -1;
				
			}
			
		} else {
			//Barra da direita
			
			if (velX > 0 && posX + width >= 
				barra.getPosX() && posY + height >= 
				barra.getPosY() && posY <= 
				barra.getPosY()+barra.getHeight()) {
				
				velX *= -1;
				
			}
			
		}
	}
	
	public void colisaoFacil(Barra barra) {
		Rectangle barraRetangulo = new Rectangle(barra.getPosX(), barra.getPosY(), barra.getWidth(), barra.getHeight());
		Rectangle bolaRetangulo = new Rectangle(posX, posY, width, height);
		if (barraRetangulo.intersects(bolaRetangulo)) {
			velX *= -1;
		}
	}

}
