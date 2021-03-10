package mark1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) {
		char[] arr=new char[9];
		Board.setArr(arr);
		Run run=new Run();
		CheckWin checkWin=Board::checkBoard;
		Arrays.fill(arr, ' ');
		while(true) {
			boolean win=run.process(arr, checkWin);
			if(win) {
				run.won(arr);
			}
		}
	}
}
	
class Run{
	Player player=X.getPlayer();
	Scanner sc=new Scanner(System.in);
	private char playerValue;
	
	public char getPlayerValue() {
		return playerValue;
	}

	public void setPlayerValue(char playerValue) {
		this.playerValue = playerValue;
	}

	public boolean process(char[] arr, CheckWin checkWin) {
		try {
			Board.print();
			player.printPlayerData();
			byte in=sc.nextByte();
			if(in>=1 && in<=9 && arr[in-1]==' ') {
				playerValue=player.getMove(this);
				arr[in-1]=playerValue;
				
			}else {
				throw new WrongInputException();
			}
		}catch(WrongInputException wie) {
			sc.next();
			System.out.println(wie.getMessage());
			return false;
		}catch(InputMismatchException e) {
			sc.next();
			System.out.println("\nInvalid input data.. Please enter values/number between 1-9 only\n\n");
			return false;
		}catch(Exception e) {
			sc.next();
			System.out.println("Sorry for inconvenience something went wrong..");
			return false;
		}
		return checkWin.check(arr, playerValue);
	}
	
	public void won(char[] arr) {
		
		System.out.println("\nHola!!... Now "+playerValue+" won the game! \n\n");
		Arrays.fill(arr, ' ');
		System.out.println("------------------------------\n");
		System.out.println("New Game :\n");
	}
}

abstract class Player{
	public abstract void printPlayerData();
	public abstract char getMove(Run run);
}
class X extends Player{
	private static X obj;
	private X() {
	}
	public static X getPlayer() {
		if(obj==null) obj=new X();
		return obj;
	}
	public void printPlayerData() {
		System.out.println("Player1 'X' Enter a value between (1-9): ");
	}
	public char getMove(Run run) {
		run.player=O.getPlayer();
		return 'X';
	}
}
class O extends Player{
	private static O obj;
	private O() {
	}
	public static O getPlayer() {
		if(obj==null) obj=new O();
		return obj;
	}
	public void printPlayerData() {
		System.out.println("Player2 'O' Enter a value between (1-9): ");
	}
	public char getMove(Run run) {
		run.player=X.getPlayer();
		return 'O';
	}
}

interface CheckWin{
	public boolean check(char arr[], char input);
}

class Board{
	private static char in;
	private static char[] arr;
	
	public static char[] getArr() {
		return arr;
	}

	public static void setArr(char[] arr) {
		Board.arr = arr;
	}

	public static void print() {
		byte i=0;
		for(char ar:arr) {
			System.out.print(ar+" | ");
			i++;
			if(i==3) {
				System.out.println();
				i=0;
			}
		}
	}

	public static boolean checkBoard(char array[], char input) {
		arr=array;
		in=input;
		return checkRows() || checkColumn() || checkDiagonal();
	}
	
	public static boolean checkRows() {
		return arr[0]==in && arr[1]==in && arr[2]==in	||
				arr[3]==in && arr[4]==in && arr[5]==in	||
				arr[6]==in && arr[7]==in && arr[8]==in;
	}
	public static boolean checkColumn() {
		return arr[0]==in && arr[3]==in && arr[6]==in	||
				arr[1]==in && arr[4]==in && arr[7]==in	||
				arr[2]==in && arr[5]==in && arr[8]==in;
	}
	public static boolean checkDiagonal() {
		return arr[0]==in && arr[4]==in && arr[8]==in	||
				arr[2]==in && arr[4]==in && arr[6]==in;
	}
}

class WrongInputException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		return "\nNote:\nInvalid input.. Only numbers 0-9 allowed!\nAnd not allowed to use already occupied space!\n\n";
	}
}