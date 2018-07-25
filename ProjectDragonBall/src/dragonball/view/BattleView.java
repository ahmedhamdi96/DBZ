package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class BattleView extends JPanel{
	private JProgressBar meHealthBar;
	private JProgressBar meStaminaBar;
	private JProgressBar meKiBar;
	private JProgressBar foeHealthBar;
	private JProgressBar foeStaminaBar;
	private JProgressBar foeKiBar;
	private JTextArea meInfo;
	private JTextArea foeInfo;
	private JButton physicalAttack;
	private JButton use;
	private JButton block;
	private JButton chooseSuperAttack;
	private JButton chooseUltimateAttack; 
	private JComboBox<String> superAttacks;
	private JComboBox<String> ultimateAttacks;

	public BattleView(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		this.setBounds(0, 0, width, height);
		this.setName("Battle");
		setLayout(null);
		
		JLabel backGround = new JLabel();
		backGround.setBounds(0, 0, this.getWidth(), this.getHeight());
		backGround.setIcon(new ImageIcon("Battle.png"));
		backGround.setBorder(BorderFactory.createEmptyBorder());
		
		meHealthBar = new JProgressBar();
		meStaminaBar = new JProgressBar();
		meKiBar = new JProgressBar();
		foeHealthBar = new JProgressBar();
		foeStaminaBar = new JProgressBar();
		foeKiBar = new JProgressBar();
		meInfo = new JTextArea();
		foeInfo = new JTextArea();
		physicalAttack = new JButton("Physical Attack");
		use = new JButton("Use");
		block = new JButton("Block");
		chooseSuperAttack = new JButton("Use Super Attack");
		chooseUltimateAttack = new JButton("Use Ultimate Attack");
		superAttacks = new JComboBox<String>();
		ultimateAttacks = new JComboBox<String>();
		JLabel meHealthLbl = new JLabel("Health");
		JLabel meStaminaLbl = new JLabel("Stamina");
		JLabel meKiLbl = new JLabel("Ki");
		JLabel foeHealthLbl = new JLabel("Health");
		JLabel foeStaminaLbl = new JLabel("Stamina");
		JLabel foeKiLbl = new JLabel("Ki");
		JLabel meImage = new JLabel();
		JLabel foeImage = new JLabel();
		
		meInfo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		foeInfo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		physicalAttack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		use.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		block.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		chooseSuperAttack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		chooseUltimateAttack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
		superAttacks.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		ultimateAttacks.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		meHealthLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		meStaminaLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		meKiLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		foeHealthLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		foeStaminaLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		foeKiLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

		meInfo.setForeground(Color.WHITE);
		foeInfo.setForeground(Color.WHITE);
		physicalAttack.setForeground(Color.WHITE);
		use.setForeground(Color.WHITE);
		block.setForeground(Color.WHITE);
		chooseSuperAttack.setForeground(Color.WHITE);
		chooseUltimateAttack.setForeground(Color.WHITE);
		superAttacks.setForeground(Color.BLACK);
		ultimateAttacks.setForeground(Color.BLACK);
		meHealthLbl.setForeground(Color.WHITE);
		meStaminaLbl.setForeground(Color.WHITE);
		meKiLbl.setForeground(Color.WHITE);
		foeHealthLbl.setForeground(Color.WHITE);
		foeStaminaLbl.setForeground(Color.WHITE);
		foeKiLbl.setForeground(Color.WHITE);
		meImage.setForeground(Color.WHITE);
		foeImage.setForeground(Color.WHITE);

		meHealthBar.setOpaque(false);
		meStaminaBar.setOpaque(false);
		meKiBar.setOpaque(false);
		foeHealthBar.setOpaque(false);
		foeStaminaBar.setOpaque(false);
		foeKiBar.setOpaque(false);
		meInfo.setOpaque(false);
		foeInfo.setOpaque(false);
		physicalAttack.setOpaque(false);
		use.setOpaque(false);
		block.setOpaque(false);
		chooseSuperAttack.setOpaque(false);
		chooseUltimateAttack.setOpaque(false);
		superAttacks.setOpaque(true);
		ultimateAttacks.setOpaque(true);
		meHealthLbl.setOpaque(false);
		meStaminaLbl.setOpaque(false);
		meKiLbl.setOpaque(false);
		foeHealthLbl.setOpaque(false);
		foeStaminaLbl.setOpaque(false);
		foeKiLbl.setOpaque(false);
		meImage.setOpaque(false);
		foeImage.setOpaque(false);
		
		chooseSuperAttack.setContentAreaFilled(false);
		chooseUltimateAttack.setContentAreaFilled(false);
		physicalAttack.setContentAreaFilled(false);
		block.setContentAreaFilled(false);
		use.setContentAreaFilled(false);
		
		meHealthBar.setBorderPainted(true);
		meStaminaBar.setBorderPainted(true);
		meKiBar.setBorderPainted(true);
		foeHealthBar.setBorderPainted(true);
		foeStaminaBar.setBorderPainted(true);
		foeKiBar.setBorderPainted(true);
		physicalAttack.setBorderPainted(true);
		use.setBorderPainted(true);
		block.setBorderPainted(true);
		chooseSuperAttack.setBorderPainted(true);
		chooseUltimateAttack.setBorderPainted(true);
		
		meHealthBar.setBounds(140,80,450,30);
		meStaminaBar.setBounds(140,120,350,30);
		meKiBar.setBounds(140,160,250,30);
		foeHealthBar.setBounds(1400,80,450,30);
		foeStaminaBar.setBounds(1400,120,350,30);
		foeKiBar.setBounds(1400,160,250,30);
		
		meInfo.setBounds(40,30,450,30);
		foeInfo.setBounds(1400,30,450,30);
		
		block.setBounds(20,900,250,50);
		use.setBounds(290,900,250,50);
		physicalAttack.setBounds(560,900,250,50);
		chooseSuperAttack.setBounds(830,900,250,50);
		chooseUltimateAttack.setBounds(1370,900,250,50);
		
		superAttacks.setBounds(1100,900,250,50);
		ultimateAttacks.setBounds(1640,900,250,50);
		
		meHealthLbl.setBounds(40,80,100,30);
		meStaminaLbl.setBounds(40,120,100,30);
		meKiLbl.setBounds(40,160,100,30);
		foeHealthLbl.setBounds(1300,80,100,30);
		foeStaminaLbl.setBounds(1300,120,100,30);
		foeKiLbl.setBounds(1300,160,100,30);
		
		meImage.setBounds(0,200,540,1500);
		foeImage.setBounds(1200,300,770,770);
		
		
		meImage.setIcon(new ImageIcon("me6.png"));
		foeImage.setIcon(new ImageIcon("foe7.png"));
		
		
		add(meHealthBar);
		add(meStaminaBar);
		add(meKiBar);
		add(foeHealthBar);
		add(foeStaminaBar);
		add(foeKiBar);
		add(meInfo);
		add(foeInfo);
		add(physicalAttack);
		add(chooseSuperAttack);
		add(chooseUltimateAttack);
		add(use);
		add(block);
		add(superAttacks);
		add(ultimateAttacks);
		add(meHealthLbl);
		add(meStaminaLbl);
		add(meKiLbl);
		add(foeHealthLbl);
		add(foeStaminaLbl);
		add(foeKiLbl);
		add(meImage);
		add(foeImage);
		add(backGround);
		
		validate();
	}

	public JProgressBar getMeHealthBar() {
		return meHealthBar;
	}

	public void setMeHealthBar(JProgressBar meHealthBar) {
		this.meHealthBar = meHealthBar;
	}

	public JProgressBar getMeStaminaBar() {
		return meStaminaBar;
	}

	public void setMeStaminaBar(JProgressBar meStaminaBar) {
		this.meStaminaBar = meStaminaBar;
	}

	public JProgressBar getMeKiBar() {
		return meKiBar;
	}

	public void setMeKiBar(JProgressBar meKiBar) {
		this.meKiBar = meKiBar;
	}

	public JProgressBar getFoeHealthBar() {
		return foeHealthBar;
	}

	public void setFoeHealthBar(JProgressBar foeHealthBar) {
		this.foeHealthBar = foeHealthBar;
	}

	public JProgressBar getFoeStaminaBar() {
		return foeStaminaBar;
	}

	public void setFoeStaminaBar(JProgressBar foeStaminaBar) {
		this.foeStaminaBar = foeStaminaBar;
	}

	public JProgressBar getFoeKiBar() {
		return foeKiBar;
	}

	public void setFoeKiBar(JProgressBar foeKiBar) {
		this.foeKiBar = foeKiBar;
	}

	public JTextArea getMeInfo() {
		return meInfo;
	}

	public void setMeInfo(JTextArea meInfo) {
		this.meInfo = meInfo;
	}

	public JTextArea getFoeInfo() {
		return foeInfo;
	}

	public void setFoeInfo(JTextArea foeInfo) {
		this.foeInfo = foeInfo;
	}

	public JButton getPhysicalAttack() {
		return physicalAttack;
	}

	public void setPhysicalAttack(JButton physicalAttack) {
		this.physicalAttack = physicalAttack;
	}

	public JButton getUse() {
		return use;
	}

	public void setUse(JButton use) {
		this.use = use;
	}

	public JButton getBlock() {
		return block;
	}

	public void setBlock(JButton block) {
		this.block = block;
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

	public JButton getChooseSuperAttack() {
		return chooseSuperAttack;
	}

	public void setChooseSuperAttack(JButton chooseSuperAttack) {
		this.chooseSuperAttack = chooseSuperAttack;
	}

	public JButton getChooseUltimateAttack() {
		return chooseUltimateAttack;
	}

	public void setChooseUltimateAttack(JButton chooseUltimateAttack) {
		this.chooseUltimateAttack = chooseUltimateAttack;
	}
}
