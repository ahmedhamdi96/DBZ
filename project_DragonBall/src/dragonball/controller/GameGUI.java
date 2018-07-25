package dragonball.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Cell;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.player.Player;
import dragonball.view.DragonballView;

@SuppressWarnings("serial")
public class GameGUI implements ActionListener, GameListener{
	private Game game;
	private DragonballView dragonballView;
	private JButton[][] cellButtons;
	private Dragon dragon;
	private boolean dragonCalled;
	private Battle battle;
	
	public GameGUI(){
		dragonballView = new DragonballView();
		cellButtons = new JButton[10][10];
		
		try {
			game = new Game();
			game.setListener(this);
		} catch (MissingFieldException | UnknownAttackTypeException e) {
			JOptionPane.showMessageDialog(dragonballView,"The CSV files needed for the game run contain errors!","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		this.dragonballView.getStartGame().getLoadGame().addActionListener(this);
		this.dragonballView.getStartGame().getNewGame().addActionListener(this);
		
		
		this.dragonballView.getNewGame().getPlayerName().addActionListener(this);
		this.dragonballView.getNewGame().getFighterName().addActionListener(this);
		this.dragonballView.getNewGame().getRace().addActionListener(this);
		this.dragonballView.getNewGame().getStartGameBtn().addActionListener(this);
		
		
		this.dragonballView.getWorldView().getChooseFighter().addActionListener(this);
		this.dragonballView.getWorldView().getAssignAttack().addActionListener(this);
		this.dragonballView.getWorldView().getNewFighter().addActionListener(this);
		this.dragonballView.getWorldView().getSaveGame().addActionListener(this);
		this.dragonballView.getWorldView().getUpgradeFighter().addActionListener(this);
		this.dragonballView.getWorldView().getUp().addActionListener(this);
		this.dragonballView.getWorldView().getDown().addActionListener(this);
		this.dragonballView.getWorldView().getLeft().addActionListener(this);
		this.dragonballView.getWorldView().getRight().addActionListener(this);
		this.dragonballView.getWorldView().getEndGame().addActionListener(this);
		
		
		this.dragonballView.getUpgradeFighterView().getUpMaxHealthPoints().addActionListener(this);
		this.dragonballView.getUpgradeFighterView().getUpBlastDamage().addActionListener(this);
		this.dragonballView.getUpgradeFighterView().getUpMaxKi().addActionListener(this);
		this.dragonballView.getUpgradeFighterView().getUpMaxStamina().addActionListener(this);
		this.dragonballView.getUpgradeFighterView().getUpPhysicalDamage().addActionListener(this);
		this.dragonballView.getUpgradeFighterView().getQuit().addActionListener(this);
		
		
		this.dragonballView.getDragonView().getSenzuBeans().addActionListener(this);
		this.dragonballView.getDragonView().getAbilityPoints().addActionListener(this);
		this.dragonballView.getDragonView().getUltimateAttack().addActionListener(this);
		this.dragonballView.getDragonView().getSuperAttack().addActionListener(this);
		this.dragonballView.getDragonView().getBack().addActionListener(this);
		
		this.dragonballView.getBattleView().getPhysicalAttack().addActionListener(this);
		this.dragonballView.getBattleView().getUse().addActionListener(this);
		this.dragonballView.getBattleView().getBlock().addActionListener(this);
		this.dragonballView.getBattleView().getChooseSuperAttack().addActionListener(this);
		this.dragonballView.getBattleView().getChooseUltimateAttack().addActionListener(this);
		
		ArrayList<Cell> cells = new ArrayList<Cell>(); 
		for(int i=0; i<game.getWorld().getMap().length; i++){
			for(int j=0; j<game.getWorld().getMap()[i].length; j++){
				cells.add(game.getWorld().getMap()[i][j]);
			}
		}
		
		ImageIcon grassImage = new ImageIcon("GrassScale.png");
		
		
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				JButton cellBtn = new JButton(grassImage);
     			cellBtn.setContentAreaFilled(false);
     			cellBtn.setBorderPainted(true);
				cellBtn.addActionListener(this);
				dragonballView.addCellBtn(cellBtn);
				cellButtons[i][j]=cellBtn;
			}
		}
		
		cellButtons[0][0].setIcon(new ImageIcon("Boss.png"));
		cellButtons[9][9].setIcon(new ImageIcon("GrassFighter.png"));
		
		dragonballView.getStartGame().setVisible(true);
		dragonballView.validate();
		
		this.dragonballView.setVisible(true);
	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		//this.game.getPlayer().callDragon();
		dragonCalled = true;
		this.dragonballView.getDragonView().setVisible(true);
		this.dragonballView.getWorldView().setVisible(false);
		this.dragon = dragon;
		int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
		int senzuBeans = this.game.getPlayer().getSenzuBeans();
		this.dragonballView.getDragonView().getPlayerInfo().setText("Senzu Beans: "+senzuBeans+"\n\nAbility Points: "+abilityPoints+"\n");
		ArrayList<SuperAttack> playerSuperAttacks = this.game.getPlayer().getSuperAttacks();
		ArrayList<UltimateAttack> playerUltimateAttacks = this.game.getPlayer().getUltimateAttacks();
		for(SuperAttack s:playerSuperAttacks){
			this.dragonballView.getDragonView().getSuperAttacks().addItem(s.getName());
		}
		for(UltimateAttack u:playerUltimateAttacks){
			this.dragonballView.getDragonView().getUltimateAttacks().addItem(u.getName());
		}
	}
	

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if(collectible == Collectible.SENZU_BEAN){
			
			JOptionPane.showMessageDialog(dragonballView,"You collected a senzu bean!","Collectible Found!",JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(dragonballView,"You collected a dragonball!","Collectible Found!",JOptionPane.INFORMATION_MESSAGE);
		}
		String playerName = this.dragonballView.getNewGame().getPlayerName().getText();
		String fighterName = this.dragonballView.getNewGame().getFighterName().getText();
		int fighterLevel = game.getPlayer().getActiveFighter().getLevel();
		int senzuBeans = (game.getPlayer().getSenzuBeans());
		int dragonBalls = game.getPlayer().getDragonBalls();
		this.dragonballView.getWorldView().getPlayerInfoPnl().setText("Player's Name: "+playerName+"\nFighter's Name: "+fighterName+
				                                                      "\nLevel: "+fighterLevel+"\nSenzu Beans: "+senzuBeans+"\nDragonBalls: "+dragonBalls);
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		int beforeFightLevel = 1;
		battle = (Battle) e.getSource();
		ArrayList<SuperAttack> playerSuperAttacksBeforeBattle = new ArrayList<SuperAttack>();
		ArrayList<UltimateAttack> playerUltimateAttacksBeforeBattle = new ArrayList<UltimateAttack>();
		PlayableFighter activeFighter = game.getPlayer().getActiveFighter();
		NonPlayableFighter foe = (NonPlayableFighter) battle.getFoe();
		dragonballView.getBattleView().getMeHealthBar().setMinimum(0);
		dragonballView.getBattleView().getMeStaminaBar().setMinimum(0);
		dragonballView.getBattleView().getMeKiBar().setMinimum(0);
		dragonballView.getBattleView().getFoeHealthBar().setMinimum(0);
		dragonballView.getBattleView().getFoeStaminaBar().setMinimum(0);
		dragonballView.getBattleView().getFoeKiBar().setMinimum(0);
		int meMaxHealth = game.getPlayer().getActiveFighter().getMaxHealthPoints();
		int meMaxStamnia = game.getPlayer().getActiveFighter().getMaxStamina();
		int meMaxKi = game.getPlayer().getActiveFighter().getMaxKi();
		int foeMaxHealth = foe.getMaxHealthPoints();
		int foeMaxStamnia = foe.getMaxStamina();
		int foeMaxKi = foe.getMaxKi();
		dragonballView.getBattleView().getMeHealthBar().setMaximum(meMaxHealth);
		dragonballView.getBattleView().getMeStaminaBar().setMaximum(meMaxStamnia);
		dragonballView.getBattleView().getMeKiBar().setMaximum(meMaxKi);
		dragonballView.getBattleView().getFoeHealthBar().setMaximum(foeMaxHealth);
		dragonballView.getBattleView().getFoeStaminaBar().setMaximum(foeMaxStamnia);
		dragonballView.getBattleView().getFoeKiBar().setMaximum(foeMaxKi);
		int meHealth = game.getPlayer().getActiveFighter().getHealthPoints();
		int meStamnia = game.getPlayer().getActiveFighter().getStamina();
		int meKi = game.getPlayer().getActiveFighter().getKi();
		int foeHealth = foe.getHealthPoints();
		int foeStamnia = foe.getStamina();
		int foeKi = foe.getKi();
		dragonballView.getBattleView().getMeHealthBar().setValue(meHealth);
		dragonballView.getBattleView().getMeStaminaBar().setValue(meStamnia);
		dragonballView.getBattleView().getMeKiBar().setValue(meKi);
		dragonballView.getBattleView().getFoeHealthBar().setValue(foeHealth);
		dragonballView.getBattleView().getFoeStaminaBar().setValue(foeStamnia);
		dragonballView.getBattleView().getFoeKiBar().setValue(foeKi);
		
		if(game.getPlayer().getActiveFighter().getStamina()==0 && game.getPlayer().getActiveFighter().getKi()==0){
			JOptionPane.showMessageDialog(dragonballView,"Your fighter is no longer a Super Sayian!","Battle",JOptionPane.INFORMATION_MESSAGE);
		}

		this.dragonballView.getBattleView().getMeInfo().setText("Name: "+activeFighter.getName()+"\tLevel: "+activeFighter.getLevel());
		this.dragonballView.getBattleView().getFoeInfo().setText("Name: "+foe.getName()+"\tLevel: "+foe.getLevel());
		cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassScale.png"));
		cellButtons[0][0].setIcon(new ImageIcon("Boss.png"));
		switch (e.getType()){
		case STARTED: 
			JOptionPane.showMessageDialog(dragonballView,"You encountered a foe, get ready for the battle!","Battle",JOptionPane.INFORMATION_MESSAGE);
			beforeFightLevel = activeFighter.getLevel();
			playerSuperAttacksBeforeBattle = game.getPlayer().getSuperAttacks();
			playerUltimateAttacksBeforeBattle =  game.getPlayer().getUltimateAttacks();
			this.dragonballView.getBattleView().setVisible(true);
			this.dragonballView.getWorldView().setVisible(false);
			break;
		case ENDED:
			Fighter winner = (Fighter) e.getWinner();
			if(winner.getName().equals(activeFighter.getName())){
				JOptionPane.showMessageDialog(dragonballView,"Congratulations, You won the battle!","Battle",JOptionPane.INFORMATION_MESSAGE);
				int newXp = ((PlayableFighter)battle.getMe()).getXp();
				String newSuperAttacks = "Gained Super Attacks:\n";
				for (SuperAttack superAttack : foe.getSuperAttacks()) {
					if (!playerSuperAttacksBeforeBattle.contains(superAttack)) {
						newSuperAttacks+=superAttack.getName()+"\n";
					}
				}
				String newUltimateAttacks = "Gained Ultimate Attacks:\n";
				for (UltimateAttack ultimateAttack : foe.getUltimateAttacks()) {
					if (!playerUltimateAttacksBeforeBattle.contains(ultimateAttack)) {
						newUltimateAttacks+=ultimateAttack.getName()+"\n";
					}
				}
				String info = "Your XP is now: "+newXp+"\n"+newSuperAttacks+newUltimateAttacks;
				int afterFightLevel = activeFighter.getLevel();
				int newTargetXp = activeFighter.getTargetXp();
				int newAbilityPoints = activeFighter.getAbilityPoints();
				if(afterFightLevel>beforeFightLevel){
					info+="Your Level is now: "+afterFightLevel+"\nYour new Target Xp: "+newTargetXp+"\nYour new Ability Points: "+newAbilityPoints;
				}
				if(foe.isStrong()){
					int newExploredMaps = game.getPlayer().getExploredMaps();
					String bossDefeated ="You have deafeated a boss!\n"+"Explored Maps: "+newExploredMaps+"\n";
					JOptionPane.showMessageDialog(dragonballView,bossDefeated,"Battle Upgrades",JOptionPane.INFORMATION_MESSAGE);
				}
				JOptionPane.showMessageDialog(dragonballView,info,"Battle Upgrades",JOptionPane.INFORMATION_MESSAGE);
				if(foe.isStrong()){
					JOptionPane.showMessageDialog(dragonballView,"Generating a new map!","Battle Upgrades",JOptionPane.INFORMATION_MESSAGE);
				}
				cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
			}
			else{
				JOptionPane.showMessageDialog(dragonballView,"You lost the battle, Try harder!","Battle",JOptionPane.INFORMATION_MESSAGE);
				cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
			}
			String playerName = game.getPlayer().getName();
			String fighterName = game.getPlayer().getActiveFighter().getName();
			int fighterLevel = game.getPlayer().getActiveFighter().getLevel();
			int senzuBeans = game.getPlayer().getSenzuBeans();
			int dragonBalls = game.getPlayer().getDragonBalls();
			this.dragonballView.getWorldView().getPlayerInfoPnl().setText("Player's Name: "+playerName+"\nFighter's Name: "+fighterName+
                    "\nLevel: "+fighterLevel+"\nSenzu Beans: "+senzuBeans+"\nDragonBalls: "+dragonBalls);
			this.dragonballView.getBattleView().setVisible(false);
			this.dragonballView.getWorldView().setVisible(true);
			break;
		case NEW_TURN:
			Fighter attacker = (Fighter) e.getCurrentOpponent();
			if(attacker.getName().equals(activeFighter.getName())){
				JOptionPane.showMessageDialog(dragonballView,"Its your turn!","Battle",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(dragonballView,"Its your foe's turn!","Battle",JOptionPane.INFORMATION_MESSAGE);
				try {
					battle.play();
				} catch (NotEnoughKiException e1) {
					//JOptionPane.showMessageDialog(dragonballView,"You do not have enough Ki to perform this attack!","Error",JOptionPane.ERROR_MESSAGE);
					try {
						battle.attack(new PhysicalAttack());
					} catch (NotEnoughKiException e2) {
						//Do Nothing
					}
				}
			}
			
			break;
		case ATTACK:
			break;
		case BLOCK: break;
		case USE: break;
		default: return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			if(((JButton)e.getSource()).getText().equals("New Game")){
				this.dragonballView.getNewGame().setVisible(true);
				this.dragonballView.getStartGame().setVisible(false);
			}
			if(((JButton)e.getSource()).getText().equals("Load Game")){
				try {
					game.load("SavedGame");
					game.setListener(this);
					cellButtons[9][9].setIcon(new ImageIcon("GrassScale.png"));
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
					String playerName = game.getPlayer().getName();
					String fighterName = game.getPlayer().getActiveFighter().getName();
					int fighterLevel = game.getPlayer().getActiveFighter().getLevel();
					int senzuBeans = game.getPlayer().getSenzuBeans();
					int dragonBalls = game.getPlayer().getDragonBalls();
					this.dragonballView.getWorldView().getPlayerInfoPnl().setText("Player's Name: "+playerName+"\nFighter's Name: "+fighterName+
		                    "\nLevel: "+fighterLevel+"\nSenzu Beans: "+senzuBeans+"\nDragonBalls: "+dragonBalls);
					ArrayList<PlayableFighter> fighters  =game.getPlayer().getFighters();
					for(PlayableFighter fighter: fighters){
						dragonballView.getWorldView().getFighters().addItem(fighter.getName());
					}
					this.dragonballView.getWorldView().setVisible(true);
					this.dragonballView.getStartGame().setVisible(false);
					JOptionPane.showMessageDialog(dragonballView,"Your Game is loaded successfully.","Load Game",JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You need to save a game first to be able to load!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Start Game!")){
				String playerName = this.dragonballView.getNewGame().getPlayerName().getText();
				String fighterName = this.dragonballView.getNewGame().getFighterName().getText();
				this.dragonballView.getWorldView().getFighters().addItem(fighterName);
				if(playerName.equals("") || fighterName.equals("")){
					JOptionPane.showMessageDialog(dragonballView,"You need to choose a name for your player and a name for your fighter!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}			
				char race = ((String)this.dragonballView.getNewGame().getRace().getSelectedItem()).charAt(0);
				game.getPlayer().setName(playerName);
				game.getPlayer().createFighter(race, fighterName);
				int fighterLevel = game.getPlayer().getActiveFighter().getLevel();
				int senzuBeans = game.getPlayer().getSenzuBeans();
				int dragonBalls = game.getPlayer().getDragonBalls();
				this.dragonballView.getWorldView().getPlayerInfoPnl().setText("Player's Name: "+playerName+"\nFighter's Name: "+fighterName+
						                                                      "\nLevel: "+fighterLevel+"\nSenzu Beans: "+senzuBeans+"\nDragonBalls: "+dragonBalls);
				this.dragonballView.getWorldView().setVisible(true);
				this.dragonballView.getNewGame().setVisible(false);
			}
			
			
			if(((JButton)e.getSource()).getText().equals("Up")){
				try {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassScale.png"));
					game.getWorld().moveUp();
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));

				} catch (MapIndexOutOfBoundsException e1) {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				}

				
			}
			if(((JButton)e.getSource()).getText().equals("Down")){
				try {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassScale.png"));
					game.getWorld().moveDown();
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				} catch (MapIndexOutOfBoundsException e1) {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Right")){
				try {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassScale.png"));
					game.getWorld().moveRight();
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				} catch (MapIndexOutOfBoundsException e1) {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Left")){
				try {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassScale.png"));
					game.getWorld().moveLeft();
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				} catch (MapIndexOutOfBoundsException e1) {
					cellButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon("GrassFighter.png"));
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Save Game")){
				try {
					game.save("SavedGame");
					JOptionPane.showMessageDialog(dragonballView,"Your Game is saved successfully.","Save Game",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(dragonballView,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("End Game")){
				this.dragonballView.getWorldView().setVisible(false);
				this.dragonballView.getStartGame().setVisible(true);
			}
			
			if(((JButton)e.getSource()).getText().equals("New Fighter")){
				String fighterName = (String) JOptionPane.showInputDialog(null, "Fighter's Name", "Create a new Fighter", JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(fighterName==null){
					return;
				}
				if(fighterName.equals("")){
					JOptionPane.showMessageDialog(dragonballView,"You need to choose a name for your fighter!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					Object[] races = {"Earthling", "Frieza", "Majin", "Namekian", "Saiyan"};
					Object race = JOptionPane.showInputDialog(null, "Races", "Create a new Fighter", JOptionPane.INFORMATION_MESSAGE, null, races, null);
					if(race==null){
						return;
					}
					char r =((String) race).charAt(0);
					game.getPlayer().createFighter(r, fighterName);
					this.dragonballView.getWorldView().getFighters().addItem(fighterName);
					
				}
				
			}
			
			if(((JButton)e.getSource()).getText().equals("Choose Fighter")){
				String playerName = this.dragonballView.getNewGame().getPlayerName().getText();
				//String fighterName = this.dragonballView.getNewGame().getFighterName().getText();		
				String assignedFighter = (String)this.dragonballView.getWorldView().getFighters().getSelectedItem();
				int index = 0;
				for(Fighter f: game.getPlayer().getFighters()){
					if(f.getName().equals(assignedFighter)){
						index = game.getPlayer().getFighters().indexOf(f);
					}
				}
				game.getPlayer().setActiveFighter(game.getPlayer().getFighters().get(index));
				int fighterLevel = game.getPlayer().getActiveFighter().getLevel();
				int senzuBeans = game.getPlayer().getSenzuBeans();
				int dragonBalls = game.getPlayer().getDragonBalls();
				this.dragonballView.getWorldView().getPlayerInfoPnl().setText("Player's Name: "+playerName+"\nFighter's Name: "+assignedFighter+
						                                                      "\nLevel: "+fighterLevel+"\nSenzu Beans: "+senzuBeans+"\nDragonBalls: "+dragonBalls);
				this.dragonballView.getWorldView().setVisible(true);
				this.dragonballView.getNewGame().setVisible(false);
			}
			
			
			if(((JButton)e.getSource()).getText().equals("Assign Attack")){
				ArrayList<SuperAttack> playerSuperAttacks = this.game.getPlayer().getSuperAttacks();
				ArrayList<UltimateAttack> playerUltimateAttacks = this.game.getPlayer().getUltimateAttacks();
				
				String[] playerSuperAttacksNames = new String[playerSuperAttacks.size()];
				String[] playerUltimateAttacksNames = new String[playerUltimateAttacks.size()];
				
				for(int i=0; i<playerSuperAttacks.size(); i++){
					playerSuperAttacksNames[i] = playerSuperAttacks.get(i).getName();
				}
				
				for(int i=0; i<playerUltimateAttacks.size(); i++){
					playerUltimateAttacksNames[i] = playerUltimateAttacks.get(i).getName();
				}
				
				
				ArrayList<SuperAttack> fighterSuperAttacks = this.game.getPlayer().getActiveFighter().getSuperAttacks();
				ArrayList<UltimateAttack> fighterUltimateAttacks = this.game.getPlayer().getActiveFighter().getUltimateAttacks();
				
				String[] fighterSuperAttacksNames = new String[fighterSuperAttacks.size()+1];
				String[] fighterUltimateAttacksNames = new String[fighterUltimateAttacks.size()+1];
				
				fighterSuperAttacksNames[0] = "None";
				fighterUltimateAttacksNames[0] = "None";
				
				for(int i=1; i<fighterSuperAttacks.size()+1; i++){
					fighterSuperAttacksNames[i] = fighterSuperAttacks.get(i-1).getName();
				}
				
				for(int i=1; i<fighterUltimateAttacks.size()+1; i++){
					fighterUltimateAttacksNames[i] = fighterUltimateAttacks.get(i-1).getName();
				}
				
				SuperAttack oldSuperAttack = null;
				UltimateAttack oldUltimateAttack = null;
				
				SuperAttack newSuperAttack = null;
				UltimateAttack newUltimateAttack = null;
				
				Object[] attackTypes = {"Super Attack", "Ultimate Attack"};
				Object attackType = JOptionPane.showInputDialog(null, "Choose attack type", "Assign Attack", JOptionPane.INFORMATION_MESSAGE, null, attackTypes, null);
				if(attackType==null){
					return;
				}
				char a =((String) attackType).charAt(0);
				if(a=='S'){
					Object oldSuperAttackName = JOptionPane.showInputDialog(null, "Choose an attack to be replaced", "Assign Attack", JOptionPane.INFORMATION_MESSAGE, null, fighterSuperAttacksNames, null);
					if(oldSuperAttackName==null){
						return;
					}
					if(!((String)oldSuperAttackName).equals("None")){
						for(SuperAttack s:fighterSuperAttacks){
							if(s.getName().equals((String)oldSuperAttackName)){
								oldSuperAttack = s;
								
							}
						}
					}
				}
				else{
					Object oldUltimateAttackName = JOptionPane.showInputDialog(null, "Choose an attack to be replaced", "Assign Attack", JOptionPane.INFORMATION_MESSAGE, null, fighterUltimateAttacksNames, null);
					if(oldUltimateAttackName==null){
						return;
					}
					if(!((String)oldUltimateAttackName).equals("None")){
						for(UltimateAttack u:fighterUltimateAttacks){
							if(u.getName().equals((String)oldUltimateAttackName)){
								oldUltimateAttack = u;
							}
						}
					}
				}
				
				
				
				if(a=='S'){
					Object newSuperAttackName = JOptionPane.showInputDialog(null, "Choose an new attack", "Assign Attack", JOptionPane.INFORMATION_MESSAGE, null, playerSuperAttacksNames, null);
					if(newSuperAttackName==null){
						return;
					}
					for(SuperAttack s:playerSuperAttacks){
						if(s.getName().equals((String)newSuperAttackName)){
							newSuperAttack = s;
						}
					}
				}
				else{
					Object newUltimateAttackName = JOptionPane.showInputDialog(null, "Choose an new attack", "Assign Attack", JOptionPane.INFORMATION_MESSAGE, null, playerUltimateAttacksNames, null);
					if(newUltimateAttackName==null){
						return;
					}
					for(UltimateAttack u:playerUltimateAttacks){
						if(u.getName().equals((String)newUltimateAttackName)){
							newUltimateAttack = u;
						}
					}
					
				}
				
				PlayableFighter activeFighter = this.game.getPlayer().getActiveFighter();
				
				if(a=='S'){
					try {
						this.game.getPlayer().assignAttack(activeFighter ,newSuperAttack,oldSuperAttack);
						this.dragonballView.getBattleView().getSuperAttacks().addItem(newSuperAttack.getName());
					} catch (MaximumAttacksLearnedException e1) {
						JOptionPane.showMessageDialog(dragonballView,"You have reached the maximum number of super attacks allowed(4)!","Error",JOptionPane.ERROR_MESSAGE);
					} catch (DuplicateAttackException e1) {
						JOptionPane.showMessageDialog(dragonballView,"You already assigned this attack to this fighter before!","Error",JOptionPane.ERROR_MESSAGE);
					} catch (NotASaiyanException e1) {
						JOptionPane.showMessageDialog(dragonballView,"You can not use the SuperSayian attack on a non sayian!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					try {
						this.game.getPlayer().assignAttack(activeFighter ,newUltimateAttack,oldUltimateAttack);
						this.dragonballView.getBattleView().getUltimateAttacks().addItem(newUltimateAttack.getName());
					} catch (MaximumAttacksLearnedException e1) {
						JOptionPane.showMessageDialog(dragonballView,"You have reached the maximum number of ultimate attacks allowed(2)!","Error",JOptionPane.ERROR_MESSAGE);
					} catch (DuplicateAttackException e1) {
						JOptionPane.showMessageDialog(dragonballView,"You already assigned this attack to this fighter before!","Error",JOptionPane.ERROR_MESSAGE);
					} catch (NotASaiyanException e1) {
						JOptionPane.showMessageDialog(dragonballView,"You can not use the SuperSayian attack on a non sayian!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if(((JButton)e.getSource()).getText().equals("Upgrade Fighter")){
				int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
				int maxHealthPoints = this.game.getPlayer().getActiveFighter().getMaxHealthPoints();
				int blastDamage = this.game.getPlayer().getActiveFighter().getBlastDamage();
				int physicalDamage = this.game.getPlayer().getActiveFighter().getPhysicalDamage();
				int maxKi = this.game.getPlayer().getActiveFighter().getMaxKi();
				int maxStamina = this.game.getPlayer().getActiveFighter().getMaxStamina();
				
				
				this.dragonballView.getUpgradeFighterView().getAbilityPoints().setText("Ability Points: "+abilityPoints);
				this.dragonballView.getUpgradeFighterView().getFighterAttributes().setText("Max Health Points: "+maxHealthPoints+"\n\nBlastDamage: "+blastDamage+
						"\n\nPhysical Damage: "+physicalDamage+"\n\nMax Ki: "+maxKi+"\n\nMax Stamina: "+maxStamina);
				
				this.dragonballView.getUpgradeFighterView().setVisible(true);
				this.dragonballView.getWorldView().setVisible(false);
				
			}
			if(((JButton)e.getSource()).getText().equals("Quit")){
				this.dragonballView.getUpgradeFighterView().setVisible(false);
				this.dragonballView.getWorldView().setVisible(true);
			}
			
			if(((JButton)e.getSource()).getText().equals("Upgrade Max Health Points")){
				
				PlayableFighter activeFighter = this.game.getPlayer().getActiveFighter();
				try {
					this.game.getPlayer().upgradeFighter(activeFighter, 'H');
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int maxHealthPoints = this.game.getPlayer().getActiveFighter().getMaxHealthPoints();
					int blastDamage = this.game.getPlayer().getActiveFighter().getBlastDamage();
					int physicalDamage = this.game.getPlayer().getActiveFighter().getPhysicalDamage();
					int maxKi = this.game.getPlayer().getActiveFighter().getMaxKi();
					int maxStamina = this.game.getPlayer().getActiveFighter().getMaxStamina();
					
					
					this.dragonballView.getUpgradeFighterView().getAbilityPoints().setText("Ability Points: "+abilityPoints);
					this.dragonballView.getUpgradeFighterView().getFighterAttributes().setText("Max Health Points: "+maxHealthPoints+"\n\nBlastDamage: "+blastDamage+
							"\n\nPhysical Damage: "+physicalDamage+"\n\nMax Ki: "+maxKi+"\n\nMax Stamina: "+maxStamina);
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough ability points to perform this upgrade!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Upgrade Blast Damage")){
				PlayableFighter activeFighter = this.game.getPlayer().getActiveFighter();
				try {
					this.game.getPlayer().upgradeFighter(activeFighter, 'B');
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int maxHealthPoints = this.game.getPlayer().getActiveFighter().getMaxHealthPoints();
					int blastDamage = this.game.getPlayer().getActiveFighter().getBlastDamage();
					int physicalDamage = this.game.getPlayer().getActiveFighter().getPhysicalDamage();
					int maxKi = this.game.getPlayer().getActiveFighter().getMaxKi();
					int maxStamina = this.game.getPlayer().getActiveFighter().getMaxStamina();
					
					
					this.dragonballView.getUpgradeFighterView().getAbilityPoints().setText("Ability Points: "+abilityPoints);
					this.dragonballView.getUpgradeFighterView().getFighterAttributes().setText("Max Health Points: "+maxHealthPoints+"\n\nBlastDamage: "+blastDamage+
							"\n\nPhysical Damage: "+physicalDamage+"\n\nMax Ki: "+maxKi+"\n\nMax Stamina: "+maxStamina);
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough ability points to perform this upgrade!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Upgrade Physical Damage")){
				PlayableFighter activeFighter = this.game.getPlayer().getActiveFighter();
				try {
					this.game.getPlayer().upgradeFighter(activeFighter, 'P');
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int maxHealthPoints = this.game.getPlayer().getActiveFighter().getMaxHealthPoints();
					int blastDamage = this.game.getPlayer().getActiveFighter().getBlastDamage();
					int physicalDamage = this.game.getPlayer().getActiveFighter().getPhysicalDamage();
					int maxKi = this.game.getPlayer().getActiveFighter().getMaxKi();
					int maxStamina = this.game.getPlayer().getActiveFighter().getMaxStamina();
					
					
					this.dragonballView.getUpgradeFighterView().getAbilityPoints().setText("Ability Points: "+abilityPoints);
					this.dragonballView.getUpgradeFighterView().getFighterAttributes().setText("Max Health Points: "+maxHealthPoints+"\n\nBlastDamage: "+blastDamage+
							"\n\nPhysical Damage: "+physicalDamage+"\n\nMax Ki: "+maxKi+"\n\nMax Stamina: "+maxStamina);
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough ability points to perform this upgrade!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Upgrade Max Ki")){
				PlayableFighter activeFighter = this.game.getPlayer().getActiveFighter();
				try {
					this.game.getPlayer().upgradeFighter(activeFighter, 'K');
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int maxHealthPoints = this.game.getPlayer().getActiveFighter().getMaxHealthPoints();
					int blastDamage = this.game.getPlayer().getActiveFighter().getBlastDamage();
					int physicalDamage = this.game.getPlayer().getActiveFighter().getPhysicalDamage();
					int maxKi = this.game.getPlayer().getActiveFighter().getMaxKi();
					int maxStamina = this.game.getPlayer().getActiveFighter().getMaxStamina();
					
					
					this.dragonballView.getUpgradeFighterView().getAbilityPoints().setText("Ability Points: "+abilityPoints);
					this.dragonballView.getUpgradeFighterView().getFighterAttributes().setText("Max Health Points: "+maxHealthPoints+"\n\nBlastDamage: "+blastDamage+
							"\n\nPhysical Damage: "+physicalDamage+"\n\nMax Ki: "+maxKi+"\n\nMax Stamina: "+maxStamina);
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough ability points to perform this upgrade!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Upgrade Max Stamina")){
				PlayableFighter activeFighter = this.game.getPlayer().getActiveFighter();
				try {
					this.game.getPlayer().upgradeFighter(activeFighter, 'S');
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int maxHealthPoints = this.game.getPlayer().getActiveFighter().getMaxHealthPoints();
					int blastDamage = this.game.getPlayer().getActiveFighter().getBlastDamage();
					int physicalDamage = this.game.getPlayer().getActiveFighter().getPhysicalDamage();
					int maxKi = this.game.getPlayer().getActiveFighter().getMaxKi();
					int maxStamina = this.game.getPlayer().getActiveFighter().getMaxStamina();
					
					
					this.dragonballView.getUpgradeFighterView().getAbilityPoints().setText("Ability Points: "+abilityPoints);
					this.dragonballView.getUpgradeFighterView().getFighterAttributes().setText("Max Health Points: "+maxHealthPoints+"\n\nBlastDamage: "+blastDamage+
							"\n\nPhysical Damage: "+physicalDamage+"\n\nMax Ki: "+maxKi+"\n\nMax Stamina: "+maxStamina);
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough ability points to perform this upgrade!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Senzu beans")){
				if(dragonCalled==true){
					DragonWish[] dragonWishs = dragon.getWishes();
					this.game.getPlayer().chooseWish(dragonWishs[0]);
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int senzuBeans = this.game.getPlayer().getSenzuBeans();
					this.dragonballView.getDragonView().getPlayerInfo().setText("Senzu Beans: "+senzuBeans+"\n\nAbility Points: "+abilityPoints);
					dragonCalled = false;
				}
				else{
					JOptionPane.showMessageDialog(dragonballView,"You already choose a wish, press Back!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Ability points")){
				if(dragonCalled==true){
					DragonWish[] dragonWishs = dragon.getWishes();
					this.game.getPlayer().chooseWish(dragonWishs[1]);
					int abilityPoints = this.game.getPlayer().getActiveFighter().getAbilityPoints();
					int senzuBeans = this.game.getPlayer().getSenzuBeans();
					this.dragonballView.getDragonView().getPlayerInfo().setText("Senzu Beans: "+senzuBeans+"\n\nAbility Points: "+abilityPoints);
					dragonCalled = false;
				}
				else{
					JOptionPane.showMessageDialog(dragonballView,"You already choose a wish, press Back!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Super attack")){
				if(dragonCalled==true){
					DragonWish[] dragonWishs = dragon.getWishes();
					this.game.getPlayer().chooseWish(dragonWishs[2]);
					SuperAttack superAttack = dragonWishs[2].getSuperAttack();
					this.dragonballView.getDragonView().getSuperAttacks().addItem(superAttack.getName());
					dragonCalled = false;
				}
				else{
					JOptionPane.showMessageDialog(dragonballView,"You already choose a wish, press Back!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Ultimate attack")){
				if(dragonCalled==true){
					DragonWish[] dragonWishs = dragon.getWishes();
					this.game.getPlayer().chooseWish(dragonWishs[3]);
					UltimateAttack ultimateAttack = dragonWishs[3].getUltimateAttack();
					this.dragonballView.getDragonView().getUltimateAttacks().addItem(ultimateAttack.getName());
					dragonCalled = false;
				}
				else{
					JOptionPane.showMessageDialog(dragonballView,"You already choose a wish, press Back!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Back")){
				if(dragonCalled==true){
					JOptionPane.showMessageDialog(dragonballView,"You should choose a wish!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String playerName = this.dragonballView.getNewGame().getPlayerName().getText();
				String fighterName = this.dragonballView.getNewGame().getFighterName().getText();
				int fighterLevel = game.getPlayer().getActiveFighter().getLevel();
				int senzuBeans = (game.getPlayer().getSenzuBeans());
				int dragonBalls = game.getPlayer().getDragonBalls();
				this.dragonballView.getWorldView().getPlayerInfoPnl().setText("Player's Name: "+playerName+"\nFighter's Name: "+fighterName+
						                                                      "\nLevel: "+fighterLevel+"\nSenzu Beans: "+senzuBeans+"\nDragonBalls: "+dragonBalls);
				this.dragonballView.getDragonView().setVisible(false);
				this.dragonballView.getWorldView().setVisible(true);
			}
			
			if(((JButton)e.getSource()).getText().equals("Physical Attack")){
				try {
					battle.attack(new PhysicalAttack());
				} catch (NotEnoughKiException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough Ki to perform this attack!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(((JButton)e.getSource()).getText().equals("Use")){
				Player player = game.getPlayer();
				try {
					battle.use(player, Collectible.SENZU_BEAN);
				} catch (NotEnoughSenzuBeansException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough Senzu Beans to perform this attack!","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			if(((JButton)e.getSource()).getText().equals("Block")){
				battle.block();
			}
			
			if(((JButton)e.getSource()).getText().equals("Use Super Attack")){
				String superAttackName = (String) this.dragonballView.getBattleView().getSuperAttacks().getSelectedItem();
				if(superAttackName==null){
					JOptionPane.showMessageDialog(dragonballView,"You do not have any Super attacks!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				SuperAttack superAttack = null;
				ArrayList<SuperAttack> superAttacks = game.getPlayer().getActiveFighter().getSuperAttacks();
				for(SuperAttack s:superAttacks){
					if(s.getName().equals(superAttackName)){
						superAttack = s;
					}
				}
				try {
					battle.attack(superAttack);
				} catch (NotEnoughKiException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough Ki to perform this attack!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(((JButton)e.getSource()).getText().equals("Use Ultimate Attack")){
				PlayableFighter activeFighter = game.getPlayer().getActiveFighter();
				String ultimateAttackName = (String) this.dragonballView.getBattleView().getUltimateAttacks().getSelectedItem();
				if(ultimateAttackName==null){
					JOptionPane.showMessageDialog(dragonballView,"You do not have any Ultimate attacks!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				UltimateAttack ultimateAttack = null;
				ArrayList<UltimateAttack> ultimateAttacks = game.getPlayer().getActiveFighter().getUltimateAttacks();
				for(UltimateAttack s:ultimateAttacks){
					if(s.getName().equals(ultimateAttackName)){
						ultimateAttack = s;
					}
				}
				try {
					if(activeFighter instanceof Saiyan){
						if(ultimateAttackName.equals("Super Saiyan")&&game.getPlayer().getActiveFighter().getKi()>=3){
							JOptionPane.showMessageDialog(dragonballView,"Your fighter is now a Super Saiyan!","Battle",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					battle.attack(ultimateAttack);
				} catch (NotEnoughKiException e1) {
					JOptionPane.showMessageDialog(dragonballView,"You do not have enough Ki to perform this attack!","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
	
	public JButton[][] getCellButtons() {
		return cellButtons;
	}

	public void setCellButtons(JButton[][] cellButtons) {
		this.cellButtons = cellButtons;
	}
}
