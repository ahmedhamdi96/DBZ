package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UpgradeFighter extends JPanel{
	private JButton quit;
	private JButton upMaxHealthPoints;
	private JButton upBlastDamage;
	private JButton upPhysicalDamage;
	private JButton upMaxKi;
	private JButton upMaxStamina;
	private JLabel abilityPoints;
	private JTextArea fighterAttributes;
	
	
	public UpgradeFighter(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		this.setBounds(0, 0, width, height);
		this.setName("Upgrade Fighter");
		this.setLayout(null);
		
		
		quit = new JButton("Quit");
		upMaxHealthPoints= new JButton("Upgrade Max Health Points");
		upBlastDamage= new JButton("Upgrade Blast Damage");
		upPhysicalDamage= new JButton("Upgrade Physical Damage");
		upMaxKi= new JButton("Upgrade Max Ki");
		upMaxStamina= new JButton("Upgrade Max Stamina");
		abilityPoints=new JLabel("");
		fighterAttributes=new JTextArea();
		JLabel backGround = new JLabel();
		
		
		quit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		upMaxHealthPoints.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		upBlastDamage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		upPhysicalDamage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		upMaxKi.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		upMaxStamina.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		abilityPoints.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		fighterAttributes.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		upMaxHealthPoints.setOpaque(false);
		upMaxHealthPoints.setContentAreaFilled(false);
		upBlastDamage.setOpaque(false);
		upBlastDamage.setContentAreaFilled(false);
		upPhysicalDamage.setOpaque(false);
		upPhysicalDamage.setContentAreaFilled(false);
		upMaxKi.setOpaque(false);
		upMaxKi.setContentAreaFilled(false);
		upMaxStamina.setOpaque(false);
		upMaxStamina.setContentAreaFilled(false);
		abilityPoints.setOpaque(false);
		//abilityPoints.setContentAreaFilled(false);
		fighterAttributes.setBackground(Color.BLACK);
		
		fighterAttributes.setEditable(false);
		
		
		quit.setForeground(Color.WHITE);
		upMaxHealthPoints.setForeground(Color.WHITE);
		upBlastDamage.setForeground(Color.WHITE);
		upPhysicalDamage.setForeground(Color.WHITE);
		upMaxKi.setForeground(Color.WHITE);
		upMaxStamina.setForeground(Color.WHITE);
		abilityPoints.setForeground(Color.WHITE);
		fighterAttributes.setForeground(Color.WHITE);

		
		quit.setBounds(1640,900,240,50);
		
		upMaxHealthPoints.setBounds(1300,300,600,100);
		upBlastDamage.setBounds(1300,400,600,100);
		upPhysicalDamage.setBounds(1300,500,600,100);
		upMaxKi.setBounds(1300,600,600,100);
		upMaxStamina.setBounds(1300,700,600,100);
		
		abilityPoints.setBounds(50,60,600,100);
		
		fighterAttributes.setBounds(35,300,600,500);
		
		backGround.setBounds(0, 0, this.getWidth(), this.getHeight());
		backGround.setIcon(new ImageIcon("UpgradeFighter.jpg"));
		
			
		quit.setBorderPainted(false);
		upMaxHealthPoints.setBorderPainted(false);
		upBlastDamage.setBorderPainted(false);
		upPhysicalDamage.setBorderPainted(false);
		upMaxKi.setBorderPainted(false);
		upMaxStamina.setBorderPainted(false);
		
		

		this.add(quit);
		this.add(upMaxHealthPoints);
		this.add(upBlastDamage);
		this.add(upPhysicalDamage);
		this.add(upMaxKi);
		this.add(upMaxStamina);
		this.add(abilityPoints);
		this.add(fighterAttributes);
		add(backGround);
		
	
		this.validate();
	}

	public JButton getQuit() {
		return quit;
	}

	public void setQuit(JButton quit) {
		this.quit = quit;
	}

	public JButton getUpMaxHealthPoints() {
		return upMaxHealthPoints;
	}

	public void setUpMaxHealthPoints(JButton upMaxHealthPoints) {
		this.upMaxHealthPoints = upMaxHealthPoints;
	}

	public JButton getUpBlastDamage() {
		return upBlastDamage;
	}

	public void setUpBlastDamage(JButton upBlastDamage) {
		this.upBlastDamage = upBlastDamage;
	}

	public JButton getUpPhysicalDamage() {
		return upPhysicalDamage;
	}

	public void setUpPhysicalDamage(JButton upPhysicalDamage) {
		this.upPhysicalDamage = upPhysicalDamage;
	}

	public JButton getUpMaxKi() {
		return upMaxKi;
	}

	public void setUpMaxKi(JButton upMaxKi) {
		this.upMaxKi = upMaxKi;
	}

	public JButton getUpMaxStamina() {
		return upMaxStamina;
	}

	public void setUpMaxStamina(JButton upMaxStamina) {
		this.upMaxStamina = upMaxStamina;
	}

	public JTextArea getFighterAttributes() {
		return fighterAttributes;
	}

	public void setFighterAttributes(JTextArea fighterAttributes) {
		this.fighterAttributes = fighterAttributes;
	}

	public JLabel getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(JLabel abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

}
