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
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DragonView extends JPanel{
	private JTextArea playerInfo;
	private JButton senzuBeans;
	private JButton abilityPoints;
	private JButton ultimateAttack;
	private JButton superAttack;
	private JButton back;
	private JComboBox<String> superAttacks;
	private JComboBox<String> ultimateAttacks;
	
	
	
	
	public DragonView(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		this.setBounds(0, 0, width, height);
		this.setName("Dragon");
		setLayout(null);
		
		
		playerInfo =new JTextArea();
		senzuBeans = new JButton("Senzu beans");
		abilityPoints = new JButton("Ability points");
		ultimateAttack = new JButton("Ultimate attack");
		superAttack = new JButton("Super attack");
		back = new JButton("Back");
		JLabel dargonName = new JLabel("");
		JLabel backGround = new JLabel();
		JLabel attributes = new JLabel("Choose a wish!");
		JLabel superAttacksLbl = new JLabel("Super Attacks");
		JLabel ultimateAttacksLbl = new JLabel("Ultimate Attacks");
		superAttacks = new JComboBox<String>();
		ultimateAttacks = new JComboBox<String>();

		

		
		playerInfo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		senzuBeans .setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		abilityPoints.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		ultimateAttack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		superAttack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		back.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		dargonName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		attributes.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		superAttacksLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		ultimateAttacksLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		superAttacks.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		ultimateAttacks.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		
		
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		senzuBeans.setOpaque(false);
		senzuBeans.setContentAreaFilled(false);
		senzuBeans.setBorderPainted(false);
		abilityPoints.setOpaque(false);
		abilityPoints.setContentAreaFilled(false);
		abilityPoints.setBorderPainted(false);
		ultimateAttack.setOpaque(false);
		ultimateAttack.setContentAreaFilled(false);
		ultimateAttack.setBorderPainted(false);
		superAttack.setOpaque(false);
		superAttack.setContentAreaFilled(false);
		superAttack.setBorderPainted(false);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		dargonName.setOpaque(false);
		attributes.setOpaque(false);
//		superAttacksLbl.setOpaque(false);
//		ultimateAttacksLbl.setOpaque(false);
//		superAttacks.setOpaque(false);
//		ultimateAttacks.setOpaque(false);
		
		
		playerInfo.setForeground(Color.WHITE);
		senzuBeans .setForeground(Color.WHITE);
		abilityPoints.setForeground(Color.WHITE);
		ultimateAttack.setForeground(Color.WHITE);
		superAttack.setForeground(Color.WHITE);
		back.setForeground(Color.WHITE);
		dargonName.setForeground(Color.WHITE);
		attributes.setForeground(Color.WHITE);
		superAttacksLbl.setForeground(Color.WHITE);
		ultimateAttacksLbl.setForeground(Color.WHITE);
		superAttacks.setForeground(Color.WHITE);
		ultimateAttacks.setForeground(Color.WHITE);
		
		

		playerInfo.setBounds(50,50,600,300);
		senzuBeans.setBounds(1320,200,600,100);
		abilityPoints.setBounds(1320,300,600,100);
		ultimateAttack.setBounds(1320,400,600,100);
		superAttack.setBounds(1320,500,600,100);
		back.setBounds(1610,900,240,50);
		//dargonName.setBounds(50,50,600,100);
		attributes.setBounds(1450,50,600,100);
		
		ultimateAttacksLbl.setBounds(50,400,400,50);
		superAttacksLbl.setBounds(50,550,400,50);
		ultimateAttacks.setBounds(50,450,400,50);
		superAttacks.setBounds(50,600,400,50);
		
		
		backGround.setBounds(0, 0, this.getWidth(), this.getHeight());
		backGround.setIcon(new ImageIcon("Shenron.jpg"));
		
		
		this.add(back);
		this.add(senzuBeans);
		this.add(abilityPoints);
		this.add(ultimateAttack);
		this.add(superAttack);
		//this.add(dargonName);
		this.add(playerInfo);
		this.add(attributes);
		this.add(superAttacksLbl);
		this.add(ultimateAttacksLbl);
		this.add(superAttacks);
		this.add(ultimateAttacks);
		this.add(backGround);
	
		
		
		validate();
	}

	public JTextArea getPlayerInfo() {
		return playerInfo;
	}

	public void setPlayerInfo(JTextArea playerInfo) {
		this.playerInfo = playerInfo;
	}

	public JButton getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(JButton senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public JButton getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(JButton abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public JButton getUltimateAttack() {
		return ultimateAttack;
	}

	public void setUltimateAttack(JButton ultimateAttack) {
		this.ultimateAttack = ultimateAttack;
	}

	public JButton getSuperAttack() {
		return superAttack;
	}

	public void setSuperAttack(JButton superAttack) {
		this.superAttack = superAttack;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public JComboBox<String> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(JComboBox<String> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public JComboBox<String> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(JComboBox<String> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}	
}
