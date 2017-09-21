
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;


public class TicTacToe{
	
	int[][] board = new int[3][3];
	ArrayList<Integer> availableCoordinates = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	JButton JB1 = new JButton(); 
	JButton JB2 = new JButton(); 
	JButton JB3 = new JButton(); 
	JButton JB4 = new JButton(); 
	JButton JB5 = new JButton(); 
	JButton JB6 = new JButton(); 
	JButton JB7 = new JButton(); 
	JButton JB8 = new JButton(); 
	JButton JB9 = new JButton(); 
	
	public TicTacToe() {
		JFrame JF = new JFrame("TIC TAC TOE MOFO");
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		GridLayout grid = new GridLayout(3,3);
		jp.setLayout(grid);
		jp.setBorder (BorderFactory.createLineBorder (Color.gray, 3));
		jp.setBackground(Color.white);
		jp.setSize(500, 500);
		jp.setVisible(true);
	
		Font f = new Font("Arial", Font.PLAIN, 80);
		JB1.setFont(f);
		JB2.setFont(f);
		JB3.setFont(f);
		JB4.setFont(f);
		JB5.setFont(f);
		JB6.setFont(f);
		JB7.setFont(f);
		JB8.setFont(f);
		JB9.setFont(f);
		
		jp.add(JB1);
		jp.add(JB2);
		jp.add(JB3);
		jp.add(JB4);
		jp.add(JB5);
		jp.add(JB6);
		jp.add(JB7);
		jp.add(JB8);
		jp.add(JB9);
		
		JF.getContentPane().add(jp);
		JF.pack();
		JF.setVisible(true);
		JF.setSize(500,500);
		
		
		JB1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB1.getText().equals("")) {	
					JB1.setText("X");
					board[0][0] = 1;
					availableCoordinates.remove(new Integer(1));
					computerDecision();	
				}
			}
		});
		
		JB2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB2.getText().equals(""))  {
					JB2.setText("X");
					board[0][1] = 1;	
					availableCoordinates.remove(new Integer(2));
					computerDecision();	
				}			
			}
		});
		
		JB3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB3.getText().equals("")) {
					JB3.setText("X");
					board[0][2] = 1;
					availableCoordinates.remove(new Integer(3));
					computerDecision();	
				}
			}
		});
		
		JB4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB4.getText().equals("")) {
					JB4.setText("X");
					board[1][0] = 1;
					availableCoordinates.remove(new Integer(4));
					computerDecision();	
				}			
			}
		});
		
		JB5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB5.getText().equals("")) {
					JB5.setText("X");
					board[1][1] = 1;	
					availableCoordinates.remove(new Integer(5));
					computerDecision();	
				}			
			}
		});
		
		JB6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB6.getText().equals("")) {
					JB6.setText("X");
					board[1][2] = 1;
					availableCoordinates.remove(new Integer(6));
					computerDecision();	
				}			
			}
		});
		
		JB7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB7.getText().equals("")) {
					JB7.setText("X");
					board[2][0] = 1;	
					availableCoordinates.remove(new Integer(7));
					computerDecision();	
				}			
			}
		});
		
		JB8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB8.getText().equals("")) {
					JB8.setText("X");
					board[2][1] = 1;
					availableCoordinates.remove(new Integer(8));
					computerDecision();	
				}			
			}
		});
		
		JB9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(JB9.getText().equals("")) {
					JB9.setText("X");
					board[2][2] = 1;	
					availableCoordinates.remove(new Integer(9));
					computerDecision();	
				}
			}
		});	
	}
	
	public static void main(String[] args) {
		new TicTacToe();	
	}

	public void computerDecision() {
		if(!gameOver()) {
		int coordinate = -1;
		
		if(!this.availableCoordinates.isEmpty()) coordinate = this.availableCoordinates.get(0);
		
		if(board[2][0] == 0) {
			coordinate = 7;
		}		
		if(board[0][2] == 0) {
			coordinate = 3;
		}
		if(board[0][0] == 0) {
			coordinate = 1;
		}
		if(board[2][2] == 0) {
			coordinate = 9;
		}
		
		//case 4 : if I (computer) can make a fork
		if(fork(2) != -1) coordinate = fork(2);
		
		//case 3 : if opponent can make a fork, block it
		if(fork(1) != -1) coordinate = fork(1);
		
		//case 2 : Opponent has two in a row
		//2a : horizontal
		if(twoOfThreeHoriz(1, board) != -1)
			coordinate = twoOfThreeHoriz(1, board);
		//2b : vertical
		if(twoOfThreeVert(1, board) != -1)
			coordinate = twoOfThreeVert(1, board);
		//2c : diagonal
		if(twoOfThreeDiag(1, board) != -1)
			coordinate = twoOfThreeDiag(1, board);		
		
		//case 1 : I have two in a row
		//1a : horizontal
		if(twoOfThreeHoriz(2, board) != -1)
			coordinate = twoOfThreeHoriz(2, board);
		//1b : vertical
		if(twoOfThreeVert(1, board) != -1)
			coordinate = twoOfThreeVert(1, board);
		//1c : diagonal
		if(twoOfThreeDiag(1, board) != -1)
			coordinate = twoOfThreeDiag(1, board);
		
		graphDecision(coordinate);
		}
	}
	
	public void graphDecision(int coord) {
		//System.out.println("COMPUTER CHOSE: " + coord);
		this.availableCoordinates.remove(new Integer(coord));
		System.out.println(availableCoordinates);
		switch (coord) {
		case 1 :
			JB1.setText("O");
			board[0][0] = 2;
			break;
		case 2 : 
			JB2.setText("O");
			board[0][1] = 2; 
			break;
		case 3 :
			JB3.setText("O");
			board[0][2] = 2;
			break;
		case 4 :
			JB4.setText("O");
			board[1][0] = 2;
			break;
		case 5 : 
			JB5.setText("O");
			board[1][1] = 2; 
			break;
		case 6 :
			JB6.setText("O");
			board[1][2] = 2;
			break;
		case 7 :
			JB7.setText("O");
			board[2][0] = 2;
			break;
		case 8 : 
			JB8.setText("O");
			board[2][1] = 2; 
			break;
		case 9 :
			JB9.setText("O");
			board[2][2] = 2;
			break;
		}
	}
	
	//returns -1 if no fork possible
	public int fork(int player) {
		int coordinate = -1;
		// does playing one of the remaining coordinates yield a 2 of 3 situation in two directions
		int[][] tempBoard = board;
		ArrayList<Integer> tempCoords = availableCoordinates;
		for(int coord : tempCoords) {
			//convert from coord to row, column
			switch(coord) {
			case 1 :
				tempBoard[0][0] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 1;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 1;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 1;
				tempBoard[0][0] = 0;
				break;
			case 2 :
				tempBoard[0][1] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 2;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 2;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 2;
				tempBoard[0][1] = 0;
				break;
			case 3 :	
				tempBoard[0][2] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 3;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 3;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 3;
				tempBoard[0][2] = 0;
				break;
			case 4 :	
				tempBoard[1][0] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 4;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 4;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 4;
				tempBoard[1][0] = 0;
				break;	
			case 5 :	
				tempBoard[1][1] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 5;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 5;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 5;
				tempBoard[1][1] = 0;
				break;	
			case 6 :	
				tempBoard[1][2] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 6;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 6;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 6;
				tempBoard[1][2] = 0;
				break;
			case 7 :	
				tempBoard[2][0] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 7;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 7;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 7;
				tempBoard[2][0] = 0;
				break;
			case 8 :	
				tempBoard[2][1] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 8;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 8;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 8;
				tempBoard[2][1] = 0;
				break;
			case 9 :	
				tempBoard[2][2] = player;
				if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeHoriz(player, tempBoard) != -1)
					coordinate = 9;
				else if(twoOfThreeVert(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 9;
				else if(twoOfThreeHoriz(player, tempBoard) != -1 && twoOfThreeDiag(player, tempBoard) != -1) 
					coordinate = 9;
				tempBoard[2][2] = 0;
				break;
			}
		}
		return coordinate;
	}
	
	//computer player = 2; opponent player = 1
	public int twoOfThreeHoriz(int player, int[][] tempBoard) {
		int coordinate = -1;
		for(int i = 0; i < 3; i++) {
			if(tempBoard[i][0] == player && tempBoard[i][1] == player && tempBoard[i][2] == 0) {
				coordinate = (i+1) * 3;
			}
			else if(tempBoard[i][0] == player && tempBoard[i][1] == 0 && tempBoard[i][2] == player) {
				coordinate = (i+1) * 3 - 1;
			}
			else if(tempBoard[i][0] == 0 && tempBoard[i][1] == player && tempBoard[i][2] == player) {
				coordinate = (i+1) * 3 - 2;
			}	
		}
		return coordinate;
	}
	
	public int twoOfThreeVert(int player, int[][] tempBoard) {
		int coordinate = -1;
		//1b : vertical
		for(int i = 0; i < 3; i++) {
			if(tempBoard[0][i] == player && tempBoard[1][i] == player && tempBoard[2][i] == 0) {
				coordinate = i+7;
			}
			else if(tempBoard[0][i] == player && tempBoard[1][i] == 0 && tempBoard[2][i] == player) {
						coordinate = i+4;
			}
			else if(tempBoard[0][i] == 0 && tempBoard[1][i] == player && tempBoard[2][i] == player) {
						coordinate = i+1;
			}
		}
		return coordinate;
	}
	
	public int twoOfThreeDiag(int player, int[][] tempBoard) {
		int coordinate = -1;
				//1c : diagonal
				if(tempBoard[0][0] == player && tempBoard[1][1] == player && tempBoard[2][2] == 0) {
					coordinate = 9;
				}
				else if(tempBoard[0][0] == player && tempBoard[1][1] == 0 && tempBoard[2][2] == player) {
					coordinate = 5;
				}
				else if(tempBoard[0][0] == 0 && tempBoard[1][1] == player && tempBoard[2][2] == player) {
					coordinate = 1;
				}
				else if(tempBoard[2][0] == player && tempBoard[1][1] == player && tempBoard[0][2] == 0) {
					coordinate = 3;
				}
				else if(tempBoard[2][0] == player && tempBoard[1][1] == 0 && tempBoard[0][2] == player) {
					coordinate = 5;
				}
				else if(tempBoard[2][0] == 0 && tempBoard[1][1] == player && tempBoard[0][2] == player) {
					coordinate = 7;
				}
		return coordinate;
	}
	
	private boolean gameOver() {
		boolean result = false;
		//vert and horizontal check if someone won
		for(int i = 0; i < 3; i++) {
			int countH1 = 0; int countH2 = 0;
			int countV1 = 0; int countV2 = 0;
			for(int j = 0; j < 3; j++) {
			if(board[i][j] == 1)
				countH1++;
			if(board[i][j] == 2)
				countH2++;
			if(board[j][i] == 1)
				countV1++;
			if(board[j][i] == 2)
				countV2++;
			if(countH1 == 3 || countH2 == 3 || countV1 == 3 || countV2 == 3)
				result = true;
		}
		}
		//diagonal check
		for(int player = 1; player <= 2; player++) {
			if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
				result = true;
			else if(board[0][2] == player && board[1][1] == player && board[2][0] == player)
				result = true;
		}
			
		return result;
	}
	
}
