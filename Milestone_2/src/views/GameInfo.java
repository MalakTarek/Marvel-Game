
package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import engine.Game;
import engine.PriorityQueue;
import exceptions.*;
import model.abilities.*;
import model.effects.Effect;
import model.world.*;

@SuppressWarnings("serial")
public class GameInfo extends JFrame implements ActionListener {

	JButton leaderability;
	JTextArea turnorderqueue;
	JButton endTurn;
	JPanel board;
	JButton move;
	JButton up;
	JButton down;
	JButton right;
	JButton left;
	JButton aUp;
	JButton aDown;
	JButton aRight;
	JButton aLeft;
	JTextArea area;
	String champType;
	String abilityType;
	Effect ccwEffect;
	int damageAmount;
	int healAmount;
	PriorityQueue turnOrder;
	JPanel boardGrid;
	String champstype;
	String leader;
	JFrame mainGame;
	Game game ;
	ArrayList<Champion> temp;
	JButton abilities [] = new JButton[3];
	JLabel leaderAbility1;
	JLabel leaderAbility2;
	String amount;
	JButton xZero;
	JButton xOne;
	JButton xTwo;
	JButton xThree;
	JButton xFour;
	JButton yZero;
	JButton yOne;
	JButton yTwo;
	JButton yThree;
	JButton yFour;
	JButton abiUp;
	JButton abiDown;
	JButton abiRight;
	JButton abiLeft;
	

	public GameInfo(JFrame mainGame, Game game) {

		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setBounds(50, 50, 800, 600);
		// this.setResizable(false);
		this.mainGame = mainGame;
		this.game = game;
		mainGame.setTitle("Game");
		mainGame.setLayout(null);
		// mainGame.setTitle("Marvel");
		mainGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainGame.setBounds(50, 50, 1000, 1000);
		mainGame.setVisible(true);
		infoPanel(mainGame, game);

	}

