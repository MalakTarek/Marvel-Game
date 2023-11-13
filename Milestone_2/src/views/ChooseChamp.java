package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import engine.Game;
import engine.Player;

@SuppressWarnings("serial")
public class ChooseChamp extends JFrame implements ActionListener {
	JButton[] champions;
	JPanel champSelection;
	JButton captainAmerica;
	JButton ironMan;
	JButton hulk;
	JButton thor;
	JButton start;
	JFrame mainGame;
	JLabel error;
	JButton champs[];
	Player second;
	Player first;
	boolean[] isTaken;
	boolean isFirstPlayer;

	public ChooseChamp(JFrame mainGame, Player first, Player second) {
		isTaken = new boolean[15];
		isFirstPlayer = true;
		this.mainGame = mainGame;
		this.first = first;
		this.second = second;
		mainGame.setTitle("Marvel");
		mainGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainGame.setBounds(50, 50, 800, 800);
		// champSelction.this.setResizable(false);
		// this.setVisible(true);
		choices(mainGame);
	}

	public void choices(JFrame mainGame) {

		champSelection = new JPanel();
		champSelection.setLayout(null);
		champSelection.setSize(700, 400);

		JLabel info = new JLabel(
				"Each Player please only choose 3 champions while keeping in mind the first player you choose is the leader of your team!");
		info.setBounds(5, 10, 300, 500);
		info.setSize(1000, 50);
		champSelection.add(info);
		champs = new JButton[15];
		int k = 0;
		for (int i = 0; i < Game.getAvailableChampions().size(); i++) {
			champs[i] = new JButton(Game.getAvailableChampions().get(i).getName());
			for (int j = 0; j < Game.getAvailableChampions().get(i).getAbilities().size(); j++) {
				champs[i].setToolTipText(
						// "hp:" + Integer.toString(Game.getAvailableChampions().get(i).getCurrentHP())
						// + " \n " +
						"Mana: " + Integer.toString(Game.getAvailableChampions().get(i).getMana()) + "Mana Cost : "
								+ Game.getAvailableChampions().get(i).getAbilities().get(j).getManaCost() + "Speed: "
								+ Integer.toString(Game.getAvailableChampions().get(i).getSpeed()) + "Attack Damage: "
								+ Integer.toString(Game.getAvailableChampions().get(i).getAttackDamage())
								+ "Attack Range: "
								+ Integer.toString(Game.getAvailableChampions().get(i).getAttackRange()) + "Max HP: "
								+ Integer.toString(Game.getAvailableChampions().get(i).getMaxHP())
								+ "Current Action Points: "
								+ Integer.toString(Game.getAvailableChampions().get(i).getCurrentActionPoints())
								+ "Max Action Points Per Turn: "
								+ Integer.toString(Game.getAvailableChampions().get(i).getMaxActionPointsPerTurn())
								+ "Abilites: { " + Game.getAvailableChampions().get(i).getAbilities().get(j).getName()
								+ "Base Cooldown: "
								+ Game.getAvailableChampions().get(i).getAbilities().get(j).getBaseCooldown()
//		    + "Current cool down : " + Game.getAvailableChampions().get(i).getAbilities().get(j).getCurrentCooldown() + "\n "  
								+ "Required Action Points: "
								+ Game.getAvailableChampions().get(i).getAbilities().get(j).getRequiredActionPoints()
								+ "Cast Range: "
								+ Game.getAvailableChampions().get(i).getAbilities().get(j).getCastRange()
								+ "Cast Area:"
								+ Game.getAvailableChampions().get(i).getAbilities().get(j).getCastArea() + " }");
			}
			if (i < 5)
				champs[i].setBounds(k * 200, 50, 10, 20);
			else if (i < 10)
				champs[i].setBounds(k * 200, 150, 10, 20);
			else
				champs[i].setBounds(k * 200, 250, 10, 20);
			champs[i].setSize(140, 70);
			k++;
			if (k == 5)
				k = 0;
			champSelection.add(champs[i]);
			champs[i].addActionListener(this);
		}

		start = new JButton("Start");
		start.setBounds(400, 500, 50, 50);
		start.setSize(140, 70);
		champSelection.add(start, BorderLayout.SOUTH);
		start.setEnabled(false);

		start.addActionListener(this);

		champSelection.setVisible(true);
		mainGame.add(champSelection);
		champSelection.revalidate();
		champSelection.repaint();

	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < Game.getAvailableChampions().size(); i++) {
			if (e.getSource() == champs[i]) {
				if (isFirstPlayer) {
					if (first.getTeam().size() < 3) {
						first.getTeam().add(Game.getAvailableChampions().get(i));
						for (int j = 0; j < Game.getAvailableChampions().size(); j++) {
							if (champs[i].getText().equals(Game.getAvailableChampions().get(j).getName())) {
//								Game.getAvailableChampions().remove(j);
								champs[i].setEnabled(false);
								champSelection.remove(champs[i]);
								champSelection.revalidate();
								champSelection.repaint();
							}
						}
					} else if (first.getTeam().size() == 3) {
						isFirstPlayer = false;
					}
				} else if (!isFirstPlayer) {
					if (second.getTeam().size() < 3) {
						second.getTeam().add(Game.getAvailableChampions().get(i));
						for (int j = 0; j < Game.getAvailableChampions().size(); j++) {
							if (champs[i].getText().equals(Game.getAvailableChampions().get(j).getName())) {
//								Game.getAvailableChampions().remove(j);
								champs[i].setEnabled(false);
								champSelection.remove(champs[i]);
								champSelection.revalidate();
								champSelection.repaint();
							}
						}
					}
				}
				if (first.getTeam().size() + second.getTeam().size() == 6) {

					JLabel ll = new JLabel("please click the start button");
					ll.setBounds(200, 400, 50, 50);
					ll.setSize(300, 50);
					start.setEnabled(true);
					champSelection.add(ll);
					champSelection.revalidate();
					champSelection.repaint();

				}
			}

			if (e.getSource() == start) {

				Game game ;
				game = new Game(first, second);
				first.setLeader(first.getTeam().get(0));

				second.setLeader(second.getTeam().get(0));

				mainGame.remove(champSelection);
				@SuppressWarnings("unused")
				GameInfo realDeal = new GameInfo(mainGame, game);
				
				
			}
		}
	}
}