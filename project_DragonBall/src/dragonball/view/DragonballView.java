package dragonball.view;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DragonballView extends JFrame {
	private WorldView worldView;
	private StartGame startGame;
	private BattleView battleView;
	private DragonView dragonView;
	private NewGame newGame;
	private UpgradeFighter upgradeFighterView;
	
	
	public DragonballView(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		this.setBounds(0, 0, width, height);
		this.setTitle("Dragonball");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		startGame = new StartGame();
		newGame = new NewGame();
		worldView = new WorldView();
		battleView = new BattleView();
		dragonView = new DragonView();
		upgradeFighterView=new UpgradeFighter();
		
		add(startGame);
		add(newGame);
		add(worldView);
		add(battleView);
		add(dragonView);
		add(upgradeFighterView);
		
		worldView.setVisible(false);
		newGame.setVisible(false);
		startGame.setVisible(false);
		battleView.setVisible(false);
		dragonView.setVisible(false);
		upgradeFighterView.setVisible(false);
	}
	
	public void addCellBtn(JButton cellBtn){
		worldView.getMapPnl().add(cellBtn);
	}

	public WorldView getWorldView() {
		return worldView;
	}

	public void setWorldView(WorldView worldView) {
		this.worldView = worldView;
	}

	public StartGame getStartGame() {
		return startGame;
	}

	public void setStartGame(StartGame startGame) {
		this.startGame = startGame;
	}

	public BattleView getBattleView() {
		return battleView;
	}

	public void setBattleView(BattleView battleView) {
		this.battleView = battleView;
	}

	public DragonView getDragonView() {
		return dragonView;
	}

	public void setDragonView(DragonView dragonView) {
		this.dragonView = dragonView;
	}

	public NewGame getNewGame() {
		return newGame;
	}

	public void setNewGame(NewGame newGame) {
		this.newGame = newGame;
	}

	public UpgradeFighter getUpgradeFighterView() {
		return upgradeFighterView;
	}

	public void setUpgradeFighterView(UpgradeFighter upgradeFighterView) {
		this.upgradeFighterView = upgradeFighterView;
	}
	
}
