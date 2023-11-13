package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.Game;
import engine.Player;


@SuppressWarnings("serial")
public class EntryPoint extends JFrame implements ActionListener {
	JButton submit;
	JPanel enterPage;
	String playerOne;
	String playerTwo;
	JTextField p1;
	JTextField p2;
	JFrame mainGame;
	public EntryPoint() throws IOException{
		mainGame = new JFrame();
	    mainGame.setTitle("Marvel");
		mainGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainGame.setBounds(50,50,800,800);
		//EntryPoint.mainGame.setResizable(false);
		mainGame.setVisible(true);
		entryPage();
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		
	}
	
	public void entryPage(){
		enterPage = new JPanel();
		enterPage.setLayout(null);
		enterPage.setSize(500,400);
		
		JLabel player1Name = new JLabel("Player 1 Name");
		player1Name.setBounds(150, 20, 120, 40);
		player1Name.setSize(250, 100);
		enterPage.add(player1Name,BorderLayout.WEST);
		
		p1 = new JTextField(8);				//player 1
		p1.setBounds(150, 80, 80, 25);
		p1.setSize(80,25);
		enterPage.add(p1 , BorderLayout.CENTER);
		
		JLabel player2Name = new JLabel("Player 2 Name");
		player2Name.setBounds(150, 120, 80, 25);
		player2Name.setSize(100,20);
		enterPage.add(player2Name,BorderLayout.WEST);
		
		p2 = new JTextField(8);				//player 2
		p2.setBounds(150 , 140 , 80 , 30);
		p2.setSize(80,25);
		enterPage.add(p2 , BorderLayout.CENTER);
		
		submit = new JButton("submit");
		submit.setBounds(400,500,50,50);
		submit.setSize(140,70);
		enterPage.add(submit,BorderLayout.SOUTH);
		enterPage.setVisible(true);
		
		  ImageIcon background=new ImageIcon("C:\\Users\\HP\\Downloads\\3rNLP4q.jpg");
		    Image img=background.getImage();
		    Image temp=img.getScaledInstance(800,600,Image.SCALE_SMOOTH);
		    background=new ImageIcon(temp);
		    JLabel back=new JLabel(background);
		    back.setLayout(new FlowLayout());
		    back.setBounds(10,10,800,800);
		  //  back.setSize(900,800);
		    enterPage.add(back);
		
		submit.addActionListener(this);
		mainGame.add(enterPage);
		enterPage.revalidate();
		enterPage.repaint();
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            //validation en hwa el feilds not null
            playerOne = p1.getText();
            playerTwo = p2.getText();
            if(playerOne.equals("") || playerOne.equals("")){
            	 mainGame.add(enterPage);

            }
            else{
            	 Player first = new Player(playerOne);
            	 Player second = new Player(playerTwo);
            	 mainGame.remove(enterPage);
            	 @SuppressWarnings("unused")
				ChooseChamp choice = new ChooseChamp(mainGame , first ,second);
            }

          
           
        }
    }
}