	public void infoPanel(JFrame mainGame, Game game) {

		board = new JPanel();
		board.setSize(1000, 1000);
		board.setLayout(null);

		JLabel first = new JLabel("Player One Name: " + game.getFirstPlayer().getName());
		JLabel second = new JLabel("Player Two Name: " + game.getSecondPlayer().getName());
		JLabel firstchamp = new JLabel("red champions are yours!");
		JLabel secondchamp = new JLabel("blue champions are yours!");
		
		 leaderAbility1 = new JLabel("first player leader ability not used");
		 leaderAbility2 = new JLabel("second player leader ability not used");
		 leaderAbility1.setBounds(10,20,300,80);
	        leaderAbility2.setBounds(750,20,400,80);
//	        leaderAbility1.setSize(500,500);
//	        leaderAbility2.setSize(500,500);
	       board.add(leaderAbility1,BorderLayout.WEST);
	        board.add(leaderAbility2,BorderLayout.EAST);
	        first.setBounds(10, 0, 150, 80);
//	        first.setSize(500, 500);
	        second.setBounds(750, 0, 150, 80);
//	        second.setSize(500, 500);
	        firstchamp.setBounds(10, 10, 150, 80);
//	        firstchamp.setSize(500, 500);
	        secondchamp.setBounds(750, 10, 150, 80);
//	        secondchamp.setSize(500, 500);
	        board.add(first, BorderLayout.WEST);
	        board.add(second, BorderLayout.EAST);
	        board.add(firstchamp, BorderLayout.WEST);
	        board.add(secondchamp, BorderLayout.EAST);
	    
	  //showing turnorder
		 temp = new ArrayList<>();
         turnorderqueue = new JTextArea();

        while(game.getTurnOrder().isEmpty()==false){

            temp.add((Champion) game.getTurnOrder().peekMin());
            game.getTurnOrder().remove();
        }
            for(int i=0; i<temp.size();i++) {

                turnorderqueue.append(temp.get(i).getName() + " ");
            }
            for(int i=0;i<temp.size();i++) {
                game.getTurnOrder().insert(temp.get(i));
                 }
            turnorderqueue.setSize(500,500);
            turnorderqueue.setBounds(10,5,400,20);
            board.add(turnorderqueue,BorderLayout.NORTH);
            turnorderqueue.setEditable(false);


        //showing current champ & abilites &effects
		Champion c = game.getCurrentChampion();
		if (c instanceof Hero) {
			champType = "Hero";
		} else if (c instanceof Villain) {
			champType = "Villain";
		} else if (c instanceof AntiHero) {
			champType = "AntiHero";
		}
		
		area = new JTextArea( "\nCurrent champ info : "+"\nName: " + game.getCurrentChampion().getName() + "\n" + "Type: " + champType 
				+ "\ncurrentHP: " + game.getCurrentChampion().getCurrentHP() + "\n" + "Mana: "+ game.getCurrentChampion().getMana() + "\n"
				+ "action points: " + game.getCurrentChampion().getCurrentActionPoints() + "\n" + "attack damage: "
				+ game.getCurrentChampion().getAttackDamage() + "\n" + "attack range: "
				+ game.getCurrentChampion().getAttackRange());
		
		for (int j = 0; j < game.getCurrentChampion().getAbilities().size(); j++) {
			Ability ability = game.getCurrentChampion().getAbilities().get(j);
			if (ability instanceof CrowdControlAbility) {
				abilityType = "CrowdControlAbility";
			} else if (ability instanceof DamagingAbility) {
				abilityType = "DamagingAbility";
			} else if (ability instanceof HealingAbility) {
				abilityType = "HealingAbility";
			}

			area.append("\nName: " + game.getCurrentChampion().getAbilities().get(j).getName() + "\n" + "Type: "
					+ abilityType + "\n" + "cast area: " + game.getCurrentChampion().getAbilities().get(j).getCastArea()
					+ "\n" + "cast range: " + game.getCurrentChampion().getAbilities().get(j).getCastRange() + "\n"
					+ "mana: " + game.getCurrentChampion().getAbilities().get(j).getManaCost() + "\n"
					+ "Required action points: "
					+ game.getCurrentChampion().getAbilities().get(j).getRequiredActionPoints() + "\n"
					+ "Current cooldown: " + game.getCurrentChampion().getAbilities().get(j).getCurrentCooldown() + "\n"
					+ "Base cooldown: " + game.getCurrentChampion().getAbilities().get(j).getBaseCooldown() + "\n");

			  if (ability instanceof CrowdControlAbility) {
				 area.append("Effect Name: " + ((CrowdControlAbility) ability).getEffect().getName() + "\n"
						      + "Duration: " + ((CrowdControlAbility) ability).getEffect().getDuration());
			  } else if (ability instanceof DamagingAbility) {
				area.append("Damage amount: " + ((DamagingAbility) ability).getDamageAmount());
			  } else if (ability instanceof HealingAbility) {
				area.append("Heal Amount: " + ((HealingAbility) ability).getHealAmount());
			 }
		 }
		
		for (int k = 0; k < game.getCurrentChampion().getAppliedEffects().size(); k++) {
			Effect effect = game.getCurrentChampion().getAppliedEffects().get(k);
			area.append( "\n"+"Name: " + effect.getName() + "\n" + "Duration : " + effect.getDuration());
			
		}

		area.setBounds(20,80,300,100);
		area.setSize(150,600);
		area.setEditable(false);
//		area.setLineWrap(true);
		
		board.add(area,BorderLayout.WEST);
		
		// make the board n always updating
		boardGrid = new JPanel();
		boardGrid.setLayout(new GridLayout(5, 5));
        boardGrid.setBounds(250,100,550,550);
        
        updates();

		boardGrid.setSize(600,600);
		mainGame.add(board, BorderLayout.CENTER);
		board.revalidate();
		board.repaint();
		
		
		
		boardGrid.revalidate();
		boardGrid.repaint();
	
	//BUTTONS
		
		//move
		up = new JButton("mUp");
        up.setBounds(180,685,50,50);
        up.setSize(80,50);
        board.add(up,BorderLayout.SOUTH);

        up.addActionListener(this);

        down = new JButton("mDown");
        down.setBounds(180,760,50,50);
        down.setSize(80,50);
        board.add(down,BorderLayout.SOUTH);

        down.addActionListener(this);

        left = new JButton("mLeft");
        left.setBounds(100,715,50,50);
        left.setSize(80,50);
        board.add(left,BorderLayout.SOUTH);

        left.addActionListener(this);

        right = new JButton("mRight");
        right.setBounds(260,715,50,50);
        right.setSize(80,50);
        board.add(right,BorderLayout.SOUTH);

        right.addActionListener(this);
		
		//attack
        aUp = new JButton("aUp");
        aUp.setBounds(850,685,50,50);
        aUp.setSize(80,50);
        board.add(aUp,BorderLayout.WEST);

        aUp.addActionListener(this);

        aDown = new JButton("aDown");
        aDown.setBounds(850 ,760,50,50);;
        aDown.setSize(80,50);
        board.add(aDown,BorderLayout.SOUTH);

        aDown.addActionListener(this);

        aLeft = new JButton("aLeft");
        aLeft.setBounds(770,715,50,50);
        aLeft.setSize(80,50);
        board.add(aLeft,BorderLayout.SOUTH);

        aLeft.addActionListener(this);

        aRight = new JButton("aRight");
        aRight.setBounds(910,715,50,50);
        aRight.setSize(80,50);
        board.add(aRight,BorderLayout.SOUTH);

        aRight.addActionListener(this);
		
		//ending turn
		endTurn = new JButton("end turn");
		endTurn.setBounds(870,270,50,50);
		endTurn.setSize(100,70);
		board.add(endTurn,BorderLayout.SOUTH);
		
		endTurn.addActionListener(this);
		
		leaderability = new JButton("Use Leader Ability");
        leaderability.setBounds(480,720,50,50);
        leaderability.setSize(140,70);
        board.add(leaderability,BorderLayout.SOUTH);

        leaderability.addActionListener(this);
		 
		 
        abiUp = new JButton("abiUp");
		abiUp.setBounds(870,350,50,50);
		abiUp.setSize(100,70);
		board.add(abiUp,BorderLayout.SOUTH);
		
		abiUp.addActionListener(this);
		
		
		abiDown = new JButton("abiDown");
		abiDown.setBounds(870,430,50,50);
		abiDown.setSize(100,70);
		board.add(abiDown,BorderLayout.SOUTH);
		
		abiDown.addActionListener(this);
		
		
		abiRight = new JButton("abiRight");
		abiRight.setBounds(870,510,50,50);
		abiRight.setSize(100,70);
		board.add(abiRight,BorderLayout.SOUTH);
		
		abiRight.addActionListener(this);
		
		abiLeft = new JButton("abiLeft");
		abiLeft.setBounds(870,590,50,50);
		abiLeft.setSize(100,70);
		board.add(abiLeft,BorderLayout.SOUTH);
		
		abiLeft.addActionListener(this);
		 
		xZero = new JButton("x.0");
		   xZero.setBounds(850, 100, 120, 40);
		   xZero.setSize(50, 25);
		  board.add(xZero,BorderLayout.WEST);
		  xZero.addActionListener(this);
		  yZero = new JButton("x.0");
		   yZero.setBounds(900, 100, 120, 40);
		   yZero.setSize(50, 25);
		  board.add(yZero,BorderLayout.WEST);
		  yZero.addActionListener(this);
		   
		   xOne = new JButton("x.1"); //player 1
		   xOne.setBounds(850, 125, 80, 25);
		   xOne.setSize(50,25);
		   board.add(xOne , BorderLayout.WEST);
		   xOne.addActionListener(this);
		   
		   xTwo = new JButton("x.2");
		   xTwo.setBounds(850, 150, 80, 25);
		   xTwo.setSize(50,25);
		   board.add(xTwo,BorderLayout.WEST);
		   xTwo.addActionListener(this);
		   
		   xThree = new JButton("x.3"); //
		   xThree.setBounds(850 , 175 , 80 , 30);
		   xThree.setSize(50,25);
		   board.add(xThree , BorderLayout.WEST);
		   xThree.addActionListener(this);
		   
		   xFour = new JButton("x.4"); //
		   xFour.setBounds(850 , 200 , 80 , 30);
		   xFour.setSize(50,25);
		   board.add(xFour , BorderLayout.WEST);
		   xFour.addActionListener(this);
		   
		   yOne = new JButton("y.1"); //player 1
		   yOne.setBounds(900, 125, 80, 25);
		   yOne.setSize(50,25);
		   board.add(yOne , BorderLayout.WEST);
		   yOne.addActionListener(this);
		   
		   yTwo = new JButton("y.2");
		   yTwo.setBounds(900, 150, 80, 25);
		   yTwo.setSize(50,25);
		   board.add(yTwo,BorderLayout.WEST);
		   yTwo.addActionListener(this);
		   
		   yThree = new JButton("y.3"); //
		   yThree.setBounds(900 , 175 , 80 , 30);
		   yThree.setSize(50,25);
		   board.add(yThree , BorderLayout.WEST);
		   yThree.addActionListener(this);
		   
		   yFour = new JButton("y.4"); //
		   yFour.setBounds(900 , 200 , 80 , 30);
		   yFour.setSize(50,25);
		   board.add(yFour , BorderLayout.WEST);
		   yFour.addActionListener(this);
			
			
		 
		 
		int  g = 0;
		int k =1;
		 for (Ability a : game.getCurrentChampion().getAbilities()) {
			 abilities [g] = new JButton(a.getName());
			 if (a instanceof CrowdControlAbility) {
	                amount =("Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n"
	                        + "Duration: " + ((CrowdControlAbility) a).getEffect().getDuration());
	            } else if (a instanceof DamagingAbility) {
	                amount =("Damage amount: " + ((DamagingAbility) a).getDamageAmount());
	            } else if (a instanceof HealingAbility) {
	                amount =("Heal Amount: " + ((HealingAbility) a).getHealAmount());
	            }
	          
			 abilities[g].setToolTipText("Type: "
	                    + abilityType + "\n" + "cast area: " + a.getCastArea()
	                    + "\n" + "cast range: " + a.getCastRange() + "\n"
	                    + "mana: " + a.getManaCost() + "\n"
	                    + "Required action points: "
	                    + a.getRequiredActionPoints() + "\n"
	                    + "Current cooldown: " + a.getCurrentCooldown() + "\n"
	                    + "Base cooldown: " + a.getBaseCooldown() + "\n"
	                    + amount
	                    );
	          
			 abilities [g].setBounds(k*200 , 20,60,60);
			 abilities[g].setSize(140,70);
			 board.add(abilities[g] , BorderLayout.NORTH);
			 abilities[g].addActionListener(this);
			 g++;
			 k++;

		 }
		board.revalidate();
		board.repaint();
		
		
		
		boardGrid.setVisible(true);

		 board.setVisible(true);

	}
	public void updates () {
		if (boardGrid != null) {
			boardGrid.removeAll();
			
		}
		Object [][] champorcover= game.getBoard();
			for (int i = 4; i >= 0; i--) {
				for (int j = 0; j < 5; j++) {
					if (champorcover[i][j] instanceof Cover) {
				   	Cover cover = (Cover) champorcover[i][j];
						JButton m = new JButton ("cover");
						m.setToolTipText("hp:" +  Integer.toString(cover.getCurrentHP()));
						boardGrid.add(m);
						
						
					} 
					else if (champorcover [i][j] instanceof Champion) {
						Champion champ= (Champion) champorcover[i][j];
						
						if (champ instanceof Hero) {
							champstype = "Hero";
						} else if (champ instanceof Villain) {
							champstype = "Villain";
						} else if (champ instanceof AntiHero) {
							champstype = "AntiHero";
						}

						for (int r = 0; r < 3; r++) {

							if (champ.getName().equals(game.getFirstPlayer().getLeader().getName()) 
									|| champ.getName().equals(game.getSecondPlayer().getLeader().getName())) {
								leader = " is leader";
							}
							else {
								leader = "is not leader";
							}
						}
						int e = 0;
						JButton n = new JButton (champ.getName());
						if( champ.getAppliedEffects().size() != 0) {
						do {
//						for (int e = 0; e < champ.getAppliedEffects().size(); e++) {
							
							Effect effect = champ.getAppliedEffects().get(e);
						n.setToolTipText("hp:" + champ.getCurrentHP() + " mana: " + champ.getMana() + " speed: " + champ.getSpeed()
						+ " max actions per turn : " + champ.getMaxActionPointsPerTurn() + " attack damage: " + champ.getAttackDamage() 
						+ " attack range: " + champ.getAttackRange() + "Type: " + champstype + " " 
						+ leader 
						+ " Effect Name: " + effect.getName() + "\n" 
					+ "Duration : " + effect.getDuration() );
						e++;
					}while(e < champ.getAppliedEffects().size());
					}
					else
					{
						n.setToolTipText("hp:" + champ.getCurrentHP() + " mana: " + champ.getMana() + " speed: " + champ.getSpeed()
						+ " max actions per turn : " + champ.getMaxActionPointsPerTurn() + " attack damage: " + champ.getAttackDamage() 
						+ " attack range: " + champ.getAttackRange() + "Type: " + champstype + " " 
						+ leader );
					}
					
					
						if (game.getFirstPlayer().getTeam().contains(champ)) {
							boardGrid.add(n);
							n.setBackground(Color.RED);
						}
						else {
							boardGrid.add(n);
							n.setBackground(Color.BLUE);
						}
						
					}
					 else {
						boardGrid.add(new JButton());
					}

				}

			}
		
			board.add(boardGrid,BorderLayout.EAST);
			boardGrid.revalidate();
			boardGrid.repaint();
			board.revalidate();
			board.repaint();
		//	mainGame.add(board);
			mainGame.revalidate();
			mainGame.repaint();
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == up){
			try {
				
				game.move(Direction.UP);
				updates();
				if(game.checkGameOver() != null){
		    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
		    		System.exit(0);
		    		}
			} catch (UnallowedMovementException e1 ) {
				
				JOptionPane.showMessageDialog(null,"UnallowedMovementException");
				
			//	e1.printStackTrace();
			}
			catch( NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"NotEnoughResourcesException");
			}
		}
		if(e.getSource() == down){
			try {
				
				game.move(Direction.DOWN);
				updates();
				if(game.checkGameOver() != null){
		    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
		    		System.exit(0);
		    		}
	    } catch (UnallowedMovementException e1 ) {
				
				JOptionPane.showMessageDialog(null,"UnallowedMovementException");
				
			//	e1.printStackTrace();
			}
			catch( NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"NotEnoughResourcesException");
			}
		}
		if(e.getSource() == right){
			try {
				
				game.move(Direction.RIGHT);
				updates();
				if(game.checkGameOver() != null){
		    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
		    		System.exit(0);
		    		}
	} catch (UnallowedMovementException e1 ) {
				
				JOptionPane.showMessageDialog(null,"UnallowedMovementException");
				
			//	e1.printStackTrace();
			}
			catch( NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"NotEnoughResourcesException");
			}
		}
		if(e.getSource() == left){
			try {
				
				game.move(Direction.LEFT);
				updates();
				if(game.checkGameOver() != null){
		    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
		    		System.exit(0);
		    		}
	           } catch (UnallowedMovementException e1 ) {
				
				JOptionPane.showMessageDialog(null,"UnallowedMovementException");
				
			//	e1.printStackTrace();
			}
			catch( NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"NotEnoughResourcesException");
			}
		}
		if(e.getSource() == aUp ){
	//		int g = 0;
	//		for (@SuppressWarnings("unused") Ability a : game.getCurrentChampion().getAbilities()) {
		//		 if(e.getSource() != abilities[g]){
				
					
						try {
							game.attack(Direction.UP);
							updates();
							if(game.checkGameOver() != null){
					    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
					    		System.exit(0);
					    		}
						} catch (ChampionDisarmedException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,"ChampionDisarmedException");
							//e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,"NotEnoughResourcesException" );
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null," InvalidTargetException ");
						//	e1.printStackTrace();
						}
		//		 }
	//			 g++;
			//}
		}
		if(e.getSource() == aDown){
	//		int g = 0;
	//		for (@SuppressWarnings("unused") Ability a : game.getCurrentChampion().getAbilities()) {
	//			 if(e.getSource() != abilities[g]){
			
				try {
					game.attack(Direction.DOWN);
					updates();
					if(game.checkGameOver() != null){
			    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
			    		System.exit(0);
			    		}
				} catch (ChampionDisarmedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ChampionDisarmedException");
				//	e1.printStackTrace();
				} catch (NotEnoughResourcesException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"NotEnoughResourcesException" );
				//	e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null," InvalidTargetException ");
				//	e1.printStackTrace();
				}
	
			//	 }
			//	 g++;
			//}
		}
		if(e.getSource() == aRight){
	//		int g = 0;
		//	for (@SuppressWarnings("unused") Ability a : game.getCurrentChampion().getAbilities()) {
			//	 if(e.getSource() != abilities[g]){
			
				try {
					game.attack(Direction.RIGHT);
					updates();
					if(game.checkGameOver() != null){
			    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
			    		System.exit(0);
			    		}
				} catch (ChampionDisarmedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ChampionDisarmedException");
				//	e1.printStackTrace();
				} catch (NotEnoughResourcesException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"NotEnoughResourcesException" );
				//	e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null," InvalidTargetException ");
				//	e1.printStackTrace();
				}
				
			
			//	 }
		//		 g++;
	//		}
		}
		if(e.getSource() == aLeft){
		//	int g = 0;
			//for (@SuppressWarnings("unused") Ability a : game.getCurrentChampion().getAbilities()) {
				// if(e.getSource() != abilities[g]){
			     
				try {
					game.attack(Direction.LEFT);
					updates();
					if(game.checkGameOver() != null){
			    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
			    		System.exit(0);
			    		}
				} catch (ChampionDisarmedException e1) {
			//		// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ChampionDisarmedException");
				//	e1.printStackTrace();
				} catch (NotEnoughResourcesException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"NotEnoughResourcesException" );
				//	e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null," InvalidTargetException ");
				//	e1.printStackTrace();
				}
				
			
			//	 }
				// g++;
		//	}
		}
		if(e.getSource() == endTurn){
			
		game.endTurn();
		temp = new ArrayList<>();
	      // turnorderqueue.removeAll();
		turnorderqueue.setText(" ");
	        while(game.getTurnOrder().isEmpty()==false){

	            temp.add((Champion) game.getTurnOrder().peekMin());
	            game.getTurnOrder().remove();
	        }
	            for(int i=0; i<temp.size();i++) {
	            	turnorderqueue.setSize(500,500);
	                turnorderqueue.setBounds(10,5,300,20);
	                board.add(turnorderqueue,BorderLayout.NORTH);
	                turnorderqueue.setEditable(false);
	                turnorderqueue.append(temp.get(i).getName() + "  ");
	            }
	            for(int i=0;i<temp.size();i++) {
	                game.getTurnOrder().insert(temp.get(i));
	                 }
	         
	            turnorderqueue.setSize(500,500);
	            turnorderqueue.setBounds(10,5,400,20);
	            board.add(turnorderqueue,BorderLayout.NORTH);
	            turnorderqueue.setEditable(false);

	            
	          
	            Champion c = game.getCurrentChampion();
	    		if (c instanceof Hero) {
	    			champType = "Hero";
	    		} else if (c instanceof Villain) {
	    			champType = "Villain";
	    		} else if (c instanceof AntiHero) {
	    			champType = "AntiHero";
	    		}
	    		
	    		area.setText("Name: " + game.getCurrentChampion().getName() + "\n" + "Type: " + champType + "Mana: "
	    				+ "currentHP: " + game.getCurrentChampion().getCurrentHP() + game.getCurrentChampion().getMana() + "\n"
	    				+ "action points: " + game.getCurrentChampion().getCurrentActionPoints() + "\n" + "attack damage: "
	    				+ game.getCurrentChampion().getAttackDamage() + "\n" + "attack range: "
	    				+ game.getCurrentChampion().getAttackRange() + "\n");
	    		
	    		for (int j = 0; j < game.getCurrentChampion().getAbilities().size(); j++) {
	    			Ability ability = game.getCurrentChampion().getAbilities().get(j);
	    			if (ability instanceof CrowdControlAbility) {
	    				abilityType = "CrowdControlAbility";
	    			} else if (ability instanceof DamagingAbility) {
	    				abilityType = "DamagingAbility";
	    			} else if (ability instanceof HealingAbility) {
	    				abilityType = "HealingAbility";
	    			}

	    			area.append("Name: " + game.getCurrentChampion().getAbilities().get(j).getName() + "\n" + "Type: "
	    					+ abilityType + "\n" + "cast area" + game.getCurrentChampion().getAbilities().get(j).getCastArea()
	    					+ "\n" + "cast range" + game.getCurrentChampion().getAbilities().get(j).getCastRange() + "\n"
	    					+ "mana" + game.getCurrentChampion().getAbilities().get(j).getManaCost() + "\n"
	    					+ "Required action points: "
	    					+ game.getCurrentChampion().getAbilities().get(j).getRequiredActionPoints() + "\n"
	    					+ "Current cooldown" + game.getCurrentChampion().getAbilities().get(j).getCurrentCooldown() + "\n"
	    					+ "Base cooldown" + game.getCurrentChampion().getAbilities().get(j).getBaseCooldown() + "\n");

	    			  if (ability instanceof CrowdControlAbility) {
	    				 area.append("Effect Name: " + ((CrowdControlAbility) ability).getEffect().getName() + "\n"
	    						      + "Duration: " + ((CrowdControlAbility) ability).getEffect().getDuration());
	    			  } else if (ability instanceof DamagingAbility) {
	    				area.append("Damage amount: " + ((DamagingAbility) ability).getDamageAmount());
	    			  } else if (ability instanceof HealingAbility) {
	    				area.append("Heal Amount" + ((HealingAbility) ability).getHealAmount());
	    			 }
	    		 }
	    		
	    		for (int k = 0; k < game.getCurrentChampion().getAppliedEffects().size(); k++) {
	    			Effect effect = game.getCurrentChampion().getAppliedEffects().get(k);
	    			area.append("Name: " + effect.getName() + "\n" + "Duration : " + effect.getDuration());
	    			
	    		}

	    		area.setBounds(20,80,300,100);
	    		area.setSize(150,600);
	    		area.setEditable(false);
	    		board.add(area);
	    		
	    		int g = 0;
	    		for (Ability a : game.getCurrentChampion().getAbilities()) {
	               
	                board.remove(abilities[g]);
	                g++;

	            }
	    		 g = 0;
	    		int k=1;
	    		for (Ability a : game.getCurrentChampion().getAbilities()) {
	                abilities [g] = new JButton(a.getName());
	                if (a instanceof CrowdControlAbility) {
		                amount =("Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n"
		                        + "Duration: " + ((CrowdControlAbility) a).getEffect().getDuration());
		            } else if (a instanceof DamagingAbility) {
		                amount =("Damage amount: " + ((DamagingAbility) a).getDamageAmount());
		            } else if (a instanceof HealingAbility) {
		                amount =("Heal Amount: " + ((HealingAbility) a).getHealAmount());
		            }
		          
				 abilities[g].setToolTipText("Type: "
		                    + abilityType + "\n" + "cast area: " + a.getCastArea()
		                    + "\n" + "cast range: " + a.getCastRange() + "\n"
		                    + "mana: " + a.getManaCost() + "\n"
		                    + "Required action points: "
		                    + a.getRequiredActionPoints() + "\n"
		                    + "Current cooldown: " + a.getCurrentCooldown() + "\n"
		                    + "Base cooldown: " + a.getBaseCooldown() + "\n"
		                    + amount
		                    );
				 abilities [g].setBounds(k*200 , 20,60,60);
				 abilities[g].setSize(140,70);
				 board.add(abilities[g] , BorderLayout.NORTH);
				 abilities[g].addActionListener(this);
				 g++;
				 k++;

			 }
	            
	    		if(game.checkGameOver() != null){
	    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
	    		System.exit(0);
	    		}
	            board.revalidate();
				board.repaint();
				mainGame.revalidate();
				mainGame.repaint();
		}
		if(e.getSource()==leaderability) {
			try {
                game.useLeaderAbility();
                updates();
                if(game.isFirstLeaderAbilityUsed() ==true) {
                     leaderAbility1.setText("First Leader Ability Used");
                }
                else if(game.isSecondLeaderAbilityUsed()==true){
                    leaderAbility2.setText("Second Leader Ability Used");
                }
                if(game.checkGameOver() != null){
		    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
		    		System.exit(0);
		    		}


            } catch (LeaderAbilityAlreadyUsedException e1) {
                JOptionPane.showMessageDialog(null, "LeaderAbilityAlreadyUsedException");
                //e1.printStackTrace();
            } catch (LeaderNotCurrentException e1) {
            JOptionPane.showMessageDialog(null, "LeaderNotCurrentException");
                //e1.printStackTrace();
            }
}
		
		//cast ability (Ability a)
		int g = 0;
		for (@SuppressWarnings("unused") Ability a : game.getCurrentChampion().getAbilities()) {
			
			
			
			
			
			
		 if(e.getSource() == abilities[g]){
		
				 if( e.getSource() == abiUp){
					 
						
					try {
						game.castAbility(a, Direction.UP);
						updates();
						 if(game.checkGameOver() != null){
								JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
							    System.exit(0);
							   	}
					} catch (AbilityUseException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "AbilityUseException");
					//	e1.printStackTrace();
					} catch (NotEnoughResourcesException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "NotEnoughResourcesExceptio");
					//	e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
					//	e1.printStackTrace();
					}
					
				 }
				 else if( e.getSource() == abiDown){
						try {
							game.castAbility(a, Direction.DOWN);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesExceptio");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
				 }
				 else if( e.getSource() == abiLeft){
						try {
							game.castAbility(a, Direction.LEFT);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesExceptio");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if ( e.getSource() == abiRight){
						try {
							game.castAbility(a, Direction.RIGHT);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesExceptio");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
				 }
			
				 else if( e.getSource() == xZero  && e.getSource() == yZero){
						try {
							game.castAbility(a, 0,0);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xOne  && e.getSource() == yOne){
						try {
							game.castAbility(a, 1,1);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xTwo  && e.getSource() == yTwo){
						try {
							game.castAbility(a, 2,2);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xThree  && e.getSource() == yThree){
						try {
							game.castAbility(a, 3,3);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xFour  && e.getSource() == yFour){
						try {
							game.castAbility(a, 4,4);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xZero  && e.getSource() == yOne){
						try {
							game.castAbility(a, 0,1);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xZero  && e.getSource() == yTwo){
						try {
							game.castAbility(a, 0,2);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xZero  && e.getSource() == yThree){
						try {
							game.castAbility(a, 0,3);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xZero  && e.getSource() == yFour){
						try {
							game.castAbility(a, 0,4);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xOne  && e.getSource() == yZero){
						try {
							game.castAbility(a, 1,0);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xOne  && e.getSource() == yTwo){
						try {
							game.castAbility(a, 1,2);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xOne  && e.getSource() == yThree){
						try {
							game.castAbility(a, 1,3);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xOne  && e.getSource() == yFour){
						try {
							game.castAbility(a, 1,4);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xTwo  && e.getSource() == yZero){
						try {
							game.castAbility(a, 2,0);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xTwo  && e.getSource() == yOne){
						try {
							game.castAbility(a, 2,1);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xTwo  && e.getSource() == yThree){
						try {
							game.castAbility(a, 2,3);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xTwo  && e.getSource() == yFour){
						try {
							game.castAbility(a, 2,4);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xThree  && e.getSource() == yZero){
						try {
							game.castAbility(a, 3,0);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xThree  && e.getSource() == yOne){
						try {
							game.castAbility(a, 3,1);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xThree  && e.getSource() == yTwo){
						try {
							game.castAbility(a, 3,2);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xThree  && e.getSource() == yFour){
						try {
							game.castAbility(a, 3,4);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xFour  && e.getSource() == yZero){
						try {
							game.castAbility(a, 4,0);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xFour  && e.getSource() == yOne){
						try {
							game.castAbility(a, 4,1);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xFour  && e.getSource() == yTwo){
						try {
							game.castAbility(a, 4,2);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				 else if( e.getSource() == xFour  && e.getSource() == yThree){
						try {
							game.castAbility(a, 4,3);
							updates();
							 if(game.checkGameOver() != null){
						    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
						    		System.exit(0);
						    		}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NumberFormatException");
						//	e1.printStackTrace();
						} catch (AbilityUseException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "AbilityUseException");
						//	e1.printStackTrace();
						} catch (NotEnoughResourcesException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
						//	e1.printStackTrace();
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "InvalidTargetException");
						//	e1.printStackTrace();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
						//	e1.printStackTrace();
						}
					}
				else	 {
				 try {
					game.castAbility(a);
					 updates();
					 if(game.checkGameOver() != null){
				    		JOptionPane.showMessageDialog(null, game.checkGameOver().getName());
				    		System.exit(0);
				    		}
				} catch (AbilityUseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "AbilityUseException");
				//	e1.printStackTrace();
				} catch (NotEnoughResourcesException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "NotEnoughResourcesException");
				//	e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "CloneNotSupportedException");
				//	e1.printStackTrace();
				}
			 }
				
			 }
			 g++;
		}
	}
}