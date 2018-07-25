package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class WorldView extends JPanel{
	private JPanel mapPnl;
	private JPanel infoPnl;
	
	private JTextArea playerInfoPnl;
	private JPanel playerPnl;
	private JPanel directionsPnl;
	
	private JComboBox<String> fighters;
	private JButton chooseFighter;
	private JButton newFighter;
	private JButton assignAttack;
	private JButton saveGame;
	private JButton upgradeFighter;
	private JButton endGame;
	
	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right;
	
	
	public WorldView(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		this.setBounds(0, 0, width, (int)(height*0.975));
		this.setName("World");
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		
		mapPnl = new JPanel();
		infoPnl = new JPanel();
		
		playerInfoPnl = new JTextArea();
		playerPnl = new JPanel();
		directionsPnl = new JPanel();
		
		fighters = new JComboBox<String>();
	    assignAttack = new JButton("Assign Attack");
		newFighter = new JButton("New Fighter");
		chooseFighter = new JButton("Choose Fighter");
		saveGame = new JButton("Save Game");
		upgradeFighter = new JButton("Upgrade Fighter");
		
		up = new JButton("Up");
		down = new JButton("Down");
		left = new JButton("Left");
		right = new JButton("Right");
		endGame = new JButton("End Game");
		
		mapPnl.setLayout(new GridLayout(10,10));
		infoPnl.setLayout(new BorderLayout());
		playerPnl.setLayout(null);
		directionsPnl.setLayout(null);
		
		mapPnl.setPreferredSize(new Dimension((int)(width*0.75), (int)(height*0.98)));
		
		infoPnl.setPreferredSize(new Dimension((int)(width*0.25), height));
		infoPnl.setBackground(Color.BLACK);
		
		playerInfoPnl.setPreferredSize(new Dimension((int)(width*0.25), (int)(height*0.40)));
		playerPnl.setPreferredSize(new Dimension((int)(width*0.25), (int)(height*0.40)));
		directionsPnl.setPreferredSize(new Dimension((int)(width*0.25), (int)(height*0.20)));
		
		playerInfoPnl.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		playerInfoPnl.setForeground(Color.WHITE);
		
		playerInfoPnl.setBackground(Color.BLACK);
		playerPnl.setBackground(Color.BLACK);
		directionsPnl.setBackground(Color.BLACK);
		
		
		infoPnl.add(playerInfoPnl, BorderLayout.NORTH);
		infoPnl.add(directionsPnl, BorderLayout.SOUTH);
		infoPnl.add(playerPnl, BorderLayout.CENTER);
		
		up.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		down.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		right.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		left.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		assignAttack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		newFighter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		chooseFighter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		fighters.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		saveGame.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		upgradeFighter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		endGame.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		
		up.setBackground(Color.GRAY);
		up.setBackground(Color.GRAY);
		down.setBackground(Color.GRAY);
		right.setBackground(Color.GRAY);
		left.setBackground(Color.GRAY);
		assignAttack.setBackground(Color.GRAY);
		newFighter.setBackground(Color.GRAY);
		fighters.setBackground(Color.GRAY);
		chooseFighter.setBackground(Color.GRAY);
		saveGame.setBackground(Color.GRAY);
		upgradeFighter.setBackground(Color.GRAY);
		endGame.setBackground(Color.GRAY);
		
		newFighter.setBounds(0,0,240,50);
		assignAttack.setBounds(240,0,240,50);
		saveGame.setBounds(0,50,240,50);
		upgradeFighter.setBounds(240,50,240,50);
		fighters.setBounds(0,100,240,50);
		chooseFighter.setBounds(240,100,240,50);
		endGame.setBounds(100,250,300,70);
		
		left.setBounds(0,5,160,100);
		up.setBounds(160,5,160,50);
		down.setBounds(160,55,160,50);
		right.setBounds(320,5,160,100);
		
		playerPnl.add(fighters);
		playerPnl.add(chooseFighter);
		playerPnl.add(assignAttack);
		playerPnl.add(newFighter);
		playerPnl.add(saveGame);
		playerPnl.add(upgradeFighter);
		playerPnl.add(endGame);
		
		
		directionsPnl.add(up);
		directionsPnl.add(left);
		directionsPnl.add(down);
		directionsPnl.add(right);
		
		directionsPnl.setVisible(true);
		playerInfoPnl.setEditable(false);

		
		
		this.add(infoPnl, BorderLayout.EAST);
		this.add(mapPnl, BorderLayout.CENTER);
		this.validate();
	}


	public JPanel getMapPnl() {
		return mapPnl;
	}


	public void setMapPnl(JPanel mapPnl) {
		this.mapPnl = mapPnl;
	}


	public JPanel getInfoPnl() {
		return infoPnl;
	}


	public void setInfoPnl(JPanel infoPnl) {
		this.infoPnl = infoPnl;
	}


	public JTextArea getPlayerInfoPnl() {
		return playerInfoPnl;
	}


	public void setPlayerInfoPnl(JTextArea playerInfoPnl) {
		this.playerInfoPnl = playerInfoPnl;
	}


	public JPanel getPlayerPnl() {
		return playerPnl;
	}


	public void setPlayerPnl(JPanel playerPnl) {
		this.playerPnl = playerPnl;
	}


	public JPanel getDirectionsPnl() {
		return directionsPnl;
	}


	public void setDirectionsPnl(JPanel directionsPnl) {
		this.directionsPnl = directionsPnl;
	}


	public JComboBox<String> getFighters() {
		return fighters;
	}


	public void setFighters(JComboBox<String> fighters) {
		this.fighters = fighters;
	}


	public JButton getAssignAttack() {
		return assignAttack;
	}


	public void setAssignAttack(JButton assignAttack) {
		this.assignAttack = assignAttack;
	}


	public JButton getNewFighter() {
		return newFighter;
	}


	public void setNewFighter(JButton newFighter) {
		this.newFighter = newFighter;
	}


	public JButton getUp() {
		return up;
	}


	public void setUp(JButton up) {
		this.up = up;
	}


	public JButton getDown() {
		return down;
	}


	public void setDown(JButton down) {
		this.down = down;
	}


	public JButton getLeft() {
		return left;
	}


	public void setLeft(JButton left) {
		this.left = left;
	}


	public JButton getRight() {
		return right;
	}


	public void setRight(JButton right) {
		this.right = right;
	}


	public JButton getChooseFighter() {
		return chooseFighter;
	}
	

	public void setChooseFighter(JButton chooseFighter) {
		this.chooseFighter = chooseFighter;
	}


	public JButton getSaveGame() {
		return saveGame;
	}


	public void setSaveGame(JButton saveGame) {
		this.saveGame = saveGame;
	}


	public JButton getUpgradeFighter() {
		return upgradeFighter;
	}


	public void setUpgradeFighter(JButton upgradeFighter) {
		this.upgradeFighter = upgradeFighter;
	}


	public JButton getEndGame() {
		return endGame;
	}


	public void setEndGame(JButton endGame) {
		this.endGame = endGame;
	}
	
	
}
