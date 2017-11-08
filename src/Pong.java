import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import br.senai.sc.engine.Game;
import br.senai.sc.engine.Utils;


public class Pong extends Game {

	private Barra barraEsquerda;
	private Barra barraDireita;
	private Bola bola;
	private Jogador jogador1;
	private Jogador jogador2;
	
	public Pong() {
		addMouseListener(new MouseInputHandler());
		addKeyListener(new KeyInputHandler());
	}
	
	public static void main(String[] args) {
		Pong pong = new Pong();
		pong.startGame();
	}
	
	@Override
	public void init() {
		barraEsquerda = new Barra(20, Utils.getInstance().getHeight()/2-50, 20, 100, 5);
		barraDireita = new Barra(Utils.getInstance().getWidth()-40, Utils.getInstance().getHeight()/2-50, 20, 100, 5);
		bola = new Bola(5, 5);
		jogador1 = new Jogador("Jogador1");
		jogador2 = new Jogador("Jogador2");
	}
	
	@Override
	public void gameLoop() {
		desenharRetangulo(0, 0, 
			Utils.getInstance().getWidth(), 
			Utils.getInstance().getHeight(), 
			Color.BLACK);
		
		barraEsquerda.draw(getGraphics2D());
		barraDireita.draw(getGraphics2D());
		barraEsquerda.update();
		barraDireita.update();
		bola.draw(getGraphics2D());
		bola.update();
		int pontuou = bola.colisao();
		if (pontuou == 1) {
			jogador1.addPonto();
		} else if (pontuou == 2) {
			jogador2.addPonto();
		}
		bola.colisaoFacil(barraDireita);
		bola.colisaoFacil(barraEsquerda);
		
		desenharString(String.valueOf(jogador1.getPontuacao()), 
				(Utils.getInstance().getWidth()/2) - 30, 30, Color.GREEN, 20);
		desenharString(String.valueOf(jogador2.getPontuacao()), (Utils.getInstance().getWidth()/2)+30, 30, Color.GREEN, 20);
		
	}
	
	@Override
	public void aposTermino() {
		// TODO Auto-generated method stub
		
	}
	
	private class MouseInputHandler extends 
		MouseAdapter {
		
	}
	
	private class KeyInputHandler extends 
		KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				barraEsquerda.setUpPressed(true);
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				barraEsquerda.setDownPressed(true);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				barraDireita.setUpPressed(true);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				barraDireita.setDownPressed(true);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				barraEsquerda.setUpPressed(false);
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				barraEsquerda.setDownPressed(false);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				barraDireita.setUpPressed(false);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				barraDireita.setDownPressed(false);
			}
		}
		
	}

}
