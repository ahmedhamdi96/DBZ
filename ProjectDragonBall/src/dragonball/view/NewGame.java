package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewGame extends JPanel{
	private JTextField playerName;
	private JTextField fighterName;
	private JComboBox<String> race;
	private JButton startGameBtn;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  NewGame(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		this.setBounds(0, 0, width, height);
		this.setName("New Game");
		setLayout(null);
		
		JLabel playerNameLabel = new JLabel("Player's Name: "); 
		playerName = new JTextField();
		JLabel fighterNameLabel = new JLabel("Fighter's Name: ");
		fighterName = new JTextField();
		startGameBtn = new JButton("Start Game!");
		
		String[] races = { "Earthling", "Frieza", "Majin", "Namekian", "Saiyan" };
		JLabel raceLabel = new JLabel("Fighter's Race: ");
		race = new JComboBox(races);
		race.setSelectedIndex(4);
		
		playerNameLabel.setBounds(25,450,350, 50);
		playerName.setBounds(350,450,350, 50);
		
		playerNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
		playerName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
		
		fighterNameLabel.setBounds(25,550,350, 50);
		fighterName.setBounds(350,550,350, 50);
		
		fighterNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
		fighterName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
		
		
		raceLabel.setBounds(25,650,350, 50);
		race.setBounds(350,650,350, 50);
		
		raceLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
		race.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
		//getContentPane().setIcon(new ImageIcon("StartGame.jpg"));
		
		
		startGameBtn.setBounds(200,800,350, 50);
		startGameBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		
		JLabel background = new JLabel();
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		background.setIcon(new ImageIcon("NewGame1.jpg"));
		//background.setVisible(true);
		
		startGameBtn.setOpaque(false);
		startGameBtn.setContentAreaFilled(false);
		
		
		playerName.setForeground(Color.WHITE);
		playerNameLabel.setForeground(Color.WHITE);
		fighterName.setForeground(Color.WHITE);
		fighterNameLabel.setForeground(Color.WHITE);
		race.setForeground(Color.BLACK);
		raceLabel.setForeground(Color.WHITE);
		startGameBtn.setForeground(Color.WHITE);
		
		
		playerName.setOpaque(false);
		playerNameLabel.setOpaque(false);
		fighterName.setOpaque(false);
		fighterNameLabel.setOpaque(false);
		//race.setOpaque(false);
		raceLabel.setOpaque(false);
		startGameBtn.setOpaque(false);
		
		
		startGameBtn.setBorderPainted(false);
		
		this.add(playerName);
		this.add(playerNameLabel);
		this.add(fighterName);
		this.add(fighterNameLabel);
		this.add(race);
		this.add(raceLabel);
		this.add(startGameBtn);
		this.add(background);
		
		//this.setVisible(true);
		validate();
	}

	public JTextField getPlayerName() {
		return playerName;
	}

	public void setPlayerName(JTextField playerName) {
		this.playerName = playerName;
	}

	public JComboBox<String> getRace() {
		return race;
	}

	public void setRace(JComboBox<String> race) {
		this.race = race;
	}

	public JTextField getFighterName() {
		return fighterName;
	}

	public void setFighterName(JTextField fighterName) {
		this.fighterName = fighterName;
	}

	public JButton getStartGameBtn() {
		return startGameBtn;
	}

	public void setStartGameBtn(JButton startGame) {
		this.startGameBtn = startGame;
	}
	
	
}
