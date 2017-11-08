import java.awt.Color;
import java.awt.Graphics2D;

import br.senai.sc.engine.Utils;


public class Barra {

	private int posX;
	private int posY;
	private int width;
	private int height;
	private int velY;
	private boolean upPressed;
	private boolean downPressed;
	
	public Barra(int posX, int posY, int width, int height, int velY) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.velY = velY;
		//Comentario para teste
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		if (posX >= 0 && 
				posX <= Utils.getInstance().getWidth()-width) {
			this.posX = posX;
		}
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		if (posY < 0) {
			this.posY = 0;
		} else if (posY+height > Utils.getInstance().getHeight()) {
			this.posY = Utils.getInstance().getHeight()-height;
		} else {
			this.posY = posY;
		}
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

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(posX, posY, width, height);
	}
	
	public void update() {
		//Implementação da movimentação da barra
		if (downPressed) {
			setPosY(posY + velY);
		} else if (upPressed) {
			setPosY(posY - velY);
		}
	}
	
}
