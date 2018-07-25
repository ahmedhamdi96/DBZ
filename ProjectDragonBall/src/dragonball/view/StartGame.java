package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartGame extends JPanel{
	private JButton loadGame;
	private JButton newGame;
	
	public StartGame(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		this.setBounds(0, 0, width, height);
		this.setName("Dragonball");
		this.setLayout(new BorderLayout());
		
		loadGame = new JButton("Load Game");
		newGame = new JButton("New Game");
		JLabel backGround = new JLabel();
		
		loadGame.setBounds((int)(this.getWidth()*0.10),(int)(this.getHeight()*0.60),300, 100);
		loadGame.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		
		newGame.setBounds((int)(this.getWidth()*0.10),(int)(this.getHeight()*0.75),300, 100);
		newGame.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		
		backGround.setBounds(0, 0, this.getWidth(), this.getHeight());
		backGround.setIcon(new ImageIcon("StartGame2.jpg"));
		
		loadGame.setForeground(Color.WHITE);
		loadGame.setOpaque(false);
		loadGame.setContentAreaFilled(false);
		loadGame.setBorderPainted(false);
		
		newGame.setForeground(Color.WHITE);
		newGame.setOpaque(false);
		newGame.setContentAreaFilled(false);
		newGame.setBorderPainted(false);
		
		add(loadGame);
		add(newGame);
		add(backGround);
		
		validate();
	}

	public JButton getLoadGame() {
		return loadGame;
	}

	public void setLoadGame(JButton loadGame) {
		this.loadGame = loadGame;
	}

	public JButton getNewGame() {
		return newGame;
	}

	public void setNewGame(JButton newGame) {
		this.newGame = newGame;
	}
}
